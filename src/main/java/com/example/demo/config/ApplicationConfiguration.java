package com.example.demo.config;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;


@AutoValue
public abstract class ApplicationConfiguration {

    public static final String DEFAULT_TEMPLATES_DIRECTORY = "classpath:/templates/";

    public static final String DEFAULT_TEMPLATE_SUFFIX = ".jade";

    public static final String DEFAULT_STATIC_URL = "static";

    @Nullable public abstract String projectId();
    @Nullable public abstract String staticUrl();
    @Nullable public abstract String templatesDirectory();
    @Nullable public abstract String templateSuffix();
    public abstract boolean templateCaching();
    public abstract boolean templatePrettyPrint();

    public static Builder builder() {
        final Builder builder = new AutoValue_ApplicationConfiguration.Builder();
        builder
                .staticUrl(DEFAULT_STATIC_URL)
                .templatesDirectory(DEFAULT_TEMPLATES_DIRECTORY)
                .templateSuffix(DEFAULT_TEMPLATE_SUFFIX)
                .templatePrettyPrint(false)
                .templateCaching(true);
        return builder;
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder projectId(String projectId);

        public abstract Builder templatesDirectory(String templatesDirectory);

        public abstract Builder templateSuffix(String templateSuffix);

        public abstract Builder templateCaching(boolean templateCaching);

        public abstract Builder templatePrettyPrint(boolean templatePrettyPrint);

        public abstract Builder staticUrl(String staticUrl);

        public abstract ApplicationConfiguration build();
    }
}
