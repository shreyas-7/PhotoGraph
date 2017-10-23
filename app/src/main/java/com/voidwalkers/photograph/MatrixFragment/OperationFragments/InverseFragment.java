package com.voidwalkers.photograph.MatrixFragment.OperationFragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.voidwalkers.photograph.GlobalValues;
import com.voidwalkers.photograph.MatrixFragment.Matrix;
import com.voidwalkers.photograph.MatrixFragment.MatrixAdapter;
import com.voidwalkers.photograph.R;
import com.voidwalkers.photograph.MatrixFragment.base_classes.ShowResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class InverseFragment extends ListFragment {

    final String KEY = "DETERMINANT_FOR_INVERSE";
    boolean ENABLED_NO_DECIMAL;

    ArrayList<Matrix> SquareList;
    ProgressDialog progress;

    private static class MyHandler extends Handler{
        private final WeakReference<InverseFragment> weakReference;
        MyHandler(InverseFragment inverseFragment){
            weakReference = new WeakReference<>(inverseFragment);
        }
        @Override
        public  void handleMessage(Message message) {
            if (weakReference.get().progress.isShowing()) {
                Intent intent = new Intent(weakReference.get().getActivity(), ShowResult.class);
                if (message.getData().getFloat("DETERMINANT", 0) == 0) {
                    intent.putExtras(message.getData());
                    weakReference.get().progress.dismiss();
                    weakReference.get().startActivity(intent);
                } else {
                    intent.putExtras(message.getData());
                    intent.putExtra(weakReference.get().KEY,message.getData().getFloat("DETERMINANT",0));
                    weakReference.get().progress.dismiss();
                    weakReference.get().startActivity(intent);
                }

            }
        }
    }

    MyHandler myHandler = new MyHandler(this);

    @Override
    public void onActivityCreated(Bundle savedInstances) {
        super.onActivityCreated(savedInstances);
        SquareList=new ArrayList<>();
        for(int i = 0; i<((GlobalValues)getActivity().getApplication()).GetCompleteList().size(); i++)
        {
            if(((GlobalValues)getActivity().getApplication()).GetCompleteList().get(i).is_squareMatrix())
                SquareList.add(((GlobalValues)getActivity().getApplication()).GetCompleteList().get(i));
        }
        MatrixAdapter MatriXadapter = new MatrixAdapter(getActivity(), R.layout.list_layout_fragment,SquareList);
        getListView().setDividerHeight(1);
        setListAdapter(MatriXadapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        ENABLED_NO_DECIMAL = preferences.getBoolean("NO_FRACTION_ENABLED",false);

    }
    @Override
    public void onListItemClick(ListView L, View V, int position, long id)
    {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.Calculating));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progress = progressDialog;
        if(ENABLED_NO_DECIMAL)
            RunAndGetDeterminantWithAdjoint(position,progressDialog);
        else
            RunNewGetInverse(position, progressDialog);
    }

    public void RunNewGetInverse(final int pos,final ProgressDialog pq)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Matrix res = SquareList.get(pos).Inverse(pq);
                Message message = new Message();
                if(res!=null){
                    message.setData(res.GetDataBundled());
                    myHandler.sendMessage(message);
                }
                else{
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),R.string.NoInverse,Toast.LENGTH_SHORT).show();
                        }
                    },0);
                    pq.dismiss();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
    public void RunAndGetDeterminantWithAdjoint(final int i, final ProgressDialog progressDialog){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                Bundle bundle = new Bundle();
                float detr = (float) SquareList.get(i).GetDeterminant();
                if(detr == 0.0f){
                    myHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),R.string.NoInverse,Toast.LENGTH_SHORT).show();
                        }
                    },0);
                    progressDialog.dismiss();
                }
                else {
                    progressDialog.setProgress(0);
                    bundle.putFloat("DETERMINANT",detr);
                    Matrix res = SquareList.get(i).ReturnAdjoint(progressDialog);
                    bundle.putAll(res.GetDataBundled());
                    message.setData(bundle);
                    myHandler.sendMessage(message);
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
