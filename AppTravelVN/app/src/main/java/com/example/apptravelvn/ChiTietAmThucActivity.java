package com.example.apptravelvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.example.apptravelvn.databinding.ActivityChiTietAmThucBinding;

public class ChiTietAmThucActivity extends AppCompatActivity {

    ActivityChiTietAmThucBinding binding;
    AmThucDuLich amThucDuLich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChiTietAmThucBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        amThucDuLich= (AmThucDuLich) intent.getSerializableExtra("amthuc");
        setView();
    }
    void  setView(){
        byte[] bytes= Base64.decode(amThucDuLich.HinhAnh,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageChiTietAmThuc.setImageBitmap(bitmap);

        bytes=Base64.decode(amThucDuLich.HinhAnh1,Base64.DEFAULT);
        bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageHinhAnhChiTietAmThuc1.setImageBitmap(bitmap);

        bytes=Base64.decode(amThucDuLich.HinhAnh2,Base64.DEFAULT);
        bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageHinhAnhChiTietAmThuc2.setImageBitmap(bitmap);

        bytes=Base64.decode(amThucDuLich.HinhAnh3,Base64.DEFAULT);
        bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageHinhAnhChiTietAmThuc3.setImageBitmap(bitmap);
        binding.textViewTenChiTietDiaDiem.setText(amThucDuLich.TenAT);
        binding.textViewDiaChi.setText(amThucDuLich.Vitri);
        binding.textViewTongQuanAmthuc.setText(amThucDuLich.ThongTin);
    }
}