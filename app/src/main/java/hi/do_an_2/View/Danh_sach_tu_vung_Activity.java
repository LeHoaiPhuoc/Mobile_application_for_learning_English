package hi.do_an_2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hi.do_an_2.Model.Danh_sach_tu_vung;
import hi.do_an_2.Controller.Danh_sach_tu_vung_Adapter;
import hi.do_an_2.Model.MySQLite_open_helper;
import hi.do_an_2.R;
import hi.do_an_2.Model.Tu_vung;

public class Danh_sach_tu_vung_Activity extends AppCompatActivity {
    ListView lvLuaChonHocTuVung;
    ArrayList<Danh_sach_tu_vung> danhSachTuVungArrayList;
    Danh_sach_tu_vung_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tu_vung);

        addControl();

        danhSachTuVungArrayList = new ArrayList<>();

        // Tạo đối tượng MySQLiteOpenHelper
        MySQLite_open_helper dbHelper = new MySQLite_open_helper(this);

        // Lấy danh sách từ vựng và định nghĩa từ tên đăng nhập
        List<Tu_vung> tuVungList = dbHelper.layThuatNguVaDinhNghiaTuTenDangNhap(this);

        // Chuyển danh sách từ vựng và định nghĩa thành danh sách đối tượng Danh_sach_tu_vung
        for (Tu_vung tuVung : tuVungList) {
            danhSachTuVungArrayList.add(new Danh_sach_tu_vung(tuVung.getTuVung(), tuVung.getNghiaTuVung()));
        }

        // Tạo adapter và gán cho ListView
        adapter = new Danh_sach_tu_vung_Adapter(this, R.layout.item_listview_danh_sach_tu_vung_trong_them_tu_vung, danhSachTuVungArrayList);
        lvLuaChonHocTuVung.setAdapter(adapter);
    }
    private void addControl(){
        lvLuaChonHocTuVung = findViewById(R.id.lvLuaChonHocTuVung);
    }
}