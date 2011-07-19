package com.xebia.xke.benchmark.rest;

import com.google.inject.Singleton;
import com.xebia.xke.benchmark.model.SimpleFlut;
import com.xebia.xke.benchmark.service.BenchmarkService;

import javax.inject.Inject;
import javax.ws.rs.*;
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
        SimpleFlut flut = service.getFlut(id);
        if (flut == null) {
            throw new WebApplicationException(404);
        }
        return flut;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(SimpleFlut flut) {
        service.saveFlut(flut);
    }

}