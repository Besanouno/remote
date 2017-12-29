package basistam.pl.remoteclient.controller;

import android.widget.ImageButton;
import android.widget.SeekBar;

import basistam.pl.remoteclient.R;
import basistam.pl.remoteclient.enums.SpeakerStatus;
import basistam.pl.remoteclient.service.AudioService;
import basistam.pl.remoteclient.service.AudioServiceImpl;

public class AudioController {

    private final AudioService audioService;

    private SeekBar volumeBar;

    public AudioController(SeekBar volumeBar, ImageButton btnSynchronize, ImageButton btnMute, ImageButton btnUnmute) {
        this.audioService = new AudioServiceImpl();
        initVolumeBar(volumeBar);
        initBtnSynchronize(btnSynchronize);
        initBtnMute(btnMute);
        initBtnUnmute(btnUnmute);
    }

    private void initVolumeBar(SeekBar volumeBar) {
        this.volumeBar = volumeBar;
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

    private void initBtnSynchronize(ImageButton btnSynchronize) {
        btnSynchronize.setOnClickListener(view -> synchronizeAudioVolume());
    }

    private void initBtnMute(ImageButton btnMute) {
        btnMute.setOnClickListener(v -> audioService.setSpeakersStatus(SpeakerStatus.OFF));
    }

    private void initBtnUnmute(ImageButton btnUnmute) {
        btnUnmute.setOnClickListener(v -> audioService.setSpeakersStatus(SpeakerStatus.ON));

    }

}
