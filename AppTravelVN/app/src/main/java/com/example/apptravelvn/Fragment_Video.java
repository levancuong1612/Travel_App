package com.example.apptravelvn;

import android.app.Dialog;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Fragment_Video extends Fragment {
    View view;
    ListView lsvVideo;
    ArrayList<Video> videos;
    VideoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        lsvVideo = (ListView) view.findViewById(R.id.listView_Video);
        videos = new ArrayList<>();
        Video video = new Video("Hello Việt Nam", R.raw.video1, R.drawable.hinhanhvideo1);
        videos.add(video);
        video = new Video("Việt Nam_ Đi Để Yêu! - Điểm đến Văn hóa và Ẩm thực", R.raw.video2, R.drawable.hinhanhvideo2);
        videos.add(video);
        video = new Video("Clip  45 ngày du lịch Việt Nam  đẹp mê hồn dưới ống kính của 2 chàng Tây", R.raw.video3, R.drawable.hinhanhvideo3);
        videos.add(video);
        video = new Video("Giới thiệu về đất nước Việt Nam", R.raw.video4, R.drawable.hinhanhvideo4);
        videos.add(video);
        adapter = new VideoAdapter(view.getContext(), R.layout.layout_item_video, videos);
        lsvVideo.setAdapter(adapter);
        lsvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Video video1= videos.get(i);
                dialogVideo(video1.Src);
            }
        });
        return view;
    }

    private void dialogVideo(int rs) {
        Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_phat_video);
        VideoView videoView = dialog.findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + "com.example.apptravelvn" + "/" + rs));
        videoView.start();
        MediaController mediaController = new MediaController(view.getContext());
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);

        dialog.show();
    }
}
