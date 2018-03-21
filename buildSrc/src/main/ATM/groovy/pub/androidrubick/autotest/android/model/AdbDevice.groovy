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
    public String state

    /**
     * 额外的信息
     */
    public final Map extras = [:]

    AdbDevice() {
    }

    AdbDevice(String serialNumber, String state = '') {
        this.serialNumber = serialNumber
        this.state = state
    }

    public boolean isOnline() {
        return 'device'.equalsIgnoreCase(this.state)
    }

    @Override
    int hashCode() {
        return Objects.hashCode(serialNumber)
    }

    @Override
    boolean equals(Object o) {
        if (o instanceof AdbDevice) {
            return o.serialNumber == this.serialNumber
        }
        return false
    }

    @Override
    public String toString() {
        return "AdbDevice{" +
                "serialNumber='" + serialNumber + '\'' +
                ", state='" + state + '\'' +
                ", extras=" + extras +
                ", online=" + online +
                '}';
    }

    boolean asBoolean() {
        !Utils.isEmpty(serialNumber)
    }

    public static List<AdbDevice> filterOnline(List<AdbDevice> devices) {
        return devices?.findAll { AdbDevice device ->
            device?.online
        } ?: []
    }

}