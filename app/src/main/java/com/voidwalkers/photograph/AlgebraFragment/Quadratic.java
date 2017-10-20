package com.voidwalkers.photograph.AlgebraFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.voidwalkers.photograph.Latex;
import com.voidwalkers.photograph.R;
import com.voidwalkers.photograph.ScannerFragment.MarshmallowPermissions;

public class Quadratic extends AppCompatActivity {
    private static double p ;
    private static double q ;
    private static double w ;
    private static double s ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quadratic);

        GraphView myGraph = (GraphView) findViewById(R.id.outputGraph);
        TextView input = (TextView) findViewById(R.id.input_latex);

        input.setText(Latex.latexInput);

        myGraph.removeAllSeries();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();

//        double[] myArray = new double[4];
//
//        valofCoeffs.setText(" a = " + String.valueOf(myArray[0])+
//                        ", b = " + String.valueOf(myArray[1])+
//                        ", c = " + String.valueOf(myArray[2]) +
//                        ", d = " + String.valueOf(myArray[3])
//                );
//
//                double p = myArray[0];
//                double q = myArray[1];
//                double w = myArray[2];
//                double s = myArray[3];
//
//                Standard.setText("ax^2 + bx + c = d ");

        if (q * q >= 4 * p * (w - s)) {
            double x1 = Math.round(100 * (-1 * q + Math.sqrt(q * q - 4 * p * (w - s))) / (2 * p)) / 100;
            double x2 = Math.round(100 * (-1 * q - Math.sqrt(q * q - 4 * p * (w - s))) / (2 * p)) / 100;

//                    myAns.setText("root1 = " + String.valueOf(x1) + " root2 = " + String.valueOf(x2));
//
//                }else {
//                    myAns.setText("he equation has non-real solutions");
//                }

            Log.i("TAG23", "Appendedtoseries");
            myGraph.addSeries(series);
        }
    }
//        });
//
//    }
}
