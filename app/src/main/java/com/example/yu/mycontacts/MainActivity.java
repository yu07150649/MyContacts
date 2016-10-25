package com.example.yu.mycontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //声明界面控件属性
    private EditText nameEdit;
    private EditText mobileEdit;
    private EditText qqEdit;
    private EditText danweiEdit;
    private EditText addressEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("添加联系人");
        //获取界面控件实例
        nameEdit = (EditText) findViewById(R.id.name);
        mobileEdit = (EditText) findViewById(R.id.moblie);
        qqEdit = (EditText) findViewById(R.id.qq);
        danweiEdit = (EditText) findViewById(R.id.danwei);
        addressEdit = (EditText) findViewById(R.id.address);
    }

    @Override
    //选项菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"保存");
        menu.add(Menu.NONE,2,Menu.NONE,"返回");
        return super.onCreateOptionsMenu(menu);
    }
    //选项菜单事件

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1 :
                if(!nameEdit.getText().toString().equals("")){
                    User user = new User();
                    //user对象赋值
                    user.setName(nameEdit.getText().toString());
                    Log.d("",nameEdit.getText().toString());
                    user.setMoblie(mobileEdit.getText().toString());
                    user.setDanwei(danweiEdit.getText().toString());
                    user.setQq(qqEdit.getText().toString());
                    user.setAddress(addressEdit.getText().toString());
                    //创建数据表对象
                    ContactsTable ct = new ContactsTable(MainActivity.this);
                    if(ct.addData(user)){
                        Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"请先输入数据",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
