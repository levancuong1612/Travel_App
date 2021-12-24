package com.example.apptravelvn;

import java.io.Serializable;

public class Video implements Serializable {
    public  String tenVideo;
    public  int hinhAnh,Src;

    public Video(String tenVideo, int src, int hinhAnh) {
        this.tenVideo = tenVideo;
        Src = src;
        this.hinhAnh = hinhAnh;
    }
}
