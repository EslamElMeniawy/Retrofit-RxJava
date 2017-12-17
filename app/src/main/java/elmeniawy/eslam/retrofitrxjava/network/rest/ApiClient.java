package elmeniawy.eslam.retrofitrxjava.network.rest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiClient
 * <p>
 * Created by Eslam El-Meniawy on 17-Dec-2017.
 * CITC - Mansoura University
 */

public class ApiClient {
    private static final String BASE_URL = "http://www.zoftino.com/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
