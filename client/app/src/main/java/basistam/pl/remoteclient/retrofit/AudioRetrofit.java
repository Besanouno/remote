package basistam.pl.remoteclient.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AudioRetrofit {

    @PUT("remote")
    Call<Integer> changeVolumeRequest(@Query("volume") final int volume);

    @GET("remote")
    Call<Integer> getVolumeRequest();

}
