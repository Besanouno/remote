package basistam.pl.remoteclient.service;

import android.widget.SeekBar;

public interface AudioService {
    void setAudioVolume(final int volume);
    void getAudioVolumeAndUpdate(final SeekBar volumeBar);
}
