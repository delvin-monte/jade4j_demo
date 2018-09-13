package com.example.demo.view;

import de.neuland.jade4j.spring.view.JadeViewResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import java.util.Locale;


/*
 * TODO: Remove class after change to use JadeViewResolver in JadeTemplateConfiguration.
 */
public class ApplicationViewResolver extends JadeViewResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationViewResolver.class);

    @Override
    @Nullable
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        final View view = super.resolveViewName(viewName, locale);
        LOGGER.debug("resolveViewName() viewName: [{}] associated to [{}] --> {}",
                     viewName, view, canHandle(viewName, locale));
        return view;
    }

    @Override
    @Nullable
    protected View createView(String viewName, Locale locale) throws Exception {
        LOGGER.debug("createView BEGIN");
        return super.createView(viewName, locale);
    }

    @Override
    @Nullable
    protected View loadView(String viewName, Locale locale) throws Exception {
        LOGGER.debug("loadView BEGIN");
        return super.loadView(viewName, locale);
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        final AbstractUrlBasedView view = super.buildView(viewName);
        LOGGER.debug("buildView() viewName: [{}] associated to [{}]", viewName, view);
        return view;
    }
}
