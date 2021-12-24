package com.example.apptravelvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.example.apptravelvn.databinding.ActivityThongTinDiaDiemDlactivityBinding;
import com.example.apptravelvn.databinding.FragmentThongtinChitietBinding;

public class ThongTinDiaDiemDLActivity extends AppCompatActivity {
    ActivityThongTinDiaDiemDlactivityBinding binding;
    DiaDiemDuLich diaDiemDuLich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityThongTinDiaDiemDlactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent= getIntent();
        diaDiemDuLich= (DiaDiemDuLich) intent.getSerializableExtra("travel");
        setView();

    }
    void  setView(){
        byte[] bytes= Base64.decode(diaDiemDuLich.HinhAnh,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageChiTietDiaDiem.setImageBitmap(bitmap);

        bytes=Base64.decode(diaDiemDuLich.HinhAnh1,Base64.DEFAULT);
        bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageHinhAnhChiTietDiaDiem1.setImageBitmap(bitmap);

        bytes=Base64.decode(diaDiemDuLich.HinhAnh2,Base64.DEFAULT);
        bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageHinhAnhChiTietDiaDiem2.setImageBitmap(bitmap);

        bytes=Base64.decode(diaDiemDuLich.HinhAnh3,Base64.DEFAULT);
        bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageHinhAnhChiTietDiaDiem3.setImageBitmap(bitmap);
        binding.textViewTenChiTietDiaDiem.setText(diaDiemDuLich.TenDD);
        binding.textViewViTriDiaDiem.setText(diaDiemDuLich.ViTri);
        binding.textViewTongQuanDiaDiem.setText(diaDiemDuLich.ThongTin);
    }
}