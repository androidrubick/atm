package pub.androidrubick.autotest.android.support.cmd

import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import pub.androidrubick.autotest.core.util.Utils

/**
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class IntentCmd {

    private final Intent mIntent
    public IntentCmd(@NonNull Intent intent) {
        mIntent = intent
    }

    public final Intent getIntent() {
        return this.mIntent
    }

    public String getActionParamString() {
        return Utils.isEmpty(intent.action) ? '' : "-a ${intent.action}"
    }

    public String getAllParamString() {
        return toCmdString()
    }

    public String toCmdString() {
        def intentFields = []
        if (!Utils.isEmpty(intent.action)) {
            intentFields << "-a ${intent.action}"
        }

        if (intent.data != null) {
            intentFields << "-d ${intent.data}"
        }

        if (!Utils.isEmpty(intent.type)) {
            intentFields << "-t ${intent.type}"
        }

        if (!Utils.isEmpty(intent.categories)) {
            intent.categories.each { cate ->
                intentFields << "-c $cate"
            }
        }

        if (intent.component != null) {
            intentFields << "-n ${intent.component.flattenToShortString()}"
        }

        if (intent.flags != 0) {
            intentFields << "-f ${intent.flags}"
        }

        def extrasString = extrasParamString(intent.extras)
        if (!Utils.isEmpty(extrasString)) {
            intentFields << extrasString
        }
        return intentFields.join(' ')
    }

    public static String intentExtrasParamString(@NonNull Intent extras) {
        return extrasParamString(extras.extras)
    }

    private static final Map<Class, Closure> sExtraTypeClosures = [:]
    static {
        // TODO: intent -> cmd string，此处的数组的处理有待修正

        sExtraTypeClosures.put(null,            { String key,    Object val -> "--esn \"$key\""})

        sExtraTypeClosures.put(   String.class, { String key,    String val -> "--es \"$key\" \"$val\""})
        sExtraTypeClosures.put(     Byte.class, { String key,      Byte val -> "--ei \"$key\" $val"})
        sExtraTypeClosures.put(Character.class, { String key, Character val -> "--ei \"$key\" $val"})
        sExtraTypeClosures.put(    Short.class, { String key,     Short val -> "--ei \"$key\" $val"})
        sExtraTypeClosures.put(  Integer.class, { String key,   Integer val -> "--ei \"$key\" $val"})
        sExtraTypeClosures.put(     Long.class, { String key,      Long val -> "--el \"$key\" $val"})
        sExtraTypeClosures.put(    Float.class, { String key,     Float val -> "--ef \"$key\" $val"})
        sExtraTypeClosures.put(   Double.class, { String key,    Double val -> "--ef \"$key\" $val"})
        sExtraTypeClosures.put(  Boolean.class, { String key,   Boolean val -> "--ez \"$key\" $val"})

        sExtraTypeClosures.put(   String[].class, { String key,  String[] val -> "--es \"$key\" \"${val.join(',')}\""})
        sExtraTypeClosures.put(     byte[].class, { String key,    byte[] val -> "--eia \"$key\" ${val.join(',')}"})
        sExtraTypeClosures.put(     char[].class, { String key,    char[] val -> "--eia \"$key\" ${val.join(',')}"})
        sExtraTypeClosures.put(    short[].class, { String key,   short[] val -> "--eia \"$key\" ${val.join(',')}"})
        sExtraTypeClosures.put(      int[].class, { String key,     int[] val -> "--eia \"$key\" ${val.join(',')}"})
        sExtraTypeClosures.put(     long[].class, { String key,    long[] val -> "--ela \"$key\" ${val.join(',')}"})
        sExtraTypeClosures.put(    float[].class, { String key,   float[] val -> "--efa \"$key\" ${val.join(',')}"})
        sExtraTypeClosures.put(   double[].class, { String key,  double[] val -> "--efa \"$key\" ${val.join(',')}"})
        sExtraTypeClosures.put(  boolean[].class, { String key, boolean[] val -> "--es \"$key\" \"${val.join(',')}\""})

        sExtraTypeClosures.put(ArrayList.class, { String key, ArrayList val -> "--es \"$key\" \"${val.join(',')}\""})
    }

    public static String extrasParamString(Bundle extras) {
        if (extras == null || extras.isEmpty()) {
            return ''
        }
        def extraFields = []
        extras.keySet().each { String key ->
            def val = extras.get(key)
            def op = sExtraTypeClosures.get(val?.class)
            if (op == null) {
                // 如果没有找到，直接当做string处理
                op = sExtraTypeClosures.get(String.class)
                val = String.valueOf(val)
            }
            extraFields << op(key, val)
        }
        return extraFields.join(' ')
    }

}
