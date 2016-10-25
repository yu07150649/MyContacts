package com.example.yu.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yu on 16.10.24.
 */

public class MyDB extends SQLiteOpenHelper {
    private static String DB_NAME="My_DB.db";
    private static int DB_VERSION=2;
    private SQLiteDatabase db;
    public MyDB(Context context){
        super(context, DB_NAME,null,DB_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //打开数据库
    public SQLiteDatabase openConnection(){
        if(!db.isOpen()){
            db=getWritableDatabase();
        }
        return db;
    }
    //关闭数据库
    public void closeConnection(){
        try{
            if(db!=null&&db.isOpen()){
                db.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //创建数据库
    public boolean createTable(String createTableSql){
            try{
                openConnection();
                db.execSQL(createTableSql);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }finally {
                closeConnection();
            }
        return true;
    }
    //保存数据
    public boolean save(String tableName, ContentValues values){
        try{
            openConnection();
            db.insert(tableName,null,values);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //更新数据
    public boolean update(String table,ContentValues values,String whereClause,String [] whereArgs){
        try{
            openConnection();
            db.update(table,values,whereClause,whereArgs);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //删除数据
    public boolean delete(String table,String deleteSql,String obj[]){
        try{
            openConnection();
            db.delete(table,deleteSql,obj);
        }catch (Exception e ){
            e.printStackTrace();
            return  false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //查询数据
    public Cursor find(String findSql,String obj[]){
        try {
            openConnection();
            Cursor cursor = db.rawQuery(findSql,obj);
            return cursor;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //数据表存在
    public boolean isTableExits(String tableName){
        try{
            openConnection();
            String str = "select count(*) from "+tableName;
            db.rawQuery(str,null).close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
}
