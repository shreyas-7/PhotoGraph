package com.voidwalkers.photograph;

import java.util.HashMap;

public class GlobalMemory {
    public static HashMap<String,String> QuadraticData;
    public static HashMap<String,String> LinearData;
    static {
        QuadraticData.put("Vx{2}+Vx+V=V","quad1") ;
        QuadraticData.put("","quad2") ;
    }
}
