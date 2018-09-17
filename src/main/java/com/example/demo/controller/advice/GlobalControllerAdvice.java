package com.example.demo.controller.advice;

import com.example.demo.config.TemplateConfiguration;
import com.example.demo.util.TemplateHelper;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;
import java.util.Map;


@ControllerAdvice
@Order(100)
public class GlobalControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    private final TemplateHelper templateHelper;

    private final TemplateConfiguration templateConfiguration;

    /**
     * Creates an instance of GlobalControllerAdvice.
     *
     * @param templateHelper The {@link com.example.demo.util.TemplateHelper template helper}
     */
    @Autowired
    GlobalControllerAdvice(TemplateHelper templateHelper, TemplateConfiguration templateConfiguration) {
        Preconditions.checkNotNull(templateHelper, "Template helper cannot be null.");
        Preconditions.checkNotNull(templateConfiguration, "Template configuration cannot be null.");
        this.templateHelper = templateHelper;
        this.templateConfiguration = templateConfiguration;
    }

    @PostConstruct
    public void init() {
        LOGGER.debug("init() templateConfiguration: {}", templateConfiguration);
    }

    @ModelAttribute("pageData")
    public Map<String, Map<String, String>> defaultPageAttributes() {
        return templateConfiguration.pageAttributes();
    }

    @ModelAttribute("templateHelper")
    public TemplateHelper templateHelper() {
        return templateHelper;
    }
}
