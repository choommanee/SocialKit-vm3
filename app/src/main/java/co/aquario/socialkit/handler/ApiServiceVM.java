package co.aquario.socialkit.handler;

import java.util.Map;

import co.aquario.socialkit.event.FriendDataResponse;
import co.aquario.socialkit.event.TimelineDataResponse;
import co.aquario.socialkit.event.UserInfoDataResponse;
import co.aquario.socialkit.model.LoginData;
import co.aquario.socialkit.model.RegisterData;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by matthewlogan on 9/3/14.
 */
public interface ApiServiceVM {
    @POST("/1.0/auth")
    public void login(@QueryMap Map<String, String> options,
                                Callback<LoginData> responseJson);

    @POST("/1.0/fbAuth")
    public void fbLogin(@QueryMap Map<String, String> options,
                      Callback<LoginData> responseJson);

    @POST("/user/register")
    public void register(@QueryMap Map<String, String> options,
                      Callback<RegisterData> responseJson);

    @GET("/user/otp")
    public void otp(@QueryMap Map<String, String> options);

    @POST("/1.0/posts/home_timeline/{id}")
    public void getHomeTimeline(@Path("id") int id,@QueryMap Map<String, String> options,
                        Callback<TimelineDataResponse> responseJson);

    @POST("/1.0/posts/user_timeline/{id}")
    public void getUserTimeline(@Path("id") int id,@QueryMap Map<String, String> options,
                                Callback<TimelineDataResponse> responseJson);

    @POST("/1.0/user/{id}")
    public void getUserInfo(@Path("id") int id,@QueryMap Map<String, String> options,
                                Callback<UserInfoDataResponse> responseJson);

    @POST("/1.0/followers/{id}")
    public void getFollower(@Path("id") int id,@QueryMap Map<String, String> options,
                            Callback<FriendDataResponse> responseJson);

    @POST("/1.0/followings/{id}")
    public void getFollowing(@Path("id") int id,@QueryMap Map<String, String> options,
                            Callback<FriendDataResponse> responseJson);

    @POST("/1.0/friends/{id}")
    public void getFriend(@Path("id") int id,@QueryMap Map<String, String> options,
                            Callback<FriendDataResponse> responseJson);



}
