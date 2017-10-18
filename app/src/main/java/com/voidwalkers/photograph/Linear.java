package com.voidwalkers.photograph;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.*;
import java.lang.reflect.* ;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Linear {
    public static SharedPreferences linearPref ;
    public static Map linearMap ;
    public static String latexInput ;
    public static String eq1 ;
    public static String eq2 ;
    public static String originalEqn ;
    double [] Coeffs0 ;
    double [] Coeffs1 ;
    double [] Coeffs2 ;

    public static String[] transformLatex(String latexInput) {
        Log.v("TAG2","Transforming from Latex") ;
        String answer = latexInput.replaceAll(" ", "");
        answer = answer.replaceAll("x|X","Vx") ;

        answer = answer.replaceAll("y|Y","Vy") ;

        answer = answer.replaceAll("[0-9]+","V") ;
        answer = answer.replaceAll("[V]+","V") ;

        answer = answer.replaceAll("[-]+","+") ;

        String[] ans = answer.split(",") ;

        Log.v("TAG2","LATEX WAS "+ latexInput + " Transformed " + answer) ;

        return ans ;
    }


    public void solve(String latexInput) {
        String[] transformedLatex = Linear.transformLatex(latexInput) ;
        Linear.latexInput = latexInput ;
        Linear.eq1 = transformedLatex[0] ;
        Linear.eq2 = transformedLatex[1] ;
        String f1 = linearPref.getString(eq1,"N/A") ;
        String f2 = linearPref.getString(eq2,"N/A") ;
        String[] Equations = latexInput.split(",") ;

//        Log.e("TAG2","Solving") ;
//        Log.e("TAG2","functionid = " + functionID) ;
        Coeffs0 = new double[4] ;

        if (f1 != "N/A"){
            originalEqn = Equations[0] ;
                Linear.callbyName(f1) ;
                Coeffs1 = new double [4] ;
            for (int i = 0 ; i < 4 ; i++) Coeffs1[i] = Coeffs0[i] ;
        }
        else {
            addtoLinearMap(eq1);
        }
        if (f2 != "N/A"){
            originalEqn = Equations[1] ;
            Linear.callbyName(f2) ;
            Coeffs2 = new double [4] ;
            for (int i = 0 ; i < 4 ; i++) Coeffs2[i] = Coeffs0[i] ;
        }
        else {
            addtoLinearMap(eq2);
        }
    }

    public static void addtoLinearMap(String eqn) {

        int size = linearMap.size();
        Log.e("TAG2","Size of Map = " + Integer.toString(size)) ;
        SharedPreferences.Editor linearEditor = linearPref.edit() ;
        linearEditor.putString(eqn,"linear" + Integer.toString(size+1)) ;
        linearEditor.commit();
        Log.e("TAG2","New Size of Map = " + Integer.toString(linearMap.size())) ;
    }

    public static void callbyName(String funcId) {
        try {

            Method[] methods = Linear.class.getMethods();

            for(Method method : methods){
                if (method.getName().equals(funcId)) {
                    Log.e("TAG2", "CALLING BY NAME --> " + funcId) ;
                    method.invoke(new Object[] {});
                    Log.e("TAG2", "Called BY NAME --> " + funcId) ;
                }
            }
        }

        catch (InvocationTargetException e) {
            e.printStackTrace();
            Log.e("TAG2","InvocationTargetException") ;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e("TAG2","IllegalAccessException") ;
        }
    }


    public void SolveFinal () {
        // cout x and y
    }

    public void linear1 () {
        Log.e("TAG2","I am in linear1") ;
        Log.e("TAG2","LatexInput = " + Linear.latexInput) ;

        String[] Vars = new String[4] ;
        double[] Coeffs = new double[4] ;

        String pattern = "(.*)x(.*)y(.*)=(.*)";

        Log.e("TAG2", "LatexInput = " + Linear.originalEqn);

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(Linear.originalEqn);

        if (m.find()) {

            for (int i = 0; i < 4; i++) {
                Vars[i] = m.group(i + 1);
                Log.e("TAG2","Found value: " + Vars[i]);

            }
            if (Vars[0].equals("")) Vars[0] = "1";
            if (Vars[1].equals("+")) Vars[1] = "1";
            if (Vars[1].equals("-")) Vars[1] = "-1";
            if (Vars[2].equals("")) Vars[2] = "0";

            for (int i = 0; i < 4; i++) {
//                Vars[i] = m.group(i+1);
                Log.e("TAG2", "Found value: " + String.valueOf(Double.parseDouble(Vars[i])));
                Coeffs0[i] = Double.parseDouble(Vars[i]);
            }

            Log.e("TAG2","Adding") ;

        } else {
            Log.e("TAG2", "NOT FOUND");
        }

    }

    public void linear2 (String latexInput , String transformedLatexInput) {
        Log.e("TAG2","I am in linear2") ;

        Log.e("TAG2","LatexInput = " + Linear.latexInput) ;

        String[] Vars = new String[4] ;
        double[] Coeffs = new double[4] ;

        String pattern = "(.*)y(.*)x(.*)=(.*)";

        Log.e("TAG2", "LatexInput = " + Linear.originalEqn);

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(Linear.originalEqn);

        if (m.find()) {

            for (int i = 0; i < 4; i++) {
                Vars[i] = m.group(i + 1);
                Log.e("TAG2","Found value: " + Vars[i]);

            }
            if (Vars[0].equals("")) Vars[0] = "1";
            if (Vars[1].equals("+")) Vars[1] = "1";
            if (Vars[1].equals("-")) Vars[1] = "-1";
            if (Vars[2].equals("")) Vars[2] = "0";

//            for (int i = 0; i < 4; i++) {
////                Vars[i] = m.group(i+1);
//                Log.e("TAG2", "Found value: " + String.valueOf(Double.parseDouble(Vars[i])));
//                Coeffs0[i] = Double.parseDouble(Vars[i]);
//            }

            Coeffs0[0] = Double.parseDouble(Vars[1]);
            Coeffs0[1] = Double.parseDouble(Vars[0]);
            Coeffs0[2] = Double.parseDouble(Vars[2]);
            Coeffs0[3] = Double.parseDouble(Vars[3]);

            Log.e("TAG2","Adding") ;

        } else {
            Log.e("TAG2", "NOT FOUND");
        }

    }

}


