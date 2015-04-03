package org.inspire.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class IDInsertIdealAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		try {
			PrintWriter pr = response.getWriter();

			
				Ideals t = new Ideals();
				t.setApproved("0");
				t.setDeleted("0");
				t.setAdded_date(new Date());
				if(res.getSession().getAttribute("sessionName") != null)
				{
					t.setAdded_by(res.getSession().getAttribute("sessionName")
					.toString());
				}
				else
				{
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
				if(iName.equals(""))
				{
					error.add("Please Enter Name");
					missongCont = true;
				}
				if(iDesc.equals(""))
				{
					error.add("Please Enter Ideal Description");
					missongCont = true;
				}				
				if(myFile.getFileName().equals(""))
				{
					error.add("Please Select File to upload");
					missongCont = true;
				}else if(!contentType.equals("image/jpeg") 
						&&!contentType.equals("image/bmp")
						&&!contentType.equals("image/png")
						&&!contentType.equals("image/ico")
						&&!contentType.equals("image/gif"))
				{
					error.add("Please Select only JPEG|BMP|PNG|ICO|GIF File to upload");
					missongCont = true;
				}
				if(missongCont)
				{
					res.setAttribute("error", error);
					return mapping.findForward("success");
				}
				
				String filePath = getServlet().getServletContext().getRealPath(
						"/")
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
				t.setIpicpath(res.getContextPath()+"/upload/"+fileName);

				SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
				sqlMap.insert("insertIdeals", t);

				pr
						.write("<div id=\"out\" style=\"color: RED;	font-size: 14px;"
								+ "font-weight: bold;\">You can see your Ideal after being approved."
								+ "<a href=\"ID.do\"> Back</a></div>");
				pr.flush();
				pr.close();
				return null;

			
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}

	}
}
