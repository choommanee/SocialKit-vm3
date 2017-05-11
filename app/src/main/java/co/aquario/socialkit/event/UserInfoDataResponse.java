package co.aquario.socialkit.event;

import co.aquario.socialkit.model.User;

/**
 * Created by matthewlogan on 9/3/14.
 */
public class UserInfoDataResponse {

    private String status;
    private User user;

    public UserInfoDataResponse(String status, User user) {
        this.status = status;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

   }
