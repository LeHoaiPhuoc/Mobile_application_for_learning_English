package hi.do_an_2.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hi.do_an_2.Model.ListView_doc_truyen;
import hi.do_an_2.R;

public class ListView_doc_truyen_Adapter extends BaseAdapter {
    Context context;
    int layout;

    public ListView_doc_truyen_Adapter(Context context, int layout, List<ListView_doc_truyen> listViewDocTruyenList) {
        this.context = context;
        this.layout = layout;
        this.listViewDocTruyenList = listViewDocTruyenList;
    }

    List<ListView_doc_truyen> listViewDocTruyenList;

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
