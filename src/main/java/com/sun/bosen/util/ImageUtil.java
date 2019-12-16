
package com.sun.bosen.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

public class ImageUtil {

	public static BufferedImage change2jpg(File f) {
		try {
			Image i = Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
			PixelGrabber pg = new PixelGrabber(i, 0, 0, -1, -1, true);
			pg.grabPixels();
			int width = pg.getWidth(), height = pg.getHeight();
			final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
			final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
			DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
			WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
			BufferedImage img = new BufferedImage(RGB_OPAQUE, raster, false, null);
			return img;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void resizeImage(File srcFile, int width, int height, File destFile) {
		try {
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			Image i = ImageIO.read(srcFile);
			i = resizeImage(i, width, height);
			ImageIO.write((RenderedImage) i, "jpg", destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Image resizeImage(Image srcImage, int width, int height) {
		try {

			BufferedImage buffImg = null;
			buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			buffImg.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

			return buffImg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// BASE64解码成File文件
	public static void base64ToFile(String destPath, String base64, String fileName) throws IOException {
		File file = null;
		// 创建文件目录
		String filePath = destPath;
		File dir = new File(filePath);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
		BufferedOutputStream bos = null;
		java.io.FileOutputStream fos = null;

		byte[] bytes = Base64.getDecoder().decode(base64);
		file = new File(filePath + "/" + fileName);
		fos = new java.io.FileOutputStream(file);
		bos = new BufferedOutputStream(fos);
		bos.write(bytes);
		bos.close();
		fos.close();

	}

	public static String fileToBase64(String path) throws IOException {
		String base64 = null;
		InputStream in = null;
		File file = new File(path);
		in = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		in.read(bytes);
		base64 = Base64.getEncoder().encodeToString(bytes);
		in.close();

		return base64;
	}

}
