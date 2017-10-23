package com.voidwalkers.photograph;

import android.app.Application;

import com.voidwalkers.photograph.MatrixFragment.Matrix;
import com.voidwalkers.photograph.MatrixFragment.MatrixAdapter ;
import java.util.ArrayList;

public class GlobalValues extends Application {

    private ArrayList<Matrix> createdValues = new ArrayList<>();
    public ArrayList<Matrix> MatrixQueue = new ArrayList<>();

    public  Matrix current_editing = null;
    public boolean AdLoaded = false;

    private int LAST_INDEX = 0; //LastIndex of ArrayList

    public int AutoSaved = 1; //To automatically name the saved result

    public MatrixAdapter matrixAdapter;

    private boolean Status=false;

    public boolean Promotion=false;

    public boolean ThisSession = true;

    private String TAG = this.getClass().getSimpleName();

    public void AddToGlobal(Matrix mk)
    {
        createdValues.add(mk);
        if(matrixAdapter!=null)
            matrixAdapter.notifyDataSetChanged();
        LAST_INDEX++;
    }

    public void AddResultToGlobal(Matrix mk) {
        AddToGlobal(mk);
        AutoSaved++;
    }

    public ArrayList<Matrix> GetCompleteList() {

        return createdValues;
    }

    public void ClearAllMatrix() {
        createdValues.clear();
        matrixAdapter.notifyDataSetChanged();
        LAST_INDEX = 0;
    }

    public boolean CanCreateVariable(){
            return true;
    }
}
