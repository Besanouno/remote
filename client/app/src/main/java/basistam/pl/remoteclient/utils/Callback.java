package basistam.pl.remoteclient.utils;

public interface Callback<T> {
    void call(T param);
}
