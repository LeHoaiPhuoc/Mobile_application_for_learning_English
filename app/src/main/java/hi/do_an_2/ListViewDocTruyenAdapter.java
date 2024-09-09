package hi.do_an_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewDocTruyenAdapter extends BaseAdapter {
    Context context;
    int layout;

    public ListViewDocTruyenAdapter(Context context, int layout, List<ListViewDocTruyen> listViewDocTruyenList) {
        this.context = context;
        this.layout = layout;
        this.listViewDocTruyenList = listViewDocTruyenList;
    }

    List<ListViewDocTruyen> listViewDocTruyenList;

    @Override
    public int getCount() {
        return listViewDocTruyenList.size();
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
        ImageView imgTruyen = (ImageView) convertView.findViewById(R.id.imgTruyen);
        TextView tvTenTruyen = (TextView) convertView.findViewById(R.id.tvTenTruyen);
        // Gán giá trị
        imgTruyen.setImageResource(listViewDocTruyenList.get(position).imgTruyen);
        tvTenTruyen.setText(listViewDocTruyenList.get(position).tvTenTruyen);
        return convertView;
    }
}
