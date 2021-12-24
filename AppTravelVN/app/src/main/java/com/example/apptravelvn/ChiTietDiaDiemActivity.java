package com.example.apptravelvn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChiTietDiaDiemActivity extends AppCompatActivity {

    TextView txtThongtin, txtDiaDiem,txtAmThuc,txtTenTinh;
    ImageView imageViewAnhTinh;
    TravelLocation travel;
    FloatingActionButton btnAdd,btnFood,btnPlace;
    boolean check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dia_diem);
        setView();
        Intent intent= getIntent();
       travel= (TravelLocation) intent.getSerializableExtra("travel");
        txtTenTinh.setText(travel.title);
        Glide.with(getApplicationContext()).load(travel.hinhanh).into(imageViewAnhTinh);
        callFragment("ThongTin");
        btnFood.setVisibility(View.INVISIBLE);
        btnPlace.setVisibility(View.INVISIBLE);
        txtThongtin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                txtThongtin.setTextColor(Color.GREEN);
                txtDiaDiem.setTextColor(Color.BLACK);
                txtAmThuc.setTextColor(Color.BLACK);
                callFragment("ThongTin");

            }
        });
        txtDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtThongtin.setTextColor(Color.BLACK);
                txtDiaDiem.setTextColor(Color.GREEN);
                txtAmThuc.setTextColor(Color.BLACK);
                callFragment("DiaDiem");
            }
        });
        txtAmThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtThongtin.setTextColor(Color.BLACK);
                txtDiaDiem.setTextColor(Color.BLACK);
                txtAmThuc.setTextColor(Color.GREEN);
                callFragment("AmThuc");
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check==false){
                    btnFood.setVisibility(View.VISIBLE);
                    btnPlace.setVisibility(View.VISIBLE);
                    check=true;
                }else {
                    btnFood.setVisibility(View.INVISIBLE);
                    btnPlace.setVisibility(View.INVISIBLE);
                    check=false;
                }
            }
        });
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(getApplicationContext(),PostAmThucActivity.class);
                intent1.putExtra("travel",travel);
                startActivity(intent1);
            }
        });
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(getApplicationContext(),PostDiaDiemActivity.class);
                intent1.putExtra("travel",travel);
                startActivityForResult(intent1,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==101){
            recreate();

        }
    }

    void setView(){
        txtThongtin =(TextView) findViewById(R.id.textViewThongTin);
        txtDiaDiem=(TextView) findViewById(R.id.textViewDiaDiem);
        txtAmThuc=(TextView) findViewById(R.id.textViewAmThuc);
        txtTenTinh=(TextView)findViewById(R.id.textViewTenTinh);
        imageViewAnhTinh=(ImageView)findViewById(R.id.imageViewHinhAnhTinh);
        txtThongtin.setTextColor(Color.GREEN);
        txtDiaDiem.setTextColor(Color.BLACK);
        txtAmThuc.setTextColor(Color.BLACK);
        btnAdd=(FloatingActionButton) findViewById(R.id.buttonFloatAdd);
        btnFood=(FloatingActionButton) findViewById(R.id.buttonFloatAmThuc);
        btnPlace=(FloatingActionButton) findViewById(R.id.buttonFloatDiaDiem);
    }
    void callFragment(String ten){
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        if(ten.equals("ThongTin")){
            FragmentThongTinChiTiet  fragmentA= new FragmentThongTinChiTiet();
            Bundle bundle=new Bundle();
            bundle.putSerializable("travel",travel);
            fragmentA.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragmentchitietdiadiem,fragmentA);
        }
        else if(ten.equals("DiaDiem")){
            FragmentDiaDiem_Tinh fragmentHome= new FragmentDiaDiem_Tinh();
            Bundle bundle=new Bundle();
            bundle.putSerializable("travel",travel);
            fragmentHome.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragmentchitietdiadiem,fragmentHome);
        }else if(ten.equals("AmThuc")){
            FragmentAmThuc_Tinh fragmentMyPage= new FragmentAmThuc_Tinh();
            Bundle bundle=new Bundle();
            bundle.putSerializable("travel",travel);
            fragmentMyPage.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragmentchitietdiadiem,fragmentMyPage);
        }
        fragmentTransaction.commit();
    }
}