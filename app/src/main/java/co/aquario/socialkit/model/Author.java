package co.aquario.socialkit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.aquario.socialkit.util.EndpointManager;

/**
 * Created by root1 on 2/22/15.
 */
public class Author {

    @Expose
    public String id;
    @Expose
    public String username;
    @Expose
    public String name;
    @Expose
    @SerializedName("avatar_id")
    public String avatarId;
    @Expose
    @SerializedName("avatar")
    public String avatarPath;

    public String getAvatarPath() {
        return EndpointManager.getPath(avatarPath);
    }

}
