package basistam.pl.remoteclient.listeners;


import android.widget.SeekBar;

import basistam.pl.remoteclient.service.ComputerController;

public class VolumeChangeListener implements SeekBar.OnSeekBarChangeListener {

    private final ComputerController computerController = new ComputerController();

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        computerController.setAudioVolume(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

}
