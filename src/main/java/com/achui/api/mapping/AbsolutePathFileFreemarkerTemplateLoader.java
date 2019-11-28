package com.achui.api.mapping;

import freemarker.cache.TemplateLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author neo
 */
public class AbsolutePathFileFreemarkerTemplateLoader implements TemplateLoader {
    static final String FILE_PROTOCOL = "file://";

    @Override
    public Object findTemplateSource(final String name) throws IOException {
        if (!name.startsWith(TransformEngine.FILE_PROTOCOL)) {
            throw new IllegalArgumentException("invalid template file name: " + name + ", name must start with " + TransformEngine.FILE_PROTOCOL);
        }
        String path = name.substring(TransformEngine.FILE_PROTOCOL.length());
        return new File(path);
    }

    @Override
    public long getLastModified(Object templateSource) {
        return ((File) templateSource).lastModified();
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {
        if (!(templateSource instanceof File)) {
            throw new IllegalArgumentException("templateSource is a: " + templateSource.getClass().getName());
        }
        return new InputStreamReader(new FileInputStream((File) templateSource), encoding);
    }

    @Override
    public void closeTemplateSource(Object templateSource) {
    }
}
