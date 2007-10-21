package net.events.utilities;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

import com.webobjects.foundation.NSData;

import java.util.Iterator;

/**
 * Uses Java Image to manipulate images
 *  
 */
public class EVImageManipulator {

	/**
	 * Encoder quality for JPEG encoding
	 */
	protected static float _encoderQuality = .75f;

	/**
	 * the image to work on
	 */
	protected BufferedImage _image;
	
	/**
	 * Standard constructor
	 *
	 */
	public EVImageManipulator() {}
	
	/**
	 * Create an imageManipulator from a a file
	 * @param fromFilename
	 */
	public EVImageManipulator(String fromFilename) {
		Image newImage = Toolkit.getDefaultToolkit().createImage(fromFilename);
		setImage(toBufferedImage(newImage));
		newImage.flush();
	}
	
	/**
	 * Create an ImageManipulator from a byte array
	 * @param fromMemory
	 */
	public EVImageManipulator(byte[] fromMemory) {
		Image newImage = Toolkit.getDefaultToolkit().createImage(fromMemory);
		setImage(toBufferedImage(newImage));
		newImage.flush();
	}
	
	/**
	 * Scale an image stored in an NSData objects, returns a new NSData object 
	 * 
	 * @param data
	 * @param width
	 * @param heigth
	 * @return
	 */
	public static NSData scaledImageDataFromDataAndWidthAndHeight (NSData data, int width, int heigth) {
		
		if (data != null && data.bytes() != null && data.bytes().length > 0) {
			EVImageManipulator im = new EVImageManipulator(data.bytes());
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				EVImageManipulator.encodeAsJPEG(im.getScaledImage(160, 160, true), os);
				return new NSData(os.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * Converts an Image to a BufferedImage
	 * @param sourceImage
	 * @return
	 */
	private BufferedImage toBufferedImage(Image sourceImage) {
		BufferedImage bufferedImage;
		int width, height;
		waitForImage(sourceImage);
		width = sourceImage.getWidth(null);
		height = sourceImage.getHeight(null);
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = bufferedImage.createGraphics();
		graphics2D.drawImage(sourceImage, 0, 0, width, height, null);
		return bufferedImage;
	}
	
	/**
	 * Converts the current image to new scaled buffered image
	 * @param newWidth
	 * @param newHeight
	 * @param allowInverseAspect
	 * @return
	 */
	public BufferedImage getScaledImage(int newWidth, int newHeight, boolean allowInverseAspect) {
		
		BufferedImage scaledImage;
		float scale;
		float newAspectRatio;
		
		if (image() == null) 
			return null;
		
		newAspectRatio = (float)newWidth / (float)newHeight;	
		if (allowInverseAspect) {
			if ((newAspectRatio < 1.0f && imageAspectRatio() > 1.0f) || (newAspectRatio > 1.0f && imageAspectRatio() < 1.0f)) {
				// reverse aspect: flip width and height
				int flipTmp = newHeight;
				newHeight = newWidth;
				newWidth = flipTmp;
				newAspectRatio = (float)newWidth / (float)newHeight;
			}
		}
		
		if (newAspectRatio > imageAspectRatio()) {
			scale = (float)newHeight / (float)imageHeight();
			newWidth = (int)(imageWidth()*scale);
		} else {
			scale = (float)newWidth / (float)imageWidth();
			newHeight = (int)(imageHeight()*scale);	
		}
		
		// draw original image to new image object and
		// scale it to the new size on-the-fly
		scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = scaledImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics2D.drawImage(image(), 0, 0, newWidth, newHeight, null);
		
		return scaledImage;
	}
	
	/**
	 * Encode a given bufferedImage to JPEG 
	 * @param fromImage
	 * @param toOutputStream
	 * @throws IOException
	 */
	public static void encodeAsJPEG(BufferedImage fromImage, OutputStream toOutputStream) throws IOException {		
		
		// Find a jpeg writer
		ImageWriter writer = null;
		Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
		if (iter.hasNext()) {
			writer = (ImageWriter)iter.next();
		}
		
		// Prepare output file
		ImageOutputStream ios = ImageIO.createImageOutputStream(toOutputStream);
		writer.setOutput(ios);
		
		// Set the compression quality
		javax.imageio.plugins.jpeg.JPEGImageWriteParam iwparam = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(java.util.Locale.getDefault());
		iwparam.setCompressionMode(javax.imageio.plugins.jpeg.JPEGImageWriteParam.MODE_EXPLICIT) ;
		iwparam.setCompressionQuality(encoderQuality());
		
		// Write the image
		writer.write(null, new IIOImage(fromImage, null, null), iwparam);
		
		// Cleanup
		ios.flush();
		writer.dispose();
		ios.close();
	}
	
	/**
	 * Saves a BufferedImage to a file path
	 * @param fromImage
	 * @param fileName
	 * @throws IOException
	 */
	public static void saveAsJPEG(BufferedImage fromImage, String fileName) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(fileName);
		EVImageManipulator.encodeAsJPEG(fromImage, fileOut);
	}
	
	/**
	 * the current image
	 * @return
	 */
	public BufferedImage image() {
		return _image;
	}
	
	/**
	 * Set the image
	 * @param newImage
	 */
	public void setImage(BufferedImage newImage) {
		_image = newImage;
	}
	
	
	/**
	 * Wait for an image
	 * @param toWaitFor
	 */
	private void waitForImage(Image toWaitFor) {
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(toWaitFor, 0);
		try {
			mediaTracker.waitForID(0);                     
		} catch ( Exception e ) {}	
	}
	
	
	/**
	 * Current image width
	 * @return
	 */
	public int imageWidth() {
		if (_image == null) return 0;
		return _image.getWidth(null);
	}
	
	/**
	 * Current image height
	 * @return
	 */
	public int imageHeight() {
		if (_image == null) return 0;
		return _image.getHeight(null);
	}
	
	/**
	 * current image ascpect ratio
	 * @return
	 */
	public float imageAspectRatio() {
		if (_image == null) return 0;
		return (float)imageWidth() / (float)imageHeight();
	}
	
	/**
	 * Set the encoder quality
	 * @param newEncoderQuality
	 */
	public static void setEncoderQuality(float newEncoderQuality) {
		_encoderQuality = Math.max(0.0f, Math.min(newEncoderQuality, 1.0f));
	}
	
	/**
	 * The encoder quality
	 * @return
	 */
	public static float encoderQuality() {
		return _encoderQuality;
	}
}
