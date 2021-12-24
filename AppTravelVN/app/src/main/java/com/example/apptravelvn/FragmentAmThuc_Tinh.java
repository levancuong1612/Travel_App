package com.example.apptravelvn;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.apptravelvn.DBFireBases.DBAmThuc;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentAmThuc_Tinh extends Fragment {
    ArrayList<AmThucDuLich> amThucDuLiches;
    AmThucAdapter AmThucDuLichAdapter;
    GridView gridView;
    DataBase db;
    View view;
    TravelLocation travelLocation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_amthuc_tinh,container,false);
        Bundle bundle=getArguments();
      travelLocation = (TravelLocation) bundle.getSerializable("travel");

        gridView=(GridView)view.findViewById(R.id.gridViewHinhAnhDiaDiemDuLich);
        gridView=(GridView)view.findViewById(R.id.gridViewHinhAnhAmThuc);
        db= new DataBase(view.getContext(), "dulich.sqlite", null,1);
        Cursor data=db.getData("SELECT * FROM AmThuc WHERE IDTinh="+ travelLocation.id);
        amThucDuLiches= new ArrayList<>();


//        public  long IDAmThuc, IDTinh;
//        public  String TenAT, ThongTin,ThongTin2,MoTa,Vitri,HinhAnh;
//        public  int LuotXem,DanhGia,HinhAnh2;
//        while (data.moveToNext()){
//            AmThucDuLich at= new AmThucDuLich();
//            at.IDAmThuc=data.getInt(0);
//            at.TenAT=data.getString(1);
//            at.ThongTin=data.getString(2);
//            at.Vitri=data.getString(3);
//            at.HinhAnh = data.getString(4);
//            at.HinhAnh1=data.getString(5);
//            at.HinhAnh2=data.getString(6);
//            at.HinhAnh3=data.getString(7);
//            at.IDTinh=data.getInt(8);
//            amThucDuLiches.add(at);
//        }
//       AmThucDuLich amThucDuLich= new AmThucDuLich();
//        amThucDuLich.TenAT="Món Ăn 1";
//        amThucDuLich.Vitri="Hà Nội";
//        amThucDuLich.MoTa="Thông tin mô tả của  Ẩm thực";
//        amThucDuLich.HinhAnh="https://nethue.com.vn/temp/-uploaded-banner_Ch%E1%BA%A3%20Ph%C6%B0%E1%BB%A3ng%201_cr_420x420.jpg";
//        amThucDuLiches.add(amThucDuLich);
//
//
//        amThucDuLich= new AmThucDuLich();
//        amThucDuLich.TenAT="Món Ăn 1";
//        amThucDuLich.Vitri="Hà Nội";
//        amThucDuLich.MoTa="Thông tin mô tả của  Ẩm thực";
//        amThucDuLich.HinhAnh="https://statics.vinpearl.com/dac-san-da-nang-1.jpg";
//        amThucDuLiches.add(amThucDuLich);
//
//
//        amThucDuLich= new AmThucDuLich();
//        amThucDuLich.TenAT="Món Ăn 1";
//        amThucDuLich.Vitri="Hà Nội";
//        amThucDuLich.MoTa="Thông tin mô tả của  Ẩm thực";
//       amThucDuLich.HinhAnh="https://staticproxy.mytourcdn.com/480x360,q90/resources/pictures/locations/7uh1466826276.jpg";
//        amThucDuLiches.add(amThucDuLich);
//
//
//        amThucDuLich= new AmThucDuLich();
//        amThucDuLich.TenAT="Món Ăn 1";
//        amThucDuLich.Vitri="Hà Nội";
//        amThucDuLich.MoTa="Thông tin mô tả của  Ẩm thực";
//      amThucDuLich.HinhAnh="https://media.cungphuot.info/2017/04/23757/banh-uot-long-ga.jpg";
//        amThucDuLiches.add(amThucDuLich);
//        amThucDuLich= new AmThucDuLich();
//        amThucDuLich.TenAT="Món Ăn 1";
//        amThucDuLich.Vitri="Hà Nội";
//        amThucDuLich.MoTa="Thông tin mô tả của  Ẩm thực";
//     amThucDuLich.HinhAnh="https://top10tphcm.com/wp-content/uploads/2019/01/top-dac-san-lao-cai-11.jpg";
//        amThucDuLiches.add(amThucDuLich);
//
//
//        amThucDuLich= new AmThucDuLich();
//        amThucDuLich.TenAT="Món Ăn 1";
//        amThucDuLich.Vitri="Hà Nội";
//        amThucDuLich.MoTa="Thông tin mô tả của  Ẩm thực";
//       amThucDuLich.HinhAnh="https://tindulich.org/wp-content/uploads/2020/09/am-thuc-1.jpg";
//        amThucDuLiches.add(amThucDuLich);

    getAmThucs();
    gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            AmThucDuLich amthuc= amThucDuLiches.get(i);
            AlertDialog.Builder dialogXoa= new AlertDialog.Builder(view.getContext());
            dialogXoa.setMessage("Bạn có muốn xóa "+ amthuc.TenAT +" không");
            dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    delete(amthuc.ID);

                }
            });
            dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialogXoa.show();
            return true;
        }
    });
        return view;
    }





    public  void delete(String id){
        try {

            FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
            HashMap<String,Object> data= new HashMap<>();


            dataBase.collection("AmThuc").document(id).delete().addOnSuccessListener(documentReference -> {
                Toast.makeText(view.getContext(), "Xóa  thành công", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(exception->{
                Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            });


        }catch (Exception e){
            Toast.makeText(view.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();

        }
    }
    public  void getAmThucs(){
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("AmThuc")
                .whereEqualTo("IDDiaDiem",travelLocation.ID)
                .addSnapshotListener(eventListener);
    }
    private  final EventListener<QuerySnapshot> eventListener=(value, error)->{
        if(error!=null){
            return ;
        }if(value!=null){
            for(DocumentChange documentChange:value.getDocumentChanges()){
                if(documentChange.getType()==DocumentChange.Type.ADDED){
                    AmThucDuLich user= new AmThucDuLich();
                    user.ID=documentChange.getDocument().getId();
                    user.TenAT=documentChange.getDocument().getString(DBAmThuc.TENAMTHUC);
                    user.ThongTin=documentChange.getDocument().getString(DBAmThuc.THONGTIN);
                    user.Vitri=documentChange.getDocument().getString(DBAmThuc.DIACHI);
                    user.HinhAnh=documentChange.getDocument().getString(DBAmThuc.ANHBIA);
                    user.HinhAnh1=documentChange.getDocument().getString(DBAmThuc.ANH1);
                    user.HinhAnh2=documentChange.getDocument().getString(DBAmThuc.ANH2);
                    user.HinhAnh3=documentChange.getDocument().getString(DBAmThuc.ANH3);
                    user.IDTINH=documentChange.getDocument().getString(DBAmThuc.IDDIADIEM);
                    amThucDuLiches.add(user);
                }
            }
            AmThucDuLichAdapter= new AmThucAdapter(view.getContext(),R.layout.layout_amthuc_table,amThucDuLiches);
            gridView.setAdapter(AmThucDuLichAdapter);
        }
    };
}
