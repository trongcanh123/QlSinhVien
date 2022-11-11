package com.example.qlsv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qlsv.DsSinhVienActivity;
import com.example.qlsv.R;
import com.example.qlsv.model.Classes;
import com.example.qlsv.model.student;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private DsSinhVienActivity context;
    private List<student> list;

    public SinhVienAdapter(DsSinhVienActivity context, List<student> list) {
            this.context = context;
            this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();

    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null ) {
            view = LayoutInflater.from(context).inflate(R.layout.item_student, null);
        }
        TextView tvId= view.findViewById(R.id.id);
        TextView tvName= view.findViewById(R.id.name);
        TextView tvDate= view.findViewById(R.id.dateb);
        ImageButton edit = view.findViewById(R.id.edit);
        ImageButton delete = view.findViewById(R.id.delete);
        student cls = list .get(i);
        tvId.setText(cls.getId());
        tvName.setText(cls.getName());
        tvDate.setText(cls.getDob());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student student = list.get(i);
                context.Delete(Long.parseLong(student.getId()));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student student = list.get(i);
                context.Update(Long.parseLong(student.getId()),student.getName(),student.getDob());
            }
        });
        return view ;
    }
}
