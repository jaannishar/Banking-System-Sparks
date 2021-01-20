package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(7004362148,'Nishar',9472.00,'nishar7786@gmail.com','XXXXXXXXXXXX5566','BKID10110005955')");
        db.execSQL("insert into user_table values(9570848395,'Sonu',582.67,'sonu123@gmail.com','XXXXXXXXXXXX2341','BKID10110005955')");
        db.execSQL("insert into user_table values(9955121355,'Aman',1359.56,'aman9955@gmail.com','XXXXXXXXXXXX3412','BARB0DBHAMI')");
        db.execSQL("insert into user_table values(9708563788,'Babbar',1500.01,'lovebabbar01@gmail.com','XXXXXXXXXXXX4123','BARB0DBHAMI')");
        db.execSQL("insert into user_table values(9798578700,'Rattan Tata',2603.48,'rattan97987@gmail.com','XXXXXXXXXXXX2345','BKID10110005955')");
        db.execSQL("insert into user_table values(8789495533,'Bill',945.16,'bill2000@gmail.com','XXXXXXXXXXXX3452','BARB0INDBAI')");
        db.execSQL("insert into user_table values(7870589786,'Anushka',5936.00,'anushka7870@gmail.com','XXXXXXXXXXXX4523','BKID10110005955')");
        db.execSQL("insert into user_table values(9122588786,'Saif Ali',857.22,'saif799@gmail.com','XXXXXXXXXXXX5234','BARB0INDBAI')");
        db.execSQL("insert into user_table values(8789496634,'Pallab',4398.46,'pallabdaa007@gmail.com','XXXXXXXXXXXX3456','BKID10110005955')");
        db.execSQL("insert into user_table values(7004357846,'Deepak',273.90,'deepak121@gmail.com','XXXXXXXXXXXX4563','BARB0INDBAI')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}