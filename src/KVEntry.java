import java.util.List;

public class KVEntry {
    String key;
    List<Integer> value;

    KVEntry next;

    public KVEntry(String key, List<Integer> value)
    {
        this.key = key;
        this.value = value;
    }
}
