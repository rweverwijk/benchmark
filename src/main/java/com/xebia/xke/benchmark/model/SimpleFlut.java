package com.xebia.xke.benchmark.model;

import java.math.BigInteger;

public class SimpleFlut {
    private BigInteger id;
    private String shortStringAttribute;
    private String longStringAttribute;
    private BigInteger intNumber;
    private boolean trueOrFalse;

    public SimpleFlut() {
    }

    public SimpleFlut(BigInteger id, String shortStringAttribute, String longStringAttribute, BigInteger intNumber, boolean trueOrFalse) {
        this.id = id;
        this.shortStringAttribute = shortStringAttribute;
        this.longStringAttribute = longStringAttribute;
        this.intNumber = intNumber;
        this.trueOrFalse = trueOrFalse;
    }

    public SimpleFlut(Flut flut) {
        this.id = flut.getId();
        this.shortStringAttribute = flut.getShortStringAttribute();
        this.longStringAttribute = flut.getLongStringAttribute();
        this.intNumber = flut.getIntNumber();
        this.trueOrFalse = flut.isTrueOrFalse();
    }

    public BigInteger getId() {
        return id;
    }

    public String getShortStringAttribute() {
        return shortStringAttribute;
    }

    public String getLongStringAttribute() {
        return longStringAttribute;
    }

    public BigInteger getIntNumber() {
        return intNumber;
    }

    public boolean isTrueOrFalse() {
        return trueOrFalse;
    }
}