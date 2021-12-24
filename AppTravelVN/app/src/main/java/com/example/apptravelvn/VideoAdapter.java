package com.example.apptravelvn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class VideoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Video> list;

    public VideoAdapter(Context context, int layout, List<Video> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public  class ViewHolder{
    TextView txtTenVideo;
    KenBurnsView kbvLocation;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Video video=list.get(i);
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewHolder= new ViewHolder();
            viewHolder.txtTenVideo=view.findViewById(R.id.textViewTenVideo);
            viewHolder.kbvLocation= view.findViewById(R.id.kbvVideo);
            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();


        }
        viewHolder. kbvLocation.setImageResource(video.hinhAnh);
        viewHolder.txtTenVideo.setText(video.tenVideo);
        return view;
    }
}
