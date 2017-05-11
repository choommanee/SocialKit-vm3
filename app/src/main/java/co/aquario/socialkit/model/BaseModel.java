package co.aquario.socialkit.model;

import com.google.gson.Gson;

/**
 * Created by Mac on 3/10/15.
 */
public class BaseModel {

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
