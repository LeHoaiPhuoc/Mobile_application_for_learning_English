package hi.do_an_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewAppTrangChuAdapter extends BaseAdapter {
    Context context;
    int layout;

    public ListViewAppTrangChuAdapter(Context context, int layout, List<ListViewAppTrangChu> listViewAppTrangChuList) {
        this.context = context;
        this.layout = layout;
        this.listViewAppTrangChuList = listViewAppTrangChuList;
    }

    List<ListViewAppTrangChu> listViewAppTrangChuList;
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
