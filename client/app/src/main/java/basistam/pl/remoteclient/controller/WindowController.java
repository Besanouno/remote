package basistam.pl.remoteclient.controller;

import android.app.Activity;
import android.view.MotionEvent;
import android.widget.ImageButton;

import basistam.pl.remoteclient.R;
import basistam.pl.remoteclient.service.WindowService;
import basistam.pl.remoteclient.service.WindowServiceImpl;

public class WindowController {

    private final WindowService windowService;

    public WindowController(Activity mainActivity) {
        windowService = new WindowServiceImpl();
        initBtnSwitch(mainActivity);
        initDirectionalButtons(mainActivity);
        initBtnTab(mainActivity);
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
