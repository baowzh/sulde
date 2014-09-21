package com.mongolia.website.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

import com.mongolia.website.model.ImgValue;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgeUtil {
	public static ImgValue CompressPic(byte[] imgContent, String destpath,
			String filename) throws IOException {
		ByteArrayInputStream stram = new ByteArrayInputStream(imgContent);
		ByteArrayInputStream imgstram = new ByteArrayInputStream(imgContent);
		Image img = ImageIO.read(stram);
		ImgValue imgValue = new ImgValue();
		System.out.println("width:" + img.getWidth(null) + ",height:"
				+ img.getHeight(null));
		int originalWidth = img.getWidth(null);
		int originalHeight = img.getHeight(null);
		File destFile = new File(destpath, filename);
		if (originalWidth > 485 || originalHeight > 400) {
			Thumbnails.of(imgstram).size(485, 400).toFile(destFile);
			imgValue.setWidth(485);
			imgValue.setHeight(400);
		} else {
			FileOutputStream outStream = new FileOutputStream(destFile);
			outStream.write(imgContent, 0, imgContent.length);
			outStream.close();
			imgValue.setWidth(originalWidth);
			imgValue.setHeight(originalHeight);
		}
		return imgValue;
	}

	public static void CompressPic(byte[] imgContent, String destpath,
			String filename, int widh, int height) throws IOException {
		ByteArrayInputStream stram = new ByteArrayInputStream(imgContent);
		ByteArrayInputStream imgstram = new ByteArrayInputStream(imgContent);
		Image img = ImageIO.read(stram);
		System.out.println("width:" + img.getWidth(null) + ",height:"
				+ img.getHeight(null));
		int originalWidth = img.getWidth(null);
		int originalHeight = img.getHeight(null);
		File destFile = new File(destpath, filename);
		if (originalWidth > widh || originalHeight > height) {
			Thumbnails.of(imgstram).size(widh, height).toFile(destFile);
		} else {
			FileOutputStream outStream = new FileOutputStream(destFile);
			outStream.write(imgContent, 0, imgContent.length);
			outStream.close();
		}
	}

	public static String ImageMoneyVsName(String Name, InputStream imgStream,
			InputStream singimg) {
		try {
			// 读取模板图片内容
			//
			JPEGImageDecoder signdecoder = JPEGCodec.createJPEGDecoder(singimg); // 读取图片路径
			BufferedImage signimage = signdecoder.decodeAsBufferedImage();
			//
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imgStream); // 读取图片路径
			BufferedImage image = decoder.decodeAsBufferedImage();
			Graphics2D g = image.createGraphics();// 得到图形上下文
			g.setColor(Color.BLACK); // 设置画笔颜色
			RenderingHints rh = new RenderingHints(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_STROKE_CONTROL,
					RenderingHints.VALUE_STROKE_PURE);
			rh.put(RenderingHints.KEY_ALPHA_INTERPOLATION,
					RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			rh.put(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHints(rh);
			g.setColor(Color.LIGHT_GRAY);
			Font font = new Font("宋体", Font.BOLD, 12);
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics(font);
			// 设置换行操作
			int fontHeight = fm.getHeight(); // 字符的高度
			int offsetLeft = 16;
			int rowIndex = 1;
			for (int i = 0; i < Name.length(); i++) {
				char c = Name.charAt(i);
				int charWidth = fm.charWidth(c); // 字符的宽度
				// 另起一行
				if (Character.isISOControl(c)
						|| offsetLeft >= (171 - charWidth)) {
					rowIndex++;
					offsetLeft = 16;
				}
				g.drawString(String.valueOf(c), offsetLeft, rowIndex * 40); // 把一个个写到图片上
				offsetLeft += charWidth; // 设置下字符的间距
			}
			g.drawImage(signimage, image.getWidth() - signimage.getWidth(),
					image.getHeight() - signimage.getHeight(), null);

			FileOutputStream out = new FileOutputStream("c:/a.jpeg");// 通过流操作把文字图片结合
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 给图片添加水印
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			File imgFile = new File(
					"D:/apache-tomcat-6.0.39/webapps/mongoliawebsite/img/news3.jpg");
			FileInputStream stream = new FileInputStream(imgFile);
			File logoFile = new File("c:/logo.jpg");
			FileInputStream signStream = new FileInputStream(logoFile);
			// logo.jpg
			ImageMoneyVsName("MGL-Blog", stream, signStream);
			stream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
