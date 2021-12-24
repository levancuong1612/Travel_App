package com.example.apptravelvn;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.apptravelvn.DBFireBases.DBTinh;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class FragmentLocationNew extends Fragment {
    DataBase db;
    List<TravelLocation> travelLocations= new ArrayList<>();
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_location_new,container,false);
        ViewPager2 locationViewPager=view.findViewById(R.id.locationviewpage);

//        db= new DataBase(view.getContext(), "dulich.sqlite", null,1);
//        Cursor data=db.getData("SELECT  * FROM Tinh ORDER BY ID DESC LIMIT 7");

        travelLocations.clear();

        getTinhs();
        return view;
    }
    public  void getTinhs(){

        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("Tinh").orderBy("ID", Query.Direction.DESCENDING).limit(7)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult()!=null){

                        for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                            TravelLocation user= new TravelLocation();
                            user.ID=documentSnapshot.getString("ID");
                            user.id=1;
                            user.location=documentSnapshot.getString(DBTinh.TENTINH);;

                            user.imageURL=documentSnapshot.getString(DBTinh.IMGURL);
                            user.title=documentSnapshot.getString(DBTinh.TITLE);
                            user.tenTinh=documentSnapshot.getString(DBTinh.TENTINH);
                            user.diachi=documentSnapshot.getString(DBTinh.DIACHI);
                            user.starRating=Float.parseFloat(documentSnapshot.getString(DBTinh.DANHGIA));
                            user.hinhanh=documentSnapshot.getString(DBTinh.HINHANH);
                            user.SoLuong=documentSnapshot.getString(DBTinh.SOLUONG);
                            user.vung=documentSnapshot.getString(DBTinh.VUNG);
                            user.danSo=documentSnapshot.getString(DBTinh.DANSO);
                            user.UBND=documentSnapshot.getString(DBTinh.UBND);
                            user.thanhLap=documentSnapshot.getString(DBTinh.THANHLAP);
                            travelLocations.add(user);
                        }
                        if(travelLocations.size()>0){
                            ViewPager2 locationViewPager=view.findViewById(R.id.locationviewpage);
                            locationViewPager.setAdapter(new TravelLocationAdapter(travelLocations));

                            locationViewPager.setClipToPadding(false);
                            locationViewPager.setClipChildren(false);

                            locationViewPager.setOffscreenPageLimit(3);
                            locationViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
                            CompositePageTransformer compositePageTransformer= new CompositePageTransformer();
                            compositePageTransformer.addTransformer(new MarginPageTransformer(20));
                            compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                                @Override
                                public void transformPage(@NonNull View page, float position) {
                                    float r=1-Math.abs(position);

                                }
                            });

                            locationViewPager.setPageTransformer(compositePageTransformer);
                        }else{

                        }
                    }else{

                    }
                });
    }
}
