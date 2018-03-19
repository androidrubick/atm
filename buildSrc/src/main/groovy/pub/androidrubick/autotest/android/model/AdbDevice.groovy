package pub.androidrubick.autotest.android.model

import pub.androidrubick.autotest.core.util.Utils

/**
 * simple model with device/simulator information
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AdbDevice {

    /**
     * 序列号
     */
    public String serialNumber

    /**
     * 类型：
     * 1、offline
     * 2、device
     * 3、no device
     */
    public String type
    /**
     * 额外的信息
     */
    public final Map extras = [:]

    AdbDevice() {
    }

    AdbDevice(String serialNumber, String type = '') {
        this.serialNumber = serialNumber
        this.type = type
    }

    @Override
    public String toString() {
        return "AdbDevice{" +
                "serialNumber='" + serialNumber + '\'' +
                ", type='" + type + '\'' +
                ", extras=" + extras +
                '}';
    }

    boolean asBoolean(){
        !Utils.isEmpty(serialNumber)
    }

}