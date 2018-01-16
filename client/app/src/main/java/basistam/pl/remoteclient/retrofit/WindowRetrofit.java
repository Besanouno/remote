package basistam.pl.remoteclient.retrofit;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WindowRetrofit {

    @PUT("remote/windows/switch")
    Call<Void> switchWindow(@Query("status") final String status);

    @PUT("remote/move/{dir}")
    Call<Void> move(@Path("dir") final String dir);

    @PUT("remote/tabs/switch")
    Call<Void> switchTab();
}
