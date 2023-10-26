package com.kata.playboard;

public enum  QuestionTopic {

    POP("Pop"), SCIENCE("Science"), SPORTS("Sports"), ROCK("Rock");

    private final String value;

    QuestionTopic(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static QuestionTopic getTopicFromPosition(int position) {
        return switch (position) {
            case 0, 4, 8 -> POP;
            case 1, 5, 9 -> SCIENCE;
            case 2, 6, 10 -> SPORTS;
            default -> ROCK;
        };
    }
}
