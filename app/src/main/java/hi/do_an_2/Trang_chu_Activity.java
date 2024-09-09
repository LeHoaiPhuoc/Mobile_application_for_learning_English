package hi.do_an_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class Trang_chu_Activity extends AppCompatActivity {
    GridView gvTrangChu;
    ArrayList<MenuAppTrangChu> menuAppArrayList;
    MenuAppTrangChuAdapter adapteMenuAppTrangChu;
    ListView lv;
    ArrayList<ListViewAppTrangChu> listViewAppTrangChuArrayList;
    ListViewAppTrangChuAdapter adapterListViewAppTrangChu;
    Button btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        addControls();
        addEvents();
        loadDataGridView();
        loadDataListView();
    }
    public void addControls(){

        gvTrangChu = (GridView) findViewById(R.id.gvTrangChu);
        lv = (ListView) findViewById(R.id.lv);
        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
    }
    public void addEvents(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        gvTrangChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 3){
                    Intent intent = new Intent(Trang_chu_Activity.this, Doc_truyen_Activity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(Trang_chu_Activity.this, Xem_video_Activity.class);
                    startActivity(intent);
                } else if (position == 0) {
                    Intent intent = new Intent(Trang_chu_Activity.this, Them_tu_vung_Activity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(Trang_chu_Activity.this, Hinh_thuc_hoc_Activity.class);
                    startActivity(intent);
                }
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Trang_chu_Activity.this);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có muốn đăng xuất?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Trang_chu_Activity.this, Dang_nhap_Activity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                // Tùy chọn: Thêm nút Cancel
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý khi người dùng bấm nút Cancel
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void loadDataGridView(){
        menuAppArrayList = new ArrayList<>();
        menuAppArrayList.add(new MenuAppTrangChu(R.drawable.them_tu_vung,"Thêm","từ vựng"));
        menuAppArrayList.add(new MenuAppTrangChu(R.drawable.hoc_tu_vung,"Học","từ vựng"));
        menuAppArrayList.add(new MenuAppTrangChu(R.drawable.xem_video_tieng_anh,"Xem video","tiếng Anh"));
        menuAppArrayList.add(new MenuAppTrangChu(R.drawable.doc_truyen_tieng_anh,"Đọc truyện","tiếng Anh"));

        adapteMenuAppTrangChu = new MenuAppTrangChuAdapter(this, R.layout.item_gridview_trang_chu, menuAppArrayList);
        gvTrangChu.setAdapter(adapteMenuAppTrangChu);
    }
    public void loadDataListView(){
        listViewAppTrangChuArrayList = new ArrayList<>();
        listViewAppTrangChuArrayList.add(new ListViewAppTrangChu(R.drawable.thong_ke,R.drawable.thong_ke_2, "Thống kê"));
        adapterListViewAppTrangChu = new ListViewAppTrangChuAdapter(this, R.layout.item_listview_trang_chu, listViewAppTrangChuArrayList);
        lv.setAdapter(adapterListViewAppTrangChu);
    }

    // Phương thức thông báo
    public void showAlert(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cảnh báo");
        builder.setMessage("Đây là thông báo cảnh báo.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng bấm nút OK
                dialog.dismiss();
            }
        });

        // Tùy chọn: Thêm nút Cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng bấm nút Cancel
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}