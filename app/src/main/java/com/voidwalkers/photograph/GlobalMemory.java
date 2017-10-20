package com.voidwalkers.photograph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.voidwalkers.photograph.AlgebraFragment.Quadratic;
import java.util.HashMap;

public class GlobalMemory {

    public static HashMap<String,String> QuadraticData;
    public static HashMap<String,String> LinearData;

    static {
        QuadraticData = new HashMap<String, String>() ;
        QuadraticData.put("Vx^{V}+Vx+V=V","quad1") ;
        QuadraticData.put("V","quad2") ;
    }
}
