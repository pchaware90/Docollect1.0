package com.example.webmail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by User on 6/3/2016.
 */
public class LoginDBAdapter {
    static final String DATABASE_NAME = "user.db";
    static final int DATABASE_VERSION =1;
    public static final int NAME_COLUMN = 1;

    static final String DATABASE_CREATE = "Create table " +"USER"+
             "(" + "ID" + " integer primary key autoincrement," + "FULLNAME text," +"EMAIL text," +"PASSWORD text," +")";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDBAdapter(Context _context)
    {
        //this.context = context1;
        context =_context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME,null,DATABASE_VERSION);

    }

    public LoginDBAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){db.close();}

    public SQLiteDatabase getDatabaseInstance(){return db;}
    public void insertEntry(String fullname, String email, String password)
    {
        ContentValues newRow = new ContentValues();
        newRow.put("FULLNAME",fullname);
        newRow.put("EMAIL",email);
        newRow.put("PASSWORD",password);
        db.insert("USER", null, newRow);
        Toast.makeText(context,"Successfully registerd", Toast.LENGTH_LONG).show();

    }

    public int deleteEntry(String email)
    {
        String where ="EMAIL=?";
        int numberOFEntriesDeleted=db.delete("USER",where,new String[]{email});
        Toast.makeText(context, "Entry Deleted Successfully: "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getLoginDetails(String username)
    {
        Cursor cursor=db.query("USER",null," EMAIL=?", new String[]{username},null,null,null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            Toast.makeText(context, "Please register : ", Toast.LENGTH_LONG).show();
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;


    }


   public UserPOJO getUserDetails(String email)
   {
       Cursor cursor =db.query("USER" , null, "EMAIL=?", new String[]{email},null,null,null);
       if(cursor.getCount()<1)
       {
           cursor.close();
           Toast.makeText(context, "Please register : ", Toast.LENGTH_LONG).show();

       }
       cursor.moveToFirst();
       String _password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
       String _firstname = cursor.getString(cursor.getColumnIndex("FULLNAME"));
       String _phone = cursor.getString(cursor.getColumnIndex("PHONE"));

       UserPOJO userDetails = new UserPOJO(_firstname,email,_password);
       cursor.close();
       return userDetails;
   }

    public void updateEntry(String userName, String password)
    {
        ContentValues updatedValues =new ContentValues();
        updatedValues.put("EMAIL",userName);
        updatedValues.put("PASSWORD",password);

        String where ="EMAIL = ?";
        db.update("USER",updatedValues,where,new String[]{userName});

    }





}
