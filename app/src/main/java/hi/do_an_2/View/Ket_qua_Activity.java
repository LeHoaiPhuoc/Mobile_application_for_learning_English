package hi.do_an_2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hi.do_an_2.Model.Ket_qua;
import hi.do_an_2.Controller.Ket_qua_Adapter;
import hi.do_an_2.R;

public class Ket_qua_Activity extends AppCompatActivity {
    ListView lvKetQuaBaiKiemTra;
    ArrayList<Ket_qua> ketQuaArrayList;
    Ket_qua_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        // Load dữ liệu ra
        Intent intent = getIntent(); // Lấy Intent từ Activity gọi đến
        if (intent != null) {
            String listTuVung = intent.getStringExtra("listTuVung");
            String listNghiaTuVung = intent.getStringExtra("listNghiaTuVung");
            String listCauTraLoi = intent.getStringExtra("listCauTraLoi");

            String message = "Danh sách từ vựng: " + listTuVung + "\n"
                    + "Danh sách nghĩa từ vựng: " + listNghiaTuVung + "\n"
                    + "Danh sách câu trả lời: " + listCauTraLoi;

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Intent không tồn tại hoặc null", Toast.LENGTH_SHORT).show();
        }

        lvKetQuaBaiKiemTra = findViewById(R.id.lvKetQuaBaiKiemTra);

        // Chuyển chuỗi thành mảng
        String listTuVungString = intent.getStringExtra("listTuVung");
        List<String> listTuVung = Arrays.asList(listTuVungString.split(", "));

        String listCauTraLoiString = intent.getStringExtra("listCauTraLoi");
        List<String> listCauTraLoi = Arrays.asList(listCauTraLoiString.split(", "));

        String listDapAnDungString = intent.getStringExtra("listNghiaTuVung");
        List<String> listDapAnDung = Arrays.asList(listDapAnDungString.split(", "));

        ketQuaArrayList = new ArrayList<>();

        // Tạo đối tượng KetQua cho mỗi phần tử
        for (int i = 0; i < listTuVung.size(); i++) {
            String tuVung = listTuVung.get(i);
            String dapAnDung = listDapAnDung.get(i);
            String cauTraLoi = listCauTraLoi.get(i);

            Ket_qua ketQua = new Ket_qua(tuVung, cauTraLoi, dapAnDung);
            ketQuaArrayList.add(ketQua);
        }

        // Khởi tạo adapter
        adapter = new Ket_qua_Adapter(this, R.layout.item_listview_ket_qua, ketQuaArrayList);

        // Gán adapter cho ListView
        lvKetQuaBaiKiemTra = findViewById(R.id.lvKetQuaBaiKiemTra);
        lvKetQuaBaiKiemTra.setAdapter(adapter);
    }
}
