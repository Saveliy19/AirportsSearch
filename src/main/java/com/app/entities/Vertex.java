package com.app.entities;

import java.util.ArrayList;

public class Vertex implements IEntity {
    public final Character letter;
    private final ArrayList<Short> idList;
    private final ArrayList<Vertex> children;

    public Vertex(char ch, short id) {
        this.letter = ch;
        this.children = new ArrayList<Vertex>();
        this.idList = new ArrayList<Short>();
        this.idList.add(id);
    }

    public Vertex addChild(char ch, short id) {
        Vertex child = new Vertex(ch, id);
        this.children.add(child);
        return child;
    }

    public void addId(short id) {
        this.idList.add(id);
    }

    public ArrayList<Short> getIdList() {
        return this.idList;
    }

    public ArrayList<Vertex> getChildren() {
        return this.children;
    }

}
