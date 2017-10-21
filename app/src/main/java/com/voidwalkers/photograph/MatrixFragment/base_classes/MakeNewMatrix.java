package com.voidwalkers.photograph.MatrixFragment.base_classes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.voidwalkers.photograph.MatrixFragment.Type;
import com.voidwalkers.photograph.MatrixFragment.base_classes.FillingMatrix;
import com.voidwalkers.photograph.GlobalValues;
import com.voidwalkers.photograph.R;

public class MakeNewMatrix extends AppCompatActivity {
    final int RESCODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_matrix);
        Button button = (Button) findViewById(R.id.buttonMake);
        final EditText editText = (EditText) findViewById(R.id.MatrixName);
        editText.setSingleLine();
        final Spinner Typespinner = (Spinner) findViewById(R.id.MatType);
        final NumberPicker RowSpinner = (NumberPicker) findViewById(R.id.RowOrder);
        RowSpinner.setMinValue(1);
        RowSpinner.setMaxValue(9);
        RowSpinner.setValue(3);
        final NumberPicker ColSpinner = (NumberPicker) findViewById(R.id.ColOrder);
        ColSpinner.setMinValue(1);
        ColSpinner.setMaxValue(9);
        ColSpinner.setValue(3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (NoError()) {
                    Type t = TypeFromString(Typespinner.getSelectedItem().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("TYPE",t);
                    bundle.putString("NAME",editText.getText().toString());
                    bundle.putInt("ROW",RowSpinner.getValue());
                    bundle.putInt("COL",ColSpinner.getValue());
                    Intent intent;
                    intent = new Intent(getApplication(), FillingMatrix.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,RESCODE);

                }

            }

            private boolean NoError() {
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplication(), R.string.Warning1, Toast.LENGTH_LONG).show();
                    return false;
                }
                if ((Typespinner.getSelectedItem().toString().equals("Identity") ||
                        Typespinner.getSelectedItem().toString().equals("Diagonal")) &&
                        (RowSpinner.getValue() != ColSpinner.getValue())) {
                    Toast.makeText(getApplicationContext(), R.string.NotSquare, Toast.LENGTH_LONG).show();
                    return false;
                }
                if(editText.getText().toString().contains("+")||
                        editText.getText().toString().contains("x")||editText.getText().toString().contains("-")){
                    Toast.makeText(getApplicationContext(),R.string.Warning13,Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;

            }


        });



    }
    protected void onActivityResult(int requestcode,int resultCode,Intent data)
    {
        super.onActivityResult(resultCode,resultCode,data);
        if(resultCode==RESCODE)
        {
            setResult(1,data);
            finish();
        }

    }
    public Type TypeFromString (String s)
    {
        switch (s)
        {
            case "Normal" :   return Type.Normal;
            case "Null" :     return Type.Null;
            case "Diagonal" : return Type.Diagonal;
            case "Identity" : return Type.Identity;
        }
        return null;
    }
}

