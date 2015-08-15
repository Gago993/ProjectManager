package mk.finki.webprogramiranje.projectmanager.service.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;

public class ServiceUtilities {
	public static BufferedImage resizeBufferedImage(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		
		int newWidth;
		int newHeight;

		if(width > 512 || height > 512){
			if(width >= height){
				newWidth = 512;
				newHeight = (int)Math.round(512.0 * height / width);
				
			}else{
				newHeight = 512;
				newWidth = (int)Math.round(512.0 * width / height);
			}
		}else{
			return image;
		}
		
		Image scaled = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		BufferedImage result = new BufferedImage(newWidth, newHeight, image.getType());

		Graphics g = result.createGraphics();
		g.drawImage(scaled, 0, 0, newWidth, newHeight, null);
		g.dispose();
		
		return result;
	}
	
	public static String getMD5(byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(bytes);
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			
			return sb.toString();
		}catch(java.security.NoSuchAlgorithmException exception){}
		
	    return null;
	}
}