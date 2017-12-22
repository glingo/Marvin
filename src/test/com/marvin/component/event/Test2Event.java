package com.marvin.component.event;

public class Test2Event extends Event {
    
    private String toSay;

    public Test2Event() {
    }

    public Test2Event(String toSay) {
        this.toSay = toSay;
    }

    public String getToSay() {
        return toSay;
    }

    public void setToSay(String toSay) {
        this.toSay = toSay;
    }
}
