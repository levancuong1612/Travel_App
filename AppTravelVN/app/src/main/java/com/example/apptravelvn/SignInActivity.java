package com.example.apptravelvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apptravelvn.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    DataBase db;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db= new DataBase(SignInActivity.this, "dulich.sqlite", null,1);

        preferenceManager= new PreferenceManager(getApplicationContext());
        if(preferenceManager.getBoolean(TaiKhoan.KEY_USER_SIGNED_IN)){
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        binding.buttonSignIn.setOnClickListener(v->dangNhap());
        binding.textCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    void dangNhap(){
        int i=0;
        Cursor data=db.getData("SELECT * FROM TaiKhoan WHERE (Email='"+binding.inputEmail.getText().toString()+"' AND MatKhau ='"+binding.inputPassword.getText().toString()+"')");
        while (data.moveToNext()){
            i++;
            preferenceManager.putBoolean(TaiKhoan.KEY_USER_SIGNED_IN,true);
           preferenceManager.putString(TaiKhoan.KEY_USER_ID,String.valueOf(data.getInt(0)));
            preferenceManager.putString(TaiKhoan.KEY_USER_NAME,data.getString(1));
            preferenceManager.putString(TaiKhoan.KEY_USER_SDT,data.getString(2));

            preferenceManager.putString(TaiKhoan.KEY_USER_MAIL,data.getString(3));
            preferenceManager.putString(TaiKhoan.KEY_USER_PASS,data.getString(4));
            preferenceManager.putString(TaiKhoan.KEY_USER_DIACHI,data.getString(5));
            preferenceManager.putString(TaiKhoan.KEY_USER_GIOITINH,data.getString(6));
            preferenceManager.putString(TaiKhoan.KEY_USER_HINHANH,data.getString(7));
            Intent intent= new Intent(SignInActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
        if(i==0){
            showToast("Tài khoản không chính xác");
            loading(false);
        }
    }

//    public  final  int ID=0;
//    public  final  String TenTaiKhoan="user";
//    public  final  String MatKhau="123456";
//    public  final  String TrangThai="";
    private void loading(Boolean isLoading){
        if(isLoading){
            binding.buttonSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonSignIn.setVisibility(View.VISIBLE);
        }
    }
    void showToast(String mes){
        Toast.makeText(SignInActivity.this, mes, Toast.LENGTH_SHORT).show();
    }
    void signIn(){
        loading(true);




    }
}