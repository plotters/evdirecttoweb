/*
 * Created on 08.02.2005
 *
 */
package net.events.utilities;

import com.webobjects.foundation.*;
import quicktime.*;
import quicktime.qd.*;
import quicktime.std.*;
import quicktime.std.image.*;
import quicktime.std.movies.media.*;
import quicktime.util.*;

/**
 * Utility which uses the Quicktime Java API to scale images
 * 
 * @author cug
 */
public class EVImageUtility {

	/**
	 * Scale image to desired size, keeps proportions
	 * 
	 * @param image - the image
	 * @param maxWidth - the max width
	 * @param maxHeight - the max height
	 * 
	 * @return NSData object containing the new image with new size
	 */
	public static NSData sizeImage (NSData image, int maxWidth, int maxHeight) {
		NSData scaledImage = null;
		
		if (image != null) {
			byte[] bytes = image.bytes();
			try {
				QTSession.open();
				QTHandle qtHandle = new QTHandle (bytes);
				DataRef dataRef = new DataRef(qtHandle);
				
				GraphicsImporter graphicsImporter = new GraphicsImporter (dataRef);
				QDRect bounds = graphicsImporter.getNaturalBounds();
				
				double widthFactor = (double) maxWidth / bounds.getWidth();
				double heightFactor = (double) maxHeight / bounds.getHeight();
				
				double minFactor;
				
				if (widthFactor < heightFactor) {
					minFactor = widthFactor;
				}
				else {
					minFactor = heightFactor;
				}
				
				QDRect scaledBounds;
				if (minFactor < 1.0) { 
					scaledBounds = new QDRect ((int) (bounds.getWidth() * minFactor), 
								(int) (bounds.getHeight() * minFactor));
				}
				else { 
					scaledBounds = new QDRect ((int) (bounds.getWidth() * 1), 
									(int) (bounds.getHeight() * 1));
					
				}
				
				graphicsImporter.setBoundsRect(scaledBounds);
				
				GraphicsExporter graphicsExporter = new GraphicsExporter (StdQTConstants.kQTFileTypeJPEG);
				graphicsExporter.setInputGraphicsImporter(graphicsImporter);
				
				QTHandle outHandle = new QTHandle();
				graphicsExporter.setOutputHandle(outHandle);
				graphicsExporter.doExport();
				scaledImage = new NSData (outHandle.getBytes());
			}
			catch (Exception e) {
				NSLog.err.appendln ("QT error message: " + e);
			}
		}
		return scaledImage;
	}
}
