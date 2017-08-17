package com.example.dell.finalfirstproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Friendlist extends AppCompatActivity {
    ListView list;
    List<String> name;
TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        name = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FriendsDatabase dop=new FriendsDatabase(this);
        lo l=dop.getInformation(dop);
        Cursor CR=l.getC();
        int sucsess=l.getS();
        if(sucsess==0)
        {
           t= (TextView) findViewById(R.id.textView);
            t.setText("Sorry No Contatc in your List add contact");
        }
        else {
            int i = 0;
            CR.moveToFirst();
            do {
                name.add(CR.getString(0));


                i++;
            } while (CR.moveToNext());
            CustomListAdapter adapter = new CustomListAdapter(this, name);

            Log.d("number of friends ", String.valueOf(i));
            list = (ListView) findViewById(R.id.lv);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    String Slecteditem = name.get(+position);
                    Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                }
            });
            registerForContextMenu(list);
        }
        Button b = (Button) findViewById(R.id.buttonadd);
        if (b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Friendlist.this, addfriend.class);
                    startActivity(intent);
                }
            });
        }


    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(name.get(info.position));
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.menu);
        String menuItemName = menuItems[menuItemIndex];
        String listItemName = name.get(info.position);


//        text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
        Toast.makeText(getApplicationContext(),menuItemName,Toast.LENGTH_LONG).show();
         return true;
    }
}
