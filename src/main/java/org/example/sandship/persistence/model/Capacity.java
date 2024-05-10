package org.example.sandship.persistence.model;

public class Capacity {
    private int max = 1000;
    private int current = 0;

    public Capacity(int count) {
        current = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getAllowedRange() {
        return max - current;
    }
}

