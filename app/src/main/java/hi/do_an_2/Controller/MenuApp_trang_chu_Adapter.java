package hi.do_an_2.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hi.do_an_2.Model.Menu_app_trang_chu;
import hi.do_an_2.R;

public class MenuApp_trang_chu_Adapter extends BaseAdapter {
    Context context;
    int layout;

    public MenuApp_trang_chu_Adapter(Context context, int layout, List<Menu_app_trang_chu> menuAppList) {
        this.context = context;
        this.layout = layout;
        this.menuAppList = menuAppList;
    }

    List<Menu_app_trang_chu> menuAppList;
    @Override
    public int getCount() {
        return menuAppList.size();
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
        TextView tvNoiDung1 = (TextView) convertView.findViewById(R.id.tvNoiDung1);
        TextView tvNoiDung2 = (TextView) convertView.findViewById(R.id.tvNoiDung2);
        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        // Gán giá trị
        tvNoiDung1.setText(menuAppList.get(position).noiDung1);
        tvNoiDung2.setText(menuAppList.get(position).noiDung2);
        img.setImageResource(menuAppList.get(position).img);

        return convertView;
    }
}
