package co.aquario.socialkit.event;

/**
 * Created by Mac on 3/3/15.
 */
public class LoadFriendListSuccessEvent {
    private TimelineDataResponse timelineData;

    public LoadFriendListSuccessEvent(TimelineDataResponse timelineData) {
        this.timelineData = timelineData;
    }

    public TimelineDataResponse getTimelineData() {
        return timelineData;
    }
}
