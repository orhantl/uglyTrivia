package com.kata.playboard;

public enum QuestionTopic {

    POP("Pop"), SCIENCE("Science"), SPORTS("Sports"), ROCK("Rock");

    private final String value;

    QuestionTopic(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
