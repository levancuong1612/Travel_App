package com.example.apptravelvn;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;

public class FragmentLocationHot extends Fragment {
    TextView txt;
    View view;
    DataBase db;
    List<TravelLocation> travelLocations= new ArrayList<>();
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
      view= inflater.inflate(R.layout.fragment_location_hot,container,false);

        db= new DataBase(view.getContext(), "dulich.sqlite", null,1);
        db.QueryData("CREATE TABLE  IF NOT EXISTS Tinh(ID INTEGER  PRIMARY KEY  AUTOINCREMENT,imageURL VARCHAR(200),title VARCHAR(50)," +
                "tentinh VARCHAR(50),diachi VARCHAR(100),danhgia FLOAT,hinhAnh VARCHAR(200), soluong VARCHAR(50),vung VARCHAR(100),danso VARCHAR(50),ubnd VARCHAR(100),thanhlap VARCHAR(100))");

        db.QueryData("CREATE TABLE  IF NOT EXISTS AmThuc(" +
                "ID INTEGER  PRIMARY KEY  AUTOINCREMENT" +
                ",TenAmThuc VARCHAR(200)" +
                ",ThongTin TEXT," +
                "DiaChi VARCHAR(200)" +
                ",HinhAnhBia TEXT" +
                ",HinhAnh1 TEXT" +
                ",HinhAnh2 TEXT" +
                ",HinhAnh3 TEXT" +
                ",IDTinh INTEGER)" );
        db.QueryData("CREATE TABLE  IF NOT EXISTS DiaDiem(" +
                "ID INTEGER  PRIMARY KEY  AUTOINCREMENT" +
                ",TenDiaDiem VARCHAR(200)" +
                ",ThongTin TEXT," +
                "DiaChi VARCHAR(200)" +
                ",HinhAnhBia TEXT" +
                ",HinhAnh1 TEXT" +
                ",HinhAnh2 TEXT" +
                ",HinhAnh3 TEXT" +
                ",IDTinh INTEGER)" );

        db.QueryData("CREATE TABLE IF NOT EXISTS TaiKhoan(" +
                "ID INTEGER  PRIMARY KEY  AUTOINCREMENT " +
                ",TenNguoiDung VARCHAR(100)" +
                ",SDT INTEGER" +
                ",Email VARCHAR(100)" +
                ",MatKhau VARCHAR(100)" +
                ",DiaChi VARCHAR(200)" +
                ",GioiTinh VARCHAR(20)" +
                ",HinhAnh TEXT)");
//        //Huế
//        db.QueryData("UPDATE Tinh SET danhgia=4.5 WHERE ID=1");
//        //Nha trang
//        db.QueryData("UPDATE Tinh SET danhgia=4.0 WHERE ID=2");
//        //Đà lạt
//        db.QueryData("UPDATE Tinh SET danhgia=5.0 WHERE ID=3");
//        //Bạc liêu
//        db.QueryData("UPDATE Tinh SET danhgia=4.0 WHERE ID=4");
//        //Sóc trăng
//        db.QueryData("UPDATE Tinh SET danhgia=3.5 WHERE ID=5");
//        //Cần thơ
//        db.QueryData("UPDATE Tinh SET danhgia=4.5 WHERE ID=6");
//        //hà nội
//        db.QueryData("UPDATE Tinh SET danhgia=5.0 WHERE ID=7");
//        //Sapa
//        db.QueryData("UPDATE Tinh SET danhgia=5.0 WHERE ID=8");
//        //phú quốc
//        db.QueryData("UPDATE Tinh SET danhgia=4.5 WHERE ID=9");
//        db.QueryData("DELETE FROM TaiKhoan");
//        db.QueryData("INSERT INTO TaiKhoan VALUES(null," +
//                " 'Lê Văn Cương'," +
//                "0839961244," +
//                "'chimcanhcutcuong@gmail.com'," +
//                "'123456'," +
//                "'Bạc Liêu'," +
//                "'Nam'," +
//                "'')");




////
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




//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://tourhot24h.vn/wp-content/uploads/2019/09/con-phung-2.jpg'," +
//                "'Bến Tre','Bến Tre','https://goo.gl/maps/Z1KqSQp4buLGie5f8',4,'https://tourhot24h.vn/wp-content/uploads/2019/09/con-phung-2.jpg','5 Địa Điểm'," +
//                "'Đồng bằng sông Cửu Long','1.288.206 người',' Số 7, đường Cách Mạng Tháng Tám, phường An Hội, thành phố Bến Tre','1/1/1900')");
//
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://static.mservice.io/blogscontents/momo-upload-api-210510111514-637562421141377879.jpg'," +
//                "'Đà Nẵng','Đà Nẵng','https://goo.gl/maps/TEzpJ7Jfa8YbSsdn6',4.5,'https://static.mservice.io/blogscontents/momo-upload-api-210510111514-637562421141377879.jpg','13 Địa Điểm'," +
//                "'Duyên hải Nam Trung Bộ','1.134.310 người',' Số 24 đường Trần Phú, phường Thạch Thang, quận Hải Châu','24/5/1889')");
//
//        db.QueryData("INSERT INTO Tinh VALUES (null,'https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/01/lac-canh-dai-nam-van-hien.jpg'," +
//                "'Bình Dương','Bình Dương','https://goo.gl/maps/GmEn5UNyy1ZkhPtL6',4,'https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/01/lac-canh-dai-nam-van-hien.jpg','10 Địa Điểm'," +
//                "'Đông Nam Bộ','2.627.195 người','Đường Lê Lợi, phường Hoà Phú, thành phố Thủ Dầu Một ','22/10/1956')");



        Cursor data=db.getData("SELECT  * FROM Tinh ORDER BY danhgia DESC LIMIT 7");

        travelLocations.clear();
        getTinhs();
//        while (data.moveToNext()){
//            TravelLocation travelLocation = new TravelLocation();
//            travelLocation.id=data.getInt(0);
//            travelLocation.imageURL=data.getString(1);
//            travelLocation.title=data.getString(2);
//            travelLocation.location=data.getString(3);
//            travelLocation.diachi=data.getString(4);
//            travelLocation.starRating=data.getFloat(5);
//            travelLocation.hinhanh=data.getString(6);
//            travelLocation.tenTinh=data.getString(3);
//            travelLocation.SoLuong=data.getString(7);
//            travelLocation.vung=data.getString(8);
//            travelLocation.danSo=data.getString(9);
//            travelLocation.UBND=data.getString(10);
//            travelLocation.thanhLap=data.getString(11);
//            travelLocations.add(travelLocation);
//        }


//        addDataToFirestore("1","https://kenhdulichkhampha.com/Media/Articles/tim-ve-co-do-voi-nhung-diem-tham-quan-tuyet-dep-tai-hue.jpg","Huế","Huế","https://goo.gl/maps/CoZvApiLe4rncEM47","4.5","https://kenhdulichkhampha.com/Media/Articles/tim-ve-co-do-voi-nhung-diem-tham-quan-tuyet-dep-tai-hue.jpg","20 Địa Điểm","Bắc Trung Bộ","652.572 người","16 Lê Lợi, Vĩnh Ninh, Thành phố Huế, Thừa Thiên Huế","1929");
//        addDataToFirestore("2","https://wall.vn/wp-content/uploads/2020/04/hinh-anh-nha-trang-7.jpg","Nha Trang","Nha Trang","https://goo.gl/maps/MsiecsS6dZbqxizW9","4.0","https://wall.vn/wp-content/uploads/2020/04/hinh-anh-nha-trang-7.jpg","8 Địa Điểm","Duyên Hải Nam Trung Bộ","422.601 người","42 Lê Thánh Tôn, phường  Tân Lập"," 1987");
//        addDataToFirestore("3","https://image.bnews.vn/MediaUpload/Org/2018/12/03/095017_thong-tin-gio-mo-cua-thung-lung-tinh-yeu-hinh6.jpg","Đà Lạt","Đà Lạt","https://goo.gl/maps/yN47j1p5VeQV1DJMA","5.0","https://image.bnews.vn/MediaUpload/Org/2018/12/03/095017_thong-tin-gio-mo-cua-thung-lung-tinh-yeu-hinh6.jpg","10 Địa Điểm","Tây Nguyên","1.415.500 người","Số 01 Trần Phú,  Phường 4, Đà Lạt","1976");
//        addDataToFirestore("4","https://etrip4utravel.s3-ap-southeast-1.amazonaws.com/images/product/2020/11/0a0e567a-3869-475e-8a87-121e69bb7347.jpg","Bạc Liêu","Bạc Liêu","https://goo.gl/maps/6R2PpEYzWjS3LoBt5","4.0","https://etrip4utravel.s3-ap-southeast-1.amazonaws.com/images/product/2020/11/0a0e567a-3869-475e-8a87-121e69bb7347.jpg","10 Địa Điểm","Đồng bằng sông Cửu Long","962.356 người","Phường 1, thành phố Bạc Liêu","1900");
//        addDataToFirestore("5","https://thamhiemmekong.com/wp-content/uploads/2020/02/chua-doi.jpg","Sóc Trăng","Sóc Trăng","https://goo.gl/maps/frdLGuBBE8Jgyrz58","3.5","https://thamhiemmekong.com/wp-content/uploads/2020/02/chua-doi.jpg","10 Địa Điểm","Đồng bằng sông Cửu Long","221.430"," phường 2, thành phố Sóc Trăng","1991");
//        addDataToFirestore("6","https://pystravel.vn/uploads/posts/albums/3749/d615a8fd764d3c69d0dc0425f79a7e7f.jpg","Cần Thơ","Cần Thơ","https://goo.gl/maps/hFUBFLctYmE8qsjY8","4.5","https://pystravel.vn/uploads/posts/albums/3749/d615a8fd764d3c69d0dc0425f79a7e7f.jpg","10 Địa Điểm","Đồng bằng sông Cửu Long","1.244.736 ngườ","phường Tân An, quận Ninh Kiều","1739");
//        addDataToFirestore("7","https://top10vn.org/wp-content/uploads/2021/08/top-dia-diem-du-lich-ninh-binh.jpg","Hà Nội","Hà Nội","https://goo.gl/maps/jLRLFHKaKXpkWT2T8","5.0","https://top10vn.org/wp-content/uploads/2021/08/top-dia-diem-du-lich-ninh-binh.jpg","10 Địa Điểm","Đồng bằng sông Hồng","8.053.663 người","phường Lý Thái Tổ, quận Hoàn Kiếm","1010");
//        addDataToFirestore("8","https://znews-photo.zadn.vn/w860/Uploaded/mdf_eioxrd/2018_07_14/canh.jpg","Sapa","Sapa","https://goo.gl/maps/q2wL7AhTtA4ypaNT7","5.0","https://znews-photo.zadn.vn/w860/Uploaded/mdf_eioxrd/2018_07_14/canh.jpg","5 Địa Điểm","Tây Bắc Bộ","81.857 người","Số 91, phố Xuân Viên, phường Sa Pa","1/1/2020");
//        addDataToFirestore("9","https://statics.vntrip.vn/data-v2/data-guide/img_content/1470302452_anh-5.jpg","Phú Quốc","Phú Quốc","https://goo.gl/maps/DKtyLoQ3sr3UyAs3A","4.5","https://statics.vntrip.vn/data-v2/data-guide/img_content/1470302452_anh-5.jpg","13 Địa Điểm","Đồng bằng sông Cửu Long","179.480 người"," khu phố 1, phường Dương Đông","1/1/2021");
//        addDataToFirestore("10","https://tourhot24h.vn/wp-content/uploads/2019/09/con-phung-2.jpg","Bến Tre","Bến Tre","https://goo.gl/maps/Z1KqSQp4buLGie5f8","4.0","https://tourhot24h.vn/wp-content/uploads/2019/09/con-phung-2.jpg","10 Địa Điểm","Đồng bằng sông Cửu Long","1.288.206 người"," Số 7, đường Cách Mạng Tháng Tám, phường An Hội, thành phố Bến Tre","1/1/1900");
//        addDataToFirestore("11","https://static.mservice.io/blogscontents/momo-upload-api-210510111514-637562421141377879.jpg","Bến Tre","Bến Tre","https://goo.gl/maps/TEzpJ7Jfa8YbSsdn6","4.5","https://static.mservice.io/blogscontents/momo-upload-api-210510111514-637562421141377879.jpg","5 Địa Điểm","Duyên hải Nam Trung Bộ","1.134.310 người"," Số 24 đường Trần Phú, phường Thạch Thang, quận Hải Châu","24/5/1889");
//        addDataToFirestore("12","https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/01/lac-canh-dai-nam-van-hien.jpg","Bình Dương","Bình Dương","https://goo.gl/maps/GmEn5UNyy1ZkhPtL6","4.0","https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2018/01/lac-canh-dai-nam-van-hien.jpg","13 Địa Điểm","Đông Nam Bộ","2.627.195 người","Đường Lê Lợi, phường Hoà Phú, thành phố Thủ Dầu Một ","22/10/1956");


        return  view;
    }
    private  void  addDataToFirestore(String id, String imgURL, String title, String tentinh, String diachi, String danhgia, String hinhanh, String soluong,String vung, String danso, String ubnd, String thanhlap){
        FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
        HashMap<String,Object> data= new HashMap<>();
        data.put("ID",id);
        data.put("imageURL",imgURL);
        data.put("title",title);
        data.put("tentinh",tentinh);
        data.put("diachi",diachi);
        data.put("danhgia",danhgia);
        data.put("hinhanh",hinhanh);
        data.put("soluong",soluong);
        data.put("vung",vung);
        data.put("danso",danso);
        data.put("ubnd",ubnd);
        data.put("thanhlap",thanhlap);
        dataBase.collection("Tinh").add(data).addOnSuccessListener(documentReference -> {
            Toast.makeText(view.getContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(exception->{
            Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
    public  void getTinhs(){

        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("Tinh").orderBy(DBTinh.DANHGIA, Query.Direction.DESCENDING).limit(7)
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
