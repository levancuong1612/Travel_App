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
import com.example.apptravelvn.DBFireBases.DBDiaDiem;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentDiaDiem_Tinh  extends Fragment {
    ArrayList<DiaDiemDuLich> diaDiemDuLiches;
    DiaDiemDuLichAdapter diaDiemDuLichAdapter;
    GridView gridView;
    DataBase db;
    View view;
    TravelLocation travelLocation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_diadiem_tinh,container,false);
        Bundle bundle=getArguments();
     travelLocation= (TravelLocation) bundle.getSerializable("travel");

        gridView=(GridView)view.findViewById(R.id.gridViewHinhAnhDiaDiemDuLich);

        diaDiemDuLiches= new ArrayList<>();


getDiaDiems();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                DiaDiemDuLich diadiem= diaDiemDuLiches.get(i);
                AlertDialog.Builder dialogXoa= new AlertDialog.Builder(view.getContext());
                dialogXoa.setMessage("Bạn có muốn xóa "+ diadiem.TenDD +" không");
                dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(diadiem.IDDiaDiem);

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


            dataBase.collection("DiaDiem").document(id).delete().addOnSuccessListener(documentReference -> {
                Toast.makeText(view.getContext(), "Xóa  thành công", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(exception->{
                Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            });


        }catch (Exception e){
            Toast.makeText(view.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();

        }
    }
    public  void getDiaDiems(){
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("DiaDiem")
                .whereEqualTo("IDDiaDiem",travelLocation.ID)
                .addSnapshotListener(eventListener);
    }
    private  final EventListener<QuerySnapshot> eventListener=(value, error)->{
        if(error!=null){
            return ;
        }if(value!=null){
            for(DocumentChange documentChange:value.getDocumentChanges()){
                if(documentChange.getType()==DocumentChange.Type.ADDED){
                    DiaDiemDuLich user= new DiaDiemDuLich();
                    user.IDDiaDiem=documentChange.getDocument().getId();
                    user.TenDD=documentChange.getDocument().getString(DBDiaDiem.TENDIADIEM);
                    user.ThongTin=documentChange.getDocument().getString(DBDiaDiem.THONGTIN);
                    user.ViTri=documentChange.getDocument().getString(DBDiaDiem.DIACHI);
                    user.HinhAnh=documentChange.getDocument().getString(DBDiaDiem.ANHBIA);
                    user.HinhAnh1=documentChange.getDocument().getString(DBDiaDiem.ANH1);
                    user.HinhAnh2=documentChange.getDocument().getString(DBDiaDiem.ANH2);
                    user.HinhAnh3=documentChange.getDocument().getString(DBDiaDiem.ANH3);
                    user.IDTinh=documentChange.getDocument().getString(DBDiaDiem.IDDIADIEM);
                    diaDiemDuLiches.add(user);
                }
            }
            diaDiemDuLichAdapter= new DiaDiemDuLichAdapter(view.getContext(),R.layout.layout_diadiem_table,diaDiemDuLiches);

            gridView.setAdapter(diaDiemDuLichAdapter);
        }
    };
}
