package com.xebia.xke.benchmark.service;


import com.xebia.xke.benchmark.model.Flut;
import com.xebia.xke.benchmark.model.SimpleFlut;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

import java.math.BigInteger;

public class BenchmarkService {
    private GraphDatabaseService databaseService;
    private Index<Node> idIndex;

    public BenchmarkService(GraphDatabaseService databaseService) {
        this.databaseService = databaseService;
        idIndex = databaseService.index().forNodes("id");
    }


    public Flut getFlut(BigInteger id) {
        Node flut = idIndex.get("id", id).getSingle();
        return new Flut(flut);
    }

    public void saveFlut(SimpleFlut flut) {

    }
}
