package com.example.demo.util;

import com.example.demo.config.ApplicationConfigurationFactory;

import java.nio.file.Paths;


public final class TemplateHelper {

    public static final TemplateHelper TEMPLATE_HELPER = new TemplateHelper();

    public String staticUrl(String path) {
        return Paths.get(ApplicationConfigurationFactory.DEFAULT_BASE_URL, path).toString();
    }
}
