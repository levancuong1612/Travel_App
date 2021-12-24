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
import android.widget.Toast;

import com.example.apptravelvn.DBFireBases.DBAmThuc;
import com.example.apptravelvn.DBFireBases.DBDiaDiem;
import com.example.apptravelvn.databinding.ActivityPostDiaDiemBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class PostDiaDiemActivity extends AppCompatActivity {

    ActivityPostDiaDiemBinding binding;
    DataBase db ;
    private String encodeImage,encodeImage2,encodeImage3,encodeImage1;
    TravelLocation travelLocation;
    private  int btn=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPostDiaDiemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent= getIntent();
        travelLocation= (TravelLocation) intent.getSerializableExtra("travel");
        db= new DataBase(this, "dulich.sqlite", null,1);
        setListener();
    }
    private  String getEncodeImage(Bitmap bitmap){
        int previewWidth=398;
        int previewHeight=bitmap.getHeight() * previewWidth/bitmap.getWidth();

        Bitmap previewBitmap=Bitmap.createScaledBitmap(bitmap,previewWidth,previewHeight,false);
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    boolean KTDL(){
        if(String.valueOf(binding.inputTextTenDiaChi.getText()).isEmpty()){
            binding.inputTextTenDiaChi.setError("Tên địa điểm không được bỏ trống");
            return  false;
        }
        else if(String.valueOf(binding.inputTextThongTin.getText()).isEmpty()){
            binding.inputTextTenDiaChi.setError("Thông tin địa điểm không được bỏ trống");
            return false;
        }else if(String.valueOf(binding.inputTextDiaChiDiaDiem.getText()).isEmpty()){
            binding.inputTextDiaChiDiaDiem.setError("Địa chỉ địa điểm không được bỏ trống");
            return false;
        }else if(encodeImage.isEmpty() || encodeImage1.isEmpty() ||encodeImage2.isEmpty()||encodeImage3.isEmpty()){
            Toast.makeText(PostDiaDiemActivity.this, "Vui lòng load đủ số lượng hình ảnh yêu cầu", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;


    }

    void setListener(){
        binding.imageViewPostDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn=1;
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);

            }
        });
        binding.imageViewPostDiaDiem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn=2;
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);


            }
        });
        binding.imageViewPostDiaDiem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn=3;
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);


            }
        });
        binding.imageViewPostDiaDiem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn=4;
                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);


            }
        });

        binding.buttonDangDiaDiem.setOnClickListener(new View.OnClickListener() {
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
            String tenAmThuc=binding.inputTextTenDiaChi.getText().toString();
            String thongTin = binding.inputTextThongTin.getText().toString();
            String diaChi=binding.inputTextDiaChiDiaDiem.getText().toString();
            String anhBia= encodeImage;
            String anh1=encodeImage1;
            String anh2=encodeImage2;
            String anh3= encodeImage3;
//            db.QueryData("INSERT INTO DiaDiem VALUES(null," +
//                    " '"+tenAmThuc+"'," +
//                    "'"+thongTin+"'," +
//                    "'"+diaChi+"'," +
//                    "'"+anhBia+"'," +
//                    "'"+anh1+"'," +
//                    "'"+anh2+"'," +
//                    "'"+anh3+"'," +
//                    "'"+travelLocation.id+"')");
//            showToast("Thêm Thành Công");
            FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
            HashMap<String,Object> data= new HashMap<>();
            data.put(DBDiaDiem.TENDIADIEM,tenAmThuc);
            data.put(DBDiaDiem.THONGTIN,thongTin);
            data.put(DBDiaDiem.DIACHI,diaChi);
            data.put(DBDiaDiem.ANHBIA,anhBia);
            data.put(DBDiaDiem.ANH1,anh1);
            data.put(DBDiaDiem.ANH2,anh2);
            data.put(DBDiaDiem.ANH3,anh3);
            data.put(DBDiaDiem.IDDIADIEM,travelLocation.ID);
            dataBase.collection("DiaDiem").add(data).addOnSuccessListener(documentReference -> {
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
        Toast.makeText(PostDiaDiemActivity.this, mes, Toast.LENGTH_SHORT).show();
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
                                binding.imageViewPostDiaChi.setImageBitmap(bitmap);
                                encodeImage= getEncodeImage(bitmap);
                            }
                            if(btn==2){
                                binding.imageViewPostDiaDiem1.setImageBitmap(bitmap);
                                encodeImage1= getEncodeImage(bitmap);
                            }
                            if(btn==3){
                                binding.imageViewPostDiaDiem2.setImageBitmap(bitmap);
                                encodeImage2= getEncodeImage(bitmap);
                            }
                            if(btn==4){
                                binding.imageViewPostDiaDiem3.setImageBitmap(bitmap);
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