package com.interview.designpatterns;

public class CommandPatternExample {

    public static void main(String[] args) {


        Command onCommand = new TurnOnCommand(new Television());

        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(onCommand);

        remoteControl.pressButton(); // Outputs: TV is now on
    }
}
