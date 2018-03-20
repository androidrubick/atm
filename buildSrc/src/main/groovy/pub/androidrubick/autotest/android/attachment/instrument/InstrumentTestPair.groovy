package pub.androidrubick.autotest.android.attachment.instrument

@SuppressWarnings("GroovyUnusedDeclaration")
public class InstrumentTestPair<V> implements InstrumentTestable {

    private final String mKey
    private V mValue

    public InstrumentTestPair(String key) {
        mKey = key
    }

    public InstrumentTestPair(String key, V initVal) {
        mKey = key
        mValue = initVal
    }

    public InstrumentTestPair<V> setValue(V value) {
        mValue = value
        return this
    }

    public final String getKey() {
        return mKey
    }

    public final V getValue() {
        return mValue
    }

    @Override
    String toCmdString() {
        return "-e $key ${String.valueOf(value)}"
    }

    @Override
    String toString() {
        return toCmdString()
    }

    @Override
    boolean equals(Object o) {
        return (o instanceof InstrumentTestPair) &&
                Objects.equals(this.key, o.key) &&
                Objects.equals(this.value, o.value)
    }

    @Override
    int hashCode() {
        return Objects.hash(this.key, this.value)
    }
}