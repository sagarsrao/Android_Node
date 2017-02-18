package acadgild.android.node.com.android_node.server;

import java.util.HashMap;
import java.util.List;

import acadgild.android.node.com.android_node.model.ReturnRes;
import acadgild.android.node.com.android_node.model.UserSignin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sumit on 19-Jan-17.
 */

public interface ClientSide {

//I can have  all the  get and post method
@FormUrlEncoded
@POST("/api/user/authenticate")
//Call<List<UserSignin>> request(@Body HashMap<String, String> parameters);
Call<UserSignin> request(@FieldMap HashMap<String,String> parameters);



/*
@POST("/api/resetpass")
Call<List<UserSignin>> request1(@Body HashMap<String,String> parameters);


    @POST("api/resetpass/chg")
    Call<List<UserSignin>> request2(@Body HashMap<String,String> parameters);
*/


    @FormUrlEncoded
    @POST("/api/user/signup")
   // Call<List<UserSignin>> request3(@Body HashMap<String,String> parameters);

    Call<ReturnRes> request3(@FieldMap HashMap<String,String> parameters);

    /*@POST("/api/chgpass")
    Call<List<UserSignin>> request4(@Body HashMap<String,String> parameters);
*/





















}
