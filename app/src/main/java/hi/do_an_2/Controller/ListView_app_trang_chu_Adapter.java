package hi.do_an_2.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hi.do_an_2.Model.ListView_app_trang_chu;
import hi.do_an_2.R;

public class ListView_app_trang_chu_Adapter extends BaseAdapter {
    Context context;
    int layout;

    public ListView_app_trang_chu_Adapter(Context context, int layout, List<ListView_app_trang_chu> listViewAppTrangChuList) {
        this.context = context;
        this.layout = layout;
        this.listViewAppTrangChuList = listViewAppTrangChuList;
    }

    List<ListView_app_trang_chu> listViewAppTrangChuList;
    @Override
    public int getCount() {
        return listViewAppTrangChuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        // Ánh xạ
        ImageView img1ThongKe = (ImageView) convertView.findViewById(R.id.img1ThongKe);
        ImageView img2ThongKe = (ImageView) convertView.findViewById(R.id.img2ThongKe);
        TextView tvThongKe = (TextView) convertView.findViewById(R.id.tvThongKe);
        // Gán giá trị
        img1ThongKe.setImageResource(listViewAppTrangChuList.get(position).img1ThongKe);
        img2ThongKe.setImageResource(listViewAppTrangChuList.get(position).img2ThongKe);
        tvThongKe.setText(listViewAppTrangChuList.get(position).noiDungThongKe);

        return convertView;
    }
}
