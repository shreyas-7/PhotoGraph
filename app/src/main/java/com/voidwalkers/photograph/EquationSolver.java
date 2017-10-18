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
        else if (latexInput.contains(",")) {
            Log.v("TAG2" , "CALLED LINEAR Solver") ;
            Linear lin = new Linear() ;
            lin.solve(latexInput) ;
            Log.v("TAG2", "Solved Linear") ;
        }
        //
    }


}
