package hi.do_an_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Dang_ky_Activity extends AppCompatActivity {
    Button btnBanDaCoTaiKhoan, btnDangKy;
    EditText edtTenDangNhapDangKy, edtMatKhauDangKy, edtXacNhanMatKhauDangKy;
    MySQLiteOpenHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        addControls();
        addEvent();
    }
    public void addControls(){
        btnBanDaCoTaiKhoan = (Button) findViewById(R.id.btnBanDaCoTaiKhoan);
        databaseHelper = new MySQLiteOpenHelper(this);
        edtTenDangNhapDangKy = findViewById(R.id.edtTenDangNhapDangKy);
        edtMatKhauDangKy = findViewById(R.id.edtMatKhauDangKy);
        edtXacNhanMatKhauDangKy = findViewById(R.id.edtXacNhanMatKhauDangKy);
        btnDangKy = findViewById(R.id.btnDangKy);
    }
    public void addEvent(){
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDangKy();
            }
        });
        btnBanDaCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dang_ky_Activity.this, Dang_nhap_Activity.class);
                startActivity(intent);
            }
        });
    }
    private void xuLyDangKy(){

        // Lấy thông tin từ edittext xuống
        String tenDangNhapDangKy = edtTenDangNhapDangKy.getText().toString();
        String matKhauDangKy = edtMatKhauDangKy.getText().toString();
        String xacNhanMatKhauDangKy = edtXacNhanMatKhauDangKy.getText().toString();

        // Kiểm tra điều kiện đăng ký
        if(!kiemTraTenDangNhap(tenDangNhapDangKy)){
            kiemTraDieuKienDangKy(tenDangNhapDangKy, matKhauDangKy, xacNhanMatKhauDangKy);

            // Thêm vào cơ sở dữ liệu
            databaseHelper.openDatabase();
            databaseHelper.themDuLieuDangKyTableNguoidung(tenDangNhapDangKy, matKhauDangKy);

            // Đóng csdl sau khi thêm
            databaseHelper.closeDatabase();

            // Hiển thị thông báo và chuyển qua trang đăng nhập
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // Thiết lập tiêu đề và nội dung của cửa sổ thông báo
            alertDialogBuilder.setTitle("Thông báo");
            alertDialogBuilder.setMessage("Đăng ký thành công");
            // Thiết lập nút phía dưới
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Dang_ky_Activity.this, Dang_nhap_Activity.class);
                    startActivity(intent);
                }
            });

            // Tạo và hiển thị cửa sổ thông báo
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else {
            thongBaoDialog(this,"Lỗi", "Tên đăng nhập đã tồn tại", "OK");
        }

    }

    // Phương thức kiểm tra điều kiện đăng ký
    private void kiemTraDieuKienDangKy(String tenDangNhapDangKy, String matKhauDangKy, String xacNhanMatKhauDangKy){


        // Kiểm tra tên đăng nhập có tồn tại trên database chưa
        if(kiemTraTenDangNhap(tenDangNhapDangKy)){
            thongBaoDialog(this, "Lỗi", "Tên đăng nhập đã tồn tại", "Đóng");
        }
        // Kiểm tra xác nhận mật khẩu đúng chưa
        if(!matKhauDangKy.equals(xacNhanMatKhauDangKy)){
            thongBaoDialog(this, "Lỗi", "Mật khẩu xác nhận không khớp", "OK");
        }
    }

    // Phương thức kiểm tra tên đăng nhập có tồn tại trong CSDL chưa
    private boolean kiemTraTenDangNhap(String tenDangNhap){
        Cursor cursor =databaseHelper.getData();
        if(cursor != null && cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String tenDangNhapDB =cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.COLUMN_TEN_DANG_NHAP));
                if(tenDangNhapDB.equalsIgnoreCase(tenDangNhap)){
                    cursor.close();
                    return true;
                }
            }while (cursor.moveToNext());
        }
        return false; // Tên chưa tồn tại trong database
    }

    // Phương thức
    // Phương thức thông báo
    public void thongBaoDialog(Context context, String tieuDeThongBao, String noiDungThongBao, String nutPhiaDuoi){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // Thiết lập tiêu đề và nội dung của cửa sổ thông báo
        alertDialogBuilder.setTitle(tieuDeThongBao);
        alertDialogBuilder.setMessage(noiDungThongBao);

        // Thiết lập nút phía dưới
        alertDialogBuilder.setPositiveButton(nutPhiaDuoi, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss(); // Đóng cửa sổ thông báo
            }
        });

        // Tạo và hiển thị cửa sổ thông báo
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}