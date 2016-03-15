package pranav.app.com.acadproj;

import java.util.jar.Attributes;

/**
 * Created by Pranav on 15-03-2016.
 */
public class Contact {

    private String phoneNumber;
    private String contactName;
    public long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString()
    {
        return "this is a contact";
    }


    public Contact(String contactName, String phoneNumber, long id) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName()
   {
       return contactName;
   }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
