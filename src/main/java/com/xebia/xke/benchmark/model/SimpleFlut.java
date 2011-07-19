package com.xebia.xke.benchmark.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SimpleFlut {
    private Long id;

    private String shortStringAttribute;
    private String longStringAttribute;
    private Long intNumber;
    private boolean trueOrFalse;

    public SimpleFlut() {
    }

    public SimpleFlut(Long id, String shortStringAttribute, String longStringAttribute, Long intNumber, boolean trueOrFalse) {
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

    public Long getId() {
        return id;
    }

    public String getShortStringAttribute() {
        return shortStringAttribute;
    }

    public String getLongStringAttribute() {
        return longStringAttribute;
    }

    public Long getIntNumber() {
        return intNumber;
    }

    public boolean isTrueOrFalse() {
        return trueOrFalse;
    }
}
