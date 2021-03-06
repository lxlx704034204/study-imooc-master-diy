package com.myimooc.watermark.service;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片水印服务类，添加多个文字水印
 * @author ZhangCheng on 2017-07-22
 *
 */
//@Service
@SuppressWarnings("unused")
public class MoreTextMarkServiceImpl implements MarkService {

	@Override
	public String watermake(File imageFile, String imageFileName, String uploadPath, String realUploadPath) {
		String logoFileName = "logo_" + imageFileName;
		OutputStream os = null;
		
		try {
			Image image = ImageIO.read(imageFile);
			int width = image.getWidth(null);// 原图宽度
			int height = image.getHeight(null);// 原图高度
			
			// 创建图片缓存对象
			BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			// 创建绘绘图工具对象
			Graphics2D g = bufferedImage.createGraphics();
			// 使用绘图工具将原图绘制到缓存图片对象
			g.drawImage(image, 0, 0, width,height,null);
			
			// 设置水印文字字体信息
			g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
			// 设置水印文字颜色
			g.setColor(FONT_COLOR);
			
			int markWidth = FONT_SIZE * getTextLength(MARK_TEXT);
			int markHeight = FONT_SIZE;
			
			// 设置水印透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
			
			// 旋转图片
			g.rotate(Math.toRadians(30), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
			
			int x = -width / 2;
			int y = -height / 2;
			
			int xmove = 200;// 水印之间的间隔
			int ymove = 200;// 水印之间的间隔
			
			// 循环添加
			while (x < width * 1.5){
				y = -height / 2;
				while(y < height * 1.5){
					g.drawString(MARK_TEXT, x, y);
					
					y += markHeight + ymove;
				}
				x += markWidth + xmove;
			}
			
			g.dispose();
			
			os = new FileOutputStream(realUploadPath + "/" + logoFileName);
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			en.encode(bufferedImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return uploadPath + "/" + logoFileName;
	}
	
	/**
	 * 功能：获取文本长度。汉字为1:1，英文和数字为2:1
	 */
	private int getTextLength(String text){
		int length = text.length();
		for(int i = 0 ; i < text.length(); i++){
			String s = String.valueOf(text.charAt(i));
			if(s.getBytes().length > 1){
				length++;
			}
		}
		length = length % 2 == 0 ? length / 2 : length / 2 + 1;
		return length;
	}

}
