package basistam.pl.remoteclient.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AudioRetrofit {

    @PUT("remote/unix/audio/volume")
    Call<Integer> setUnixVolumeRequest(@Query("volume") final int volume);

    @GET("remote/unix/audio/volume")
    Call<Integer> getUnixVolumeRequest();

    @PUT("remote/unix/speakers/status")
    Call<String> setUnixSpeakersStatusRequest(@Query("status") final String status);

    @GET("remote/unix/speakers/status")
    Call<String> getUnixSpeakersStatusRequest();

    @GET("remote/win/audio/volume/{dir}")
    Call<String> setWindowsVolumeRequest(@Path("dir") String dir);

    @PUT("remote/audio/{dir}")
    Call<Void> skipRequest(@Path("dir") String dir);
}
