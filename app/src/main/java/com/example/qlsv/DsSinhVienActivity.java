package com.example.qlsv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qlsv.adapter.SinhVienAdapter;
import com.example.qlsv.model.Classes;
import com.example.qlsv.model.student;
import com.example.qlsv.sqlite.DatabaseSQL;
import com.example.qlsv.sqlite.StudentDao;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DsSinhVienActivity extends AppCompatActivity {
    ListView listSv;
    List<student> arrList;
    SinhVienAdapter adapter;
    DatabaseSQL databaseSQL;
    Toolbar toolbar;
    String idclass;
    private EditText etStudentId, etName, etDob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_sinh_vien);
        toolbar = findViewById(R.id.tool);
        listSv = findViewById(R.id.svlist);
        etStudentId= findViewById(R.id.edtmasv);
        etName= findViewById(R.id.edttensinhvien);
        etDob= findViewById(R.id.edtdate);
        databaseSQL = new DatabaseSQL(DsSinhVienActivity.this,"student",null,1);
        databaseSQL.Database("CREATE TABLE IF NOT EXISTS student(id VARCHAR(10) primary key, name VARCHAR(150) not null, classid  VARCHAR(20), dob VARCHAR(30))");
        getData();
        getToolBar();
        arrList = new ArrayList<>();
        adapter = new SinhVienAdapter(DsSinhVienActivity.this,arrList);

        listSv.setAdapter(adapter);
        getStudent();
    }

    private void getStudent() {
            Cursor cursor = databaseSQL.GetDatabase("SELECT * FROM student WHERE classid = '"+idclass+"'");
            arrList.clear();
            while(cursor.moveToNext()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String dateb = cursor.getString(3);
                arrList.add(new student(id,name,dateb));
            }
        adapter.notifyDataSetChanged();
    }

    private void getData() {
        Intent intent = getIntent();
        Classes classes = (Classes) intent.getSerializableExtra("idclass");
        idclass = classes.getName();

        //Toast.makeText(this, classes.getName()+"", Toast.LENGTH_SHORT).show();
    }
    private void getToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách sinh viên");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        if(item.getItemId()==R.id.tbadd){
            Dialog dialog = new Dialog(DsSinhVienActivity.this);
            dialog.setContentView(R.layout.activity_manager_student);

            EditText masv = dialog.findViewById(R.id.edtmasv);
            EditText namesv = dialog.findViewById(R.id.edttensinhvien);
            EditText ngaysv = dialog.findViewById(R.id.edtdate);
            Button confirm = dialog.findViewById(R.id.btnsave);

            Button huy = dialog.findViewById(R.id.bdong);
            huy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String masv1 = masv.getText().toString().trim();
                    String namesv1 = namesv.getText().toString().trim();
                    String ngaysv1 = ngaysv.getText().toString().trim();

                    databaseSQL.Database("INSERT INTO student VALUES('"+masv1+"','"+namesv1+"','"+idclass+"','"+ngaysv1+"')");
                    Toast.makeText(DsSinhVienActivity.this, "Add student success", Toast.LENGTH_SHORT).show();
                    getStudent();
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
    public void Delete(long i){
        databaseSQL.Database("DELETE FROM student WHERE id = '"+i+"'");
        getStudent();
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    public void Update(long i,String name,String dated){
        Dialog dialog = new Dialog(DsSinhVienActivity.this);
        dialog.setContentView(R.layout.dialog_update);

        EditText namesv = dialog.findViewById(R.id.edttensinhvien);
        EditText ngaysv = dialog.findViewById(R.id.edtdate);
        Button confirm = dialog.findViewById(R.id.btnsave);

        namesv.setText(name);
        ngaysv.setText(dated);

        Button huy = dialog.findViewById(R.id.bdong);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namesv1 = namesv.getText().toString().trim();
                String ngaysv1 = ngaysv.getText().toString().trim();
                databaseSQL.Database("UPDATE student SET name = '"+namesv1+"' WHERE id = '"+i+"'");
                databaseSQL.Database("UPDATE student SET dob = '"+ngaysv1+"' WHERE id = '"+i+"'");
                Toast.makeText(DsSinhVienActivity.this, "Success", Toast.LENGTH_SHORT).show();
                getStudent();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}