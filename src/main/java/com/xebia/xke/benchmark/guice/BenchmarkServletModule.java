package com.xebia.xke.benchmark.guice;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.xebia.xke.benchmark.rest.FlutResource;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Servlet Module
 * @author mischa
 */
public class BenchmarkServletModule extends ServletModule {
    protected void configureServlets() {
        EmbeddedGraphDatabase database = new EmbeddedGraphDatabase("/tmp/benchmark/neo");
        bind(GraphDatabaseService.class).toInstance(database);
        bind(FlutResource.class);

        Map<String, String> params = new HashMap<String, String>();
        params.put("com.sun.jersey.config.feature.ImplicitViewables", "true");
        params.put("com.sun.jersey.config.feature.Redirect", "true");
        params.put("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        params.put("com.sun.jersey.config.property.packages", "nl.heppienest.niokids.rest");
        serve("/rest/*").with(GuiceContainer.class, params);
    }
}