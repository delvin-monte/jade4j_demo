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


/** Adds data to the model for all requests. */
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
     * @param templateConfiguration The {@link com.example.demo.config.TemplateConfiguration template configuration}
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

    /** Adds the page attributes to the model. */
    @ModelAttribute("pageData")
    public Map<String, Map<String, String>> defaultPageAttributes() {
        return templateConfiguration.pageAttributes();
    }

    /** Adds, to the model, an object which contains functions that can be used in the templates. */
    @ModelAttribute("templateHelper")
    public TemplateHelper templateHelper() {
        return templateHelper;
    }
}
