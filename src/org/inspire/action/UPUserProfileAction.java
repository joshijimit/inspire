
package org.inspire.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.inspire.bean.UserBean;
import org.inspire.proxy.DBProxy;
import org.inspire.tablebean.Users;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UPUserProfileAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		String fName = null;
		String mName = null;
		String lName = null;
		String email = null;
		String website = null;
		FormFile uPic = null;
		String currImg = null;
		try {
			UserBean bean = (UserBean) form;
			String strAction = res.getParameter("action");
			SqlMapClient sqlMap = DBProxy.getSqlMapInstance();
			if (res.getSession().getAttribute("sessionName") != null
					&& strAction == null) {
				
				fName = bean.getFname();
				mName = bean.getMname();
				lName = bean.getLname();
				currImg = bean.getCurrentImg();
				email = res.getSession().getAttribute("sessionName").toString();
				website = bean.getWebsite();
				uPic = bean.getUpic();
				List<String> error = new ArrayList<String>();
				boolean missongCont = false;
				String contentType = null;
				String fileName = null;
				byte[] fileData = null;
				if (uPic != null) {
					contentType = uPic.getContentType();
					fileName = uPic.getFileName();
					int fileSize = uPic.getFileSize();
					fileData = uPic.getFileData();
				}
				if (fName.equals("")) {
					error.add("Please Enter First Name");
					missongCont = true;
				}
				if (contentType != null && !contentType.equals("image/jpeg")
						&& !contentType.equals("image/bmp")
						&& !contentType.equals("image/png")
						&& !contentType.equals("image/ico")
						&& !contentType.equals("image/gif")
						&& !contentType.equals("image/pjpeg")) {
					error
							.add("Please Select only JPEG|BMP|PNG|ICO|GIF File to upload");
					missongCont = true;
				}
				if (missongCont) {
					res.setAttribute("error", error);
					return mapping.findForward("success");
				}
				else
				{
					Users user = new Users();
					user.setUFname(fName);
					user.setUMname(mName);
					user.setULname(lName);
					user.setEmail(email);
					user.setUWebsite(website);
					if(!uPic.getFileName().equals(""))
					{	
						user.setUPic(res.getContextPath()+"/upload/"+uPic.getFileName());
					}else
					{
						user.setUPic(currImg);
						
					}
					
					String filePath = getServlet().getServletContext().getRealPath(
					"/")
					+ "upload";
					if (!fileName.equals("")) {
						System.out.println("Server path:" + filePath);
						// Create file
						File fileToCreate = new File(filePath, fileName);
						// If file does not exists create file
						if (!fileToCreate.exists()) {
							FileOutputStream fileOutStream = new FileOutputStream(
									fileToCreate);
							fileOutStream.write(fileData);
							fileOutStream.flush();
							fileOutStream.close();
						}
					}
					
					sqlMap.update("updateUserProfile",user);
				}
				res.setAttribute("msg", "Profile updated successfully.");
				
				if(uPic.getFileName()!= null && !uPic.getFileName().equals(""))
				{
					res.setAttribute("upicpath",res.getContextPath()+"/upload/"+uPic.getFileName());
					bean.setCurrentImg(res.getContextPath()+"/upload/"+uPic.getFileName());
				}
				else if(currImg != null && !currImg.equals(""))
				{
					res.setAttribute("upicpath",currImg);
				}
				else
				{
					res.setAttribute("upicpath",res.getContextPath()+ "NotAvail.jpg");
				}
				return mapping.findForward("success");
			}
			if (res.getSession().getAttribute("sessionName") != null)
			{
				
				Users user = (Users)sqlMap.queryForObject("getUserDetails",
						res.getSession().getAttribute("sessionName"));
				
				//bean.setEmail(res.getSession().getAttribute("sessionName").toString());
				bean.setFname(user.getUFname());
				bean.setMname(user.getUMname());
				bean.setLname(user.getULname());
				bean.setEmail(user.getEmail());
				bean.setWebsite(user.getUWebsite());
				bean.setCurrentImg(user.getUPic());
				if(user.getUPic() != null && !user.getUPic().equals("") )
				{
					res.setAttribute("upicpath",user.getUPic());
				}
				else if(currImg != null && !currImg.equals(""))
				{
					res.setAttribute("upicpath",currImg);
				}
				else
				{
					res.setAttribute("upicpath",res.getContextPath()+ "/images/NotAvail.jpg");
				}
			}
			
			return mapping.findForward("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mapping.findForward("failure");

		}

	}
}
