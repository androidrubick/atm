package pub.androidrubick.autotest.android.model

/**
 * simple model with device/simulator information
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class DeviceInfo {

    /**
     * 一个可读的全名
     */
    public String fullName
    /**
     * 品牌
     */
    public String brand
    /**
     * 型号，如小米手机，米4，note等；
     *
     * iPhone：4，5，6，6s等
     */
    public String model
    /**
     * 厂商
     */
    public String manufacturer
    /**
     * 系统名称
     */
    public String osName
    /**
     * 系统版本
     */
    public String osVersion
    /**
     * 额外的信息
     */
    public final Map extras = [:]

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "fullName='" + fullName + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", extras=" + extras +
                '}';
    }

}