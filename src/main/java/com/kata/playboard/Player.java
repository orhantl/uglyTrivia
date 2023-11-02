package com.kata.playboard;

final class Player {
    private static final int MAX_PLACE = 12;

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
         if (this.place >= MAX_PLACE) {
             this.place -= MAX_PLACE;
         }
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int place() {
        return place;
    }


}
