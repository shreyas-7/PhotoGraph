package com.voidwalkers.photograph;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.voidwalkers.photograph.AlgebraFragment.Quadratic;
import com.voidwalkers.photograph.MatrixFragment.Matrix;
import com.voidwalkers.photograph.MatrixFragment.MatrixAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalMemory {

    public static HashMap<String, String> QuadraticData;
    public static HashMap<String, String> LinearData;

    static {
        QuadraticData = new HashMap<String, String>();
        QuadraticData.put("Vx^{V}+Vx+V=V", "quad1");
        QuadraticData.put("V", "quad2");
    }
}