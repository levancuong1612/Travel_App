package com.example.apptravelvn;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import javax.security.auth.callback.Callback;

public class FragmentThongTinChiTiet  extends Fragment {
    TextView txtTenTinh,txtVung,txtDanSo,txtUBND,txtThanhLap;
    ImageView imgHinhAnhTinh;
    WebView webView;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.fragment_thongtin_chitiet,container,false);


         AnhXa();
         Bundle bundle=getArguments();
         TravelLocation travelLocation= (TravelLocation) bundle.getSerializable("travel");
         txtTenTinh.setText(travelLocation.title);
         txtThanhLap.setText(travelLocation.thanhLap);
         txtVung.setText(travelLocation.vung);
         txtUBND.setText(travelLocation.UBND);
         txtDanSo.setText(travelLocation.danSo);
        Glide.with(view.getContext()).load(travelLocation.imageURL).into(imgHinhAnhTinh);
        WebSettings webSettings= webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());
        webView.loadUrl(travelLocation.diachi);

         return  view;
    }
    private  class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
    void AnhXa(){
        txtTenTinh=(TextView) view.findViewById(R.id.textViewTenTinh);
        txtDanSo=(TextView) view.findViewById(R.id.textViewDanSo);
        txtVung=(TextView) view.findViewById(R.id.textViewVung);
        txtUBND=(TextView) view.findViewById(R.id.textViewTruSo);
        txtThanhLap=(TextView) view.findViewById(R.id.textViewThanhLap);
        imgHinhAnhTinh=(ImageView) view.findViewById(R.id.imageViewHinhAnhTinh);
        webView= (WebView) view.findViewById(R.id.webviewBanDo);

    }
}
