package com.example.apptravelvn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class AmThucAdapter extends BaseAdapter {
    public Context context;
    public  int layout;
    public List<AmThucDuLich> listAmThuc;

    public AmThucAdapter(Context context, int layout, List<AmThucDuLich> listAmThuc) {
        this.context = context;
        this.layout = layout;
        this.listAmThuc = listAmThuc;
    }

    @Override
    public int getCount() {
        return listAmThuc.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        TextView txtTenAmThuc,txtMoTaAmThuc,txtVitriDiaDiemAmThuc;
        ImageView imgHinhAnhAmThuc;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder;
        AmThucDuLich amThucDuLich= listAmThuc.get(i);
        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewHolder=new ViewHolder();
            viewHolder.imgHinhAnhAmThuc=(ImageView)view.findViewById(R.id.imageViewAmthuc);
            viewHolder.txtTenAmThuc=(TextView)view.findViewById(R.id.textViewAmthucTinh);
            viewHolder.txtMoTaAmThuc=(TextView)view.findViewById(R.id.textViewMotaAmthuc);
            viewHolder.txtVitriDiaDiemAmThuc=(TextView)view.findViewById(R.id.txtVitriAmthuc);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        byte[] bytes= Base64.decode(amThucDuLich.HinhAnh,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        viewHolder.imgHinhAnhAmThuc.setImageBitmap(bitmap);
        viewHolder.txtTenAmThuc.setText(amThucDuLich.TenAT);
        viewHolder.txtMoTaAmThuc.setText(amThucDuLich.Vitri);
        viewHolder.txtVitriDiaDiemAmThuc.setText(amThucDuLich.Vitri);
        viewHolder.imgHinhAnhAmThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), ChiTietAmThucActivity.class);
                intent.putExtra("amthuc",amThucDuLich);
                view.getContext().startActivity(intent);
            }
        });
        final DataBase db= new DataBase(view.getContext(), "dulich.sqlite", null,1);

        return view;
    }
}
