package hi.do_an_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Kiem_tra_Activity extends AppCompatActivity {
    TextView tvTuVungKiemTra;
    Button btnTuPhiaTruoc, btnTuTiepTheo, btnNopBai;
    EditText edtNhapThuatNgu;

    List<String> listTuVung;
    List<String> listNghiaTuVung;

    List<String> listCauTraLoi;
    List<String> listTamThoi; // Dùng để lưu từ mà người dùng đã nhập khi họ nhấn vào nút từ phía trước
    int position = 0; // Biến này dùng để xác định từ cần hiển thị lên tv
    int positionCauTraLoi = -1; // Biến này dùng để lấy câu trả lời vào mảng câu trả lời
    boolean choPhepNopBai = false; // Các biến kiểm tra nút nộp bài
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_tra);

        addControls();

        // Nếu mới vào activity thì sẽ load từ vựng đầu tiên lên textView
        loadTuDauTien();

        addEvents();
    }
    private void addControls(){
        tvTuVungKiemTra = findViewById(R.id.tvTuVungKiemTra);
        btnTuPhiaTruoc = findViewById(R.id.btnTuPhiaTruoc);
        btnTuTiepTheo = findViewById(R.id.btnTuTiepTheo);
        btnNopBai = findViewById(R.id.btnNopBai);
        edtNhapThuatNgu = findViewById(R.id.edtNhapThuaNgu);
        listTuVung = new ArrayList<>();
        listNghiaTuVung = new ArrayList<>();
        listCauTraLoi = new ArrayList<>();
        listTamThoi = new ArrayList<>();
    }
    private void addEvents(){

        // Sự kiện click vào nút từ tiếp theo
        btnTuTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((kiemTraEditText() && positionCauTraLoi< listTuVung.size() -1)){ // Kiểm tra hàm trả về true thì mới thực hiện các bước tiếp theo
                    // Nếu câu trả lời hợp lệ thì sẽ tiến hành lưu câu trả lời vào mảng câu trả lời
                    // Cùng với đó là xóa nội dung EditText để chuyển sang từ vựng tiếp theo
                    themCauTraLoiVaoMangCauTraLoi();

                    // Chuyển sang từ vựng tiếp theo
                    chuyenSangTuTiepTheo();

                    // Kiểm tra điều kiện mảng tạm thời có đang có giá trị nào không
                    // Nếu có thì thêm vào edt còn không thì để chuỗi rỗng
                    if(listTamThoi.size() !=0){

                        // Xác định chỉ số phần tử cuối cùng của mảng tạm thời trước
                        int chiSoPhanTuCuoiCung = listTamThoi.size() -1;
                        edtNhapThuatNgu.setText(listTamThoi.get(chiSoPhanTuCuoiCung));

                        // Sau khi thêm thì xóa đi phần tử cuối cùng
                        listTamThoi.remove(chiSoPhanTuCuoiCung);

                    }else {
                        edtNhapThuatNgu.setText("");
                    }

                } //else if (positionCauTraLoi == listTuVung.size() -1) {
                    //showDialog("Thông báo", "Đã hết từ vựng");
                    //choPhepNopBai = true;
                //}
              //  String listCauTraLoiString = TextUtils.join(", ", listCauTraLoi);
                //String listTuVungString = TextUtils.join(", ", listTuVung);
                //showDialog("Thông báo", listCauTraLoiString + listTuVungString + positionCauTraLoi);
            }
        });

        // Sự kiện click vào nút từ phía trước
        btnTuPhiaTruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choPhepNopBai = false; // Chưa nộp bài được
                // Trừ position đi 1 và hiển thị từ vựng tại position đó của mảng từ vựng
                // Lấy position đó để lấy câu trả lời trước đó luôn
                if(position >=1){
                    
                    // Lưu từ hiện tại vào mảng tạm thời
                    listTamThoi.add(edtNhapThuatNgu.getText().toString());
                    position -=1;
                    tvTuVungKiemTra.setText(listTuVung.get(position));
                    edtNhapThuatNgu.setText(listCauTraLoi.get(listCauTraLoi.size() -1));

                    // Xóa phần tử cuối cùng của mảng Câu trả lời
                    listCauTraLoi.remove(listCauTraLoi.size()-1);
                }
            }
        });

        btnNopBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choPhepNopBai == true){
                    themTuVungVao2List();
                    String listTuVungString = TextUtils.join(", ", listTuVung);
                    String listNghiaTuVungString = TextUtils.join(", ", listNghiaTuVung);
                    String listCauTraLoiString = TextUtils.join(", ", listCauTraLoi);
                    Intent intent = new Intent(Kiem_tra_Activity.this, Ket_qua_Activity.class);
                    intent.putExtra("listTuVung", listTuVungString);
                    intent.putExtra("listNghiaTuVung", listNghiaTuVungString);
                    intent.putExtra("listCauTraLoi", listCauTraLoiString);
                    listCauTraLoi.clear();
                    startActivity(intent);
                }
            }
        });
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

    // Phương thức load từ vựng đầu tiên lên Textview

    private void loadTuDauTien(){

        // Thêm từ vựng vào 2 list trước
        themTuVungVao2List();
        tvTuVungKiemTra.setText(listTuVung.get(0));
    }

    // Phương thức kiểm tra Editext có rỗng hay toàn khoảng trắng không
    private boolean kiemTraEditText() {
        String inputText = edtNhapThuatNgu.getText().toString().trim();

        if (inputText.isEmpty()) {
            showDialog("Thông báo", "Câu trả lời không hợp lệ");
            return false;
        }

        return true;
    }

    // Phương thức thêm câu trả lời vào mảng câu trả lời
    private void themCauTraLoiVaoMangCauTraLoi(){
        String cauTraLoi = edtNhapThuatNgu.getText().toString();
        listCauTraLoi.add(cauTraLoi);
    }

    private void chuyenSangTuTiepTheo(){
        if(position == listTuVung.size() -1){
            showDialog("Thông báo", "Đã hết từ vựng");
            choPhepNopBai = true;
        }else {
            position +=1; // +1 để xác định vị trí từ tiếp theo
            positionCauTraLoi +=1;
            themTuVungVao2List();
            tvTuVungKiemTra.setText(listTuVung.get(position));
        }
    }


    // Phương thức show thông báo
    public void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý khi người dùng bấm nút OK
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Phương thức override nút back để xóa list tạm thời

    @Override
    public void onBackPressed() {
        listTamThoi.clear();
        super.onBackPressed();
    }
}