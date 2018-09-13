package com.example.demo.config;

import com.example.demo.view.ApplicationViewResolver;

import com.google.common.base.Preconditions;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.spring.template.SpringTemplateLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.Reader;


/**
 * Based on <a href="https://github.com/neuland/jade4j">jade4j</a>.
 */
@Configuration
@EnableWebMvc
public class JadeTemplateConfiguration implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JadeTemplateConfiguration.class);

    private final ApplicationConfiguration applicationConfiguration;

    @Autowired
    public JadeTemplateConfiguration(ApplicationConfiguration applicationConfiguration) {
        Preconditions.checkNotNull(applicationConfiguration, "Application configuration cannot be null.");
        this.applicationConfiguration = applicationConfiguration;
    }

    @Bean
    SpringTemplateLoader templateLoader() {
        LOGGER.debug("templateLoader() *");
        final SpringTemplateLoader templateLoader = new SpringTemplateLoader();
        templateLoader.setBasePath(applicationConfiguration.templatesDirectory());
        templateLoader.setSuffix(applicationConfiguration.templateSuffix());
        return templateLoader;
    }

    @Bean
    JadeConfiguration jadeConfiguration(SpringTemplateLoader templateLoader) {
        LOGGER.debug("jadeConfiguration() *");
        try (final Reader reader = templateLoader.getReader("base")) {
            LOGGER.debug("jadeConfiguration() reader: {}", reader);
        } catch (Exception exception) {
            throw new IllegalStateException("Cannot locate templates directory: " + exception);
        }

        final JadeConfiguration configuration = new JadeConfiguration();
        configuration.setCaching(applicationConfiguration.templateCaching());
        configuration.setPrettyPrint(applicationConfiguration.templatePrettyPrint());
        configuration.setTemplateLoader(templateLoader);
        return configuration;
    }

    @Bean
    ViewResolver viewResolver(JadeConfiguration jadeConfiguration) {
        LOGGER.debug("viewResolver() *");
        /*
         * TODO: Change to directly use JadeViewResolver.
         */
        final ApplicationViewResolver viewResolver = new ApplicationViewResolver();
        viewResolver.setConfiguration(jadeConfiguration);
        return viewResolver;
    }
}
