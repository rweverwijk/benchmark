package com.xebia.xke.benchmark.model;

import java.math.BigInteger;

public class Flut {
    private BigInteger id;
    private String shortStringAttribute;
    private String longStringAttribute;
    private BigInteger intNumber;
    private boolean trueOrFalse;

    public Flut() {
    }

    public Flut(BigInteger id, String shortStringAttribute, String longStringAttribute, BigInteger intNumber, boolean trueOrFalse) {
        this.id = id;
        this.shortStringAttribute = shortStringAttribute;
        this.longStringAttribute = longStringAttribute;
        this.intNumber = intNumber;
        this.trueOrFalse = trueOrFalse;
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
