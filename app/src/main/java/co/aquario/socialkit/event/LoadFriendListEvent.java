package co.aquario.socialkit.event;

/**
 * Created by Mac on 3/3/15.
 */
public class LoadFriendListEvent {
    private int userId;
    private String type;
    private int page;
    private int perPage;

    public LoadFriendListEvent(int userId, String type, int page, int perPage) {
        this.userId = userId;
        this.type = type;
        this.page = page;
        this.perPage = perPage;
    }

    public int getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }
}
