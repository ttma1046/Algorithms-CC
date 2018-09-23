package hashtable;
import java.util.LinkedList;

public class MyHashtable {
    LinkedList[] data;
    
    boolean put(String key, Person value) {
        /*
        hashcode = getHashCode(key);
        int index = convertToIndex(hashCode);
        LinkedList list = data[index];
        */
        // list.insert(key, value);
        return true;
    }
    
    long convertToIndex(long hashCode) {
        return hashCode % data.length;
    }

    Person get(String key)
    {
        /*
        int hashcode = getHashCode(key);
        int index = convertToIndex(hashCode);
        LinkedList<LinkedListItem> list = data[index];
        return list.
        */
        // list.AddLast(new LinkedListItem { Key = key, Person = value});
        return new Person("Chandler");
    }

    class LinkedListItem
    {
        private String Key;
        private Person Person;
    }

    class Person
    {
        private String Name;
        private Person(String name) {
            this.Name = name;
        }
    }
}
