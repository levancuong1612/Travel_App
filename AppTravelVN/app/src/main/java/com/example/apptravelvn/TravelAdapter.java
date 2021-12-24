package com.example.apptravelvn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class TravelAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<TravelLocation> list;

    public TravelAdapter(Context context, int layout, List<TravelLocation> list) {
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
    private  class Viewholder{
        TextView txtTenTinh,txtSoLuong;
        ImageView img;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder;
        TravelLocation travelLocation= list.get(i);
        if(view==null){

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view=inflater.inflate(layout,null);
            viewholder=new Viewholder();
            viewholder.img=(ImageView)view.findViewById(R.id.imageViewTable);
            viewholder.txtTenTinh=(TextView)view.findViewById(R.id.textViewTenDiaDiem);
            viewholder.txtSoLuong=(TextView)view.findViewById(R.id.textViewSoLuong);

            view.setTag(viewholder);
        }else{
            viewholder = (Viewholder) view.getTag();
        }

        Glide.with(view.getContext()).load(travelLocation.hinhanh).into(viewholder.img);

        viewholder.txtTenTinh.setText(travelLocation.tenTinh);
        viewholder.txtSoLuong.setText(travelLocation.SoLuong);
        viewholder.txtTenTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), ChiTietDiaDiemActivity.class);


                intent.putExtra("travel",travelLocation);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }

}
