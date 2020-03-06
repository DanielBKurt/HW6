import java.util.List;

public class Dictionary {
    private int capacity;
    private int count;
    private KVEntry[] entries;

    public Dictionary(int capacity)
    {
        this.capacity = capacity;
        entries = new KVEntry[capacity];
        count = 0;
    }

    private int hashFunction(String key)
    {
        return ((int) key.charAt(0) + (int) key.charAt(1)) % capacity;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public boolean contains(String key)
    {
        int hashKey = hashFunction(key);
        KVEntry ptr = entries[hashKey];
        while (ptr != null)
        {
            if (ptr.key.equals(key))
                return true;
            ptr = ptr.next;
        }
        return false;
    }

    public List<Integer> get(String key)
    {
        int hashKey = hashFunction(key);
        KVEntry ptr = entries[hashKey];
        while (ptr != null)
        {
            if (ptr.key.equals(key))
                return ptr.value;
            ptr = ptr.next;
        }
        return null;
    }

    public int getCount()
    {
        return count;
    }

    public boolean add(String key, List<Integer> value)
    {
        int hashedKey = hashFunction(key);

        if (entries[hashedKey] == null)
        {
            if (count == capacity)
                return false;
            entries[hashedKey] = new KVEntry(key, value);
            count++;
            return true;
        }

        KVEntry ptr = entries[hashedKey];
        KVEntry pNewNode = null;
        while (ptr != null)
        {
            if (ptr.key.equals(key))
            {
                ptr.value = value;
                return true;
            }
            pNewNode = ptr;
            ptr = ptr.next;
        }
        pNewNode.next = new KVEntry(key, value);
        return true;
    }
}
