import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.core.internal.waiters.ResponseOrException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

public class EnhancedGetItem {

    public static void main(String[] args) throws JsonProcessingException {

        Region region = Region.US_WEST_2;
        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(region)
                .build();

        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(ddb)
                .build();

//        createTable(enhancedClient);

//        String result = getItem(enhancedClient);
//        System.out.println(result);

        Alert myAlert = ReadAlert(enhancedClient,"gvimg1cgtb1dctgqibogu1uc7e");
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(myAlert);
        System.out.println(json);

//        WriteAndReadCustomer(enhancedClient);

        ddb.close();
    }

        private static Alert ReadAlert(DynamoDbEnhancedClient enhancedClient, String id) {
            DynamoDbTable<Alert> mappedTable = enhancedClient.table("alert", TableSchema.fromBean(Alert.class));

            //Create a KEY object
            Key key = Key.builder()
                    .partitionValue(id)
                    .build();

            // Get the item by using the key
            Alert result = mappedTable.getItem(r->r.key(key));
            return result;

        }

    private static void WriteAndReadCustomer(DynamoDbEnhancedClient enhancedClient) throws JsonProcessingException {
        Customer newOne = new Customer();
        newOne.setCustName("Tom Tichy");
        newOne.setEmail("ttichy@yahoo.com");
        newOne.setCustomerId("customer0410");
        Hobby hobby = new Hobby();
        hobby.setName("coding");
        hobby.setSkill("killer");
        newOne.setHobby(hobby);

        setItem(enhancedClient, newOne);

        Customer result = getItem(enhancedClient,"customer0410");

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(result);
        System.out.println(json);
    }

    private static void setItem(DynamoDbEnhancedClient enhancedClient,Customer newOne) {
        DynamoDbTable<Customer> mappedTable = enhancedClient.table("Customer", TableSchema.fromBean(Customer.class));

        mappedTable.putItem(newOne);
    }

    // snippet-start:[dynamodb.java2.mapping.getitem.main]
    public static Customer getItem(DynamoDbEnhancedClient enhancedClient, String id) {
        try {
            //Create a DynamoDbTable object
            DynamoDbTable<Customer> mappedTable = enhancedClient.table("Customer", TableSchema.fromBean(Customer.class));

            //Create a KEY object
            Key key = Key.builder()
                    .partitionValue(id)
                    .build();

            // Get the item by using the key
            Customer result = mappedTable.getItem(r->r.key(key));
            return result;

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return null;
        }


    }
    // snippet-end:[dynamodb.java2.mapping.getitem.main]

    public static void createTable(DynamoDbEnhancedClient  enhancedClient) {
        // Create a DynamoDbTable object
        DynamoDbTable<Customer> customerTable = enhancedClient.table("Customer", TableSchema.fromBean(Customer.class));
        // Create the table
        customerTable.createTable(builder -> builder
                .provisionedThroughput(b -> b
                        .readCapacityUnits(10L)
                        .writeCapacityUnits(10L)
                        .build())
        );

        System.out.println("Waiting for table creation...");

        try (DynamoDbWaiter waiter = DynamoDbWaiter.create()) { // DynamoDbWaiter is Autocloseable
            ResponseOrException<DescribeTableResponse> response = waiter
                    .waitUntilTableExists(builder -> builder.tableName("Customer").build())
                    .matched();
            DescribeTableResponse tableDescription = response.response().orElseThrow(
                    () -> new RuntimeException("Customer table was not created."));
            // The actual error can be inspected in response.exception()
            System.out.println(tableDescription.table().tableName() + " was created.");
        }
    }
}