package com.example.demo.controller.advice;

import static com.example.demo.util.TemplateHelper.TEMPLATE_HELPER;

import com.example.demo.config.ApplicationConfigurationFactory;
import com.example.demo.util.TemplateHelper;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
@Order(100)
public class GlobalControllerAdvice {

    @ModelAttribute("BASE_URL")
    public String baseUrl() {
        return ApplicationConfigurationFactory.DEFAULT_BASE_URL;
    }

    @ModelAttribute("STATIC_URL")
    public String staticUrl() {
        return TEMPLATE_HELPER.staticUrl("static");
    }

    @ModelAttribute("templateHelper")
    public TemplateHelper templateHelper() {
        return TEMPLATE_HELPER;
    }
}
