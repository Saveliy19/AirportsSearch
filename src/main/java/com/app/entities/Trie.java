package com.app.entities;

import java.util.ArrayList;

public class Trie implements IEntity {
    private Vertex root;

    public Trie() {
        this.root = new Vertex('\0', (short)-1);
    }

    public void insert(short id, String word) {
        Vertex current = root;
        for (char ch : word.toCharArray()) {
            boolean found = false;
            for (Vertex child : current.getChildren()) {
                if (child.letter == ch) {
                    found = true;
                    child.addId(id);
                    current = child;
                    break;
                }
            }
            if (!found) current = current.addChild(ch, id);
        }
    }

    public ArrayList<Short> searchIds(String searchWord) {
        Vertex current = root;

        for (char ch : searchWord.toCharArray()) {
            boolean found = false;
            for (Vertex child : current.getChildren()) {
                if (child.letter == ch) {
                    found = true;
                    current = child;
                    break;
                }
            }
            if (!found) return new ArrayList<>();

        }
        return current.getIdList();
    }
}
