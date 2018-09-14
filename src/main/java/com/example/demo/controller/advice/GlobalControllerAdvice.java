package com.example.demo.controller.advice;

import static com.example.demo.config.ApplicationConfigurationFactory.DEFAULT_BASE_URL;

import com.example.demo.util.TemplateHelper;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;


@ControllerAdvice
@Order(100)
public class GlobalControllerAdvice {

    public static final ImmutableMap<String, String> PAGE_ATTRIBUTES = ImmutableMap.of(
            "baseUrl", DEFAULT_BASE_URL
    );

    public static final ImmutableMap<String, Map<String, String>> BODY_ATTRIBUTES = ImmutableMap.of(
            "bodyAttributes", ImmutableMap.of("class", "main standard"),
            "contentAttributes", ImmutableMap.of("class", "content"),
            "footerAttributes", ImmutableMap.of("class", "footer")
    );

    private final TemplateHelper templateHelper;

    /**
     * Creates an instance of GlobalControllerAdvice.
     *
     * @param templateHelper The {@link com.example.demo.util.TemplateHelper template helper}
     */
    @Autowired
    GlobalControllerAdvice(TemplateHelper templateHelper) {
        Preconditions.checkNotNull(templateHelper, "Template helper cannot be null.");
        this.templateHelper = templateHelper;
    }

    @ModelAttribute("pageData")
    public Map<String, String> defaultPageAttributes() {
        return PAGE_ATTRIBUTES;
    }

    @ModelAttribute("bodyData")
    public Map<String, Map<String, String>> defaultBodyAttributes() {
        return BODY_ATTRIBUTES;
    }

    @ModelAttribute("templateHelper")
    public TemplateHelper templateHelper() {
        return templateHelper;
    }
}
