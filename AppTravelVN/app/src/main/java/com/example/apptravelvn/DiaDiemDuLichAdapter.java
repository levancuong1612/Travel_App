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

import org.w3c.dom.Text;

import java.util.List;

public class DiaDiemDuLichAdapter extends BaseAdapter {
    public Context context;
    public  int layout;
    public List<DiaDiemDuLich> listDiaDiem;

    public DiaDiemDuLichAdapter(Context context, int layout, List<DiaDiemDuLich> listDiaDiem) {
        this.context = context;
        this.layout = layout;
        this.listDiaDiem = listDiaDiem;
    }

    @Override
    public int getCount() {
        return listDiaDiem.size();
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
        TextView txtTenDiaDiemTinh,txtMoTa,txtVitriDiaDiem;
        ImageView imgHinhAnh;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        DiaDiemDuLich diaDiemDuLich= listDiaDiem.get(i);
        if(view==null){

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewHolder=new ViewHolder();
            viewHolder.imgHinhAnh=(ImageView)view.findViewById(R.id.imageViewDiaDiemTinh);
            viewHolder.txtTenDiaDiemTinh=(TextView)view.findViewById(R.id.textViewTenDiaDiemTinh);
            viewHolder.txtMoTa=(TextView)view.findViewById(R.id.textViewMota);
            viewHolder.txtVitriDiaDiem=(TextView)view.findViewById(R.id.txtVitriDiadiem);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

//        viewHolder.imgHinhAnh.setImageResource(diaDiemDuLich.HinhAnh);
        byte[] bytes= Base64.decode(diaDiemDuLich.HinhAnh,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        viewHolder.imgHinhAnh.setImageBitmap(bitmap);

        viewHolder.txtTenDiaDiemTinh.setText(diaDiemDuLich.TenDD);
        viewHolder.txtMoTa.setText(diaDiemDuLich.ThongTin);
        viewHolder.txtVitriDiaDiem.setText(diaDiemDuLich.ViTri);
        viewHolder.txtTenDiaDiemTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), ThongTinDiaDiemDLActivity.class);
                intent.putExtra("travel",diaDiemDuLich);
                view.getContext().startActivity(intent);
            }
        });

        final DataBase db= new DataBase(view.getContext(), "dulich.sqlite", null,1);

        return view;

    }
}
