package com.voidwalkers.photograph.MatrixFragment.base_classes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.voidwalkers.photograph.MatrixFragment.Type;
import com.voidwalkers.photograph.GlobalValues;
import com.voidwalkers.photograph.R;
import com.voidwalkers.photograph.ScannerFragment.CameraActivity;

public class MakeNewMatrix extends AppCompatActivity {
    final int RESCODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_matrix);
        Button button = (Button) findViewById(R.id.buttonMake);
        final EditText editText = (EditText) findViewById(R.id.MatrixName);
        editText.setSingleLine();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.v("TAG2", "Clicked");
                Type type = TypeFromString("Normal");
                Bundle bundle = new Bundle();
                bundle.putSerializable("TYPE", type);
                bundle.putString("NAME", editText.getText().toString());

                // calling cameraActivity from SacnnerFragment
                Intent intent = new Intent(getApplication(), CameraActivity.class);
                intent.putExtras(bundle);

                Log.v("TAG2", "StartedActivity");
                startActivityForResult(intent, RESCODE);
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

