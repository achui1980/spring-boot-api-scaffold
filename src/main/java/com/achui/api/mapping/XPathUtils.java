package com.achui.api.mapping;

import com.sun.org.apache.xpath.internal.XPathAPI;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.List;

import static com.ehi.ci.statusupdate.freemarker.DOMUtils.text;


/**
 * @author neo
 */
public final class XPathUtils {
    public static Element selectElement(Node root, String xpath) {
        try {
            Node node = XPathAPI.selectSingleNode(root, xpath);
            if (node == null) {
                return null;
            }
            if (!(node instanceof Element)) {
                throw new RuntimeException("target node is not element, xpath=" + xpath + ", element=" + text(root));
            }
            return (Element) node;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Element> selectElements(Node root, String xpath) {
        List<Element> elements = new ArrayList<Element>();
        try {
            NodeList nodes = XPathAPI.selectNodeList(root, xpath);
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (!(node instanceof Element)) {
                    throw new RuntimeException("target node is not element, xpath=" + xpath + ", element=" + text(root));
                }
                elements.add((Element) node);
            }
            return elements;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static String selectText(Node root, String xpath) {
        try {
            Node node = XPathAPI.selectSingleNode(root, xpath);
            if (node == null) {
                return null;
            }
            if (node instanceof Text) {
                return node.getTextContent();
            } else if (node instanceof Element) {
                return DOMUtils.getText((Element) node);
            } else if (node instanceof Attr) {
                return ((Attr) node).getValue();
            }
            throw new RuntimeException("unsupported type, xpath=" + xpath + ", element=" + text(root));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static int selectInt(Node root, String xpath) {
        try {
            XObject result = XPathAPI.eval(root, xpath);
            return (int) result.num();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private XPathUtils() {
    }
}
