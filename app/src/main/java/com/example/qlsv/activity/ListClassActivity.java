package com.example.qlsv.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qlsv.DsSinhVienActivity;
import com.example.qlsv.R;
import com.example.qlsv.adapter.ClassesAdapter;
import com.example.qlsv.model.Classes;
import com.example.qlsv.sqlite.ClassesDao;

import java.util.List;

public class ListClassActivity extends AppCompatActivity {
    private ListView lvClasses;
    private List<Classes> list;
    Toolbar toolbar;
    private ClassesAdapter clsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_class);
        lvClasses= findViewById(R.id.lvclasses);
        toolbar = findViewById(R.id.tool);
        getToolBar();
        fillclassesListView ();
        lvClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DsSinhVienActivity.class);
                Classes cls = list.get(i);
                intent.putExtra("idclass",cls);
                startActivity(intent);
            }
        });
        lvClasses.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClassesDao dao = new ClassesDao(ListClassActivity.this);
                Classes cls = list.get(i);
                dao.delete(""+cls.getId());
                fillclassesListView();
                return false;
            }
        });
    }

    private void getToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách lớp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void fillclassesListView() {
        ClassesDao dao = new ClassesDao(this);
        list= dao.getAll();
        clsAdapter= new ClassesAdapter(this, list);
        lvClasses.setAdapter(clsAdapter);

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
        return super.onOptionsItemSelected(item);
    }
}