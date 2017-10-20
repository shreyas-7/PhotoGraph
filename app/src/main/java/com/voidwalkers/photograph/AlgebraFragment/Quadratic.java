package com.voidwalkers.photograph.AlgebraFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.voidwalkers.photograph.GlobalMemory;
import com.voidwalkers.photograph.Latex;
import com.voidwalkers.photograph.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quadratic extends AppCompatActivity {
    private static double a ;
    private static double b ;
    private static double c ;
    private static double d ;
    public ListView steps ;
    public ArrayList<String> listItems=new ArrayList<String>();
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quadratic);

        GraphView myGraph = (GraphView) findViewById(R.id.outputGraph);
        TextView input = (TextView) findViewById(R.id.input_latex);
        steps = (ListView) findViewById(R.id.quadratic_steps) ;

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);

        steps.setAdapter(adapter);

        input.setText(Latex.latexInput);

        myGraph.removeAllSeries();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();

        Latex.QuadTransform = Quadratic.parse(Latex.latexInput) ;

        if (GlobalMemory.QuadraticData.containsKey(Latex.QuadTransform)) {
            // execute the function corresponding to the entry
            this.callByName(GlobalMemory.QuadraticData.get(Latex.QuadTransform));


            if (b * b >= 4 * a * (c - d)) {
                double x1 = Math.round(100 * (-1 * b + Math.sqrt(b * b - 4 * a * (c - d))) / (2 * a)) / 100;
                double x2 = Math.round(100 * (-1 * b - Math.sqrt(b * b - 4 * a * (c - d))) / (2 * a)) / 100;

                Log.i("TAG23", "Appendedtoseries");
                myGraph.addSeries(series);
            }

        }
        else {
            a = 1 ;
            b = 2;
            c = 1 ;
            d = 0 ;
            // just make graph.
            // the grapher should be able to graph unknown templates
        }
    }

    public void addItems(View v, String step) {
        listItems.add(step);
        adapter.notifyDataSetChanged();
    }


    public void callByName(String funcName) {
            try {
                Method method = getClass().getDeclaredMethod(funcName);
                method.invoke(this, new Object[] {});
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    public static String parse(String latexInput) {
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

    public void quad1(){
        // ax^2 + bx + c = d ;

        String latexInput = Latex.latexInput ;
        String transformedLatex = Latex.QuadTransform ;

        Log.e("TAG2", "I am in quadratic1");
        Log.e("TAG2", "LatexInput = " + latexInput);

        String[] Vars = new String[4];

        latexInput = latexInput.replaceAll(" ", "");
        latexInput = latexInput.replaceAll("\\.", "");

        String pattern = "(.*)x\\^\\{.*\\}(.*)x(.*)=(.*)";

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
                Log.e("TAG2", "Found value: " + String.valueOf(Double.parseDouble(Vars[i])));
            }

            a = Double.parseDouble(Vars[0]) ;
            b = Double.parseDouble(Vars[1]) ;
            c = Double.parseDouble(Vars[2]) ;
            d = Double.parseDouble(Vars[3]) ;

            Log.e("TAG2","Adding") ;

            String step1 = "This is the Standard form!" ;
            String step2 = "ax^2 + bx + c = d" ;
            String step3 = "where a = " + String.valueOf(a) ;
            String step4 = "where b = " + String.valueOf(b) ;
            String step5 = "where c = " + String.valueOf(c) ;
            String step6 = "where d = " + String.valueOf(d) ;

            this.addItems(this.findViewById(R.id.quadratic_steps),step1);
            this.addItems(this.findViewById(R.id.quadratic_steps),step2);
            this.addItems(this.findViewById(R.id.quadratic_steps),step3);
            this.addItems(this.findViewById(R.id.quadratic_steps),step4);
            this.addItems(this.findViewById(R.id.quadratic_steps),step5);
            this.addItems(this.findViewById(R.id.quadratic_steps),step6);

        } else {
            Log.e("TAG2", "NOT FOUND");
        }

    }
}
