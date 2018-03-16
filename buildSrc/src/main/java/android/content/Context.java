/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.content;

import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

/**
 * Interface to global information about an application environment.  This is
 * an abstract class whose implementation is provided by
 * the Android system.  It
 * allows access to application-specific resources and classes, as well as
 * up-calls for application-level operations such as launching activities,
 * broadcasting and receiving intents, etc.
 */
@SuppressWarnings("unused")
public abstract class Context {

    /**
     * Launch a new activity.  You will not receive any information about when
     * the activity exits.
     * <p>
     * <p>This method throws {@link ActivityNotFoundException}
     * if there was no Activity found to run the given Intent.
     *
     * @param intent The description of the activity to start.
     * @throws ActivityNotFoundException &nbsp;
     */
    public abstract void startActivity(Intent intent);

    /**
     * Broadcast the given intent to all interested BroadcastReceivers.  This
     * call is asynchronous; it returns immediately, and you will continue
     * executing while the receivers are run.  No results are propagated from
     * receivers and receivers can not abort the broadcast.
     *
     * @param intent The Intent to broadcast; all receivers matching this
     *               Intent will receive the broadcast.
     * @see #sendBroadcast(Intent, String)
     */
    public abstract void sendBroadcast(@RequiresPermission Intent intent);

    /**
     * Broadcast the given intent to all interested BroadcastReceivers, allowing
     * an optional required permission to be enforced.  This
     * call is asynchronous; it returns immediately, and you will continue
     * executing while the receivers are run.  No results are propagated from
     * receivers and receivers can not abort the broadcast.
     *
     * @param intent             The Intent to broadcast; all receivers matching this
     *                           Intent will receive the broadcast.
     * @param receiverPermission (optional) String naming a permission that
     *                           a receiver must hold in order to receive your broadcast.
     *                           If null, no permission is required.
     * @see #sendBroadcast(Intent)
     */
    public abstract void sendBroadcast(@RequiresPermission Intent intent,
                                       @Nullable String receiverPermission);

    /** Return the name of this application's package. */
    public abstract String getPackageName();
}
