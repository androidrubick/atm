package android.media;

import android.annotation.SdkConstant;
import android.content.Context;

/**
 * <p>
 * Created by Yin Yong on 2018/3/16.
 */
public class AudioManager {

    /**
     * Broadcast Action: Wired Headset plugged in or unplugged.
     *
     * You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     *
     * <p>The intent will have the following extra values:
     * <ul>
     *   <li><em>state</em> - 0 for unplugged, 1 for plugged. </li>
     *   <li><em>name</em> - Headset type, human readable string </li>
     *   <li><em>microphone</em> - 1 if headset has a microphone, 0 otherwise </li>
     * </ul>
     * </ul>
     */
    @SdkConstant(SdkConstant.SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_HEADSET_PLUG =
            "android.intent.action.HEADSET_PLUG";
}
