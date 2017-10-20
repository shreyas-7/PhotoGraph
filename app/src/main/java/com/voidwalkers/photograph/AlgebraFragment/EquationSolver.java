package com.voidwalkers.photograph.AlgebraFragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.voidwalkers.photograph.Latex;

public class EquationSolver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.Solve();

        super.onCreate(savedInstanceState);

    }

    private void Solve(){
        String latexInput = Latex.latexInput ;
        if (latexInput.indexOf(',') == -1 ) {
            Log.v("TAG2","CALLED Quadratic Solver") ;

            Intent i = new Intent(this, Quadratic.class) ;
            startActivity(i) ;

            Log.v("TAG2","SOLVED Quadratic Solver") ;

        }
    }

}
