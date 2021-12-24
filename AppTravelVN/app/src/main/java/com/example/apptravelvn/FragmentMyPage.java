package com.example.apptravelvn;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.makeramen.roundedimageview.RoundedImageView;

public class FragmentMyPage extends Fragment {
    View view;
    TextView txtTenNguoiDung,txtSDT,txtEmail,txtDiaChi,txtGioiTinh;
    RoundedImageView imgProfile;
    ImageView imgSignout;
    private PreferenceManager preferenceManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_mypage,container,false);
        preferenceManager= new PreferenceManager(view.getContext());
        setView();
        loadUser();

        imgSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferenceManager.putBoolean(TaiKhoan.KEY_USER_SIGNED_IN,false);
                preferenceManager.putString(TaiKhoan.KEY_USER_ID,"");
                Intent intent= new Intent(view.getContext(),SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        return  view;
    }
    void loadUser(){
        txtTenNguoiDung.setText(preferenceManager.getString(TaiKhoan.KEY_USER_NAME));
        txtSDT.setText(preferenceManager.getString(TaiKhoan.KEY_USER_SDT));
        txtEmail.setText(preferenceManager.getString(TaiKhoan.KEY_USER_MAIL));
        txtGioiTinh.setText(preferenceManager.getString(TaiKhoan.KEY_USER_GIOITINH));
        txtDiaChi.setText(preferenceManager.getString(TaiKhoan.KEY_USER_DIACHI));
        byte[] bytes= Base64.decode(preferenceManager.getString(TaiKhoan.KEY_USER_HINHANH),Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        imgProfile.setImageBitmap(bitmap);
    }
    void setView(){
        imgSignout=(ImageView)view.findViewById(R.id.imageViewSignOut);
        txtTenNguoiDung=(TextView) view.findViewById(R.id.textViewTenNguoiDung);
        txtDiaChi=(TextView) view.findViewById(R.id.textViewUserDiaChi);
        txtEmail=(TextView) view.findViewById(R.id.textViewEmail);
        txtGioiTinh=(TextView) view.findViewById(R.id.textViewGioiTinh);
        txtSDT=(TextView) view.findViewById(R.id.textViewSDT);
        imgProfile=(RoundedImageView) view.findViewById(R.id.imageViewProfile);

    }
}
