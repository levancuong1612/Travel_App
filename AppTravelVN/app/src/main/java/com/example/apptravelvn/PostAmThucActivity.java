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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apptravelvn.DBFireBases.DBAmThuc;
import com.example.apptravelvn.databinding.ActivityPostAmThucBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class PostAmThucActivity extends AppCompatActivity {

    ActivityPostAmThucBinding binding;
    private String encodeImage,encodeImage2,encodeImage3,encodeImage1;
    RoundedImageView imgPost;
    TravelLocation travel;
    DataBase db ;
    private  int btn=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPostAmThucBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent= getIntent();
        travel= (TravelLocation) intent.getSerializableExtra("travel");
        showToast(travel.ID);
      //  db= new DataBase(this, "dulich.sqlite", null,1);
        setListener();
    }
    private  String getEncodeImage(Bitmap bitmap){
        int previewWidth=398;
        int previewHeight=bitmap.getHeight() * previewWidth/bitmap.getWidth();

        Bitmap previewBitmap=Bitmap.createScaledBitmap(bitmap,previewWidth,previewHeight,false);
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    boolean KTDL(){
        if(String.valueOf(binding.inputTextTenAmThuc.getText()).isEmpty()){
            binding.inputTextTenAmThuc.setError("Tên ẩm thực không được bỏ trống");
            return  false;
        }
        else if(String.valueOf(binding.inputTextThongTin.getText()).isEmpty()){
            binding.inputTextTenAmThuc.setError("Thông tin ẩm thực không được bỏ trống");
            return false;
        }else if(String.valueOf(binding.inputTextDiaChiAmThuc.getText()).isEmpty()){
            binding.inputTextTenAmThuc.setError("Địa chỉ ẩm thực không được bỏ trống");
            return false;
        }else if(encodeImage.isEmpty() || encodeImage1.isEmpty() ||encodeImage2.isEmpty()||encodeImage3.isEmpty()){
            Toast.makeText(PostAmThucActivity.this, "Vui lòng load đủ số lượng hình ảnh yêu cầu", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;


    }
    void setListener(){
        binding.imageViewPostAmThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn=1;
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);

            }
        });
        binding.imageViewPostAmThuc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn=2;
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);


            }
        });
        binding.imageViewPostAmThuc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn=3;
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);


            }
        });
        binding.imageViewPostAmThuc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn=4;
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);


            }
        });

        binding.buttonDangAmThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(KTDL()){
                    insertCSDL();
                }
            }
        });

    }
    void insertCSDL(){
        try {
            String tenAmThuc=binding.inputTextTenAmThuc.getText().toString();
            String thongTin = binding.inputTextThongTin.getText().toString();
            String diaChi=binding.inputTextDiaChiAmThuc.getText().toString();
            String anhBia= encodeImage;
            String anh1=encodeImage1;
            String anh2=encodeImage2;
            String anh3= encodeImage3;
            FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
            HashMap<String,Object> data= new HashMap<>();
            data.put(DBAmThuc.TENAMTHUC,tenAmThuc);
            data.put(DBAmThuc.THONGTIN,thongTin);
            data.put(DBAmThuc.DIACHI,diaChi);
            data.put(DBAmThuc.ANHBIA,anhBia);
            data.put(DBAmThuc.ANH1,anh1);
            data.put(DBAmThuc.ANH2,anh2);
            data.put(DBAmThuc.ANH3,anh3);
            data.put(DBAmThuc.IDDIADIEM,travel.ID);
            dataBase.collection("AmThuc").add(data).addOnSuccessListener(documentReference -> {
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(exception->{
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            });
            setResult(101);
            finish();

        }catch (Exception e){
            showToast("Thêm Thất bại");
        }

    }
 void showToast(String mes){
     Toast.makeText(PostAmThucActivity.this, mes, Toast.LENGTH_SHORT).show();
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
                            if(btn==1){
                                binding.imageViewPostAmThuc.setImageBitmap(bitmap);
                                encodeImage= getEncodeImage(bitmap);
                            }
                            if(btn==2){
                                binding.imageViewPostAmThuc1.setImageBitmap(bitmap);
                                encodeImage1= getEncodeImage(bitmap);
                            }
                            if(btn==3){
                                binding.imageViewPostAmThuc2.setImageBitmap(bitmap);
                                encodeImage2= getEncodeImage(bitmap);
                            }
                            if(btn==4){
                                binding.imageViewPostAmThuc3.setImageBitmap(bitmap);
                                encodeImage3= getEncodeImage(bitmap);

                            }




                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}