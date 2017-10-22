package com.voidwalkers.photograph.MatrixFragment.base_classes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.voidwalkers.photograph.R;


public class ExponentSetter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exponent_setter);

        //References
        Button Confirm = (Button) findViewById(R.id.ConfirmSetFill);
        Button Cancel = (Button) findViewById(R.id.CancelSetFill);
        final EditText editText = (EditText) findViewById(R.id.ExponentSetter);

        //OnClickListener
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), R.string.NoValue, Toast.LENGTH_SHORT).show();
                else {
                    if (Integer.parseInt(editText.getText().toString())>15)
                        Toast.makeText(getApplicationContext(), R.string.ExpoOverflow, Toast.LENGTH_LONG).show();
                    else {
                        Intent intent = new Intent();
                        intent.putExtra("QWERTYUIOP", Integer.parseInt(editText.getText().toString()));
                        setResult(500, intent);
                        finish();
                    }
                }

            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
