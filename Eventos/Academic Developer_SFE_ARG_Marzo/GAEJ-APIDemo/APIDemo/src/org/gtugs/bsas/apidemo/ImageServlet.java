/**
 * 
 */
package org.gtugs.bsas.apidemo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sun.misc.IOUtils;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

/**
 * @author Juan M. Irungaray (juan@irungaray.com.ar)
 * 
 */
public class ImageServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem>  items = upload.parseRequest(req);
 
			ImagesService imagesService = ImagesServiceFactory.getImagesService();
			BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			
				Image oldImage = ImagesServiceFactory.makeImage(items.get(1).get());

				Transform resize = ImagesServiceFactory.makeResize(200, 300);

				Image newImage = imagesService.applyTransform(resize, oldImage);

				byte[] newImageData = newImage.getImageData();

			
			
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}
}
