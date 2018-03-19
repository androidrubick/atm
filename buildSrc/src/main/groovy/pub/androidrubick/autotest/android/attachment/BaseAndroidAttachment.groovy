package pub.androidrubick.autotest.android.attachment

import pub.androidrubick.autotest.android.AndroidSdk
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment

@SuppressWarnings("GroovyUnusedDeclaration")
public class BaseAndroidAttachment extends BaseAttachment {

    public BaseAndroidAttachment(ATMContext context) {
        super(context)
    }

    public final AndroidSdk getAndroidSdk() {
        return AndroidSdk.fromProject(project)
    }

//    public final AndroidDeviceUtil getDevice() {
//        return this.android_sdk.device
//    }
//
//    public final AndroidStorageUtil getStorage() {
//        return this.android_sdk.storage
//    }

}