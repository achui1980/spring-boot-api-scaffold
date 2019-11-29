package com.achui.api.mapping;

import com.alibaba.fastjson.JSONPath;
import freemarker.template.DefaultMapAdapter;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author portz
 * @date 2019/11/28 14:51
 */
public class ValueFromJson implements TemplateMethodModelEx {
    private String source;

    public ValueFromJson(String source) {
        this.source = source;
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        SimpleScalar param1 = (SimpleScalar) arguments.get(0);
        String jsonPath = param1.getAsString();
        Object withNode = null;
        if (arguments.size() > 1 && arguments.get(1) != null) {
            withNode = ((DefaultMapAdapter) arguments.get(1)).getWrappedObject();
        }
        String result;
        if (withNode != null) {
            result = (String) JSONPath.eval(withNode, jsonPath);
        } else {
            result = (String) JSONPath.eval(source, jsonPath);
        }
        return StringUtils.isBlank(result) ? StringUtils.EMPTY : result;
    }
}
