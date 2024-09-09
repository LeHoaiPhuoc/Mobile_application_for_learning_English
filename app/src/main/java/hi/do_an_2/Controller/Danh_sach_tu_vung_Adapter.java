package hi.do_an_2.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hi.do_an_2.Model.Danh_sach_tu_vung;
import hi.do_an_2.R;

public class Danh_sach_tu_vung_Adapter extends BaseAdapter {
    Context context;
    int layout;

    @Override
    public int getCount() {
        return danhSachTuVungList.size();
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
        TextView tvThuatNguTrongHocTuVung = convertView.findViewById(R.id.tvThuatNguTrongHocTuVung);
        TextView tvDinhNghiaTrongHocTuVung = convertView.findViewById(R.id.tvDinhNghiaTrongHocTuVung);

        // Gán giá trị
        tvThuatNguTrongHocTuVung.setText(danhSachTuVungList.get(position).tuVung);
        tvDinhNghiaTrongHocTuVung.setText(danhSachTuVungList.get(position).nghiaTuVung);
        return convertView;
    }

    public Danh_sach_tu_vung_Adapter(Context context, int layout, List<Danh_sach_tu_vung> danhSachTuVungList) {
        this.context = context;
        this.layout = layout;
        this.danhSachTuVungList = danhSachTuVungList;
    }

    List<Danh_sach_tu_vung> danhSachTuVungList;
}
