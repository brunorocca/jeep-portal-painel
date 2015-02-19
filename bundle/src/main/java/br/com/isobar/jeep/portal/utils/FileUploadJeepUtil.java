package br.com.isobar.jeep.portal.utils;

import java.io.File;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.sling.api.SlingHttpServletRequest;

public class FileUploadJeepUtil {
	
	public static File getFileFromRequest(SlingHttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			for (FileItem item : items) {
				
				File file = new File("/opt/cq-import-files");
				item.write(file);
				
				return file;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
