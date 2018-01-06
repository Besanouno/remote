package basistam.pl.remoteclient.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AudioRetrofit {

    @PUT("remote/audio/volume")
    Call<Integer> setVolumeRequest(@Query("volume") final int volume);

    @GET("remote/audio/volume")
    Call<Integer> getVolumeRequest();

    @PUT("remote/speakers/status")
    Call<String> setSpeakersStatusRequest(@Query("status") final String status);

    @GET("remote/speakers/status")
    Call<String> getSpeakersStatusRequest();

}
