package jp.gcreate.sample.practiceandroidsupportlibrary;

/**
 * 15/10/25
 */
public class ActivityListData{
    private final String activityName;
    private final String description;
    private final Class<?> target;

    public ActivityListData(String title, String description, Class<?> target){
        this.activityName = title;
        this.description = description;
        this.target = target;
    }

    public String getActivityName(){
        return activityName;
    }

    public String getDescription(){
        return description;
    }

    public Class<?> getTarget(){
        return target;
    }
}
