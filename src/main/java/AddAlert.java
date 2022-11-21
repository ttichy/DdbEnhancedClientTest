import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;


@DynamoDbBean
public class AddAlert {
    public List<CloudAction> cloudActions;
    public String deviceId;
    public int duration;
    public boolean enabled;
    public String id;
    public String processor;
    public String triggerMode;
    public List<Trigger> triggers;

    public List<CloudAction> getCloudActions() {
        return cloudActions;
    }

    public void setCloudActions(List<CloudAction> cloudActions) {
        this.cloudActions = cloudActions;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getTriggerMode() {
        return triggerMode;
    }

    public void setTriggerMode(String triggerMode) {
        this.triggerMode = triggerMode;
    }

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }
}
