package com.itlwx.core.bo;

public class CategoryQueryBO extends Pageable{

    private String name;

    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
