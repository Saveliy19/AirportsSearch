package com.app.entities;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final char letter;
    private final List<Short> idList;
    private final List<Vertex> children;

    public Vertex(char ch, short id) {
        this.letter = ch;
        this.children = new ArrayList<Vertex>();
        this.idList = new ArrayList<Short>();
        this.idList.add(id);
    }

    public char getLetter() {
        return letter;
    }

    public Vertex addChild(char ch, short id) {
        Vertex child = new Vertex(ch, id);
        this.children.add(child);
        return child;
    }

    public void addId(short id) {
        this.idList.add(id);
    }

    public List<Short> getIdList() {
        return this.idList;
    }

    public List<Vertex> getChildren() {
        return this.children;
    }

}
