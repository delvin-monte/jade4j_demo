package com.example.demo.config;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
@EnableCaching
@ConfigurationProperties
public class ApplicationConfigurationFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfigurationFactory.class);

    public static final String DEFAULT_BASE_URL = "http://localhost:8080";

    private final ApplicationConfiguration.Builder configBuilder;

    private ApplicationConfiguration applicationConfiguration;

    public ApplicationConfigurationFactory() {
        this(ApplicationConfiguration.builder());
    }

    public ApplicationConfigurationFactory(ApplicationConfiguration.Builder configBuilder) {
        Preconditions.checkNotNull(configBuilder, "configBuilder cannot be null.");
        this.configBuilder = configBuilder;
    }

    @PostConstruct
    public void init() {
        synchronized (configBuilder) {
            if (applicationConfiguration == null) {
                applicationConfiguration = configBuilder.build();
                LOGGER.debug("init() applicationConfiguration: {}", applicationConfiguration);
            }
        }
    }

    @Bean
    ApplicationConfiguration applicationConfiguration() {
        Preconditions.checkNotNull(
                applicationConfiguration, "Please use init() prior to getting configuration.");
        return applicationConfiguration;
    }

    @Value("${PROJECT_ID}")
    void setProjectId(String projectId) {
        configBuilder.projectId(projectId);
    }

    @Value("${application.static-url:static}")
    void setStaticUrl(String staticUrl) {
        configBuilder.staticUrl(staticUrl);
    }

    @Value("${application.templates-directory:classpath:/templates/}")
    void setTemplatesDirectory(String templatesDirectory) {
        configBuilder.templatesDirectory(templatesDirectory);
    }

    @Value("${application.template-pretty-print:false}")
    void setTemplatePrettyPrint(boolean templatePrettyPrint) {
        configBuilder.templatePrettyPrint(templatePrettyPrint);
    }

    @Value("${application.template-caching:true}")
    void setTemplateCaching(boolean templateCaching) {
        configBuilder.templateCaching(templateCaching);
    }
}
