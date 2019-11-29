package com.achui.api.mapping;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.collections.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author portz
 * @date 2019/11/28 14:51
 */
public class ListFromXML implements TemplateMethodModelEx {
    private String source;

    public ListFromXML(String source) {
        this.source = source;
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        SimpleScalar param1 = (SimpleScalar) arguments.get(0);
        String xPath = param1.getAsString();
        Document document = new XMLParser().parse(source);
        List<Element> result = XPathUtils.selectElements(document, xPath);
        return CollectionUtils.isNotEmpty(result) ? result : new ArrayList();
    }
}
