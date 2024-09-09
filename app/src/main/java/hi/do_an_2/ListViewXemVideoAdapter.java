package hi.do_an_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewXemVideoAdapter extends BaseAdapter {
    Context context;
    int layout;

    public ListViewXemVideoAdapter(Context context, int layout, List<ListViewXemVideo> listViewXemVideoList) {
        this.context = context;
        this.layout = layout;
        this.listViewXemVideoList = listViewXemVideoList;
    }

    List<ListViewXemVideo> listViewXemVideoList;
    @Override
    public int getCount() {
        return listViewXemVideoList.size();
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
        ImageView imgVideo = convertView.findViewById(R.id.imgVideo);
        TextView tvTenVideo = convertView.findViewById(R.id.tvTenVideo);
        // Gán giá trị
        imgVideo.setImageResource(listViewXemVideoList.get(position).imgVideo);
        tvTenVideo.setText(listViewXemVideoList.get(position).tvTenVideo);
        return convertView;
    }
}
