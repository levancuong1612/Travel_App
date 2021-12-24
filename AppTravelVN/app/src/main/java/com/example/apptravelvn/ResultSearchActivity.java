package com.example.apptravelvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.apptravelvn.DBFireBases.DBDiaDiem;
import com.example.apptravelvn.DBFireBases.DBTinh;
import com.example.apptravelvn.databinding.ActivityResultSearchBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ResultSearchActivity extends AppCompatActivity {
    ActivityResultSearchBinding binding;

    ArrayList<TravelLocation> travelLocations;
    TravelAdapter travelLocationAdapter;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResultSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        travelLocations= new ArrayList<>();
        db= new DataBase(getApplicationContext(), "dulich.sqlite", null,1);

        Intent intent= getIntent();
        String search=intent.getStringExtra("search");


        travelLocations.clear();
      getDiaDiems(search);

    }
    public  void getDiaDiems(String search){
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("Tinh")
                .whereEqualTo(DBTinh.TENTINH,search)
                .addSnapshotListener(eventListener);
    }
    private  final EventListener<QuerySnapshot> eventListener=(value, error)->{
        if(error!=null){
            return ;
        }if(value!=null){
            for(DocumentChange documentChange:value.getDocumentChanges()){
                if(documentChange.getType()==DocumentChange.Type.ADDED){
                    TravelLocation user= new TravelLocation();
                    user.ID=documentChange.getDocument().getString("ID");
                    user.id=1;
                    user.location=documentChange.getDocument().getString(DBTinh.TENTINH);

                    user.imageURL=documentChange.getDocument().getString(DBTinh.IMGURL);
                    user.title=documentChange.getDocument().getString(DBTinh.TITLE);
                    user.tenTinh=documentChange.getDocument().getString(DBTinh.TENTINH);
                    user.diachi=documentChange.getDocument().getString(DBTinh.DIACHI);
                    user.starRating=Float.parseFloat(documentChange.getDocument().getString(DBTinh.DANHGIA));
                    user.hinhanh=documentChange.getDocument().getString(DBTinh.HINHANH);
                    user.SoLuong=documentChange.getDocument().getString(DBTinh.SOLUONG);
                    user.vung=documentChange.getDocument().getString(DBTinh.VUNG);
                    user.danSo=documentChange.getDocument().getString(DBTinh.DANSO);
                    user.UBND=documentChange.getDocument().getString(DBTinh.UBND);
                    user.thanhLap=documentChange.getDocument().getString(DBTinh.THANHLAP);
                    travelLocations.add(user);
                }
            }
            travelLocationAdapter= new TravelAdapter(getApplicationContext(),R.layout.layout_table_image,travelLocations);


            binding.gridViewHinhAnh.setAdapter(travelLocationAdapter);
        }
    };
}