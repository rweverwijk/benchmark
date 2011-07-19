package com.xebia.xke.benchmark.service;


import com.xebia.xke.benchmark.model.Flut;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.index.Index;

public class BenchmarkService {
    private GraphDatabaseService databaseService;
    private Index idIndex;

    public BenchmarkService(GraphDatabaseService databaseService) {
        this.databaseService = databaseService;
        idIndex = databaseService.index().forNodes("id");
    }


    public Flut getFlut() {
        return new Flut();
    }
}
