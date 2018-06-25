package com.example.webmagic.util;

import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.core.IMOperation;

import java.io.UnsupportedEncodingException;

public class GraphUtil {

    /**
     * GraphicsMagick的安装目录
     */
    private static final String graphicsMagickPath = "D:\\workingSoftwares\\GraphicsMagick\\GraphicsMagick-1.3.30-Q16";

    /**
     * 图片加文本水印
     * @param srcPath
     * @param destPath
     * @param content
     */
    public static void addWaterText(String srcPath, String destPath, String content){

        GMOperation op = new GMOperation();
        op.font("Vrinda");
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
     * 图片旋转
     * @param srcImagePath
     * @param destImagePath
     * @param angle
     */
    public static void rotate(String srcImagePath, String destImagePath, double angle) {
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
}
