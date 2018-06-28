package com.example.webmagic.util;

import org.im4java.core.*;
import org.im4java.process.ArrayListOutputConsumer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphUtil {


    //GraphicsMagick的安装目录
    private static final String graphicsMagickPath = "D:\\workingSoftwares\\GraphicsMagick\\GraphicsMagick-1.3.30-Q16";

    // 图片质量
    private static final String IMAGE_QUALITY = "quality";

    // 图片高度
    private static final String IMAGE_HEIGHT = "height";

    // 图片宽度
    private static final String IMAGE_WIDTH = "width";

    // 图片格式
    private static final String IMAGE_SUFFIX = "suffix";

    // 图片大小
    private static final String IMAGE_SIZE = "size";

    // 图片路径
    private static final String IMAGE_PATH = "path";


    public static Map<String, String> getImageInfo(String imagePath) {

        Map<String, String> imageInfo = new HashMap<>();
        try {
            IMOperation op = new IMOperation();
            op.format("%w,%h,%d/%f,%Q,%b,%e");
            op.addImage();
            ImageCommand identifyCmd = new IdentifyCmd(true);
            identifyCmd.setSearchPath(graphicsMagickPath);

            ArrayListOutputConsumer output = new ArrayListOutputConsumer();
            identifyCmd.setOutputConsumer(output);
            identifyCmd.run(op, imagePath);
            ArrayList<String> cmdOutput = output.getOutput();
            String[] result = cmdOutput.get(0).split(",");
            if (result.length == 6) {
                imageInfo.put(IMAGE_WIDTH, result[0]);
                imageInfo.put(IMAGE_HEIGHT, result[1]);
                imageInfo.put(IMAGE_PATH, result[2]);
                imageInfo.put(IMAGE_QUALITY, result[3]);
                imageInfo.put(IMAGE_SIZE, result[4]);
                imageInfo.put(IMAGE_SUFFIX, result[5]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageInfo;
    }

    /**
     * 图片加文本水印
     * @param srcPath
     * @param destPath
     * @param content
     */
    public static void addWaterText(String srcPath, String destPath, String content){

        GMOperation op = new GMOperation();
        op.font("Times New Roman");
        op.gravity("southeast");
        try {
            op.pointsize(38).fill("#000000").draw("text 10,10 "+ new String(content.getBytes("utf-8"),"gbk"));   //("x1 x2 x3 x4") x1 格式，x2 x轴距离 x3 y轴距离  x4名称，文字内容
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        op.addImage(srcPath);
        op.addImage(destPath);
        ConvertCmd convert = new ConvertCmd(true);
        convert.setSearchPath(graphicsMagickPath);
        try {
            convert.run(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片水印
     * @param srcImagePath   源图片
     * @param waterImagePath 水印
     * @param destImagePath  生成图片
     * @param gravity  图片位置
     * @param dissolve 水印透明度
     */
    public static void addWaterPic(String waterImagePath, String srcImagePath,
                                   String destImagePath, String gravity, int dissolve, int width, int height) {
        IMOperation op = new IMOperation();
        op.gravity(gravity); //位置center：中心;northwest：左上;southeast：右下
        op.geometry(width, height);
        op.dissolve(dissolve); //水印清晰度 ，0-100  最好设置高点要不看起来没效果
        op.addImage(waterImagePath);
        op.addImage(srcImagePath);
        op.addImage(destImagePath);
        CompositeCmd cmd = new CompositeCmd(true);
        cmd.setSearchPath(graphicsMagickPath);
        try {
            cmd.run(op);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片旋转
     * @param srcImagePath
     * @param destImagePath
     * @param angle
     */
    public static void rotateImage(String srcImagePath, String destImagePath, double angle) {
        try {
            GMOperation op = new GMOperation();
            op.rotate(angle);
            op.addImage(srcImagePath);
            op.addImage(destImagePath);
            ConvertCmd cmd = new ConvertCmd(true);
            cmd.setSearchPath(graphicsMagickPath);
            cmd.run(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据尺寸缩放图片 [等比例缩放:参数height为null,按宽度缩放比例缩放;参数width为null,按高度缩放比例缩放]
     * @param srcImagePath 源图片路径
     * @param destImagePath   处理后图片路径
     * @param width     缩放后的图片宽度
     * @param height    缩放后的图片高度
     */
    public static void zoomImage(String srcImagePath, String destImagePath, Integer width, Integer height) {

        try{
            GMOperation op = new GMOperation();
            op.addImage(srcImagePath);
            if (width == null) {// 根据高度缩放图片
                op.resize(null, height);
            } else if (height == null) {// 根据宽度缩放图片
                op.resize(width);
            } else {
                op.resize(width, height);
            }
            op.addImage(destImagePath);
            ConvertCmd convert = new ConvertCmd(true);
            convert.setSearchPath(graphicsMagickPath);
            convert.run(op);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        }
    }

    /**
     * 裁剪图片
     * @param srcImagePath 源图片路径
     * @param destImagePath   处理后图片路径
     * @param x         起始X坐标
     * @param y         起始Y坐标
     * @param width     裁剪宽度
     * @param height    裁剪高度
     */
    public static void cutImage(String srcImagePath, String destImagePath, int x, int y, int width, int height) {

        try {
            GMOperation op = new GMOperation();
            op.addImage(srcImagePath);
            // width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标
            op.crop(width, height, x, y);
            op.addImage(destImagePath);
            ConvertCmd convert = new ConvertCmd(true);
            convert.setSearchPath(graphicsMagickPath);
            convert.run(op);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把文字转化为一张背景透明的png图片
     * @param str 文字的内容
     * @param fontType 字体，例如宋体
     * @param fontSize 字体大小
     * @param colorStr 字体颜色，不带#号，例如"990033"
     * @param outfile  png图片的路径
     * @throws Exception
     */
    public static void convertFontToImage(String str, String fontType, int fontSize, String colorStr, String outfile) throws Exception{

        str = new String(str.getBytes(), "utf-8");

        Font font = new Font(fontType, Font.BOLD, fontSize);
        File file = new File(outfile);

        //获取font的样式应用在str上的整个矩形
        Rectangle2D r=font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1),false,false));
        int unitHeight=(int)Math.floor(r.getHeight());//获取单个字符的高度
        //获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int width=(int)Math.round(r.getWidth())+1;
        int height=unitHeight+3;//把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度

        //创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(new Color(Integer.parseInt(colorStr, 16)));//在换成所需要的字体颜色
        g2d.setFont(font);
        g2d.drawString(str, 0,font.getSize());

        ImageIO.write(image, "png", file);//输出png图片
    }
}
