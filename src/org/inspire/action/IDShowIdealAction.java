package org.inspire.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.inspire.bean.IdealBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Ideals;

import com.ibatis.sqlmap.client.SqlMapClient;

public class IDShowIdealAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		IdealBean bean = (IdealBean) form;
		try {
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			if (bean.getAction() != null && bean.getAction().equals("insert")) {
				Ideals t = new Ideals();

				String[] strUpList = null;
				if (bean.getUpdate() != null && !bean.getUpdate().equals("")) {
					strUpList = bean.getUpdate().split(",");

				}
				int id = 0;
				if (strUpList != null) {
					for (int i = 0; i < strUpList.length; i++) {
						id = Integer.parseInt(strUpList[i]);
						sqlMap.update("updateIdeals", id);
					}
					res.setAttribute("msg",
							"Approved Selected Records successfully.");

				} else {
					t.setApproved("0");
					t.setDeleted("0");
					t.setAdded_date(new Date());
					if (res.getSession().getAttribute("sessionName") != null) {
						t.setAdded_by(res.getSession().getAttribute(
								"sessionName").toString());
					} else {
						t.setAdded_by("Anonymous");
					}
					String iName = null;
					String iDesc = null;

					IdealBean sbean = (IdealBean) form;
					FormFile myFile = null;
					List<String> error = new ArrayList<String>();

					iName = sbean.getIname();
					iDesc = sbean.getIdesc();
					myFile = sbean.getIpics();
					boolean missongCont = false;
					String contentType = myFile.getContentType();
					String fileName = myFile.getFileName();
					int fileSize = myFile.getFileSize();
					byte[] fileData = myFile.getFileData();
					if (iName.equals("")) {
						error.add("Please Enter Name");
						missongCont = true;
					}
					if (iDesc.equals("")) {
						error.add("Please Enter Ideal Description");
						missongCont = true;
					}
					if (myFile.getFileName().equals("")) {
						error.add("Please Select File to upload");
						missongCont = true;
					} else if (!contentType.equals("image/jpeg")
							&& !contentType.equals("image/bmp")
							&& !contentType.equals("image/png")
							&& !contentType.equals("image/ico")
							&& !contentType.equals("image/gif")) {
						error
								.add("Please Select only JPEG|BMP|PNG|ICO|GIF File to upload");
						missongCont = true;
					}
					if (missongCont) {
						res.setAttribute("error", error);
						return mapping.findForward("success");
					}

					String filePath = getServlet().getServletContext()
							.getRealPath("/")
							+ "upload";
					/* Save file on the server */
					if (!fileName.equals("")) {
						System.out.println("Server path:" + filePath);
						// Create file
						File fileToCreate = new File(filePath, fileName);
						// If file does not exists create file
						if (!fileToCreate.exists()) {
							FileOutputStream fileOutStream = new FileOutputStream(
									fileToCreate);
							fileOutStream.write(myFile.getFileData());
							fileOutStream.flush();
							fileOutStream.close();
						}
					}

					t.setIname(iName);
					t.setIdesc(iDesc);
					t.setIpicpath(res.getContextPath() + "/upload/" + fileName);
					sqlMap.insert("insertIdeals", t);
					res.setAttribute("msg",
							"You can see your Ideal after being approved.");
				}
			}
			Integer intSize = null;
			int size = 0;
			int startIndex = 0;
			String strCount=null;
			int pageNo = 1;
			if (res.getParameter("d-49653-p") != null) {
				pageNo = Integer.parseInt(res.getParameter("d-49653-p")
						.toString());
			}
			ArrayList<Ideals> ideals = null;
			if (res.getSession().getAttribute("sessionName") != null
					&& res.getSession().getAttribute("sessionName").equals(
							"admin@inspire.com")) {
				strCount = sqlMap.queryForObject("getAllApproveIdealsCount")
						.toString();
				size = Integer.parseInt(strCount);
				startIndex = (pageNo * 5) - 5;
				intSize = new Integer(size);
				Map paramM = new HashMap();
				paramM.put("startIndex", startIndex);
				paramM.put("length", 2);
				ideals = (ArrayList<Ideals>) sqlMap.queryForList(
						"getAllApproveIdeals", paramM);
			} else {
				String strName = bean.getName();
				String strDesc = bean.getIdesc();

				
				if (strName != null && !strName.equals("")) {
					
					strCount = sqlMap.queryForObject(
							"getAllIdealsByNameCount", "%" + strName + "%")
							.toString();
					size = Integer.parseInt(strCount);
					startIndex = (pageNo * 5) - 5;
					intSize = new Integer(size);
					Map paramM = new HashMap();
					paramM.put("startIndex", startIndex);
					paramM.put("length", 5);
					paramM.put("iname", "%" + strName + "%");
					ideals = (ArrayList<Ideals>) sqlMap.queryForList(
							"getAllIdealsByName", paramM);
				} else {
					strCount = sqlMap.queryForObject(
							"getAllIdealsCount", "%" + strName + "%")
							.toString();
					size = Integer.parseInt(strCount);
					startIndex = (pageNo * 5) - 5;
					intSize = new Integer(size);
					Map paramM = new HashMap();
					paramM.put("startIndex", startIndex);
					paramM.put("length", 5);
					
					ideals = (ArrayList<Ideals>) sqlMap
							.queryForList("getAllIdeals",paramM);
				}				
			}
			intSize = new Integer(size);
			res.setAttribute("size", intSize);
			res.setAttribute("result", ideals);
			res
					.setAttribute("chkAll",
							"<input type=\"checkbox\" id=\"chkAll\" onclick=\"selectAll()\"/>");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("success");
	}
}
