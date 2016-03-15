package pranav.app.com.acadproj;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView L;
    public static final int GroupID = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        L = (ListView)findViewById(R.id.listView);

       //GETTING LIST OF NAMES AND PHONE NUMBERS
        String[] Names = getApplicationContext().getResources().getStringArray(R.array.nameList);
        String [] PhoneNumbers = getApplicationContext().getResources().getStringArray(R.array.phNumbers);

       //CONVERT THE STRINGS TO LIST OF CONTACT OBJECTS
        //ArrayList<Contact> Clist = new ArrayList<Contact>();

        Contact[] CList = null;
        if (Names.length>0)
        CList = new Contact[Names.length];

        // INITIALIZE ARRAY AND COMMENT
       if(CList!=null) {
           int i = 0;

          for(i=0;i<Names.length;i++)
          {
              CList[i] = new Contact(Names[i],PhoneNumbers[i],i);
          }

           Toast.makeText(MainActivity.this, Names[0], Toast.LENGTH_SHORT).show();
           Context c = getApplicationContext();

           //Convert Back to array and Set Constructor
           L.setAdapter(new phonebookadapter(c,R.layout.list_item,CList));

       }


        //
        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "Long Press to get Context Menu", Toast.LENGTH_SHORT).show();
        }
    });

        //REGISTER LIST VIEW FOR CONTEXT MENU
        registerForContextMenu(L);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.listView) {
            ListView lv = (ListView) v;

            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;

            //Retrieve Contact Object from List View Item
            Contact contact = (Contact) lv.getItemAtPosition(acmi.position);

            //menu.add returns MenuItem for which the intent Can be set
            //Set Title for Menu items
            menu.setHeaderTitle(contact.getContactName());
            MenuItem i = menu.add(GroupID, v.getId(), 0, "Call " + contact.getPhoneNumber());//groupId, itemId, order, title
            MenuItem j = menu.add(GroupID, v.getId(), 0, "SMS " + contact.getPhoneNumber());

            try {
                     j.setIntent(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contact.getPhoneNumber(), null)));
                     i.setIntent(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contact.getPhoneNumber(), null)));
                }
            catch(Exception e)
                {
                      //e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }

    }

    public boolean onContextItemSelected(MenuItem item){
        //Check if item is from ListView
        if(item.getItemId()== R.id.listView) {
        //If so simply get the intent and start the activity.
            startActivity(item.getIntent());
        }

        return true;
    }

}