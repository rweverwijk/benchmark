package com.xebia.xke.benchmark.rest;

import com.google.inject.Singleton;
import com.xebia.xke.benchmark.model.Flut;
import com.xebia.xke.benchmark.model.SimpleFlut;
import com.xebia.xke.benchmark.service.BenchmarkService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;

@Singleton
@Path("/get")
public class FlutResource {

    @Inject
    private BenchmarkService service;

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public SimpleFlut get(@PathParam("id") BigInteger id) {
        return service.getFlut(id);
    }

}