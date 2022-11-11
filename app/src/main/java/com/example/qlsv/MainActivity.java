package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;

import com.example.qlsv.activity.ListClassActivity;

import dialog_class.diaog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btntaomoi).setOnClickListener(this);
        findViewById(R.id.btndanhsach).setOnClickListener(this);
        findViewById(R.id.btnthoat).setOnClickListener(this);


    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btntaomoi:
                diaog diaog= new diaog(this);
                diaog.show();
                break;
            case R.id.btndanhsach:
                Intent intent = new Intent(this, ListClassActivity.class);
                startActivity(intent);
                break;
            case R.id.btnthoat:
                break;
        }

    }
}