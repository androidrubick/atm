package pub.androidrubick.autotest.android.attachment.autoconfirm

import org.gradle.util.ConfigureUtil

@SuppressWarnings("GroovyUnusedDeclaration")
public class AutoConfirmConfig {

    public static AutoConfirmConfig newDef() {
        return new AutoConfirmConfig()
    }

    public static AutoConfirmConfig newConfig(Map config) {
        return new AutoConfirmConfig(config)
    }

    public String name = 'AutoConfirm'
    public boolean sync = false
    public boolean doneFlag = false
    public int loopCount = 5
    public int maxLoopCount = 20
    public int delayTime = 5000
    public int periodTime = 2000
    public int timeout = -1
    // 包过滤器，array
    public List<String> packages = []
    public Object confirmBtnTextFilter = null
    public AutoConfirmConfig sync() {
        this.sync = true
        return this
    }

    public AutoConfirmConfig async() {
        this.sync = false
        return this
    }

    public AutoConfirmConfig done() {
        this.doneFlag = true
        return this
    }

    public AutoConfirmConfig(Map config = null) {
        if (config != null) {
            ConfigureUtil.configureByMap(config, this)
//            if (config.name != null) {
//                this.name = config.name
//            }
//            if (config.sync != null) {
//                this.sync = config.sync
//            }
//            if (config.loopCount != null) {
//                this.loopCount = config.loopCount as int
//            }
//            if (config.maxLoopCount != null) {
//                this.maxLoopCount = config.maxLoopCount as int
//            }
//            if (config.delayTime != null) {
//                this.delayTime = config.delayTime as int
//            }
//            if (config.periodTime != null) {
//                this.periodTime = config.periodTime as int
//            }
//            if (config.timeout != null) {
//                this.timeout = config.timeout as int
//            }
//            if (config.packages != null) {
//                this.packages = config.packages
//            }
//            if (config.confirmBtnTextFilter != null) {
//                this.confirmBtnTextFilter = config.confirmBtnTextFilter
//            }
        }
    }

}