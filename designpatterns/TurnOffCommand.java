package com.interview.designpatterns;

public class TurnOffCommand implements Command {
    private Television tv;

    @Override
    public void execute() {
        tv.turnOff();
    }

}
