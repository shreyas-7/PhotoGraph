package com.voidwalkers.photograph.MatrixFragment.base_classes ;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.voidwalkers.photograph.GlobalValues;
import com.voidwalkers.photograph.MatrixFragment.base_fragments.EditFragment;
import com.voidwalkers.photograph.R;
import com.voidwalkers.photograph.MatrixFragment.base_fragments.ViewMatrixFragment;

public class ViewCreatedMatrix extends AppCompatActivity {

    Menu ActionMenu;
    final int ChangedOrder=12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_created_matrix);

        int index =getIntent().getIntExtra("INDEX",-1);


        if(getSupportActionBar()!=null && index != (-1))
            getSupportActionBar().setTitle(((GlobalValues) getApplication()).GetCompleteList().get(index).GetName());
        ViewMatrixFragment viewMatrixFragment = new ViewMatrixFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX",getIntent().getIntExtra("INDEX",-1));
        viewMatrixFragment.setArguments(bundle);
        if(savedInstanceState==null){
            FragmentTransaction  transaction= getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.FragmentContainer,viewMatrixFragment,"VIEW_TAG").commit();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.onclick_view,menu);
        ActionMenu=menu;
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        EditFragment editFragment=new EditFragment();
        int index =getIntent().getIntExtra("INDEX",-1);
        switch (item.getItemId())
        {
//            case R.id.EditEntered:
//
//                Bundle bundle = new Bundle();
//                bundle.putInt("INDEX",getIntent().getIntExtra("INDEX",-1));
//                editFragment.setArguments(bundle);
//                FragmentTransaction FragmentTR=getSupportFragmentManager().beginTransaction();
//                FragmentTR.replace(R.id.FragmentContainer,editFragment,"FRAGMENTEDIT");
//                FragmentTR.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                FragmentTR.commit();
//                ((GlobalValues)getApplication()).current_editing = ((GlobalValues)getApplication()).
//                        GetCompleteList().get(getIntent().getIntExtra("INDEX",-1));
//                ActionMenu.findItem(R.id.EditEntered).setVisible(false);
//                ActionMenu.findItem(R.id.OrderChange).setVisible(false);
//                ActionMenu.findItem(R.id.DeleteCreated).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
//                ActionMenu.findItem(R.id.DeleteCreated).setTitle(R.string.Delete2);
//                ActionMenu.findItem(R.id.ConfirmChanges).setVisible(true);
//                ActionMenu.findItem(R.id.RevertChanges).setVisible(true);
//                return  true;
//            case R.id.DeleteCreated :
//                if(index!=(-1))
//                {
//                    ((GlobalValues)getApplication()).GetCompleteList().remove(index);
//                    ((GlobalValues)getApplication()).matrixAdapter.notifyDataSetChanged();
//                Toast.makeText(getApplicationContext(),R.string.Deleted,Toast.LENGTH_SHORT).show();
//                finish();
//                }
//                return true;
//            case R.id.OrderChange :
//                Intent intent4=new Intent(getApplicationContext(),OrderChanger.class);
//                intent4.putExtra("INDEX_OF_SELECTED_MATRIX",index);
//                startActivityForResult(intent4,ChangedOrder);
//                return true;
//            case R.id.Rename :
//                Intent intent =new Intent(getApplicationContext(),RenameCreated.class);
//                intent.putExtra("TITLE_OF_THIS_FORMATION",((GlobalValues)getApplication()).GetCompleteList().get(index).GetName());
//                startActivityForResult(intent,100);
//                return  true;
            case R.id.RevertChanges:
                EditFragment editFragment1 = ((EditFragment) getSupportFragmentManager().findFragmentByTag("FRAGMENTEDIT"));
                editFragment1.RevertChanges();
                Toast.makeText(getApplicationContext(),R.string.RevertSuccess,Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case R.id.ConfirmChanges:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.detach(editFragment);
                transaction.commit();
                finish();
                Toast.makeText(getApplicationContext(),R.string.ConfirmSuccess,Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestcode,int resultCode,Intent data)
    {
        super.onActivityResult(requestcode,resultCode,data);
        if(resultCode==100)
        {
            int index =getIntent().getExtras().getInt("INDEX",-1);
            if(index!=(-1))
            {
                ((GlobalValues)getApplication()).GetCompleteList().get(index).
                        SetName(data.getStringExtra("NEW_NAME_FOR_THIS_MATRIX"));
                ((GlobalValues)getApplication()).matrixAdapter.notifyDataSetChanged();
                finish();
            }


        }
        if(resultCode==ChangedOrder)
        {
            Toast.makeText(getApplication(),R.string.ChangedOrder,Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
