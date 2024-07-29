package com.interview.designpatterns;

import java.io.Serializable;

public class Singleton   {
    Singleton obj = new Singleton();

    protected Object readResolve(){
        return obj;
    }

}
