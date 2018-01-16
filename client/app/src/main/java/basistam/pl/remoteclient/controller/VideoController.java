package basistam.pl.remoteclient.controller;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;

import basistam.pl.remoteclient.R;
import basistam.pl.remoteclient.service.VideoService;
import basistam.pl.remoteclient.service.VideoServiceImpl;
import basistam.pl.remoteclient.service.address.AddressReader;

public class VideoController {

    private VideoService videoService;

    public VideoController(Activity mainActivity, AddressReader addressReader) {
        this.videoService = new VideoServiceImpl(addressReader);
        initBtnPlayOrPause(mainActivity);
    }

    private void initBtnPlayOrPause(Activity mainActivity) {
        ImageButton imageButton = mainActivity.findViewById(R.id.btn_playPause);
        imageButton.setOnClickListener(view -> {
            videoService.playOrPause();
        });
    }
}
