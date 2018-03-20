package pub.androidrubick.autotest.android.content;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

/**
 * Context implementation for AutoTest
 *
 * <p>
 * Created by Yin Yong on 2018/3/15.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class ATAndroidContext extends Context {

    @Override
    public void startActivity(Intent intent) {

    }

    @Override
    public void sendBroadcast(@RequiresPermission Intent intent) {

    }

    @Override
    public void sendBroadcast(@RequiresPermission Intent intent, @Nullable String receiverPermission) {

    }

    @Override
    public String getPackageName() {
        return null
    }
}
