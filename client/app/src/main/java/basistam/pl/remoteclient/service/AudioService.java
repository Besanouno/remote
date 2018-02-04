package basistam.pl.remoteclient.service;

import basistam.pl.remoteclient.utils.Callback;

public interface AudioService {
    void setUnixAudioVolume(final int volume);
    void getUnixAudioVolumeAndCall(final Callback<Integer> callback);
    void setUnixSpeakersStatus(final String status);
    void getUnixSpeakersStatusAndCall(final Callback<String> callback);
    void setWinAudioVolumeUp();
    void setWinAudioVolumeDown();
    void next();
    void prev();
}
