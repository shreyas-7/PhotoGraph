package com.voidwalkers.photograph;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.*;
import java.lang.reflect.* ;

public class Linear {
    public static SharedPreferences linearPref ;
    public static Map linearMap ;
    public static String latexInput ;
    public static String transformedLatex ;

    public static void solve(String latexInput) {
        String transformedLatex = EquationSolver.transformLatex(latexInput) ;
        Linear.latexInput = latexInput ;
        Linear.transformedLatex = transformedLatex ;
        String functionID = linearPref.getString(transformedLatex,"N/A") ;

//        Log.e("TAG2","Solving") ;
        Log.e("TAG2","functionid = " + functionID) ;

        if (functionID != "N/A"){

            Linear.callbyName(functionID,latexInput,transformedLatex) ;
//            Quadratic.stringtofunctionID() ;
        }
        else {
            addtoLinearMap(transformedLatex);
        }

    }

    public static void addtoLinearMap(String transformedLatexInput) {

        int size = linearMap.size();
        Log.e("TAG2","Size of Map = " + Integer.toString(size)) ;
        SharedPreferences.Editor linearEditor = linearPref.edit() ;
        linearEditor.putString(transformedLatexInput,"quadratic" + Integer.toString(size+1)) ;
        linearEditor.commit();
        Log.e("TAG2","New Size of Map = " + Integer.toString(linearMap.size())) ;
    }

    public static void callbyName(String funcName, String latexInput, String transformedLatex) {
        try {

            Method[] methods = Linear.class.getMethods();

            for(Method method : methods){
                if (method.getName().equals(funcName)) {
                    Log.e("TAG2", "CALLING BY NAME --> " + funcName) ;
                    method.invoke(new Object[] {});
                    Log.e("TAG2", "Called BY NAME --> " + funcName) ;
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

    public static void linear1 () {
        Log.e("TAG2","I am in linear1") ;
        Log.e("TAG2","LatexInput = " + Linear.latexInput) ;
        Log.e("TAG2","TransformedLatex = "+ Linear.transformedLatex) ;



    }

    public static void linear2 (String latexInput , String transformedLatexInput) {
        Log.e("TAG2","I am in linear2") ;
    }

}


