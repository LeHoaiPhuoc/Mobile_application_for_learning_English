package hi.do_an_2.Controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hi.do_an_2.Model.Ket_qua;
import hi.do_an_2.R;

public class Ket_qua_Adapter extends BaseAdapter {
    Context context;
    public int layout;

    public Ket_qua_Adapter(Context context, int layout, List<Ket_qua> ketQuaList) {
        this.context = context;
        this.layout = layout;
        this.ketQuaList = ketQuaList;
    }

    List<Ket_qua> ketQuaList;
    @Override
    public int getCount() {
        return ketQuaList.size();
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
        TextView tvTuVung = convertView.findViewById(R.id.tvTuVung);
        TextView tvCauTraLoi = convertView.findViewById(R.id.tvCauTraLoi);
        TextView tvDapAn = convertView.findViewById(R.id.tvDapAn);

        // Gán giá trị
        tvTuVung.setText(ketQuaList.get(position).tuVung);
        tvCauTraLoi.setText(ketQuaList.get(position).cauTraLoi);
        tvDapAn.setText(ketQuaList.get(position).dapAnDung);


        // Lấy thông tin từ danh sách ketQuaList tương ứng với vị trí position
        Ket_qua ketQua = ketQuaList.get(position);

        // Kiểm tra nếu câu trả lời giống với đáp án thì tô màu xanh và ẩn đáp án
        if (ketQua.cauTraLoi.equals(ketQua.dapAnDung)) {
            tvCauTraLoi.setTextColor(Color.GREEN); // Tô màu xanh cho câu trả lời
            tvDapAn.setVisibility(View.GONE);     // Ẩn đáp án
        }else {
            tvCauTraLoi.setTextColor(Color.RED); // Tô màu xanh cho câu trả lời
        }
        return convertView;
    }
}
