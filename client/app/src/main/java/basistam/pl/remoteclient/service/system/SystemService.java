package basistam.pl.remoteclient.service.system;

import basistam.pl.remoteclient.R;
import basistam.pl.remoteclient.enums.SystemType;

public class SystemService {

    private SystemType current;

    public void change() {
        if (current == SystemType.UNIX) {
            setCurrent(SystemType.WINDOWS);
        } else {
            setCurrent(SystemType.UNIX);
        }
    }

    public void setCurrent(SystemType current) {
        this.current = current;
    }

    public SystemType getCurrent() {
        return current;
    }

    public int getIcon() {
        if (current == SystemType.UNIX) {
            return R.drawable.ic_linux;
        } else {
            return R.drawable.ic_win;
        }
    }
}
