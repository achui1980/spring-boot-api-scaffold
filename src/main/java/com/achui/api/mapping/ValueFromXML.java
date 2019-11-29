package com.achui.api.mapping;

import freemarker.ext.dom.NodeModel;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.List;

/**
 * @author portz
 * @date 2019/11/28 14:51
 */
public class ValueFromXML implements TemplateMethodModelEx {
    private String source;

    public ValueFromXML(String source) {
        this.source = source;
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        SimpleScalar param1 = (SimpleScalar) arguments.get(0);
        String xPath = param1.getAsString();
        Node withNode = null;
        if (arguments.size() > 1 && arguments.get(1) != null) {
            withNode = ((NodeModel) arguments.get(1)).getNode();
        }
        String result;
        if (withNode != null) {
            result = XPathUtils.selectText(withNode, xPath);
        } else {
            Document document = new XMLParser().parse(source);
            result = XPathUtils.selectText(document, xPath);
        }
        return StringUtils.isBlank(result) ? StringUtils.EMPTY : result;
    }
}
