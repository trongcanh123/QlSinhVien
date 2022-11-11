package dialog_class;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.qlsv.R;
import com.example.qlsv.model.Classes;
import com.example.qlsv.sqlite.ClassesDao;

public class diaog extends Dialog implements View.OnClickListener {
    private  Context context;
    private EditText etclassid, etname;
    public diaog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        etclassid= findViewById(R.id.edtmalop);
        etname= findViewById(R.id.edttenlop);
        findViewById(R.id.btluu).setOnClickListener(this);
        findViewById(R.id.btdong).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btluu:
                Classes cls = new Classes();
                cls.setName(etname.getText().toString());
                ClassesDao dao = new ClassesDao(context);
                dao.insert(cls);
                Toast.makeText(context, "Lớp đã được lưu!", Toast.LENGTH_SHORT).show();
                dismiss();


                break;
            case R.id.btdong:
                dismiss();
                break;

        }
    }
}
