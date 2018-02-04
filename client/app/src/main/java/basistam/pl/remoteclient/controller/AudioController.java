package basistam.pl.remoteclient.controller;

import android.app.Activity;
import android.widget.SeekBar;

import basistam.pl.remoteclient.R;
import basistam.pl.remoteclient.enums.SpeakerStatus;
import basistam.pl.remoteclient.service.AudioService;
import basistam.pl.remoteclient.service.AudioServiceImpl;
import basistam.pl.remoteclient.service.address.AddressReader;

public class AudioController {

    private final AudioService audioService;

    private SeekBar volumeBar;

    public AudioController(Activity mainActivity, AddressReader addressReader) {
        this.audioService = new AudioServiceImpl(addressReader);
        initVolumeBar(mainActivity);
        initBtnSynchronize(mainActivity);
        initBtnMute(mainActivity);
        initBtnUnmute(mainActivity);
        initBtnNext(mainActivity);
        initBtnPrev(mainActivity);
        initBtnVolumeUp(mainActivity);
        initBtnVolumeDown(mainActivity);
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
                audioService.setUnixAudioVolume(progress);
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
        audioService.getUnixAudioVolumeAndCall(param -> volumeBar.setProgress(param));
    }

    private void initBtnSynchronize(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_synchronize)
                .setOnClickListener(view -> synchronizeAudioVolume());
    }

    private void initBtnMute(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_mute)
                .setOnClickListener(v -> audioService.setUnixSpeakersStatus(SpeakerStatus.OFF));
    }

    private void initBtnUnmute(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_unmute)
                .setOnClickListener(v -> audioService.setUnixSpeakersStatus(SpeakerStatus.ON));
    }

    private void initBtnNext(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_next)
                .setOnClickListener(v -> audioService.next());
    }

    private void initBtnPrev(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_prev)
                .setOnClickListener(v -> audioService.prev());
    }

    private void initBtnVolumeUp(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_volume_up)
                .setOnClickListener(v -> audioService.setWinAudioVolumeUp());
    }

    private void initBtnVolumeDown(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_volume_down)
                .setOnClickListener(v -> audioService.setWinAudioVolumeDown());
    }
}
