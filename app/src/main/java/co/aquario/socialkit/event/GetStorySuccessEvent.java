package co.aquario.socialkit.event;

/**
 * Created by Mac on 3/3/15.
 */
public class GetStorySuccessEvent {
    private TimelineDataResponse timelineData;

    public GetStorySuccessEvent(TimelineDataResponse timelineData) {
        this.timelineData = timelineData;
    }

    public TimelineDataResponse getTimelineData() {
        return timelineData;
    }
}
