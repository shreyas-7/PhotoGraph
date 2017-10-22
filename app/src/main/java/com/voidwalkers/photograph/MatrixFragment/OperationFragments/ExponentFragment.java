package com.voidwalkers.photograph.MatrixFragment.OperationFragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.voidwalkers.photograph.MatrixFragment.base_classes.ExponentSetter;
import com.voidwalkers.photograph.GlobalValues;
import com.voidwalkers.photograph.MatrixFragment.Matrix;
import com.voidwalkers.photograph.MatrixFragment.MatrixAdapter;
import com.voidwalkers.photograph.R;
import com.voidwalkers.photograph.MatrixFragment.base_classes.ShowResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ExponentFragment extends ListFragment {

    int Clicked_pos;
    ArrayList<Matrix> SquareList;

    private static class MyHandler extends Handler{
        private final WeakReference<ExponentFragment> exponentFragmentWeakReference;
        private MyHandler(ExponentFragment fragment){
            exponentFragmentWeakReference = new WeakReference<>(fragment);
        }
        @Override
        public void handleMessage(Message msg) {
            ExponentFragment exponentfrag = exponentFragmentWeakReference.get();
            Intent intent = new Intent(exponentfrag.getActivity(),ShowResult.class);
            intent.putExtras(msg.getData());
            exponentfrag.startActivity(intent);
        }

    }

    MyHandler handler = new MyHandler(this);
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

    }
    @Override
    public void onListItemClick(ListView L, View V, int position, long id)
    {
        Clicked_pos = position;
        Intent intent = new Intent(getActivity(),ExponentSetter.class);
        startActivityForResult(intent,500);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==500)
        {
            ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(getString(R.string.Calculating));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            RunAndGetResult(Clicked_pos,data.getIntExtra("QWERTYUIOP",0),progressDialog);
        }
    }
    public void RunAndGetResult(final int pos, final int exponent, final ProgressDialog progressDialog)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Matrix res = new Matrix(((GlobalValues)getActivity().getApplication()).GetCompleteList().get(pos).GetRow()
                        ,((GlobalValues)getActivity().getApplication()).GetCompleteList().get(pos).GetCol()
                        ,((GlobalValues)getActivity().getApplication()).GetCompleteList().get(pos).GetType());
                res.CloneFrom(((GlobalValues)getActivity().getApplication()).GetCompleteList().get(pos));
                res.Raiseto(exponent);
                progressDialog.dismiss();
                Message message = new Message();
                message.setData(res.GetDataBundled());
                handler.sendMessage(message);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
