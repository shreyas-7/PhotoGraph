
package com.voidwalkers.photograph.MatrixFragment;


import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.voidwalkers.photograph.R;
import com.voidwalkers.photograph.ScannerFragment.CameraActivity;


public class MatrixMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_main);

        Button AddMatrix = (Button) findViewById(R.id.add_matrix) ;
        Button ClearAllVariables = (Button) findViewById(R.id.clear_all_variables) ;
        Button Go = (Button) findViewById(R.id.perform_operations) ;
        ListView MatrixList = (ListView) findViewById(R.id.list_of_variables)  ;

        AddMatrix.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), CameraActivity.class) ;
                        startActivity(i);
                    }
                }
        );

        ClearAllVariables.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //
                    }
                }
        );

        Go.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            //
                    }
                }
        );
    }
}
