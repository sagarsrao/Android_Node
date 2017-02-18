package acadgild.android.node.com.android_node.server;

import acadgild.android.node.com.android_node.constants.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sumit on 19-Jan-17.
 */



/*Retrofit instance created*/
public class ApiClient  {

    //public static final String BASE_URL = Co
    public static Retrofit retrofit = null;


    /*public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }*/

    /*Get the retrofit instance*/
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */

    /*Return type of this method is ClientSide which is the interface  */
    public static ClientSide getApiService() {
        return getRetrofitInstance().create(ClientSide.class);
    }





}
