import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.Instant;

@DynamoDbBean
public class Customer {


    private String customerId;
    private String custName;
    private String email;
    private Instant regDate;
    private Hobby hobby;


    @DynamoDbAttribute("fun_times")
    public Hobby getHobby() {
        return hobby;
    }
    @DynamoDbAttribute("fun_times")
    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getCustomerId() {
        return this.customerId;
    };

    public void setCustomerId(String id) {

        this.customerId = id;
    }

    @DynamoDbAttribute("name")
    public String getCustName() {
        return custName;
    }

    public void setCustName(String name) {
        this.custName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getRegDate() {
        return regDate;
    }

    public void setRegDate(Instant regDate) {
        this.regDate = regDate;
    }
}