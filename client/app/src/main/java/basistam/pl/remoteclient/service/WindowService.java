package basistam.pl.remoteclient.service;

public interface WindowService {
    void startSwitching();
    void stopSwitching();
    void moveRight();
    void moveLeft();
    void moveUp();
    void moveDown();
    void switchTab();
}
