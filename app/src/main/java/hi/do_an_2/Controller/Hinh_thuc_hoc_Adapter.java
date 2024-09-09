package hi.do_an_2.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

import hi.do_an_2.Model.Hinh_thuc_hoc;
import hi.do_an_2.R;

public class Hinh_thuc_hoc_Adapter extends BaseAdapter {
    Context context;
    int layout;

    public Hinh_thuc_hoc_Adapter(Context context, int layout, List<Hinh_thuc_hoc> hinhThucHocList) {
        this.context = context;
        this.layout = layout;
        this.hinhThucHocList = hinhThucHocList;
    }

    List<Hinh_thuc_hoc> hinhThucHocList;
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
