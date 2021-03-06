package com.xebia.xke.benchmark.model;

import org.neo4j.graphdb.Node;

import java.math.BigInteger;

public class Flut extends DelegatingNodeObject {

    public Flut(Node flut) {
        super(flut);
    }

    public Flut(Node node, SimpleFlut simpleFlut) {
        super(node);
        setId(simpleFlut.getId());
        setIntNumber(simpleFlut.getIntNumber());
        setLongStringAttribute(simpleFlut.getLongStringAttribute());
        setShortStringAttribute(simpleFlut.getShortStringAttribute());
        setTrueOrFalse(simpleFlut.isTrueOrFalse());
    }

    public Long getId() {
        return (Long) getNode().getProperty("id");
    }

    public void setId(Long id) {
        getNode().setProperty("id", id);
    }

    public String getShortStringAttribute() {
        return (String) getNode().getProperty("shortString");
    }

    public void setShortStringAttribute(String shortStringAttribute) {
        getNode().setProperty("shortString", shortStringAttribute);
    }

    public String getLongStringAttribute() {
        return (String) getNode().getProperty("longString");
    }

    public void setLongStringAttribute(String longStringAttribute) {
        getNode().setProperty("longString", longStringAttribute);
    }

    public Long getIntNumber() {
        return (Long) getNode().getProperty("intNumber");
    }

    public void setIntNumber(Long intNumber) {
        getNode().setProperty("intNumber", intNumber);
    }

    public boolean isTrueOrFalse() {
        return (Boolean) getNode().getProperty("trueOrFalse");
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        getNode().setProperty("trueOrFalse", trueOrFalse);
    }
}
