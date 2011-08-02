package com.xebia.xke.benchmark.service;


import com.google.inject.Inject;
import com.xebia.xke.benchmark.model.Flut;
import com.xebia.xke.benchmark.model.SimpleFlut;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

import java.util.NoSuchElementException;

public class BenchmarkService {
    private GraphDatabaseService databaseService;
    private Index<Node> idIndex;

    @Inject
    public BenchmarkService(GraphDatabaseService databaseService) {
        this.databaseService = databaseService;
        idIndex = databaseService.index().forNodes("NodeIdIndex");
    }

    public SimpleFlut getFlut(Long id) {
        Node node = getNode(id);
        return node != null ? new SimpleFlut(new Flut(node)) : null;
    }

    private Node getNode(Long id) {
        Node node = null;
        try {
        node = idIndex.get("id", id).next();
        } catch (NoSuchElementException e) {

        }
        return node;
    }

    public void saveFlut(SimpleFlut flut) {
        Transaction transaction = databaseService.beginTx();
        try {
            Node node = getNode(flut.getId());
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
