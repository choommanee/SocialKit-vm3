package co.aquario.socialkit.model;

/**
 * Created by root1 on 3/5/15.
 */
public class Live {

    String urlLive;
    String photoLive;
    String nameLive;
    String hours;
    String minutes;
    String seconds;
    String thumb;

    public Live(String urlLive, String photoLive, String nameLive, String hours, String minutes, String seconds, String thumb) {
        this.urlLive  = urlLive;
        this.photoLive = photoLive;
        this.nameLive = nameLive;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.thumb = thumb;
    }

    public String getUrlLive() {
        return urlLive;
    }

    public void setUrlLive(String urlLive) {
        this.urlLive = urlLive;
    }

    public String getPhotoLive() {
        return photoLive;
    }

    public void setPhotoLive(String photoLive) {
        this.photoLive = photoLive;
    }

    public String getNameLive() {
        return nameLive;
    }

    public void setNameLive(String nameLive) {
        this.nameLive = nameLive;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
