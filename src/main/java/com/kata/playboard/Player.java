package com.kata.playboard;

final class Player {
    private static final int MAX_PLACE = 12;
    public static final int MAX_PURSE = 6;

    private final String name;
    private int place;
    private int purse;
    private boolean inPenaltyBox;

    private boolean isGettingOutOfPenaltyBox;

    Player(String name, int place, int purse, boolean inPenaltyBox, boolean isGettingOutOfPenaltyBox) {
        this.name = name;
        this.place = place;
        this.purse = purse;
        this.inPenaltyBox = inPenaltyBox;
        this.isGettingOutOfPenaltyBox = isGettingOutOfPenaltyBox;
    }

    Player(String name) {
        this(name, 0, 0, false, false);
    }

    void move(int place) {
        this.place += place;
        if (this.place >= MAX_PLACE) {
            this.place -= MAX_PLACE;
        }
    }

    void addPoint() {
        this.purse++;
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

    public int purse() {
        return purse;
    }



    public boolean hasWon() {
        return this.purse >= MAX_PURSE;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void goToPenaltyBox() {
        this.inPenaltyBox = true;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(boolean gettingOutOfPenaltyBox) {
        isGettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
    }
}
