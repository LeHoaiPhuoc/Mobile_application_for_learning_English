package hi.do_an_2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import hi.do_an_2.Controller.Hinh_thuc_hoc_Adapter;
import hi.do_an_2.Model.Hinh_thuc_hoc;
import hi.do_an_2.R;

public class Hinh_thuc_hoc_Activity extends AppCompatActivity {
    ListView lvHinhThucHoc;
    ArrayList<Hinh_thuc_hoc> hinhThucHocArrayList;
    Hinh_thuc_hoc_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_tu_vung);

        addControl();
        hinhThucHocArrayList = new ArrayList<>();
        hinhThucHocArrayList.add(new Hinh_thuc_hoc("Xem danh sách từ vựng", R.drawable.hinh_anh_trang_hoc_tu_vung));
        hinhThucHocArrayList.add(new Hinh_thuc_hoc("Thẻ ghi nhớ", R.drawable.hoc_tu_vung));
        hinhThucHocArrayList.add(new Hinh_thuc_hoc("Kiểm tra", R.drawable.anh_dang_ky));
        adapter = new Hinh_thuc_hoc_Adapter(this, R.layout.item_listview_hinh_thuc_hoc, hinhThucHocArrayList);
        lvHinhThucHoc.setAdapter(adapter);
        addEvent();
    }

    private void addControl() {
        lvHinhThucHoc = findViewById(R.id.lvHinhThucHoc);
    }

    private void addEvent() {
        lvHinhThucHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(Hinh_thuc_hoc_Activity.this, Danh_sach_tu_vung_Activity.class);
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(Hinh_thuc_hoc_Activity.this, The_ghi_nho_Activity.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(Hinh_thuc_hoc_Activity.this, Kiem_tra_Activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
