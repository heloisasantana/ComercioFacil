package com.example.comerciofacil.model;

public abstract class EntidadeBase {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public abstract String toString();
}

