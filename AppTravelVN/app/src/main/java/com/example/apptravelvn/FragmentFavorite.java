package com.example.apptravelvn;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

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

public class FragmentFavorite extends Fragment {
    ImageView imgHinhanh;
    GridView gridView;

    ArrayList<TravelLocation> travelLocations;
    TravelAdapter travelLocationAdapter;
    DataBase db;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_favorite,container,false);
        imgHinhanh= (ImageView) view.findViewById(R.id.imageViewTable);
        gridView=(GridView) view.findViewById(R.id.gridViewHinhAnh);
        travelLocations= new ArrayList<>();

//        //tạo database dulich
      db= new DataBase(view.getContext(), "dulich.sqlite", null,1);
//////        //tạo bảng
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://kenhdulichkhampha.com/Media/Articles/tim-ve-co-do-voi-nhung-diem-tham-quan-tuyet-dep-tai-hue.jpg'," +
//                "'Huế','Huế','Địa Chỉ',4,'https://kenhdulichkhampha.com/Media/Articles/tim-ve-co-do-voi-nhung-diem-tham-quan-tuyet-dep-tai-hue.jpg','20 Địa Điểm','Bắc Trung Bộ','652.572 người'," +
//                "'16 Lê Lợi, Vĩnh Ninh, Thành phố Huế, Thừa Thiên Huế','1929')");
//
//
////
//        // Nha Trang
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://wall.vn/wp-content/uploads/2020/04/hinh-anh-nha-trang-7.jpg'," +
//                "'Nha Trang','Nha Trang','Địa Chỉ',4,'https://wall.vn/wp-content/uploads/2020/04/hinh-anh-nha-trang-7.jpg','8 Địa Điểm'," +
//                "'Duyên Hải Nam Trung Bộ','422.601 người','42 Lê Thánh Tôn, phường Tân Lập',' 1987')");
////
////     //đà lạt
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://image.bnews.vn/MediaUpload/Org/2018/12/03/095017_thong-tin-gio-mo-cua-thung-lung-tinh-yeu-hinh6.jpg'," +
//                "'Đà Lạt','Đà Lạt','Địa Chỉ',4,'https://image.bnews.vn/MediaUpload/Org/2018/12/03/095017_thong-tin-gio-mo-cua-thung-lung-tinh-yeu-hinh6.jpg','10 Địa Điểm'," +
//                "'Tây Nguyên','1.415.500 người','Số 01 Trần Phú,  Phường 4, Đà Lạt','1976')");
////
////     //Bạc Liêu
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://etrip4utravel.s3-ap-southeast-1.amazonaws.com/images/product/2020/11/0a0e567a-3869-475e-8a87-121e69bb7347.jpg'," +
//                "'Bạc Liêu','Bạc Liêu','Địa Chỉ',4,'https://etrip4utravel.s3-ap-southeast-1.amazonaws.com/images/product/2020/11/0a0e567a-3869-475e-8a87-121e69bb7347.jpg','10 Địa Điểm'," +
//                "'Đồng bằng sông Cửu Long','962.356 người','Phường 1, thành phố Bạc Liêu','1900')");
////
////
//        //Sóc Trăng
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://thamhiemmekong.com/wp-content/uploads/2020/02/chua-doi.jpg'," +
//                "'Sóc Trăng','Sóc Trăng','Địa Chỉ',4,'https://thamhiemmekong.com/wp-content/uploads/2020/02/chua-doi.jpg','10 Địa Điểm'," +
//                "'Đồng bằng sông Cửu Long','221.430',' phường 2, thành phố Sóc Trăng','1991')");
////
//        //Cần Thơ
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://pystravel.vn/uploads/posts/albums/3749/d615a8fd764d3c69d0dc0425f79a7e7f.jpg'," +
//                "'Cần Thơ','Cần Thơ','Địa Chỉ',4,'https://pystravel.vn/uploads/posts/albums/3749/d615a8fd764d3c69d0dc0425f79a7e7f.jpg','10 Địa Điểm'," +
//                "'Đồng bằng sông Cửu Long','1.244.736 ngườ','phường Tân An, quận Ninh Kiều','1739')");
////
////     //Hà Nội
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://top10vn.org/wp-content/uploads/2021/08/top-dia-diem-du-lich-ninh-binh.jpg'," +
//                "'Hà Nội','Hà Nội','Địa Chỉ',4,'https://top10vn.org/wp-content/uploads/2021/08/top-dia-diem-du-lich-ninh-binh.jpg','10 Địa Điểm'," +
//                "'Đồng bằng sông Hồng','8.053.663 ngườ','phường Lý Thái Tổ, quận Hoàn Kiếm','1010')");
////
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://znews-photo.zadn.vn/w860/Uploaded/mdf_eioxrd/2018_07_14/canh.jpg'," +
//                "'Sapa','Sapa','Địa Chỉ',4,'https://znews-photo.zadn.vn/w860/Uploaded/mdf_eioxrd/2018_07_14/canh.jpg','10 Địa Điểm'," +
//                "'Tây Bắc Bộ','81.857 người','Số 91, phố Xuân Viên, phường Sa Pa','1/1/2020')");
////
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://statics.vntrip.vn/data-v2/data-guide/img_content/1470302452_anh-5.jpg'," +
//                "'Phú Quốc','Phú Quốc','Địa Chỉ',4,'https://statics.vntrip.vn/data-v2/data-guide/img_content/1470302452_anh-5.jpg','10 Địa Điểm'," +
//                "'Đồng bằng sông Cửu Long','179.480 ngườ',' khu phố 1, phường Dương Đông','1/1/2021')");
//
        String search="";
//        Cursor data=db.getData("SELECT * FROM Tinh ");
//
//     travelLocations.clear();
//     while (data.moveToNext()){
//      TravelLocation travelLocation = new TravelLocation();
//      travelLocation.id=data.getInt(0);
//      travelLocation.imageURL=data.getString(1);
//      travelLocation.title=data.getString(2);
//      travelLocation.location=data.getString(3);
//      travelLocation.starRating=data.getFloat(5);
//      travelLocation.hinhanh=data.getString(6);
//      travelLocation.diachi=data.getString(4);
//      travelLocation.tenTinh=data.getString(3);
//      travelLocation.SoLuong=data.getString(7);
//         travelLocation.vung=data.getString(8);
//         travelLocation.danSo=data.getString(9);
//         travelLocation.UBND=data.getString(10);
//         travelLocation.thanhLap=data.getString(11);
//      travelLocations.add(travelLocation);
//     }
     getTinhs();

//     travelLocation.imageURL="https://elead.com.vn/wp-content/uploads/2020/04/hinh-anh-thien-nhien-dep-ho-nuoc-dep.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.tenTinh="Vũng Tàu";
//        travelLocation.SoLuong="20 địa điểm";
//        travelLocations.add(travelLocation);
//
//        travelLocation = new TravelLocation();
//        travelLocation.imageURL="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.tenTinh="Huế";
//        travelLocation.SoLuong="10 địa điểm";
//        travelLocations.add(travelLocation);
//
//        travelLocation = new TravelLocation();
//        travelLocation.imageURL="https://cdn.tgdd.vn//GameApp/-1//dienthoai-12-675x1200-2.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.tenTinh="Phú Quốc";
//        travelLocation.SoLuong="32 địa điểm";
//        travelLocations.add(travelLocation);
//
//        travelLocation = new TravelLocation();
//        travelLocation.imageURL="https://i.pinimg.com/originals/66/40/dd/6640dded16eae3a7adef2c2f7e9b83f3.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.tenTinh="Nha Trang";
//        travelLocation.SoLuong="10 địa điểm";
//        travelLocations.add(travelLocation);
//
//        travelLocation = new TravelLocation();
//        travelLocation.imageURL="https://i.pinimg.com/originals/66/40/dd/6640dded16eae3a7adef2c2f7e9b83f3.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.tenTinh="Đà Lạt";
//        travelLocation.SoLuong="15 địa điểm";
//        travelLocations.add(travelLocation);
//
//        travelLocation = new TravelLocation();
//        travelLocation.imageURL="https://i.pinimg.com/originals/66/40/dd/6640dded16eae3a7adef2c2f7e9b83f3.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.tenTinh="Bạc Liêu";
//        travelLocation.SoLuong="5 địa điểm";
//        travelLocations.add(travelLocation);
//
//        travelLocation = new TravelLocation();
//        travelLocation.imageURL="https://i.pinimg.com/originals/66/40/dd/6640dded16eae3a7adef2c2f7e9b83f3.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.tenTinh="Sapa";
//        travelLocation.SoLuong="10 địa điểm";
//        travelLocations.add(travelLocation);
//
//        travelLocation = new TravelLocation();
//        travelLocation.imageURL="https://i.pinimg.com/originals/66/40/dd/6640dded16eae3a7adef2c2f7e9b83f3.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.tenTinh="Sóc Trăng";
//        travelLocation.SoLuong="6 địa điểm";
//        travelLocations.add(travelLocation);
//
//        travelLocation = new TravelLocation();
//        travelLocation.imageURL="https://i.pinimg.com/originals/66/40/dd/6640dded16eae3a7adef2c2f7e9b83f3.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//        travelLocation.tenTinh="Cần Thơ";
//        travelLocation.SoLuong="10 địa điểm";
//        travelLocations.add(travelLocation);
//
//        travelLocation = new TravelLocation();
//        travelLocation.imageURL="https://i.pinimg.com/originals/66/40/dd/6640dded16eae3a7adef2c2f7e9b83f3.jpg";
//        travelLocation.title="Địa điểm 1";
//        travelLocation.location="Địa chỉ 1";
//        travelLocation.starRating=4.0f;
//        travelLocation.hinhanh="https://i.pinimg.com/originals/84/d1/bf/84d1bf9abbeebcab5e3bae86ffe9159f.jpg";
//
//        travelLocation.tenTinh="Hà Nội";
//        travelLocation.SoLuong="10 địa điểm";


        return view;
    }
    public  void getTinhs(){

        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("Tinh")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult()!=null){

                        for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                            TravelLocation user= new TravelLocation();
                            user.ID=documentSnapshot.getString("ID");
                            user.id=1;
                            user.location=documentSnapshot.getString(DBTinh.TENTINH);

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
                            travelLocationAdapter= new TravelAdapter(view.getContext(),R.layout.layout_table_image,travelLocations);


                            gridView.setAdapter(travelLocationAdapter);
                        }else{

                        }
                    }else{

                    }
                });
    }

}
