package com.voidwalkers.photograph.AlgebraFragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.voidwalkers.photograph.GlobalMemory;
import com.voidwalkers.photograph.GlobalValues;
import com.voidwalkers.photograph.Latex;
import com.voidwalkers.photograph.MatrixFragment.Matrix;
import com.voidwalkers.photograph.MatrixFragment.MatrixMain;
import com.voidwalkers.photograph.MatrixFragment.Type;
import com.voidwalkers.photograph.R;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquationSolver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();
        Bundle b = i.getExtras() ;

        super.onCreate(savedInstanceState);

        if(Latex.latexInput.contains("array")) {
            Log.e("TaG2","Matrix called") ;
            this.SolveMatrix();
        }
    else {
            this.Solve();
        }
        setContentView(R.layout.activity_matrix_main);
        this.finish();
    }

    private double RemoveFrac(String a, String b){

//        String pattern = "\\\\frac\\{(.*)\\}\\{(.*)\\}" ;
//
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(frac);
//
//        if (m.find()){
//            if (!(m.group(2) == "0"))
//            return Double.parseDouble(m.group(1)) / Double.parseDouble(m.group(2)) ;
//
// }
        return Math.round(Double.parseDouble(a)/Double.parseDouble(b)) ;
        // denominator zero error is to be taken care of.
    }

    private double RemoveSlash(String a,String b){
        return Math.round(Double.parseDouble(a)/Double.parseDouble(b)) ;
    }

    private void SolveMatrix(){

//        String latexInput = Latex.latexInput ;
//
//        latexInput = latexInput.replaceAll(" ", "");
//
//        String pattern = "\\\\begin\\{array\\}\\{(.*)\\}";

//            int rows =  StringUtils.countMatches(latexInput, "\\\\\\\\") ;

//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(latexInput);
//
//        int begin = latexInput.indexOf("\\{", 21);
//        int end = latexInput.indexOf("\\\\end") - 1;
//
//        latexInput = latexInput.substring(begin, end);
//        latexInput = latexInput.replaceAll("\\\\frac\\{(.*)\\}\\{(.*)\\}", Double.toString(RemoveFrac("$1", "$2")));
//        latexInput = latexInput.replaceAll("\\{(.*)/(.*)\\}", Double.toString(RemoveSlash("$1", "$2")));
//
//        String[] rows = latexInput.split("\\\\\\\\");

        // have to modify some things

//            Matrix input = new Matrix(3, 3, Type.Normal);
//
//            float[][] variable = new float[3][3];
//
//            for (int i = 0; i < input.GetRow(); i++) {
//                for (int j = 0; j < input.GetCol(); j++) {
//                    input.SetElementof(1, i, j);
//                    variable[i][j] =  i+j ;
//                }
//            }
//
//            Bundle bundle=new Bundle();
//            bundle.putAll(getIntent().getExtras());
//            bundle.putSerializable("VALUES",variable);
//            bundle.putDoubleArray("VALUES",new Matrix().Compress(variable,3,3));
//            Intent intnt=new Intent();
//            intnt.putExtras(bundle);
//            setResult(0,intnt);
//            finish();
//            return;

        Matrix matrix = new Matrix(3,3,Type.Normal) ;
        ((GlobalValues) getApplication()).AddResultToGlobal(matrix);

        Intent i = new Intent(this, MatrixMain.class) ;
        startActivity(i);
    }


    private void Solve(){
        String latexInput = Latex.latexInput ;


        if (latexInput.indexOf(',') == -1 ) {

            // So a comma is not found in the string, it is a quadratic
            // very vague right now

            Log.v("TAG2","CALLED Quadratic Solver") ;

            Intent i = new Intent(this, Quadratic.class) ;
            startActivity(i) ;

            Log.v("TAG2","SOLVED Quadratic Solver") ;

        }
    }

}
