package com.voidwalkers.photograph;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;
import java.lang.reflect.* ;

import android.widget.TextView ;

import butterknife.BindView;

public class Quadratic{

    @BindView(R.id.outputGraph)
    GraphView myGraph;
    @BindView(R.id.standardForm)
    TextView Standardform;
    @BindView(R.id.valofCoeffs)
    TextView valofCoeffs;
    @BindView(R.id.Finalans)
    TextView myAns;




    public static SharedPreferences quadPref;
    public static Map quadraticMap;
    public static String latexInput;
    public static String transformedLatex;
    public static double[] Coeffs = new double[4];


    public void solve(String latexInput) {
        String transformedLatex = EquationSolver.transformLatex(latexInput);
        Quadratic.latexInput = latexInput;
        Quadratic.transformedLatex = transformedLatex;
        String functionID = quadPref.getString(transformedLatex, "N/A");

//        Log.e("TAG2","Solving") ;
        Log.e("TAG2", "functionid = " + functionID);

        if (functionID != "N/A") {

            callbyName(functionID, latexInput, transformedLatex);
//            Quadratic.stringtofunctionID() ;
        } else {
            addToQuadraticMap(transformedLatex);
        }

//        int size = quadraticMap.size() + 1;
//        Log.e("TAG2","Size of Map = " + Integer.toString(size)) ;

    }

    public void addToQuadraticMap(String transformedLatexInput) {

        int size = quadraticMap.size();
        Log.e("TAG2", "Size of Map = " + Integer.toString(size));
        SharedPreferences.Editor quadraticEditor = quadPref.edit();
        quadraticEditor.putString(transformedLatexInput, "quadratic" + Integer.toString(size + 1));
        quadraticEditor.commit();
        Log.e("TAG2", "New Size of Map = " + Integer.toString(quadraticMap.size()));
    }

    public void callbyName(String funcName, String latexInput, String transformedLatex) {
        try {

            Method[] methods = Quadratic.class.getMethods();

            for (Method method : methods) {
                if (method.getName().equals(funcName)) {
                    Log.e("TAG2", "CALLING BY NAME --> " + funcName);
                    method.invoke(this);
                    Log.e("TAG2", "Called BY NAME --> " + funcName);
                }
            }

//            Method method = Quadratic.class.getDeclaredMethod(funcName);
//
//            if (method.equals(funcName)){
//                Log.e("TAG2", "CALLING BY NAME --> " + funcName) ;
//                    method.invoke(new Object[] {});
//                    Log.e("TAG2", "Called BY NAME --> " + funcName) ;
//                }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Log.e("TAG2", "InvocationTargetException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e("TAG2", "IllegalAccessException");
        }
    }


    public void quadratic1() {
        Log.e("TAG2", "I am in quadratic1");
        Log.e("TAG2", "LatexInput = " + latexInput);


        String[] Vars = new String[4];

        latexInput = latexInput.replaceAll(" ", "");
        latexInput = latexInput.replaceAll("\\.", "");

//        String pattern = "(.*)(\\d+)(.*)";
        String pattern = "(.*)x\\^\\{.*\\}(.*)x(.*)=(.*)";

        Log.e("TAG2", "LatexInput = " + latexInput);
        Log.e("TAG2", "TransformedLatex = " + transformedLatex);


        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(latexInput);

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
//                Viars[i] = m.group(i+1);
                Log.e("TAG2", "Found value: " + String.valueOf(Double.parseDouble(Vars[i])));
                Coeffs[i] = Double.parseDouble(Vars[i]);
            }





            Log.e("TAG2","Adding") ;
//            sampletextview.getText();



        } else {
            Log.e("TAG2", "NOT FOUND");
        }

//        Quadratic quad = new Quadratic() ;
//
//        quad.graph();
    }

    public static LineGraphSeries<DataPoint> getSeries(){
        for (int i = 0 ; i < 4 ; i++) Log.e("TAG23",Double.toString(Coeffs[i])) ;

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>() ;

        double a = -20.0, b;
        for (int i = 0; i < 400; i++) {
            b = Coeffs[0] * a * a + Coeffs[1] * a + Coeffs[2] - Coeffs[3];
            a = a + 0.1;
            series.appendData(new DataPoint(a, b), true, 400);
        }
        return series ;
    }

    public static double[] getarray(){
        return Coeffs;
    }

    public static String gettransformedlatex(){
        return transformedLatex;
    }

//
//    public void graph() {
////        GraphView graph = (GraphView) findViewById(R.id.outputGraph);
//        Log.e("TAG2","Graphing") ;
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>() ;
//        double a = -5.0, b;
//        for (int i = 0; i < 500; i++) {
//            b = Coeffs[0] * a * a + Coeffs[1] * a + Coeffs[2] - Coeffs[3];
//            a = a + 0.1;
//            series.appendData(new DataPoint(a, b), true, 500);
//        }
//        Log.e("TAG2","Adding") ;
//        sampletextview.setText("HEY");
//        Log.e("TAG2","AddingtoSeries") ;
////        myGraph.addSeries(series);
//    }
////        Quadratic.graph();

    public void quadratic2() {
        Log.e("TAG2", "I am in quadratic2");
        Log.e("TAG2", "LatexInput = " + latexInput);

        String[] Vars = new String[4];

        latexInput = latexInput.replaceAll(" ", "");
        latexInput = latexInput.replaceAll("\\.", "");

//        String pattern = "(.*)x\\^\\{.*\\}(.*)x(.*)=(.*)";
        String pattern = "\\frac{(.*)x}" ;

        Log.e("TAG2", "LatexInput = " + latexInput);
        Log.e("TAG2", "TransformedLatex = " + transformedLatex);

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(latexInput);

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
//                Viars[i] = m.group(i+1);
                Log.e("TAG2", "Found value: " + String.valueOf(Double.parseDouble(Vars[i])));
                Coeffs[i] = Double.parseDouble(Vars[i]);
            }

            Log.e("TAG2","Adding") ;

        } else {
            Log.e("TAG2", "NOT FOUND");
        }

//        Quadratic quad = new Quadratic() ;
//
//        quad.graph();
    }



}







