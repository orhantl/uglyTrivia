package com.kata.playboard;

public record Player(String name, int place, int purse, boolean inPenaltyBox) {

    public Player(String name) {
        this(name, 0, 0, false);
    }

    @Override
    public String toString() {
        return name;
    }
}
