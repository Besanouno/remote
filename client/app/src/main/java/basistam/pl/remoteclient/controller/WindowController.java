package basistam.pl.remoteclient.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageButton;

import basistam.pl.remoteclient.R;
import basistam.pl.remoteclient.service.WindowService;
import basistam.pl.remoteclient.service.WindowServiceImpl;
import basistam.pl.remoteclient.service.address.AddressReader;
import basistam.pl.remoteclient.service.address.AddressService;
import basistam.pl.remoteclient.service.address.AddressWriter;

public class WindowController {

    private final WindowService windowService;

    public WindowController(Activity mainActivity, AddressService addressService) {
        windowService = new WindowServiceImpl(addressService);
        initAddressField(mainActivity, addressService);
        initBtnSwitch(mainActivity);
        initDirectionalButtons(mainActivity);
        initBtnTab(mainActivity);
    }

    private void initAddressField(Activity mainActivity, AddressReader addressReader) {

        EditText edtAddress = mainActivity.findViewById(R.id.edt_address);
        edtAddress.setText(addressReader.getAddress());
        EditText edtPort = mainActivity.findViewById(R.id.edt_port);
        edtPort.setText(addressReader.getPort());

        mainActivity.findViewById(R.id.btn_save).setOnClickListener(view -> {
            SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("pl.basistam.remote", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("ADDRESS", edtAddress.getText().toString());
            editor.putString("PORT", edtPort.getText().toString());
            editor.commit();
        });
    }

    private void initBtnSwitch(Activity mainActivity) {
        ImageButton btnSwitch = mainActivity.findViewById(R.id.btn_windows);
        btnSwitch.setOnTouchListener(
                (view, motionEvent) -> {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            windowService.startSwitching();
                            break;
                        case MotionEvent.ACTION_UP:
                            windowService.stopSwitching();
                            break;
                    }
                    return false;
                });
    }

    private void initDirectionalButtons(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_right)
                .setOnClickListener(view -> windowService.moveRight());
        mainActivity
                .findViewById(R.id.btn_left)
                .setOnClickListener(view -> windowService.moveLeft());
        mainActivity
                .findViewById(R.id.btn_up)
                .setOnClickListener(view -> windowService.moveUp());
        mainActivity
                .findViewById(R.id.btn_down)
                .setOnClickListener(view -> windowService.moveDown());
    }

    private void initBtnTab(Activity mainActivity) {
        mainActivity
                .findViewById(R.id.btn_tabs)
                .setOnClickListener(view -> windowService.switchTab());
    }
}
