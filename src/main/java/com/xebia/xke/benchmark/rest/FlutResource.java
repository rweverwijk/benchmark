package com.xebia.xke.benchmark.rest;

import com.google.inject.Singleton;
import com.xebia.xke.benchmark.model.SimpleFlut;
import com.xebia.xke.benchmark.service.BenchmarkService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;

@Singleton
@Path("/")
public class FlutResource {

    @Inject
    private BenchmarkService service;

    @Path("get/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public SimpleFlut get(@PathParam("id") BigInteger id) {
        SimpleFlut flut = service.getFlut(id);
        if (flut == null) {
            throw new WebApplicationException(404);
        }
        return flut;
    }

    @Path("put/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@PathParam("id") BigInteger id, SimpleFlut flut) {
        service.saveFlut(flut);
    }

}