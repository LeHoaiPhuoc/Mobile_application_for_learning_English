package hi.do_an_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.lang.reflect.Constructor;
import java.util.List;

public class HinhThucHocAdapter extends BaseAdapter {
    Context context;
    int layout;

    public HinhThucHocAdapter(Context context, int layout, List<HinhThucHoc> hinhThucHocList) {
        this.context = context;
        this.layout = layout;
        this.hinhThucHocList = hinhThucHocList;
    }

    List<HinhThucHoc> hinhThucHocList;
    @Override
    public int getCount() {
        return hinhThucHocList.size();
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
        TextView tvHinhThucHoc = convertView.findViewById(R.id.tvHinhThucHoc);
        ImageView imgHinhThucHoc = convertView.findViewById(R.id.imgHinhThucHoc);

        // Gán giá trị
        tvHinhThucHoc.setText(hinhThucHocList.get(position).hinhThucHoc);
        imgHinhThucHoc.setImageResource(hinhThucHocList.get(position).imgHinhThucHoc);

        if(position %2 == 0){
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.item_background_mau_cam));
        }else{
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.item_background_mau_da_gach));

        }
        return convertView;
    }
}
