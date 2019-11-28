package com.achui.api.mapping;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import java.util.List;

/**
 * @author portz
 * @date 2019/11/28 14:51
 */
public class FromXML implements TemplateMethodModelEx {
    private String source;

    public FromXML(String source) {
        this.source = source;
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        SimpleScalar param1 = (SimpleScalar) arguments.get(0);
        String xPath = param1.getAsString();
        Document document = new XMLParser().parse(source);
        String result = XPathUtils.selectText(document, xPath);
        return StringUtils.isBlank(result) ? StringUtils.EMPTY : result;
    }
}
