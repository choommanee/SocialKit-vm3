package co.aquario.socialkit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostStory {
    @Expose
    public String id;
    @Expose
    public String active;
    @Expose
    public Author author;
    @Expose
    public String google_map_name;
    @SerializedName("post_id")
    @Expose
    public String postId;
    @Expose
    public String recipient_id;
    @Expose
    public String seen;
    @Expose
    public String text;
    @Expose
    public String time;
    @Expose
    public String timeline_id;
    @Expose
    public String timestamp;
    @Expose
    public String type1;
    @Expose
    public String type2;
    @Expose
    public String view;
    @SerializedName("follow_count")
    @Expose
    public int followCount;
    @Expose
    public Follow[] follow;
    @Expose
    public int loveCount;
    @Expose
    public Love[] love;
    @SerializedName("comment_count")
    @Expose
    public int commentCount;
    @Expose
    public Comment[] comment;
    @SerializedName("share_count")
    @Expose
    public int shareCount;
    @Expose
    public Share[] share;
    @SerializedName("media_type")
    @Expose
    public String type;
    @Expose
    public Media media;
    @Expose
    public Clip clip;
    @Expose
    public SoundCloud soundCloud;
    @Expose
    public Youtube youtube;

    public String getId() {
        return id;
    }

    public String getActive() {
        return active;
    }

    public Author getAuthor() {
        return author;
    }

    public String getGoogle_map_name() {
        return google_map_name;
    }

    public String getPostId() {
        return postId;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public String getSeen() {
        return seen;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getTimeline_id() {
        return timeline_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public String getView() {
        return view;
    }

    public int getFollowCount() {
        return followCount;
    }

    public Follow[] getFollow() {
        return follow;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public Love[] getLove() {
        return love;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public Comment[] getComment() {
        return comment;
    }

    public int getShareCount() {
        return shareCount;
    }

    public Share[] getShare() {
        return share;
    }

    public String getType() {
        return type;
    }

    public Media getMedia() {
        return media;
    }

    public Clip getClip() {
        return clip;
    }

    public SoundCloud getSoundCloud() {
        return soundCloud;
    }

    public Youtube getYoutube() {
        return youtube;
    }
}
