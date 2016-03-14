package com.liu.easydialog_master;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.liu.easydialog.DialogView;
import com.liu.easydialog.EasyDialog;
import com.liu.easydialog.MenuDialogView;
import com.liu.easydialog.listener.OnMenuClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private Button menuDialogShow;
    private Button customerDialogShow;

    private EasyDialog easyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_menu_dialog:
                showMenuDialog();
                break;
            case R.id.btn_customer_dialog:
                showCustomerDialog();
                break;

        }
    }

    private void showMenuDialog(){
        List<String> menu = new ArrayList<String>();
        menu.add("版本更新");
        menu.add("反馈");
        menu.add("退出");

        MenuDialogView menuDialogView = new MenuDialogView();


        OnMenuClickListener onMenuClickListener = new OnMenuClickListener() {
            @Override
            public void onClick(int position, String menuItem) {
                Log.d("TAG", "NNNN=====>position:" + position + "====>menuItem:" + menuItem);
            }
        };

        easyDialog = EasyDialog.newBuilder(this)
                .setDialogView(menuDialogView)
                .setMenuNames(menu)
                .setOnMenuClickListener(onMenuClickListener)
                //.setShowCanCel(false)         //设置是否显示取消按钮，默认显示
                //.setCancelText("我要取消")     //设置取消按钮文字
                //.setMenuTextSize(22)          //设置菜单文字大小
                //.setMenuTextColor(Color.WHITE)    //设置菜单文字颜色
                //.setMenuBackground(Color.BLUE)    //设置菜单背景颜色
                //.setCancelBackground(Color.RED)   //设置取消按钮背景颜色
                .create();

        easyDialog.show();
    }

    private void showCustomerDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.customer_dialog, null);

        DialogView dialogView = new DialogView(view);
        easyDialog = EasyDialog.newBuilder(this)
                .setDialogView(dialogView).create();
        easyDialog.show();


        ImageView imv1 = (ImageView) view.findViewById(R.id.imv1);
        ImageView imv2 = (ImageView) view.findViewById(R.id.imv2);
        ImageView imv3 = (ImageView) view.findViewById(R.id.imv3);
        ImageView imv4 = (ImageView) view.findViewById(R.id.imv4);
        imv1.setOnClickListener(setDismissClickListener);
        imv2.setOnClickListener(setDismissClickListener);
        imv3.setOnClickListener(setDismissClickListener);
        imv4.setOnClickListener(setDismissClickListener);

    }

    View.OnClickListener setDismissClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(null != easyDialog){
                easyDialog.dismiss();
            }
        }
    };

    private void init(){
        initToolBar();
        menuDialogShow = (Button) findViewById(R.id.btn_menu_dialog);
        customerDialogShow = (Button) findViewById(R.id.btn_customer_dialog);
        menuDialogShow.setOnClickListener(this);
        customerDialogShow.setOnClickListener(this);
    }



    private void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }


}
