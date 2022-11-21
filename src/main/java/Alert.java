import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@DynamoDbBean
public class Alert {
    public String alertId;
    public AddAlert addAlert;


    @DynamoDbPartitionKey
    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public AddAlert getAddAlert() {
        return addAlert;
    }

    public void setAddAlert(AddAlert addAlert) {
        this.addAlert = addAlert;
    }
}

