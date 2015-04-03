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

public class UBUpBackgroundAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest res, HttpServletResponse response) {

		UserBean bean = (UserBean) form;
		FormFile uBack = null;
		
		if (res.getSession().getAttribute("sessionName") != null) {
			String contentType = null;
			String fileName = null;
			byte[] fileData = null;
			List<String> error = new ArrayList<String>();
			boolean missongCont = false;

			try {
				uBack = bean.getBackImg();
				if (uBack != null) {
					contentType = uBack.getContentType();
					fileName = uBack.getFileName();
					int fileSize = uBack.getFileSize();
					fileData = uBack.getFileData();
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
				String filePath = getServlet().getServletContext().getRealPath(
						"/")
						+ "images";
				if (!fileName.equals("")) {
					
					// Create file
					File fileToCreate = new File(filePath, 
							res.getSession().getAttribute("sessionName").toString()+".jpg");
					// If file does not exists create file
					
						FileOutputStream fileOutStream = new FileOutputStream(
								fileToCreate);
						fileOutStream.write(fileData);
						fileOutStream.flush();
						fileOutStream.close();	
				}
				response.setHeader("Cache-Control","no-cache"); 
				/*--This is used for HTTP 1.1 --*/
				response.setHeader("Pragma","no-cache");
				 /*--This is used for HTTP 1.0 --*/
				response.setDateHeader ("Expires", 0); 
			} catch (Exception ex) {
				mapping.findForward("failure");
			}
		}

		return mapping.findForward("success");
	}
}
