package basistam.pl.remoteclient.service;

import basistam.pl.remoteclient.utils.Callback;

public interface AudioService {
    void setAudioVolume(final int volume);
    void getAudioVolumeAndCall(final Callback<Integer> callback);
}
