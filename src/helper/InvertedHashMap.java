package helper;

import java.util.*;

public class InvertedHashMap<K,V> extends HashMap<K,V>
{
    Map<V,K> inverted = new HashMap<>();

    @Override
    public V put(K key, V value)
    {
        inverted.put(value, key);
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K,? extends V> m)
    {
       for(Entry<? extends K, ? extends V> e: m.entrySet())
           inverted.put(e.getValue(), e.getKey());
    }


    public K getKey(V value) { return inverted.get(value); }

}
