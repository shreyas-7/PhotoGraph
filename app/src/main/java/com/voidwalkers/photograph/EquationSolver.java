package com.voidwalkers.photograph;


public class EquationSolver {

    public static void solve(String latexInput) {
        if (latexInput.indexOf(',') == -1 ) {
            Quadratic.solve(latexInput) ;
        }
        //
    }
}
