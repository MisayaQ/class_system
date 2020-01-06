package com.course.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * bpmn文件 增加修改节点工具类
 *
 * @author David
 */
public class BpmnXmlUtils {

    static Logger logger = LoggerFactory.getLogger(BpmnXmlUtils.class);

    private BpmnXmlUtils() {

    }

    /**
     * 获取流程文件的ProcessId
     * @param file
     * @return
     */
    public static String getProcessId(File file) {
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(file);
            Element rootElement = doc.getRootElement();
            Element processElement = rootElement.elements().get(0);
            return processElement.attribute("id").getValue();
        } catch (DocumentException e) {
            logger.error("XML文件读取或操作异常, {}", e.getMessage());
            return "";
        }
    }

    /**
     * 排他网关增加表达式
     *
     * @param path           xml文件路径
     * @param sequenceFlowId xml 中 sequenceFlow 的 id
     * @param expression     表达式
     * @return 成功失败
     */
    public static boolean addConditionExpression(String path, String sequenceFlowId, String expression) {
        File file = new File(path);

        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(file);
            Element rootElement = doc.getRootElement();
            Element processElement = rootElement.elements().get(0);
            List<Element> processChildrenElements = processElement.elements();
            Element node = null;
            for (Element element : processChildrenElements) {
                if ("sequenceFlow".equals(element.getQName().getName()) && element.attribute("id").getValue().equals(sequenceFlowId)) {
                    node = element;
                }
            }
            if (node != null) {
                List<Element> nodes = node.elements();
                if (nodes != null && !nodes.isEmpty()) {
                    for (Element element : nodes) {
                        node.remove(element);
                    }
                }
                // 添加conditionExpression节点
                Element sequenceFlowNode = node.addElement("conditionExpression");
                // 设置xsi:type 固定值
                sequenceFlowNode.addAttribute("xsi:type", "tFormalExpression");
                // 设置表达式
                //表达式拼接
                String expressionNew = "${" + expression + "}";
                sequenceFlowNode.addCDATA(expressionNew);
                //格式化为缩进格式
                OutputFormat format = OutputFormat.createPrettyPrint();
                //设置编码格式
                format.setEncoding("UTF-8");

                XMLWriter writer = new XMLWriter(new FileWriter(file), format);
                //写入数据
                writer.write(doc);
                writer.close();
                return true;
            } else {
                return false;
            }
        } catch (DocumentException | IOException e) {
            logger.error("XML文件读取或操作异常, {}", e.getMessage());
            return false;
        }
    }

    /**
     *  获取流程中所有的用户任务节点
     * @param path
     * @return
     */
    public static List<String> getUserTaskActivityIds(String path){
        List<String> result = new ArrayList<>();
        File file = new File(path);
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(file);
            Element rootElement = doc.getRootElement();
            Element processElement = rootElement.elements().get(0);
            List<Element> processChildrenElements = processElement.elements();
            for (Element element : processChildrenElements) {
                if ("userTask".equals(element.getQName().getName())) {
                    result.add(element.attribute("id").getValue());
                }
            }
        } catch (Exception e) {
            logger.error("XML文件读取或操作异常, {}", e.getMessage());
        }
        return result;
    }

    public static List<String> getAllActivityIds(String path){
        List<String> result = new ArrayList<>();
        File file = new File(path);
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(file);
            Element rootElement = doc.getRootElement();
            Element processElement = rootElement.elements().get(0);
            List<Element> processChildrenElements = processElement.elements();
            for (Element element : processChildrenElements) {
                result.add(element.attribute("id").getValue());
            }
        } catch (Exception e) {
            logger.error("XML文件读取或操作异常, {}", e.getMessage());
        }
        return result;
    }
}
