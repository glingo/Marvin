package com.marvin.component.event;

public class TestEvent extends Event {
    
    private String toSay;

    public TestEvent() {
    }

    public TestEvent(String toSay) {
        this.toSay = toSay;
    }

    public String getToSay() {
        return toSay;
    }

    public void setToSay(String toSay) {
        this.toSay = toSay;
    }
}
