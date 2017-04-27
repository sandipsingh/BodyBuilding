package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.PersonDetails;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "f_name";
    private static final String KEY_LNAME = "l_name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_DOB = "dob";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_WSIGN = "w_sign";
    private static final String KEY_HIEGHT = "hieght";
    private static final String KEY_HSIGN = "h_sign";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance  
    }

    // Creating Tables  
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_FNAME + " TEXT,"
                + KEY_LNAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_DOB + " TEXT,"
                + KEY_PH_NO + " TEXT,"
                + KEY_WEIGHT + " TEXT,"
                + KEY_WSIGN + " TEXT,"
                + KEY_HIEGHT + " TEXT," +
                KEY_HSIGN + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database  
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed  
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again  
        onCreate(db);
    }

    // code to add the new contact
    public void addContact(PersonDetails personDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, personDetails.getF_name()); // Person f_name
        values.put(KEY_LNAME, personDetails.getL_name()); // Person l_name
        values.put(KEY_EMAIL, personDetails.getEmail()); // Contact email
        values.put(KEY_DOB, personDetails.getDob()); // Person dob
        values.put(KEY_PH_NO, personDetails.getMob_no()); // Person mob_number
        values.put(KEY_WEIGHT, personDetails.getWeight()); // Person weight
        values.put(KEY_WSIGN, personDetails.getW_sign()); // Person w_sign
        values.put(KEY_HIEGHT, personDetails.getHieght()); // Person hieght
        values.put(KEY_HSIGN, personDetails.getH_sign()); // Person h_sign
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack  
        db.close(); // Closing database connection  
    }

    // code to get the single contact  
    PersonDetails getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{
                        KEY_ID,
                        KEY_FNAME,
                        KEY_LNAME,
                        KEY_EMAIL,
                        KEY_DOB,
                        KEY_PH_NO,
                        KEY_WEIGHT,
                        KEY_WSIGN,
                        KEY_HIEGHT,
                        KEY_HSIGN}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        PersonDetails contact = new PersonDetails(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)
                , cursor.getString(7), cursor.getString(8), cursor.getString(9));
        // return contact
        return contact;
    }

    // code to get all contacts in a list view  
    // code to get all contacts in a list view
    public List<PersonDetails> getAllContacts() {
        List<PersonDetails> contactList = new ArrayList<PersonDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PersonDetails contact = new PersonDetails();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setF_name(cursor.getString(1));
                contact.setL_name(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setDob(cursor.getString(4));
                contact.setMob_no(cursor.getString(5));
                contact.setWeight(cursor.getString(6));
                contact.setW_sign(cursor.getString(7));
                contact.setHieght(cursor.getString(8));
                contact.setHieght(cursor.getString(9));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact  
    public int updateContact(PersonDetails contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.getId());
        values.put(KEY_FNAME, contact.getF_name());
        values.put(KEY_LNAME, contact.getL_name());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_DOB, contact.getDob());
        values.put(KEY_PH_NO, contact.getMob_no());
        values.put(KEY_WEIGHT, contact.getWeight());
        values.put(KEY_WSIGN, contact.getW_sign());
        values.put(KEY_HIEGHT, contact.getHieght());
        values.put(KEY_HSIGN,  contact.getHieght());
//        return db.update(TABLE_CONTACTS, values, KEY_ID + "="+contact.getId() , null);

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});

      /*  String update = "UPDATE " + TABLE_CONTACTS + " set"
                + KEY_FNAME = contact.getF_name() + ","
                + KEY_LNAME = contact.getL_name() + ","
                + KEY_EMAIL = contact.getEmail() + ","
                + KEY_DOB = contact.getDob() + ","
                + KEY_PH_NO = contact.getMob_no() + ","
                + KEY_WEIGHT = contact.getWeight() + ","
                + KEY_WSIGN = contact.getW_sign() + ","
                + KEY_HIEGHT = contact.getHieght() + ","
                + KEY_HSIGN = contact.getH_sign()+" where q_id= " + contact.getId();

db.execSQL(update);*/
        /*String update ="UPDATE `www_posts` SET `post_name`='1555'," +
                "`guid`='https://www.lynkpal.com/forums/reply/1555'," +
                "`post_modified`='21-12-2016 18:03:55',`post_modified_gmt`='21-12-2016 18:03:55' " +
                "where `ID`='1555'*/
    }


}  