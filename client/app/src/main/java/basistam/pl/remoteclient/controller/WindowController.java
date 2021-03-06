package basistam.pl.remoteclient.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import basistam.pl.remoteclient.R;
import basistam.pl.remoteclient.enums.SystemType;
import basistam.pl.remoteclient.service.WindowService;
import basistam.pl.remoteclient.service.WindowServiceImpl;
import basistam.pl.remoteclient.service.address.AddressReader;
import basistam.pl.remoteclient.service.address.AddressService;
import basistam.pl.remoteclient.service.address.AddressWriter;
import basistam.pl.remoteclient.service.system.SystemService;

public class WindowController {

    private final WindowService windowService;
    private final SystemService systemService;

    public WindowController(Activity mainActivity, AddressService addressService) {
        this.windowService = new WindowServiceImpl(addressService);
        this.systemService = new SystemService();
        initAddressField(mainActivity, addressService);
        initBtnSwitch(mainActivity);
        initDirectionalButtons(mainActivity);
        initBtnTab(mainActivity);
    }

    private void initAddressField(Activity mainActivity, AddressReader addressReader) {

        EditText edtAddress = mainActivity.findViewById(R.id.edt_host);
        edtAddress.setText(addressReader.getAddress());
        EditText edtPort = mainActivity.findViewById(R.id.edt_port);
        edtPort.setText(addressReader.getPort());

        final SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("pl.basistam.remote", Context.MODE_PRIVATE);

        mainActivity.findViewById(R.id.btn_save).setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("ADDRESS", edtAddress.getText().toString());
            editor.putString("PORT", edtPort.getText().toString());
            editor.commit();

            Intent i = mainActivity.getPackageManager().getLaunchIntentForPackage( mainActivity.getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mainActivity.startActivity(i);
        });

        String systemType = sharedPreferences.getString("SYSTEM", SystemType.UNIX.name());
        final ImageButton btnSystem = mainActivity.findViewById(R.id.btn_system);

        if (systemType.equals(SystemType.UNIX.name())) {
            systemService.setCurrent(SystemType.UNIX);
            mainActivity.findViewById(R.id.win_audio_panel).setVisibility(View.GONE);
            mainActivity.findViewById(R.id.unix_audio_panel).setVisibility(View.VISIBLE);
        } else {
            systemService.setCurrent(SystemType.WINDOWS);
            mainActivity.findViewById(R.id.unix_audio_panel).setVisibility(View.GONE);
            mainActivity.findViewById(R.id.win_audio_panel).setVisibility(View.VISIBLE);
        }
        btnSystem.setImageResource(systemService.getIcon());

        btnSystem.setOnClickListener(view -> {
            systemService.change();
            btnSystem.setImageResource(systemService.getIcon());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("SYSTEM", systemService.getCurrent().name());
            editor.commit();

            Intent i = mainActivity.getPackageManager().getLaunchIntentForPackage( mainActivity.getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mainActivity.startActivity(i);
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
