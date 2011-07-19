package com.xebia.xke.benchmark.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Guice servlet config.
 * @author mischa
 */
public class BenchmarkServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new BenchmarkServletModule());
    }
}