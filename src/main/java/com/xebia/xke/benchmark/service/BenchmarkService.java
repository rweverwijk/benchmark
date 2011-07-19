package com.xebia.xke.benchmark.service;


import com.google.inject.Inject;
import com.xebia.xke.benchmark.model.Flut;
import com.xebia.xke.benchmark.model.SimpleFlut;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

import java.math.BigInteger;

public class BenchmarkService {
    private GraphDatabaseService databaseService;
    private Index<Node> idIndex;

    @Inject
    public BenchmarkService(GraphDatabaseService databaseService) {
        this.databaseService = databaseService;
        idIndex = databaseService.index().forNodes("NodeIdIndex");
    }

    public SimpleFlut getFlut(BigInteger id) {
        Node node = idIndex.get("id", id).getSingle();
        return node != null ? new SimpleFlut(new Flut(node)) : null;
    }

    public void saveFlut(SimpleFlut flut) {
        Transaction transaction = databaseService.beginTx();
        try {
            Node node = idIndex.get("id", flut.getId()).getSingle();
            if (node == null) {
                node = databaseService.createNode();
            }
            Flut neoFlut = new Flut(node, flut);
            idIndex.add(neoFlut.getNode(), "id", neoFlut.getId());
            transaction.success();
        } finally {
            transaction.finish();
        }
    }

}
