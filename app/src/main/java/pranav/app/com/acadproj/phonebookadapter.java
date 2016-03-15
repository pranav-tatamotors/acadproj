package pranav.app.com.acadproj;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pranav on 14-03-2016.
 */
public class phonebookadapter extends ArrayAdapter<Contact> {

    //VARS FOR ARRAYADAPTER BASE CLASS
    private LayoutInflater mInflater;
    private int mViewResourceId;


    //DERIVED CLASS VARIABLES
    private Contact[] Contacts;

    public phonebookadapter(Context c,int resourceId,Contact[] contactList)
    {
        super(c,resourceId,contactList);
        Contacts =contactList;


        mInflater = (LayoutInflater)c.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        mViewResourceId = resourceId;
    }

    @Override
    public int getCount() {
        return Contacts.length;
    }

    @Override
    public Contact getItem(int position) {
        return Contacts[position];
    }

    @Override
    public long getItemId(int position) {
        return Contacts[position].id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(mViewResourceId, null);

        TextView cname = (TextView)convertView.findViewById(R.id.contactName);
        cname.setText(Contacts[position].getContactName());

        TextView cnumber = (TextView)convertView.findViewById(R.id.phoneNumber);
        cnumber.setText(Contacts[position].getPhoneNumber());

        return convertView;

    }
}
