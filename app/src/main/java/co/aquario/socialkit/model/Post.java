package co.aquario.socialkit.model;

import java.util.ArrayList;

public class Post {

    private String imageProfileUrl;
    private String name;
    private String date;
    private String loveCount;
    private String commentCount;
    private String shareCount;
    private String shortMessage;
    private String message;
    private String viewCount;
    private String thumbUrl;

    private Author author;
    private Media media;
    private ArrayList<Love> love = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Follow> follows = new ArrayList<>();

    public Post(String imageProfileUrl, String name, String date, String loveCount, String commentCount,
                String shareCount, String message, String shortMessage, String viewCount, String thumbUrl) {

        this.imageProfileUrl = imageProfileUrl;
        this.name = name;
        this.date = date;
        this.loveCount = loveCount;
        this.commentCount = commentCount;
        this.shareCount = shareCount;
        this.message = message;
        this.shortMessage = shortMessage;
        this.viewCount = viewCount;
        this.thumbUrl = thumbUrl;

    }

    public String getImageProfileUrl() {
        return imageProfileUrl;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLoveCount() {
        return loveCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public String getShareCount() {
        return shareCount;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public Author getAuthor() {
        return author;
    }

    public Media getMedia() {
        return media;
    }

    public ArrayList<Love> getLove() {
        return love;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Follow> getFollows() {
        return follows;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
