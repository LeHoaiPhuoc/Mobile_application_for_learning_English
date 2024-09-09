package hi.do_an_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Doc_truyen_Activity extends AppCompatActivity {
    ListView lvDocTruyen;
    List<ListViewDocTruyen> listViewDocTruyenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);

        addControl();
        loadTenTruyen("truyen.txt");
        addEvent();


    }
    public void addControl(){
        lvDocTruyen = (ListView) findViewById(R.id.lvDocTruyen);
    }
    public void addEvent(){
        lvDocTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy thông tin truyện được click
                ListViewDocTruyen truyen = listViewDocTruyenList.get(position);

                // Hiển thị thông tin truyện trong activity chi tiết
                Intent intent = new Intent(Doc_truyen_Activity.this, Chi_tiet_truyen_Activity.class);
                intent.putExtra("tenTruyen", truyen.tvTenTruyen);
                intent.putExtra("hinhAnhTruyen", truyen.imgTruyen);
                startActivity(intent);
            }
        });
    }
    public void loadTenTruyen(String fileName){
        // Tạo danh sách mục truyện
        listViewDocTruyenList = new ArrayList<>();
        List<String> tenTruyenList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            String tenTruyen = null;
            while ((line = bufferedReader.readLine()) !=null){
                if(line.equals("****")){
                    // Nếu gặp dòng chứa "****" thì lấy dòng tiếp theo làm tên truyện
                    tenTruyen = bufferedReader.readLine();
                    // Thêm tên truyện vào danh sách nếu chưa tồn tại
                    if (!tenTruyenList.contains(tenTruyen)) {
                        tenTruyenList.add(tenTruyen);
                        // Lấy tên file ảnh từ tên truyện
                        String tenFileAnh = tenTruyen.toLowerCase().replaceAll("\\s+","_").replaceAll("[^a-zA-Z0-9_]", "");
                        // Lấy id ảnh
                        int drawableResourceId = getResources().getIdentifier(tenFileAnh, "drawable", getPackageName());
                        // Thêm mục truyện vào danh sách với hình ảnh tương ứng
                        listViewDocTruyenList.add(new ListViewDocTruyen(drawableResourceId, tenTruyen));
                    }
                }
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Tạo adapter và gán cho listView
        ListViewDocTruyenAdapter adapter = new ListViewDocTruyenAdapter(this, R.layout.item_listview_doc_truyen, listViewDocTruyenList);
        lvDocTruyen.setAdapter(adapter);
    }
}