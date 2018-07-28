package hashtable;
import java.util.LinkedList;

public class Hashtable {
    LinkedList[] data;

    boolean put(String key, Person value) {
        int hashcode = getHashCode(key);
        int index = convertToIndex(hashCode);
        LinkedList list = data[index];
        list.insert(key, value);
        return true;
    }
}
