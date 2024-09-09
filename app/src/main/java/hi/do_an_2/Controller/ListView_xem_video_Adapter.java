package hi.do_an_2.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hi.do_an_2.Model.ListView_xem_video;
import hi.do_an_2.R;

public class ListView_xem_video_Adapter extends BaseAdapter {
    Context context;
    public int layout;

    public ListView_xem_video_Adapter(Context context, int layout, List<ListView_xem_video> listViewXemVideoList) {
        this.context = context;
        this.layout = layout;
        this.listViewXemVideoList = listViewXemVideoList;
    }

    List<ListView_xem_video> listViewXemVideoList;
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
