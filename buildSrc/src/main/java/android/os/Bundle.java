package android.os;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Created by Yin Yong on 2018/3/15.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused", "unchecked"})
public class Bundle implements Cloneable {

    private static final String TAG = "Bundle";

    public static final Bundle EMPTY;

    static {
        EMPTY = new Bundle();
        EMPTY.mMap = new HashMap<>(1);
    }

    /**
     * Make a Bundle for a single key/value pair.
     *
     * @hide
     */
    public static Bundle forPair(String key, Object value) {
        Bundle b = new Bundle(1);
        b.put(key, value);
        return b;
    }

    /**
     * Make a new empty Bundle.
     */
    public static Bundle begin() {
        return new Bundle();
    }

    /**
     * Make a new empty Bundle.
     *
     * @param capacity the initial capacity of the Bundle
     */
    public static Bundle begin(int capacity) {
        return new Bundle(capacity);
    }

    private Map<String, Object> mMap = null;

    /**
     * Constructs a new, empty Bundle.
     */
    public Bundle() {
        this(0);
    }

    /**
     * Constructs a new, empty Bundle sized to hold the given number of
     * elements. The Bundle will grow as needed.
     *
     * @param capacity the initial capacity of the Bundle
     */
    public Bundle(int capacity) {
        mMap = capacity > 0 ? new HashMap<>(capacity) : new HashMap<>();
    }

    /**
     * Constructs a Bundle containing a copy of the mappings from the given
     * Bundle.
     *
     * @param b a Bundle to be copied.
     */
    public Bundle(Bundle b) {
        if (b.mMap != null) {
            mMap = new HashMap<>(b.mMap);
        } else {
            mMap = null;
        }
    }

    /**
     * Inserts a Object value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value any object
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    /*package*/ Bundle put(@Nullable String key, @Nullable Object value) {
        unparcel();
        mMap.put(key, value);
        return this;
    }

    /**
     * Inserts a String value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a String value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putString(@Nullable String key, @Nullable String value) {
        return put(key, value);
    }

    /**
     * Inserts a String array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a String array value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putStringArray(@Nullable String key, @Nullable String[] value) {
        return put(key, value);
    }

    /**
     * Inserts a String ArrayList value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a String ArrayList value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        return put(key, value);
    }

    /**
     * Inserts a CharSequence value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a CharSequence, or null
     */
    public Bundle putCharSequence(@Nullable String key, @Nullable CharSequence value) {
        return put(key, value);
    }

    /**
     * Inserts a CharSequence array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a CharSequence array, or null
     */
    public Bundle putCharSequenceArray(@Nullable String key, @Nullable CharSequence[] value) {
        return put(key, value);
    }

    /**
     * Inserts a CharSequence ArrayList value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a CharSequence ArrayList, or null
     */
    public Bundle putCharSequenceArrayList(@Nullable String key, @Nullable ArrayList<CharSequence> value) {
        return put(key, value);
    }

    /**
     * Inserts a Bundle value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Bundle object, or null
     */
    public Bundle putBundle(@Nullable String key, @Nullable Bundle value) {
        return put(key, value);
    }

    /**
     * Inserts a byte value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a byte value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putByte(@Nullable String key, byte value) {
        return put(key, value);
    }

    /**
     * Inserts a byte array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a byte array value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putByteArray(@Nullable String key, byte[] value) {
        return put(key, value);
    }

    /**
     * Inserts a char value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a char value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putChar(@Nullable String key, char value) {
        return put(key, value);
    }

    /**
     * Inserts a char array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a char array value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putCharArray(@Nullable String key, char[] value) {
        return put(key, value);
    }

    /**
     * Inserts a short value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a short value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putShort(@Nullable String key, short value) {
        return put(key, value);
    }

    /**
     * Inserts a short array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a short array value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putShortArray(@Nullable String key, short[] value) {
        return put(key, value);
    }

    /**
     * Inserts a int value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a int value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putInt(@Nullable String key, int value) {
        return put(key, value);
    }

    /**
     * Inserts a int array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a int array value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putIntArray(@Nullable String key, int[] value) {
        return put(key, value);
    }

    /**
     * Inserts a Integer ArrayList value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Integer ArrayList value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putIntegerArrayList(@Nullable String key, ArrayList<Integer> value) {
        return put(key, value);
    }

    /**
     * Inserts a long value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a long value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putLong(@Nullable String key, long value) {
        return put(key, value);
    }

    /**
     * Inserts a long array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a long array value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putLongArray(@Nullable String key, long[] value) {
        return put(key, value);
    }

    /**
     * Inserts a float value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a float value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putFloat(@Nullable String key, float value) {
        return put(key, value);
    }

    /**
     * Inserts a float array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a float array value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putFloatArray(@Nullable String key, float[] value) {
        return put(key, value);
    }

    /**
     * Inserts a double value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a double value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putDouble(@Nullable String key, double value) {
        return put(key, value);
    }

    /**
     * Inserts a double array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a double array value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putDoubleArray(@Nullable String key, double[] value) {
        return put(key, value);
    }

    /**
     * Inserts a boolean value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a boolean value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putBoolean(@Nullable String key, boolean value) {
        return put(key, value);
    }

    /**
     * Inserts a boolean array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a boolean array value
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putBooleanArray(@Nullable String key, boolean[] value) {
        return put(key, value);
    }

    /**
     * Inserts a Serializable value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Serializable object, or null
     */
    public Bundle putSerializable(@Nullable String key, @Nullable Serializable value) {
        return put(key, value);
    }

    /**
     * Inserts all mappings from the given Bundle into this Bundle.
     *
     * @param bundle a Bundle
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle putAll(@NonNull Bundle bundle) {
        unparcel();
        bundle.unparcel();
        mMap.putAll(bundle.mMap);
        return this;
    }

    /**
     * Returns the number of mappings contained in this Bundle.
     *
     * @return the number of mappings as an int.
     */
    public int size() {
        unparcel();
        return mMap.size();
    }

    /**
     * Returns true if the mapping of this Bundle is empty, false otherwise.
     */
    public boolean isEmpty() {
        unparcel();
        return mMap.isEmpty();
    }

    /**
     * Removes all elements from the mapping of this Bundle.
     *
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle clear() {
        unparcel();
        mMap.clear();
        return this;
    }

    /**
     * Returns true if the given key is contained in the mapping
     * of this Bundle.
     *
     * @param key a String key
     * @return true if the key is part of the mapping, false otherwise
     */
    public boolean containsKey(String key) {
        unparcel();
        return mMap.containsKey(key);
    }

    /**
     * Removes any entry with the given key from the mapping of this Bundle.
     *
     * @param key a String key
     * @return Returns the same Bundle object, for chaining multiple calls
     * into a single statement.
     */
    public Bundle remove(String key) {
        unparcel();
        mMap.remove(key);
        return this;
    }

    /**
     * Returns the entry with the given key as an object.
     *
     * @param key a String key
     * @return an Object, or null
     */
    @Nullable
    public Object get(String key) {
        unparcel();
        return mMap.get(key);
    }

    private <T> T getTryCast(String key, T defaultValue, String clzName, Class<T> clz) {
        unparcel();
        Object o = mMap.get(key);
        if (o == null) {
            return defaultValue;
        }
        try {
            return clz.cast(o);
        } catch (ClassCastException e) {
            typeWarning(key, o, clzName, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or (byte) 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a byte value
     */
    public byte getByte(String key) {
        return getByte(key, (byte) 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a byte value
     */
    public byte getByte(String key, byte defaultValue) {
        return getTryCast(key, defaultValue, "Byte", Byte.class);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a byte array value
     */
    public byte[] getByteArray(String key) {
        return getTryCast(key, null, "byte[]", byte[].class);
    }

    /**
     * Returns the value associated with the given key, or (char) 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a char value
     */
    public char getChar(String key) {
        return getChar(key, (char) 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a char value
     */
    public char getChar(String key, char defaultValue) {
        return getTryCast(key, defaultValue, "Character", Character.class);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a char array value
     */
    public char[] getCharArray(String key) {
        return getTryCast(key, null, "char[]", char[].class);
    }

    /**
     * Returns the value associated with the given key, or (short) 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a short value
     */
    public short getShort(String key) {
        return getShort(key, (short) 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a short value
     */
    public short getShort(String key, short defaultValue) {
        return getTryCast(key, defaultValue, "Short", Short.class);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a short array value
     */
    public short[] getShortArray(String key) {
        return getTryCast(key, null, "short[]", short[].class);
    }

    /**
     * Returns the value associated with the given key, or 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return an int value
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return an int value
     */
    public int getInt(String key, int defaultValue) {
        return getTryCast(key, defaultValue, "Integer", Integer.class);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a int array value
     */
    public int[] getIntArray(String key) {
        return getTryCast(key, null, "int[]", int[].class);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a Integer ArrayList value
     */
    public ArrayList<Integer> getIntegerArrayList(String key) {
        return getTryCast(key, null, "ArrayList<Integer>", ArrayList.class);
    }

    /**
     * Returns the value associated with the given key, or 0L if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a long value
     */
    public long getLong(String key) {
        return getLong(key, 0L);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a long value
     */
    public long getLong(String key, long defaultValue) {
        return getTryCast(key, defaultValue, "Long", Long.class);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a long array value
     */
    public long[] getLongArray(String key) {
        return getTryCast(key, null, "long[]", long[].class);
    }

    /**
     * Returns the value associated with the given key, or 0.0f if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a float value
     */
    public float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a float value
     */
    public float getFloat(String key, float defaultValue) {
        return getTryCast(key, defaultValue, "Float", Float.class);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a float array value
     */
    public float[] getFloatArray(String key) {
        return getTryCast(key, null, "float[]", float[].class);
    }

    /**
     * Returns the value associated with the given key, or 0.0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a double value
     */
    public double getDouble(String key) {
        return getDouble(key, 0.0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a double value
     */
    public double getDouble(String key, double defaultValue) {
        return getTryCast(key, defaultValue, "Double", Double.class);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a double array value
     */
    public double[] getDoubleArray(String key) {
        return getTryCast(key, null, "double[]", double[].class);
    }

    /**
     * Returns the value associated with the given key, or false if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a boolean value
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a boolean value
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return getTryCast(key, defaultValue, "Boolean", Boolean.class);
    }

    /**
     * Returns the value associated with the given key, or false if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a boolean array value
     */
    public boolean[] getBooleanArray(String key) {
        return getTryCast(key, null, "boolean[]", boolean[].class);
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a CharSequence value, or null
     */
    public CharSequence getCharSequence(@Nullable String key) {
        return getCharSequence(key, null);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key or if a null
     * value is explicitly associated with the given key.
     *
     * @param key a String, or null
     * @param defaultValue Value to return if key does not exist or if a null
     *     value is associated with the given key.
     * @return the CharSequence value associated with the given key, or defaultValue
     *     if no valid CharSequence object is currently mapped to that key.
     */
    public CharSequence getCharSequence(@Nullable String key, CharSequence defaultValue) {
        return getTryCast(key, defaultValue, "CharSequence", CharSequence.class);
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a CharSequence[] value, or null
     */
    public CharSequence[] getCharSequenceArray(@Nullable String key) {
        return getTryCast(key, null, "CharSequence[]", CharSequence[].class);
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return an ArrayList<CharSequence> value, or null
     */
    public ArrayList<CharSequence> getCharSequenceArrayList(@Nullable String key) {
        return getTryCast(key, null, "ArrayList<CharSequence>", ArrayList.class);
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a Serializable value, or null
     */
    public Serializable getSerializable(@Nullable String key) {
        return getTryCast(key, null, "Serializable", Serializable.class);
    }

    /**
     * Returns a Set containing the Strings used as keys in this Bundle.
     *
     * @return a Set of String keys
     */
    @NonNull
    public Set<String> keySet() {
        unparcel();
        return mMap.keySet();
    }

    /**
     * If the underlying data are stored as a Parcel, unparcel them
     * using the currently assigned class loader.
     */
    /* package */
    synchronized void unparcel() {
        Map<String, Object> map = mMap;
        if (map == null) {
            map = new HashMap<>();
        }
        mMap = map;
    }

    // Log a message if the value was non-null but not of the expected type
    @SuppressWarnings("StringBufferReplaceableByString")
    void typeWarning(String key, Object value, String className,
                     Object defaultValue, ClassCastException e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Key ");
        sb.append(key);
        sb.append(" expected ");
        sb.append(className);
        sb.append(" but value was a ");
        sb.append(value.getClass().getName());
        sb.append(".  The default value ");
        sb.append(defaultValue);
        sb.append(" was returned.");
        Log.w(TAG, sb.toString());
        Log.w(TAG, "Attempt to cast generated internal exception:", e);
    }

    void typeWarning(String key, Object value, String className, ClassCastException e) {
        typeWarning(key, value, className, "<null>", e);
    }

    @Override
    public String toString() {
        return "Bundle[" + mMap.toString() + "]";
    }
}
