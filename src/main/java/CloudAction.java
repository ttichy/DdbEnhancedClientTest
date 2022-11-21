import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class CloudAction {
    public String type;
    public TypeParams typeParams;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TypeParams getTypeParams() {
        return typeParams;
    }

    public void setTypeParams(TypeParams typeParams) {
        this.typeParams = typeParams;
    }
}
