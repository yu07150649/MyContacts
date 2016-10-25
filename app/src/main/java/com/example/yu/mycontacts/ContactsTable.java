package com.example.yu.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yu on 16.10.24.
 */

public class ContactsTable {
    //数据库表名常量
    private final static String TABLENAME="contactsTable";
    //声明数据库对象
    private MyDB db;
    //构造方法
    public ContactsTable(Context context){
        //创建MyDB对象
        db = new MyDB(context);
        //判断数据表存在
        if(!db.isTableExits(TABLENAME)){
            String createTableSql = "CREATE TABLE IF NOT EXISTS "+ TABLENAME +"(id_DB integer "+" primary key AUTOINCREMENT,"
                    +User.NAME +" VARCHAR,"
                    +User.MOBILE +" VARCHAR,"
                    +User.QQ +" VARCHAR,"
                    +User.DANWEI +" VARCHAR,"
                    +User.ADDRESS +" VARCHAR)";
            db.createTable(createTableSql);

        }
    }
    //添加数据
    public boolean addData(User user){
        //创建ContentValues对象用于保存数据
        ContentValues values = new ContentValues();
        //contentvalue 赋值
        values.put(User.NAME,user.getName());
        values.put(User.MOBILE,user.getMoblie());
        values.put(User.QQ,user.getQq());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.ADDRESS,user.getAddress());
        //保存数据
        return db.save(TABLENAME,values);
    }
}
