package hi.do_an_2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import hi.do_an_2.Model.ListView_doc_truyen;
import hi.do_an_2.R;

public class Chi_tiet_truyen_Activity extends AppCompatActivity {
    ImageView imgChiTietTruyen; // ImageView để hiển thị hình ảnh của truyện được chọn
    TextView tvChiTietTenTruyen, tvChiTietNoiDungTruyen; // TextViews để hiển thị tiêu đề và nội dung của truyện được chọn
    List<ListView_doc_truyen> listViewDocTruyenList; // Danh sách để lưu các mục trong ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_truyen); // Thiết lập layout cho activity này

        addControls(); // Gọi phương thức để khởi tạo các controls/views
        loadTruyen(); // Gọi phương thức để tải truyện được chọn
    }

    public void addControls(){
        imgChiTietTruyen = findViewById(R.id.imgChiTietTruyen); // Khởi tạo ImageView bằng cách tìm ID trong layout
        tvChiTietTenTruyen = findViewById(R.id.tvChiTietTenTruyen); // Khởi tạo TextView để hiển thị tiêu đề truyện
        tvChiTietNoiDungTruyen = findViewById(R.id.tvChiTietNoiDungTruyen); // Khởi tạo TextView để hiển thị nội dung truyện
    }

    public void loadTruyen(){
        Intent intent = getIntent(); // Lấy intent từ activity trước đó
        String tenTruyen = intent.getStringExtra("tenTruyen"); // Lấy tiêu đề truyện được chọn từ intent
        int hinhAnhTruyen = intent.getIntExtra("hinhAnhTruyen", 0); // Lấy ID của hình ảnh truyện được chọn từ intent

        imgChiTietTruyen.setImageResource(hinhAnhTruyen); // Đặt hình ảnh của truyện được chọn vào ImageView
        tvChiTietTenTruyen.setText(tenTruyen); // Đặt tiêu đề của truyện được chọn vào TextView

        StringBuilder noiDungTruyenBuilder = new StringBuilder(); // Tạo StringBuilder để lưu nội dung truyện

        try {
            InputStream inputStream = getAssets().open("truyen.txt"); // Mở tệp văn bản "truyen.txt" trong thư mục assets
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            boolean isMatchingTruyen = false; // Biến kiểm tra xem truyện hiện tại có khớp với tiêu đề truyện được chọn hay không

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("****")) { // Kiểm tra nếu dòng này là dòng phân cách cho biết bắt đầu một truyện mới
                    String tenTruyenLine = bufferedReader.readLine(); // Đọc dòng chứa tiêu đề của truyện hiện tại
                    if (tenTruyenLine.equals(tenTruyen)) { // Kiểm tra nếu truyện hiện tại khớp với tiêu đề truyện được chọn
                        isMatchingTruyen = true;
                    } else {
                        isMatchingTruyen = false;
                    }
                } else if (isMatchingTruyen) { // Nếu truyện hiện tại khớp với tiêu đề truyện được chọn
                    noiDungTruyenBuilder.append(line); // Thêm dòng vào StringBuilder chứa nội dung truyện
                    noiDungTruyenBuilder.append("\n"); // Thêm dấu xuống dòng sau mỗi dòng
                }
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String noiDungTruyen = noiDungTruyenBuilder.toString(); // Lấy nội dung truyện hoàn chỉnh dưới dạng chuỗi
        tvChiTietNoiDungTruyen.setText(noiDungTruyen); // Đặt nội dung truyện vào TextView
    }
}
