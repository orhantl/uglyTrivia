package com.kata.playboard;

final class Player {
    private final String name;
    private int place;
    private final int purse;
    private final boolean inPenaltyBox;

     Player(String name, int place, int purse, boolean inPenaltyBox) {
        this.name = name;
        this.place = place;
        this.purse = purse;
        this.inPenaltyBox = inPenaltyBox;
    }

    Player(String name) {
        this(name, 0, 0, false);
    }

    void move(int place) {
        this.place += place;
    }

    public String name() {
        return name;
    }

}
