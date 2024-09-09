package hi.do_an_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Them_tu_vung_Activity extends AppCompatActivity {
    ListView lvThemTuVung;
    ArrayList<Tu_vung> tuVungArrayList;
    Tu_vung_Adapter adapter;
    Button btnThemTuVung;

    MySQLiteOpenHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tu_vung);


        addControl();
        tuVungArrayList = new ArrayList<>();

        tuVungArrayList.add(new Tu_vung("",""));
        tuVungArrayList.add(new Tu_vung("",""));
        tuVungArrayList.add(new Tu_vung("",""));

        database = new MySQLiteOpenHelper(this);
        adapter = new Tu_vung_Adapter(this, R.layout.item_listview_them_tu_vung, tuVungArrayList);
        lvThemTuVung.setAdapter(adapter);

        addEvent();
    }

    private void addControl(){
        lvThemTuVung = findViewById(R.id.lvThemTuVung);
        btnThemTuVung = findViewById(R.id.btnThemTuVung);
    }

    private void addEvent(){
        btnThemTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luuTuVungVaoDB();
            }
        });
    }

    private void luuTuVungVaoDB(){
        for (int i = 0; i<lvThemTuVung.getChildCount(); i++){
            View listItem = lvThemTuVung.getChildAt(i);
            if(listItem !=null){
                Tu_vung tu_vung = tuVungArrayList.get(i);
                String tuVung =((EditText) listItem.findViewById(R.id.edtThuatNgu)).getText().toString().trim();
                String nghiaTuVung = ((EditText) listItem.findViewById(R.id.edtDinhNghia)).getText().toString().trim();

                // Cập nhật từ vựng và nghĩa từ vựng trong đối tượng tuVung
                tu_vung.setTuVung(tuVung);
                tu_vung.setNghiaTuVung(nghiaTuVung);

                // Thêm vào cơ sở dữ liệu
                database.themTuVung(tu_vung);
                database.themThongTinVaoBangNguoiDungTuVung();

            }
        }
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Đã lưu dữ liệu vào cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
    }
}