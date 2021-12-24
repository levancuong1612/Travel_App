package com.example.apptravelvn;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.apptravelvn.databinding.ActivitySignUpBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DataBase db ;
    private String encodeImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignUpBinding.inflate(getLayoutInflater());
        db= new DataBase(SignUpActivity.this, "dulich.sqlite", null,1);
        setContentView(binding.getRoot());
        suKien();
    }

    void suKien(){
        binding.imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);

            }
        });
        binding.buttonSignUp.setOnClickListener(v->insertCSDL());
    }


    void insertCSDL(){
        loading(true);
        if(isValidSignUpUpDetails()){
            String ten=binding.inputName.getText().toString();
            String email = binding.inputEmail.getText().toString();
            String sdt=binding.inputSDT.getText().toString();
            String diaChi= binding.inputDiaChi.getText().toString();
            String gioitinh="";
            if(binding.radioNam.isChecked()){
                gioitinh="Nam";
            }else {
                gioitinh="Nữ";
            }
            String Matkhau=binding.inputPassword.getText().toString();
            String anh=encodeImage;

            db.QueryData("INSERT INTO TaiKhoan VALUES(null," +
                    " '"+ten+"'," +
                    ""+sdt+"," +
                    "'"+email+"'," +
                    "'"+Matkhau+"'," +
                    "'"+diaChi+"'," +
                    "'"+gioitinh+"'," +
                    "'"+encodeImage+"')");
            showToast("Thêm Thành Công");
            finish();
        }else{
            loading(false);
        }



    }
    private  String getEncodeImage(Bitmap bitmap){
        int previewWidth=200;
        int previewHeight=bitmap.getHeight() * previewWidth/bitmap.getWidth();

        Bitmap previewBitmap=Bitmap.createScaledBitmap(bitmap,previewWidth,previewHeight,false);
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    private  final ActivityResultLauncher<Intent> pickImage= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode()==RESULT_OK){
                    if(result.getData()!=null){
                        Uri imageUri= result.getData().getData();
                        try{
                            InputStream inputStream= getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                            binding.imageProfile.setImageBitmap(bitmap);
                            binding.textAddImage.setVisibility(View.GONE);
                            encodeImage= getEncodeImage(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    void showToast(String mess){
        Toast.makeText(SignUpActivity.this, mess, Toast.LENGTH_SHORT).show();
    }
    private  Boolean isValidSignUpUpDetails(){
        if(encodeImage ==null){
            showToast("Không để trống hình ảnh");
            return false;
        }else if(binding.inputName.getText().toString().trim().isEmpty()){
            showToast("Vui lòng nhập tên");
            return false;
        }else if(binding.inputEmail.getText().toString().trim().isEmpty()){
            showToast("Vui lòng nhập email");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()){
            showToast("Vui lòng chọn nhập đúng định dạng email");
            return false;
        }else if(binding.inputPassword.getText().toString().trim().isEmpty()){
            showToast("Vui lòng nhập mật khẩu");
            return false;
        }else if(binding.inputComfirmPassword.getText().toString().trim().isEmpty()){
            showToast("vui lòng xác nhận mật khẩu");
            return false;
        }else if(!binding.inputPassword.getText().toString().equals(binding.inputComfirmPassword.getText().toString())){
            return false;
        }else{
            return true;
        }
    }
    private void loading(Boolean isLoading){
        if(isLoading){
            binding.buttonSignUp.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonSignUp.setVisibility(View.VISIBLE);
        }
    }
}