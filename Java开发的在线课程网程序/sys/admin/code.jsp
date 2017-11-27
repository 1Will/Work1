<%@ page contentType="text/html; charset=utf-8" %>
<%@ page autoFlush="false" import="java.awt.*,java.awt.image.*,com.sun.image.codec.jpeg.*,java.util.*"%>
<%@ page import="org.apache.commons.lang.RandomStringUtils"%>
<%
RandomStringUtils rs=new RandomStringUtils();
String random=rs.randomNumeric(4).toUpperCase();
session.setAttribute("randomcode",random);
%>
<%!
Color getRandColor(int fc,int bc){//给定范围获得随机颜色
Random rd = new Random();
if(fc>255) fc=255;
if(bc>255) bc=255;
int r=fc+rd.nextInt(bc-fc);
int g=fc+rd.nextInt(bc-fc);
int b=fc+rd.nextInt(bc-fc);
return new Color(r,g,b);
}
%>
<%
out.clear();
response.setContentType("image/jpeg");
response.addHeader("pragma","NO-cache");
response.addHeader("Cache-Control","no-cache");
response.addDateHeader("Expries",0);
int width=90, height=36;
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
Graphics g = image.getGraphics();
//以下填充背景色
g.setColor(new Color(159,160,160));
Font DeFont=new Font("SansSerif", Font.PLAIN, 32);
g.setFont(DeFont);
g.fillRect(0, 0, width, height);

//置字体色
g.setColor(new Color(255,241,0));
g.drawString(random,3,30);
g.dispose();

ServletOutputStream outStream = response.getOutputStream();
JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(outStream);
encoder.encode(image);
outStream.close();
out.clear();
out = pageContext.pushBody();
%>
