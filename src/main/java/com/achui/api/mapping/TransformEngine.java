package com.achui.api.mapping;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author portz
 * @date 2019/11/28 11:27
 */
public class TransformEngine {
    static final String FILE_PROTOCOL = "file://";
    private Configuration configuration;
    private File templateFile;

    Configuration getConfiguration() throws IOException {
        if (configuration == null) {
            DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
            configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setTemplateLoader(new AbsolutePathFileFreemarkerTemplateLoader());
            configuration.setLocalizedLookup(false);
            configuration.setObjectWrapper(builder.build());
        }
        return configuration;
    }

    public String transform(String source, Map<String, Object> context) {
        try {
            Configuration cfg = getConfiguration();
            cfg.setSharedVariable("fromJson", new FromJson(source));
            cfg.setSharedVariable("fromXML", new FromXML(source));
            String path = getTemplateResourcePath(templateFile);
            Template template = configuration.getTemplate(path);
            StringWriter writer = new StringWriter();
            template.process(context, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    String getTemplateResourcePath(File templateFile) {
        return FILE_PROTOCOL + templateFile.getAbsolutePath();
    }

    public void setTemplateFile(File templateFile) {
        this.templateFile = templateFile;
    }
}
