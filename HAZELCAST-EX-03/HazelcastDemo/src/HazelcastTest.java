import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastTest {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("localhost:5701");

        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Integer, Person> map = client.getMap("peopleMap");

        // Put 10,000 Person objects
        for (int i = 0; i < 10_000; i++) {
            map.put(i, new Person("Person_" + i));
        }

        // Retrieve and print them
        for (int i = 0; i < 10_000; i++) {
            Person p = map.get(i);
            System.out.println("Retrieved: " + p.getName());
        }

        client.shutdown();
    }
}
