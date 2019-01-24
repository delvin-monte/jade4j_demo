package com.example.demo.config;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * Contains the properties/configuration to support the template pages.
 *
 * See https://projectlombok.org.
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix="template-pages")
public class TemplateConfiguration {
    @Setter(AccessLevel.PACKAGE)
    private Map<String, String> javascriptLibs = new HashMap<>();

    @Setter(AccessLevel.PACKAGE)
    private Map<String, String> bodyAttributes = new HashMap<>();

    @Setter(AccessLevel.PACKAGE)
    private Map<String, String> contentAttributes = new HashMap<>();

    @Setter(AccessLevel.PACKAGE)
    private Map<String, String> footerAttributes = new HashMap<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.PACKAGE)
    private Map<String, Map<String, String>> pageAttributes;

    @PostConstruct
    public void init() {
        pageAttributes = ImmutableMap.of(
                "javascriptLibs", ImmutableMap.copyOf(getJavascriptLibs()),
                "bodyAttributes", ImmutableMap.copyOf(getBodyAttributes()),
                "contentAttributes", ImmutableMap.copyOf(getContentAttributes()),
                "footerAttributes", ImmutableMap.copyOf(getFooterAttributes())
        );
    }

    /** Returns the page attributes. */
    public Map<String, Map<String, String>> pageAttributes() {
        return pageAttributes;
    }
}
