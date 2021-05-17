package com.example.secondtry;

import java.util.ArrayList;
import java.util.List;

//этот класс для хранения всех параметров одного события?
public class Store {
    //и подпиши что это за константа
    private static final Store INST = new Store();

    private final List<Item> items = new ArrayList<>();

    private Store() {
    }

    public static Store getStore() {
        return INST;
    }

    public void add(Item item) {
        this.items.add(item);
    }

    public List<Item> getAll() {
        return this.items;
    }

    public int size() {
        return this.items.size();
    }

    public Item get(int index) {
        return this.items.get(index);
    }
}
