package com.achui.api.mapping;

import com.alibaba.fastjson.JSONPath;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author portz
 * @date 2019/11/28 14:51
 */
public class FromJson implements TemplateMethodModelEx {
    private String source;

    public FromJson(String source) {
        this.source = source;
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        SimpleScalar param1 = (SimpleScalar) arguments.get(0);
        String jsonPath = param1.getAsString();
        String result = (String) JSONPath.read(source, jsonPath);
        return StringUtils.isBlank(result) ? StringUtils.EMPTY : result;
    }
}
