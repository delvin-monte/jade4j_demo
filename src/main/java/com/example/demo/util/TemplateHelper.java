package com.example.demo.util;

import com.example.demo.config.ApplicationConfiguration;
import com.example.demo.config.ApplicationConfigurationFactory;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;


@Component
public final class TemplateHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateHelper.class);

    private final ApplicationConfiguration applicationConfiguration;

    /**
     * Creates an instance of TemplateHelper.
     *
     * @param applicationConfiguration The application configuration
     */
    @Autowired
    public TemplateHelper(ApplicationConfiguration applicationConfiguration) {
        Preconditions.checkNotNull(applicationConfiguration, "Application configuration cannot be null.");
        this.applicationConfiguration = applicationConfiguration;
    }

    /**
     * Builds a full URL, which contains the PROTOCOL + DOMAIN + path.
     *
     * @param path The path
     *
     * @return A full URL, which contains the PROTOCOL + DOMAIN + path
     */
    public String buildUrl(String path) {
        final UriComponentsBuilder urlBuilder = UriComponentsBuilder.newInstance();
        try {
            urlBuilder.fromHttpUrl(ApplicationConfigurationFactory.DEFAULT_BASE_URL);
        } catch (IllegalArgumentException exception) {
            // Ignore exception.
        }
        urlBuilder.pathSegment(path);

        final UriComponents urlPath = urlBuilder.build();
        LOGGER.debug("buildUrl() {} --> {}", path, urlPath);
        return urlPath.toString();
    }

    /**
     * Builds a full URL to a <b>static</b> resource.
     *
     * @param resourcePath The resource path
     *
     * @return A full URL to a <b>static</b> resource
     */
    public String staticUrl(String resourcePath) {
        final UriComponentsBuilder urlBuilder = UriComponentsBuilder.newInstance();
        try {
            urlBuilder.fromHttpUrl(applicationConfiguration.staticUrl());
        } catch (IllegalArgumentException exception) {
            // Ignore exception.
        }
        urlBuilder.pathSegment(resourcePath);

        final UriComponents staticPath = urlBuilder.build();
        LOGGER.debug("staticUrl() {} --> {}", resourcePath, staticPath);
        return staticPath.toString();
    }

    /**
     * Copies the source.
     *
     * @param source The source
     *
     * @return A <b>mutable</b>copy of the source
     */
    public Map<String, Object> copyModel(@Nullable Map<String, Object> source) {
        if (source != null) {
            try {
                final Map<String, Object> target = new HashMap<>(source.size());
                target.putAll(source);
                return target;
            } catch (Exception exception) {
                LOGGER.debug("copyMap() Failed to copy map: {}", exception);
            }
        }
        return new HashMap<>();
    }

    /**
     * Copies the source, and updates.
     *
     * @param source The source
     * @param updates The updates
     *
     * @return A <b>mutable</b>copy of the source
     */
    public Map<String, Object> copyModelAndUpdate(
            @Nullable Map<String, Object> source, @Nullable Map<String, Object> updates) {
        final Map<String, Object> target = copyModel(source);
        if (updates != null) {
            target.putAll(updates);
        }
        return target;
    }
}
