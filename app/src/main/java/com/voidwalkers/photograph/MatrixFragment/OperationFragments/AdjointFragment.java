package com.voidwalkers.photograph.MatrixFragment.OperationFragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.voidwalkers.photograph.GlobalValues;
import com.voidwalkers.photograph.MatrixFragment.Matrix;
import com.voidwalkers.photograph.MatrixFragment.MatrixAdapter;
import com.voidwalkers.photograph.R;
import com.voidwalkers.photograph.MatrixFragment.base_classes.ShowResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AdjointFragment extends ListFragment {

    private ProgressDialog progress;

    private static class MyHandler extends Handler{
        private final WeakReference<AdjointFragment> weakReference;
        MyHandler(AdjointFragment ap){
            weakReference = new WeakReference<>(ap);
        }
        @Override
        public void handleMessage(Message msg){
            if(weakReference.get().progress.isShowing()) {
                Intent intent = new Intent(weakReference.get().getActivity(), ShowResult.class);
                intent.putExtras(msg.getData());
                weakReference.get().progress.dismiss();
                weakReference.get().startActivity(intent);
            }
        }
    }

    MyHandler myhandler = new MyHandler(this);

    ArrayList<Matrix> SquareList;
    @Override
    public void onActivityCreated(Bundle savedInstances) {
        super.onActivityCreated(savedInstances);
        SquareList=new ArrayList<>();
        for(int i = 0; i<((GlobalValues)getActivity().getApplication()).GetCompleteList().size(); i++)
        {
            if(((GlobalValues)getActivity().getApplication()).GetCompleteList().get(i).is_squareMatrix())
                //if square matrix is present them only ajoint can be found hence only square matrix being selected
                SquareList.add(((GlobalValues)getActivity().getApplication()).GetCompleteList().get(i));
        }
        MatrixAdapter MatriXadapter = new MatrixAdapter(getActivity(), R.layout.list_layout_fragment,SquareList);
        getListView().setDividerHeight(1);
        setListAdapter(MatriXadapter);

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
        RunToGetDeterminant(position,progressDialog);
    }
    public void RunToGetDeterminant(final int pos, final ProgressDialog px)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bundle bundle =SquareList.get(pos).ReturnAdjoint(px).GetDataBundled();
                Message message = new Message();
                message.setData(bundle);
                progress = px;
                myhandler.sendMessage(message);

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
