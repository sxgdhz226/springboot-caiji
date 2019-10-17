package net.cn.ssd.util;

import org.dom4j.*;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class GetSqlUtil {

    public static void main(String args[]) {
//        /**
//         * 解析XML文件
//         */
//        Element element = null;
//        // 可以使用绝对路劲
//        File f = new File(GetSqlUtil.class.getClassLoader().getResource("mybatis/mapper/weather/WeatherMapper.xml").getPath());
//        // documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
//        DocumentBuilder db = null;
//        DocumentBuilderFactory dbf = null;
//        try {
//            dbf = DocumentBuilderFactory.newInstance();
//            db = dbf.newDocumentBuilder();
//            Document dt = db.parse(f);// 得到一个DOM
//            element = dt.getDocumentElement();
//            String nodeName = element.getNodeName();
//            System.out.println("根元素：" + nodeName);
//            // 根元素下的所有子节点
//            NodeList childNodes = element.getChildNodes();
//            // 遍历这些子节点
//            for (int i = 0; i < childNodes.getLength(); i++) {
//                // 获得每个对应位置i的结点
//                Node node1 = childNodes.item(i);
//                if ("select".equals(node1.getNodeName())) {
//                    String selectId = node1.getAttributes().getNamedItem("id").getNodeValue();
//                    System.out.println(selectId);
//                    String resultType = node1.getAttributes().getNamedItem("resultType").getNodeValue();
//                    System.out.println(resultType);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private static void get构造方法(String className, String pa1) throws Exception {
        Class<?> c = Class.forName(className);
        // 获取构造
        Constructor<?> constr = c.getConstructor(String.class);
        // 实例化对象
        Object o = constr.newInstance(pa1);
        System.out.println(o);
    }


    public static List<String> parseXml(){
        List<String> sqlList = new ArrayList<>();
        File file = new File(GetSqlUtil.class.getClassLoader().getResource("config/WeatherMapper.xml").getPath());
        String xml = readFile(file);
        try {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();
            for (Element element : elementList){
                sqlList.add(element.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlList;
    }

    public static String readFile(File file) {
        InputStream ins = null;
        StringBuilder builder = null;
        try {
            // 从服务器上读取指定的文件
            ins = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    ins, "UTF-8"));
            String line;
            builder = new StringBuilder(150);
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            if (ins != null) {
                ins.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
