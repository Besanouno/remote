package basistam.pl.remoteclient.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ComputerConnectionService {

    @PUT("remote")
    Call<String> changeVolume(@Query("volume") final int volume);

}
