package hi.do_an_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Xem_video_Activity extends AppCompatActivity {
    ListView lvXemVideo;
    List<ListViewXemVideo> listViewXemVideoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_video);

        addControl();
        loadTenVideo("video.txt");
        addEvent();
    }

    public void addControl() {
        lvXemVideo = findViewById(R.id.lvXemVideo);
    }

    public void addEvent() {
        lvXemVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy thông tin video được click
                ListViewXemVideo video = listViewXemVideoList.get(position);

                // Lấy tên video được chọn sau khi trừ đi 2 ký tự
                String tenVideo = video.tvTenVideo.substring(0, video.tvTenVideo.length());
                // Lấy link video từ danh sách
                String linkVideo = getLinkVideo(tenVideo);

                // Chuyển sang activity Chi_tiet_video_Activity và truyền đường dẫn video
                Intent intent = new Intent(Xem_video_Activity.this, Chi_tiet_video_Activity.class);
                intent.putExtra("linkVideo", linkVideo);
                startActivity(intent);
            }
        });
    }

    public void loadTenVideo(String fileName) {
        // Tạo danh sách mục video
        listViewXemVideoList = new ArrayList<>();
        List<String> tenVideoList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            String tenVideo = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("****")) {
                    // Nếu gặp dòng chứa "****" thì lấy dòng tiếp theo làm tên video
                    tenVideo = bufferedReader.readLine();
                    // Thêm tên video vào danh sách nếu chưa tồn tại
                    if (!tenVideoList.contains(tenVideo)) {
                        tenVideoList.add(tenVideo);
                        // Lấy tên file ảnh từ tên video
                        String tenFileVideo = tenVideo.toLowerCase().replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9_]", "");
                        // Lấy id ảnh
                        int drawableResourceId = getResources().getIdentifier(tenFileVideo, "drawable", getPackageName());
                        // Thêm mục video vào danh sách với hình ảnh và đường dẫn tương ứng
                        listViewXemVideoList.add(new ListViewXemVideo(drawableResourceId, tenVideo));
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
        ListViewXemVideoAdapter adapter = new ListViewXemVideoAdapter(this, R.layout.item_listview_xem_video, listViewXemVideoList);
        lvXemVideo.setAdapter(adapter);
    }

    private String getLinkVideo(String tenVideo) {
        String fileName = "video.txt";
        try {
            InputStream inputStream = getAssets().open(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("****")) {
                    // Nếu gặp dòng chứa "****" thì lấy dòng tiếp theo làm tên video
                    String tempTenVideo = bufferedReader.readLine();
                    if (tempTenVideo.equals(tenVideo)) {
                        // Nếu tìm thấy tên video trùng khớp, lấy dòng tiếp theo là link video
                        String linkVideo = bufferedReader.readLine();
                        // Xóa ký tự số cuối (index video)
                    //    linkVideo = linkVideo.substring(0, linkVideo.length() - 2);
                        bufferedReader.close();
                        inputStreamReader.close();
                        inputStream.close();
                        return linkVideo;
                    }
                }
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
