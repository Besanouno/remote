package basistam.pl.remoteclient.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import basistam.pl.remoteclient.R;
import basistam.pl.remoteclient.enums.SpeakerStatus;
import basistam.pl.remoteclient.service.AudioService;
import basistam.pl.remoteclient.service.AudioServiceImpl;

public class AudioController {

    private final AudioService audioService;

    private SeekBar volumeBar;

    public AudioController(Activity mainActivity) {
        this.audioService = new AudioServiceImpl();
        initVolumeBar(mainActivity);
        initBtnSynchronize(mainActivity);
        initBtnMute(mainActivity);
        initBtnUnmute(mainActivity);
    }

    private void initVolumeBar(Activity mainActivity) {
        this.volumeBar = mainActivity.findViewById(R.id.volumeBar);
        initVolumeBarChangeListener();
        synchronizeAudioVolume();
    }

    private void initVolumeBarChangeListener() {
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioService.setAudioVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void synchronizeAudioVolume() {
        audioService.getAudioVolumeAndCall(param -> volumeBar.setProgress(param));
    }

    private void initBtnSynchronize(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_synchronize)
                .setOnClickListener(view -> synchronizeAudioVolume());
    }

    private void initBtnMute(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_mute)
                .setOnClickListener(v -> audioService.setSpeakersStatus(SpeakerStatus.OFF));
    }

    private void initBtnUnmute(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_unmute)
                .setOnClickListener(v -> audioService.setSpeakersStatus(SpeakerStatus.ON));
    }
}
