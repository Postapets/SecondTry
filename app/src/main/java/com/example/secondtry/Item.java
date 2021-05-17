package com.example.secondtry;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Objects;

public class Item {
    private String name;
    private String desc;
    private Calendar created;
    private boolean done;

    public Item(String name, String desc, Calendar created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
