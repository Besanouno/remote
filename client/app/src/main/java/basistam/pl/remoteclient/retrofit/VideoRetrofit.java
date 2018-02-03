package basistam.pl.remoteclient.retrofit;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface VideoRetrofit {

    @PUT("remote/video")
    Call<Void> playOrPause();

}
