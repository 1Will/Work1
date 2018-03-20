package com.ahctx.reward.common.util.io;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import com.ahctx.reward.common.Constants;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片压缩处理
 * 
 * @author wangping
 */
@SuppressWarnings("restriction")
public class ImgCompress {
	private Image img;
	private int width;
	private int height;
	private String fileName;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		System.out.println("开始：" + new Date().toLocaleString());
		ImgCompress imgCom = new ImgCompress(
				"C:\\e702b0f6-f721-40c6-8f2f-dfefdb882885.jpg",null);
		imgCom.resizeFix(150, 150);
		System.out.println("结束：" + new Date().toLocaleString());
	}

	/**
	 * 构造函数
	 */
	public ImgCompress(String filePath, String fileName) throws IOException {
		File file = new File(filePath);// 读入文件
		this.fileName = fileName;
		img = ImageIO.read(file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	/**
	 * 按照宽度还是高度进行压缩
	 * 
	 * @param w
	 *            int 最大宽度
	 * @param h
	 *            int 最大高度
	 */
	public String resizeFix(int w, int h) throws IOException {
		if (width / height > w / h) {
			return resizeByWidth(w);
		}
		else {
			return resizeByHeight(h);
		}
	}

	/**
	 * 以宽度为基准，等比例放缩图片
	 * 
	 * @param w
	 *            int 新宽度
	 */
	public String resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		return resize(w, h);
	}

	/**
	 * 以高度为基准，等比例缩放图片
	 * 
	 * @param h
	 *            int 新高度
	 */
	public String resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		return resize(w, h);
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * 
	 * @param w
	 *            int 新宽度
	 * @param h
	 *            int 新高度
	 */
	public String resize(int w, int h) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		String currentDate = new java.sql.Date(new java.util.Date().getTime()).toString();
		File destDir = new File(Constants.IMAGE_DIRECTIORY + "\\" + currentDate);
		if (!destDir.exists())
			destDir.mkdir();
		FileOutputStream out = new FileOutputStream(destDir + "\\" + fileName); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
		return destDir + "\\" + fileName;
	}
}
