package com.google.gson.internal;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

public final class StringMap<V> extends AbstractMap<String, V> {
    private static final Entry[] EMPTY_TABLE = new LinkedEntry[2];
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final int MINIMUM_CAPACITY = 4;
    private static final int seed = new Random().nextInt();
    private Set<Entry<String, V>> entrySet;
    private LinkedEntry<V> header = new LinkedEntry();
    private Set<String> keySet;
    private int size;
    private LinkedEntry<V>[] table = ((LinkedEntry[]) EMPTY_TABLE);
    private int threshold = -1;
    private Collection<V> values;

    private final class EntrySet extends AbstractSet<Entry<String, V>> {

        /* renamed from: com.google.gson.internal.StringMap$EntrySet$1 */
        class C05771 extends LinkedHashIterator<Entry<String, V>> {
            C05771() {
                super();
            }

            public final Entry<String, V> next() {
                return nextEntry();
            }
        }

        private EntrySet() {
        }

        public Iterator<Entry<String, V>> iterator() {
            return new C05771();
        }

        public boolean contains(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> e = (Entry) o;
            V mappedValue = StringMap.this.get(e.getKey());
            if (mappedValue == null || !mappedValue.equals(e.getValue())) {
                return false;
            }
            return true;
        }

        public boolean remove(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> e = (Entry) o;
            return StringMap.this.removeMapping(e.getKey(), e.getValue());
        }

        public int size() {
            return StringMap.this.size;
        }

        public void clear() {
            StringMap.this.clear();
        }
    }

    private final class KeySet extends AbstractSet<String> {

        /* renamed from: com.google.gson.internal.StringMap$KeySet$1 */
        class C05781 extends LinkedHashIterator<String> {
            C05781() {
                super();
            }

            public final String next() {
                return nextEntry().key;
            }
        }

        private KeySet() {
        }

        public Iterator<String> iterator() {
            return new C05781();
        }

        public int size() {
            return StringMap.this.size;
        }

        public boolean contains(Object o) {
            return StringMap.this.containsKey(o);
        }

        public boolean remove(Object o) {
            int oldSize = StringMap.this.size;
            StringMap.this.remove(o);
            return StringMap.this.size != oldSize;
        }

        public void clear() {
            StringMap.this.clear();
        }
    }

    static class LinkedEntry<V> implements Entry<String, V> {
        final int hash;
        final String key;
        LinkedEntry<V> next;
        LinkedEntry<V> nxt;
        LinkedEntry<V> prv;
        V value;

        LinkedEntry() {
            this(null, null, 0, null, null, null);
            this.prv = this;
            this.nxt = this;
        }

        LinkedEntry(String key, V value, int hash, LinkedEntry<V> next, LinkedEntry<V> nxt, LinkedEntry<V> prv) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
            this.nxt = nxt;
            this.prv = prv;
        }

        public final String getKey() {
            return this.key;
        }

        public final V getValue() {
            return this.value;
        }

        public final V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> e = (Entry) o;
            Object eValue = e.getValue();
            if (!this.key.equals(e.getKey())) {
                return false;
            }
            if (this.value == null) {
                if (eValue != null) {
                    return false;
                }
            } else if (!this.value.equals(eValue)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            int i = 0;
            int hashCode = this.key == null ? 0 : this.key.hashCode();
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode ^ i;
        }

        public final String toString() {
            return this.key + "=" + this.value;
        }
    }

    private abstract class LinkedHashIterator<T> implements Iterator<T> {
        LinkedEntry<V> lastReturned;
        LinkedEntry<V> next;

        private LinkedHashIterator() {
            this.next = StringMap.this.header.nxt;
            this.lastReturned = null;
        }

        public final boolean hasNext() {
            return this.next != StringMap.this.header;
        }

        final LinkedEntry<V> nextEntry() {
            LinkedEntry<V> e = this.next;
            if (e == StringMap.this.header) {
                throw new NoSuchElementException();
            }
            this.next = e.nxt;
            this.lastReturned = e;
            return e;
        }

        public final void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            StringMap.this.remove(this.lastReturned.key);
            this.lastReturned = null;
        }
    }

    private final class Values extends AbstractCollection<V> {

        /* renamed from: com.google.gson.internal.StringMap$Values$1 */
        class C05791 extends LinkedHashIterator<V> {
            C05791() {
                super();
            }

            public final V next() {
                return nextEntry().value;
            }
        }

        private Values() {
        }

        public Iterator<V> iterator() {
            return new C05791();
        }

        public int size() {
            return StringMap.this.size;
        }

        public boolean contains(Object o) {
            return StringMap.this.containsValue(o);
        }

        public void clear() {
            StringMap.this.clear();
        }
    }

    public int size() {
        return this.size;
    }

    public boolean containsKey(Object key) {
        return (key instanceof String) && getEntry((String) key) != null;
    }

    public V get(Object key) {
        if (!(key instanceof String)) {
            return null;
        }
        LinkedEntry<V> entry = getEntry((String) key);
        if (entry != null) {
            return entry.value;
        }
        return null;
    }

    private LinkedEntry<V> getEntry(String key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        LinkedEntry<V>[] tab = this.table;
        for (LinkedEntry<V> e = tab[(tab.length - 1) & hash]; e != null; e = e.next) {
            String eKey = e.key;
            if (eKey == key) {
                return e;
            }
            if (e.hash == hash && key.equals(eKey)) {
                return e;
            }
        }
        return null;
    }

    public V put(String key, V value) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        int hash = hash(key);
        LinkedEntry<V>[] tab = this.table;
        int index = hash & (tab.length - 1);
        LinkedEntry<V> e = tab[index];
        while (e != null) {
            if (e.hash == hash && key.equals(e.key)) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
            e = e.next;
        }
        int i = this.size;
        this.size = i + 1;
        if (i > this.threshold) {
            index = hash & (doubleCapacity().length - 1);
        }
        addNewEntry(key, value, hash, index);
        return null;
    }

    private void addNewEntry(String key, V value, int hash, int index) {
        LinkedEntry<V> header = this.header;
        LinkedEntry<V> oldTail = header.prv;
        LinkedEntry<V> newTail = new LinkedEntry(key, value, hash, this.table[index], header, oldTail);
        LinkedEntry[] linkedEntryArr = this.table;
        header.prv = newTail;
        oldTail.nxt = newTail;
        linkedEntryArr[index] = newTail;
    }

    private LinkedEntry<V>[] makeTable(int newCapacity) {
        LinkedEntry[] newTable = (LinkedEntry[]) new LinkedEntry[newCapacity];
        this.table = newTable;
        this.threshold = (newCapacity >> 1) + (newCapacity >> 2);
        return newTable;
    }

    private LinkedEntry<V>[] doubleCapacity() {
        LinkedEntry<V>[] oldTable = this.table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            return oldTable;
        }
        LinkedEntry<V>[] newTable = makeTable(oldCapacity * 2);
        if (this.size == 0) {
            return newTable;
        }
        for (int j = 0; j < oldCapacity; j++) {
            LinkedEntry<V> e = oldTable[j];
            if (e != null) {
                int highBit = e.hash & oldCapacity;
                LinkedEntry<V> broken = null;
                newTable[j | highBit] = e;
                for (LinkedEntry<V> n = e.next; n != null; n = n.next) {
                    int nextHighBit = n.hash & oldCapacity;
                    if (nextHighBit != highBit) {
                        if (broken == null) {
                            newTable[j | nextHighBit] = n;
                        } else {
                            broken.next = n;
                        }
                        broken = e;
                        highBit = nextHighBit;
                    }
                    e = n;
                }
                if (broken != null) {
                    broken.next = null;
                }
            }
        }
        return newTable;
    }

    public V remove(Object key) {
        if (key == null || !(key instanceof String)) {
            return null;
        }
        int hash = hash((String) key);
        LinkedEntry<V>[] tab = this.table;
        int index = hash & (tab.length - 1);
        LinkedEntry<V> e = tab[index];
        LinkedEntry<V> prev = null;
        while (e != null) {
            if (e.hash == hash && key.equals(e.key)) {
                if (prev == null) {
                    tab[index] = e.next;
                } else {
                    prev.next = e.next;
                }
                this.size--;
                unlink(e);
                return e.value;
            }
            prev = e;
            e = e.next;
        }
        return null;
    }

    private void unlink(LinkedEntry<V> e) {
        e.prv.nxt = e.nxt;
        e.nxt.prv = e.prv;
        e.prv = null;
        e.nxt = null;
    }

    public void clear() {
        if (this.size != 0) {
            Arrays.fill(this.table, null);
            this.size = 0;
        }
        LinkedEntry<V> header = this.header;
        LinkedEntry<V> e = header.nxt;
        while (e != header) {
            LinkedEntry<V> nxt = e.nxt;
            e.prv = null;
            e.nxt = null;
            e = nxt;
        }
        header.prv = header;
        header.nxt = header;
    }

    public Set<String> keySet() {
        Set<String> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        ks = new KeySet();
        this.keySet = ks;
        return ks;
    }

    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        vs = new Values();
        this.values = vs;
        return vs;
    }

    public Set<Entry<String, V>> entrySet() {
        Set<Entry<String, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        es = new EntrySet();
        this.entrySet = es;
        return es;
    }

    private boolean removeMapping(Object key, Object value) {
        if (key == null || !(key instanceof String)) {
            return false;
        }
        int hash = hash((String) key);
        LinkedEntry<V>[] tab = this.table;
        int index = hash & (tab.length - 1);
        LinkedEntry<V> e = tab[index];
        LinkedEntry<V> prev = null;
        while (e != null) {
            if (e.hash != hash || !key.equals(e.key)) {
                prev = e;
                e = e.next;
            } else if (value != null ? !value.equals(e.value) : e.value != null) {
                return false;
            } else {
                if (prev == null) {
                    tab[index] = e.next;
                } else {
                    prev.next = e.next;
                }
                this.size--;
                unlink(e);
                return true;
            }
        }
        return false;
    }

    private static int hash(String key) {
        int h = seed;
        for (int i = 0; i < key.length(); i++) {
            int h2 = h + key.charAt(i);
            int h3 = (h2 + h2) << 10;
            h = h3 ^ (h3 >>> 6);
        }
        h ^= (h >>> 20) ^ (h >>> 12);
        return ((h >>> 7) ^ h) ^ (h >>> 4);
    }
}
