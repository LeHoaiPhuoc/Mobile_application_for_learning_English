package hi.do_an_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class The_ghi_nho_Activity extends AppCompatActivity {
    TextView tvTuVung;
    Button btnTuPhiaTruoc, btnTuTiepTheo;
    List<String> listTuVung;
    List<String> listNghiaTuVung;
    int position = 0;
    // Khai báo biến kiểm tra textView đang hiển thị từ vựng hay nghĩa
    // Nếu true là nghĩa và ngược lại
    boolean checkTuVungHayNghiaChoTV = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_ghi_nho);

        addControl();

        // Nếu mới load lên thì sẽ hiển thị từ đầu tiên trong db lên tv
        themTuVungVao2List();
        tvTuVung.setText(listTuVung.get(0));

        addEvents();
    }
    private void addControl(){
        tvTuVung = findViewById(R.id.tvTuVung);
        btnTuPhiaTruoc = findViewById(R.id.btnTuPhiaTruoc);
        btnTuTiepTheo = findViewById(R.id.btnTuTiepTheo);
        listTuVung = new ArrayList<>();
        listNghiaTuVung = new ArrayList<>();
    }
    private void addEvents(){
        tvTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position <= listTuVung.size() && checkTuVungHayNghiaChoTV == false){
                    // Tiến hành lấy nghĩa với vị trí tương ứng
                    themTuVungVao2List();
                    String nghiaTuVung = listNghiaTuVung.get(position);
                    tvTuVung.setText(nghiaTuVung);
                    checkTuVungHayNghiaChoTV = true;
                } else if (position <=listTuVung.size() && checkTuVungHayNghiaChoTV == true) {
                    // Tiến hành lấy từ vựng với vị trí tương ứng
                    themTuVungVao2List();
                    String tuVung = listTuVung.get(position);
                    tvTuVung.setText(tuVung);
                    checkTuVungHayNghiaChoTV = false;
                }
            }
        });
        btnTuTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position < listTuVung.size() - 1){ // Kiểm tra điều kiện của list trước khi hiển thị
                    // +1 cho position để xác định từ tiếp theo
                    position +=1;
                    themTuVungVao2List();
                    String tuVung = listTuVung.get(position);
                    tvTuVung.setText(tuVung);
                    checkTuVungHayNghiaChoTV = false;
                }
            }
        });
        btnTuPhiaTruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position >0){ // Nếu position lớn hơn không thì mới được
                    position -= 1;
                    themTuVungVao2List();
                    String tuVung = listTuVung.get(position);
                    tvTuVung.setText(tuVung);
                    checkTuVungHayNghiaChoTV = false;
                }
            }
        });
    }

    // Phương thức load phần tử đầu tiên

    // Phương thức load từ vựng tiếp theo
    private void loadTuVungDauTienVaTiepTheo(){
        themTuVungVao2List();

    }

    // Phương thức load từ vựng phía trước
    private void loadTuVungPhiaTruoc(){

        // Thêm vào 2 list danh sách các từ vựng và định nghĩa tương ứng
        themTuVungVao2List();

    }

    // Phương thức thêm từ vựng vào 2 list
    private void themTuVungVao2List(){

        // Tạo đối tượng MySQLiteOpenHelper
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(this);

        // Lấy danh sách từ vựng và định nghĩa từ tên đăng nhập
        List<Tu_vung> tuVungList =dbHelper.layThuatNguVaDinhNghiaTuTenDangNhap(this);

        // Xóa hết các phần tử trong danh sách trước khi thêm vô lại
        if(listNghiaTuVung.size() != 0 && listTuVung.size() !=0){ // Kiểm tra nếu nó khác rỗng thì mới được xóa
            listTuVung.clear();
            listNghiaTuVung.clear();
        }

        // Đưa từ vựng và nghĩa vào 2 danh sách riêng
        for(Tu_vung tuVung: tuVungList){
            listTuVung.add(tuVung.getTuVung());
            listNghiaTuVung.add(tuVung.getNghiaTuVung());
        }
    }
}