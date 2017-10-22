package com.voidwalkers.photograph.MatrixFragment.OperationFragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.voidwalkers.photograph.MatrixFragment.Matrix;
import com.voidwalkers.photograph.MatrixFragment.MatrixAdapter;
import com.voidwalkers.photograph.R;
import com.voidwalkers.photograph.GlobalValues;

import java.lang.ref.WeakReference;

public class RankFragment extends ListFragment {



    private static class MyHandler extends Handler{
        private WeakReference<RankFragment> weakReference;
        private MyHandler(RankFragment rankFragment){
            weakReference = new WeakReference<>(rankFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            final int rank = msg.getData().getInt("CALCULATED_RANK",-1);
            final String mes = "Rank of Matrix is : "+String.valueOf(rank);

            final AlertDialog.Builder builder = new AlertDialog.Builder(weakReference.get().getContext());
            builder.setPositiveButton(R.string.copy, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ClipboardManager clipboardManager = (ClipboardManager) weakReference.get().getActivity()
                            .getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("RANK_RES",String.valueOf(rank));
                    clipboardManager.setPrimaryClip(clipData);
                    if(clipboardManager.hasPrimaryClip()){
                        Toast.makeText(weakReference.get().getContext(),R.string.CopyToClip,Toast.LENGTH_SHORT).show();
                    }
                    else
                        Log.d("ClipData","Failed to set to Clip board");
                    dialogInterface.dismiss();
                }
            });

            builder.setNeutralButton(R.string.Done, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setMessage(mes);
            builder.setTitle(R.string.RankofMat);
            builder.setCancelable(false);
            builder.show();
        }
    }

    MyHandler handler = new MyHandler(this);

    @Override
    public void onActivityCreated(Bundle saved){
        super.onActivityCreated(saved);
        MatrixAdapter adapter = new MatrixAdapter(getContext(),R.layout.list_layout_fragment,((GlobalValues)getActivity().getApplication()).GetCompleteList());
        getListView().setDividerHeight(1);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FindRank(position);
    }

    private void FindRank(final int pos){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Matrix mat = ((GlobalValues)getActivity().getApplication()).GetCompleteList().get(pos);
                int rank = mat.GetRank();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt("CALCULATED_RANK",rank);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
