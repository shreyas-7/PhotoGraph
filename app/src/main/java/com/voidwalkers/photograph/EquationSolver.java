package com.voidwalkers.photograph;
import android.util.Log ;

public class EquationSolver {

    public static void solve(String latexInput) {
//        Log.v("TAG2", "Equation Solver Called") ;

        if (latexInput.indexOf("sum") != -1) {
            Summation sum = new Summation() ;
            sum.solve(latexInput) ;
        }
        else if (latexInput.indexOf(',') == -1 ) {
            Log.v("TAG2","CALLED Quadratic Solver") ;
            Quadratic quad = new Quadratic() ;
            quad.solve(latexInput) ;
            Log.v("TAG2","SOLVED Quadratic Solver") ;

        }
        //
    }

    public static String transformLatex(String latexInput) {
        Log.v("TAG2","Transforming from Latex") ;
        String answer = latexInput.replaceAll(" ", "");
        answer = answer.replaceAll("x|X","Vx") ;

        answer = answer.replaceAll("y|Y","Vy") ;

        answer = answer.replaceAll("[0-9]+","V") ;
        answer = answer.replaceAll("[V]+","V") ;

        answer = answer.replaceAll("[-]+","+") ;

        Log.v("TAG2","LATEX WAS "+ latexInput + " Transformed " + answer) ;

        return answer ;

    }

}
