package com.voidwalkers.photograph.MatrixFragment.base_fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.voidwalkers.photograph.GlobalValues;
import com.voidwalkers.photograph.MatrixFragment.base_classes.ViewCreatedMatrix;
import com.voidwalkers.photograph.MatrixFragment.MatrixAdapter;
import com.voidwalkers.photograph.R;

public class MatrixMainFragmentList extends ListFragment {

    int position1 = -1;

    @Override
    public void onActivityCreated(Bundle savedInstances) {
        super.onActivityCreated(savedInstances);

        ((GlobalValues) getActivity().getApplication()).matrixAdapter = new MatrixAdapter(getActivity(), R.layout.list_layout_fragment,
                ((GlobalValues) getActivity().getApplication()).GetCompleteList());
        getListView().setDividerHeight(0);
        setListAdapter(((GlobalValues) getActivity().getApplication()).matrixAdapter);

        registerForContextMenu(getListView());

    }

    @Override
    public void onListItemClick(ListView L, View V, int position, long id) { //Last Index from Global is 1 more that the position provided

        Intent intent = new Intent(getActivity().getApplication(), ViewCreatedMatrix.class);
        intent.putExtra("INDEX", position);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.quick_main_action, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.QuickDelete:
                ((GlobalValues) getActivity().getApplication()).GetCompleteList().remove(info.position);
                ((GlobalValues) getActivity().getApplication()).matrixAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), R.string.Deleted, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestcode, int resultCode, Intent data) {
        super.onActivityResult(requestcode, resultCode, data);
//        if (resultCode == 100) {
//            ((GlobalValues) getActivity().getApplication()).GetCompleteList().get(position1).
//                    SetName(data.getStringExtra("NEW_NAME_FOR_THIS_MATRIX"));
        ((GlobalValues) getActivity().getApplication()).matrixAdapter.notifyDataSetChanged();

//        }
//        if(resultCode==12)
//            Toast.makeText(getContext(),R.string.ChangedOrder,Toast.LENGTH_SHORT).show();
//    }
    }
}
