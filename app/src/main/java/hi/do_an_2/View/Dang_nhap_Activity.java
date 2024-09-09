package hi.do_an_2.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import hi.do_an_2.Model.MySQLite_open_helper;
import hi.do_an_2.R;

public class Dang_nhap_Activity extends AppCompatActivity {
    Button btnBanChuaCoTaiKhoan, btnDangNhap;
    EditText edtTenDangNhap, edtMatKhau;
    MySQLite_open_helper databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        addControls();
        addEvents();
    }
    public void addControls(){
        btnBanChuaCoTaiKhoan = (Button) findViewById(R.id.btnBanChuaCoTaiKhoan);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        databaseHandler = new MySQLite_open_helper(getApplicationContext());
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
    }
    public void addEvents(){
        btnBanChuaCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dang_nhap_Activity.this, Dang_ky_Activity.class);
                startActivity(intent);
            }
        });
        eventDangNhap();
    }
    public void eventDangNhap(){
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lấy giá trị tên đăng nhập và mật khẩu qua
                String dangnhap = edtTenDangNhap.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                // Kiểm tra thông tin đăng nhập
                if(databaseHandler.kiemTraThongTinDangNhap(dangnhap, matkhau)){
                    // Lưu tên đăng nhập vào SharedPreferences để sử dụng truy xuất dữ liệu trong csdl
                    SharedPreferences sharedPreferences = getSharedPreferences("AppPref", MODE_PRIVATE);

                    // Kiểm tra xem tên đăng nhập đã được lưu từ trước hay chưa
                    String savedTenDangNhap = sharedPreferences.getString("tenDangNhap", "");
                    if(!savedTenDangNhap.equals(edtTenDangNhap.getText().toString())){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("tenDangNhap", edtTenDangNhap.getText().toString());
                        editor.apply();
                    }

                    // Chuyển activity
                    Intent intent = new Intent(Dang_nhap_Activity.this, Trang_chu_Activity.class);
                    startActivity(intent);
                }
                else {
                    // Hiển thị thông báo lỗi
                   hienThiThongBaoLoi("Lỗi", "Tên đăng nhập hoặc mật khẩu không đúng");
                }
            }
        });
    }
    // Phương thức hiển thị thông báo lỗi
    private void hienThiThongBaoLoi(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Dang_nhap_Activity.this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}