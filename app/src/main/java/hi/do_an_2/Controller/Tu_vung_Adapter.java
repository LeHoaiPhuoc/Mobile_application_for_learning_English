package hi.do_an_2.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.List;

import hi.do_an_2.Model.Tu_vung;
import hi.do_an_2.R;

public class Tu_vung_Adapter extends BaseAdapter {
    Context context;
    int layout;

    public Tu_vung_Adapter(Context context, int layout, List<Tu_vung> tuVungList) {
        this.context = context;
        this.layout = layout;
        this.tuVungList = tuVungList;
    }

    List<Tu_vung> tuVungList;
    @Override
    public int getCount() {
        return tuVungList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
        EditText edtThuatNgu = convertView.findViewById(R.id.edtThuatNgu);
        EditText edtDinhNghia = convertView.findViewById(R.id.edtDinhNghia);

        Tu_vung tu_vung = tuVungList.get(position);

        // Gán giá trị
        edtThuatNgu.setText(tu_vung.getTuVung());
        edtDinhNghia.setText(tu_vung.getNghiaTuVung());
        return convertView;
    }
}
