package com.xebia.xke.benchmark.model;

import org.neo4j.graphdb.Node;

import java.math.BigInteger;

public class Flut extends DelegatingNodeObject{


    public Flut(Node flut) {
        super(flut);
    }

    public BigInteger getId() {
        return (BigInteger) getNode().getProperty("id");
    }

    public void setId(BigInteger id) {
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

    public BigInteger getIntNumber() {
        return (BigInteger) getNode().getProperty("intNumber");
    }

    public void setIntNumber(BigInteger intNumber) {
        getNode().setProperty("intNumber", intNumber);
    }

    public boolean isTrueOrFalse() {
        return (Boolean)getNode().getProperty("trueOrFalse");
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        getNode().setProperty("trueOrFalse", trueOrFalse);
    }
}
