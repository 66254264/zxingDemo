package com.google.zxing;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.encode.EncodeActivity;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //拍照权限
        if (Build.VERSION.SDK_INT >= 23) {
            //如果用户并没有同意该权限
            if (checkSelfPermission(Manifest.permission.CAMERA) != PERMISSION_GRANTED) {
                //申请权限
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        }
        final EditText content=findViewById(R.id.et_content);
        Button ewmGenerate=findViewById(R.id.qr_generate);
        ewmGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,EncodeActivity.class);
                intent.setAction(Intents.Encode.ACTION);
                intent.putExtra(Intents.Encode.TYPE,Contents.Type.TEXT);
                intent.putExtra(Intents.Encode.DATA,content.getText().toString());
                startActivity(intent);
            }
        });
        Button button=findViewById(R.id.scan_qr);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,CaptureActivity.class);
                startActivity(intent);
            }
        });
    }
}
