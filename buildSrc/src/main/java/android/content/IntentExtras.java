package android.content;

import android.annotation.SystemApi;
import android.os.Bundle;

/**
 * <p>
 * Created by Yin Yong on 2018/3/15.
 */
@SuppressWarnings({"unused", "UnnecessaryInterfaceModifier", "deprecation"})
interface IntentExtras {
    // ---------------------------------------------------------------------
    // ---------------------------------------------------------------------
    // Standard extra data keys.

    /**
     * The initial data to place in a newly created record.  Use with
     * {@link #ACTION_INSERT}.  The data here is a Map containing the same
     * fields as would be given to the underlying ContentProvider.insert()
     * call.
     */
    public static final String EXTRA_TEMPLATE = "android.intent.extra.TEMPLATE";

    /**
     * A constant CharSequence that is associated with the Intent, used with
     * {@link #ACTION_SEND} to supply the literal data to be sent.  Note that
     * this may be a styled CharSequence, so you must use
     * {@link Bundle#getCharSequence(String) Bundle.getCharSequence()} to
     * retrieve it.
     */
    public static final String EXTRA_TEXT = "android.intent.extra.TEXT";

    /**
     * A constant String that is associated with the Intent, used with
     * {@link #ACTION_SEND} to supply an alternative to {@link #EXTRA_TEXT}
     * as HTML formatted text.  Note that you <em>must</em> also supply
     * {@link #EXTRA_TEXT}.
     */
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";

    /**
     * A content: URI holding a stream of data associated with the Intent,
     * used with {@link #ACTION_SEND} to supply the data being sent.
     */
    public static final String EXTRA_STREAM = "android.intent.extra.STREAM";

    /**
     * A String[] holding e-mail addresses that should be delivered to.
     */
    public static final String EXTRA_EMAIL       = "android.intent.extra.EMAIL";

    /**
     * A String[] holding e-mail addresses that should be carbon copied.
     */
    public static final String EXTRA_CC       = "android.intent.extra.CC";

    /**
     * A String[] holding e-mail addresses that should be blind carbon copied.
     */
    public static final String EXTRA_BCC      = "android.intent.extra.BCC";

    /**
     * A constant string holding the desired subject line of a message.
     */
    public static final String EXTRA_SUBJECT  = "android.intent.extra.SUBJECT";

    /**
     * An Intent describing the choices you would like shown with
     * {@link #ACTION_PICK_ACTIVITY} or {@link #ACTION_CHOOSER}.
     */
    public static final String EXTRA_INTENT = "android.intent.extra.INTENT";

    /**
     * An int representing the user id to be used.
     *
     * @hide
     */
    public static final String EXTRA_USER_ID = "android.intent.extra.USER_ID";

    /**
     * An int representing the task id to be retrieved. This is used when a launch from recents is
     * intercepted by another action such as credentials confirmation to remember which task should
     * be resumed when complete.
     *
     * @hide
     */
    public static final String EXTRA_TASK_ID = "android.intent.extra.TASK_ID";

    /**
     * An Intent[] describing additional, alternate choices you would like shown with
     * {@link #ACTION_CHOOSER}.
     *
     * <p>An app may be capable of providing several different payload types to complete a
     * user's intended action. For example, an app invoking {@link #ACTION_SEND} to share photos
     * with another app may use EXTRA_ALTERNATE_INTENTS to have the chooser transparently offer
     * several different supported sending mechanisms for sharing, such as the actual "image/*"
     * photo data or a hosted link where the photos can be viewed.</p>
     *
     * <p>The intent present in {@link #EXTRA_INTENT} will be treated as the
     * first/primary/preferred intent in the set. Additional intents specified in
     * this extra are ordered; by default intents that appear earlier in the array will be
     * preferred over intents that appear later in the array as matches for the same
     * target component. To alter this preference, a calling app may also supply
     * {@link #EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER}.</p>
     */
    public static final String EXTRA_ALTERNATE_INTENTS = "android.intent.extra.ALTERNATE_INTENTS";

    /**
     * A {@link ComponentName ComponentName[]} describing components that should be filtered out
     * and omitted from a list of components presented to the user.
     *
     * <p>When used with {@link #ACTION_CHOOSER}, the chooser will omit any of the components
     * in this array if it otherwise would have shown them. Useful for omitting specific targets
     * from your own package or other apps from your organization if the idea of sending to those
     * targets would be redundant with other app functionality. Filtered components will not
     * be able to present targets from an associated <code>ChooserTargetService</code>.</p>
     */
    public static final String EXTRA_EXCLUDE_COMPONENTS
            = "android.intent.extra.EXCLUDE_COMPONENTS";

    /**
     * A {@link android.service.chooser.ChooserTarget ChooserTarget[]} for {@link #ACTION_CHOOSER}
     * describing additional high-priority deep-link targets for the chooser to present to the user.
     *
     * <p>Targets provided in this way will be presented inline with all other targets provided
     * by services from other apps. They will be prioritized before other service targets, but
     * after those targets provided by sources that the user has manually pinned to the front.</p>
     *
     * @see #ACTION_CHOOSER
     */
    public static final String EXTRA_CHOOSER_TARGETS = "android.intent.extra.CHOOSER_TARGETS";

    /**
     * An {@link IntentSender} for an Activity that will be invoked when the user makes a selection
     * from the chooser activity presented by {@link #ACTION_CHOOSER}.
     *
     * <p>An app preparing an action for another app to complete may wish to allow the user to
     * disambiguate between several options for completing the action based on the chosen target
     * or otherwise refine the action before it is invoked.
     * </p>
     *
     * <p>When sent, this IntentSender may be filled in with the following extras:</p>
     * <ul>
     *     <li>{@link #EXTRA_INTENT} The first intent that matched the user's chosen target</li>
     *     <li>{@link #EXTRA_ALTERNATE_INTENTS} Any additional intents that also matched the user's
     *     chosen target beyond the first</li>
     *     <li>{@link #EXTRA_RESULT_RECEIVER} A {@link ResultReceiver} that the refinement activity
     *     should fill in and send once the disambiguation is complete</li>
     * </ul>
     */
    public static final String EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER
            = "android.intent.extra.CHOOSER_REFINEMENT_INTENT_SENDER";

    /**
     * A {@link ResultReceiver} used to return data back to the sender.
     *
     * <p>Used to complete an app-specific
     * {@link #EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER refinement} for {@link #ACTION_CHOOSER}.</p>
     *
     * <p>If {@link #EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER} is present in the intent
     * used to start a {@link #ACTION_CHOOSER} activity this extra will be
     * {@link #fillIn(Intent, int) filled in} to that {@link IntentSender} and sent
     * when the user selects a target component from the chooser. It is up to the recipient
     * to send a result to this ResultReceiver to signal that disambiguation is complete
     * and that the chooser should invoke the user's choice.</p>
     *
     * <p>The disambiguator should provide a Bundle to the ResultReceiver with an intent
     * assigned to the key {@link #EXTRA_INTENT}. This supplied intent will be used by the chooser
     * to match and fill in the final Intent or ChooserTarget before starting it.
     * The supplied intent must {@link #filterEquals(Intent) match} one of the intents from
     * {@link #EXTRA_INTENT} or {@link #EXTRA_ALTERNATE_INTENTS} passed to
     * {@link #EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER} to be accepted.</p>
     *
     * <p>The result code passed to the ResultReceiver should be
     * {@link android.app.Activity#RESULT_OK} if the refinement succeeded and the supplied intent's
     * target in the chooser should be started, or {@link android.app.Activity#RESULT_CANCELED} if
     * the chooser should finish without starting a target.</p>
     */
    public static final String EXTRA_RESULT_RECEIVER
            = "android.intent.extra.RESULT_RECEIVER";

    /**
     * A CharSequence dialog title to provide to the user when used with a
     * {@link #ACTION_CHOOSER}.
     */
    public static final String EXTRA_TITLE = "android.intent.extra.TITLE";

    /**
     * A Parcelable[] of {@link Intent} or
     * {@link android.content.pm.LabeledIntent} objects as set with
     * {@link #putExtra(String, Parcelable[])} of additional activities to place
     * a the front of the list of choices, when shown to the user with a
     * {@link #ACTION_CHOOSER}.
     */
    public static final String EXTRA_INITIAL_INTENTS = "android.intent.extra.INITIAL_INTENTS";

    /**
     * A {@link IntentSender} to start after ephemeral installation success.
     * @hide
     */
    public static final String EXTRA_EPHEMERAL_SUCCESS = "android.intent.extra.EPHEMERAL_SUCCESS";

    /**
     * A {@link IntentSender} to start after ephemeral installation failure.
     * @hide
     */
    public static final String EXTRA_EPHEMERAL_FAILURE = "android.intent.extra.EPHEMERAL_FAILURE";

    /**
     * A Bundle forming a mapping of potential target package names to different extras Bundles
     * to add to the default intent extras in {@link #EXTRA_INTENT} when used with
     * {@link #ACTION_CHOOSER}. Each key should be a package name. The package need not
     * be currently installed on the device.
     *
     * <p>An application may choose to provide alternate extras for the case where a user
     * selects an activity from a predetermined set of target packages. If the activity
     * the user selects from the chooser belongs to a package with its package name as
     * a key in this bundle, the corresponding extras for that package will be merged with
     * the extras already present in the intent at {@link #EXTRA_INTENT}. If a replacement
     * extra has the same key as an extra already present in the intent it will overwrite
     * the extra from the intent.</p>
     *
     * <p><em>Examples:</em>
     * <ul>
     *     <li>An application may offer different {@link #EXTRA_TEXT} to an application
     *     when sharing with it via {@link #ACTION_SEND}, augmenting a link with additional query
     *     parameters for that target.</li>
     *     <li>An application may offer additional metadata for known targets of a given intent
     *     to pass along information only relevant to that target such as account or content
     *     identifiers already known to that application.</li>
     * </ul></p>
     */
    public static final String EXTRA_REPLACEMENT_EXTRAS =
            "android.intent.extra.REPLACEMENT_EXTRAS";

    /**
     * An {@link IntentSender} that will be notified if a user successfully chooses a target
     * component to handle an action in an {@link #ACTION_CHOOSER} activity. The IntentSender
     * will have the extra {@link #EXTRA_CHOSEN_COMPONENT} appended to it containing the
     * {@link ComponentName} of the chosen component.
     *
     * <p>In some situations this callback may never come, for example if the user abandons
     * the chooser, switches to another task or any number of other reasons. Apps should not
     * be written assuming that this callback will always occur.</p>
     */
    public static final String EXTRA_CHOSEN_COMPONENT_INTENT_SENDER =
            "android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER";

    /**
     * The {@link ComponentName} chosen by the user to complete an action.
     *
     * @see #EXTRA_CHOSEN_COMPONENT_INTENT_SENDER
     */
    public static final String EXTRA_CHOSEN_COMPONENT = "android.intent.extra.CHOSEN_COMPONENT";

    /**
     * A {@link android.view.KeyEvent} object containing the event that
     * triggered the creation of the Intent it is in.
     */
    public static final String EXTRA_KEY_EVENT = "android.intent.extra.KEY_EVENT";

    /**
     * Set to true in {@link #ACTION_REQUEST_SHUTDOWN} to request confirmation from the user
     * before shutting down.
     *
     * {@hide}
     */
    public static final String EXTRA_KEY_CONFIRM = "android.intent.extra.KEY_CONFIRM";

    /**
     * Set to true in {@link #ACTION_REQUEST_SHUTDOWN} to indicate that the shutdown is
     * requested by the user.
     *
     * {@hide}
     */
    public static final String EXTRA_USER_REQUESTED_SHUTDOWN =
            "android.intent.extra.USER_REQUESTED_SHUTDOWN";

    /**
     * Used as a boolean extra field in {@link android.content.Intent#ACTION_PACKAGE_REMOVED} or
     * {@link android.content.Intent#ACTION_PACKAGE_CHANGED} intents to override the default action
     * of restarting the application.
     */
    public static final String EXTRA_DONT_KILL_APP = "android.intent.extra.DONT_KILL_APP";

    /**
     * A String holding the phone number originally entered in
     * {@link android.content.Intent#ACTION_NEW_OUTGOING_CALL}, or the actual
     * number to call in a {@link android.content.Intent#ACTION_CALL}.
     */
    public static final String EXTRA_PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";

    /**
     * Used as an int extra field in {@link android.content.Intent#ACTION_UID_REMOVED}
     * intents to supply the uid the package had been assigned.  Also an optional
     * extra in {@link android.content.Intent#ACTION_PACKAGE_REMOVED} or
     * {@link android.content.Intent#ACTION_PACKAGE_CHANGED} for the same
     * purpose.
     */
    public static final String EXTRA_UID = "android.intent.extra.UID";

    /**
     * @hide String array of package names.
     */
    @SystemApi
    public static final String EXTRA_PACKAGES = "android.intent.extra.PACKAGES";

    /**
     * Used as a boolean extra field in {@link android.content.Intent#ACTION_PACKAGE_REMOVED}
     * intents to indicate whether this represents a full uninstall (removing
     * both the code and its data) or a partial uninstall (leaving its data,
     * implying that this is an update).
     */
    public static final String EXTRA_DATA_REMOVED = "android.intent.extra.DATA_REMOVED";

    /**
     * @hide
     * Used as a boolean extra field in {@link android.content.Intent#ACTION_PACKAGE_REMOVED}
     * intents to indicate that at this point the package has been removed for
     * all users on the device.
     */
    public static final String EXTRA_REMOVED_FOR_ALL_USERS
            = "android.intent.extra.REMOVED_FOR_ALL_USERS";

    /**
     * Used as a boolean extra field in {@link android.content.Intent#ACTION_PACKAGE_REMOVED}
     * intents to indicate that this is a replacement of the package, so this
     * broadcast will immediately be followed by an add broadcast for a
     * different version of the same package.
     */
    public static final String EXTRA_REPLACING = "android.intent.extra.REPLACING";

    /**
     * Used as an int extra field in {@link android.app.AlarmManager} intents
     * to tell the application being invoked how many pending alarms are being
     * delievered with the intent.  For one-shot alarms this will always be 1.
     * For recurring alarms, this might be greater than 1 if the device was
     * asleep or powered off at the time an earlier alarm would have been
     * delivered.
     */
    public static final String EXTRA_ALARM_COUNT = "android.intent.extra.ALARM_COUNT";

    /**
     * Used as an int extra field in {@link android.content.Intent#ACTION_DOCK_EVENT}
     * intents to request the dock state.  Possible values are
     * {@link android.content.Intent#EXTRA_DOCK_STATE_UNDOCKED},
     * {@link android.content.Intent#EXTRA_DOCK_STATE_DESK}, or
     * {@link android.content.Intent#EXTRA_DOCK_STATE_CAR}, or
     * {@link android.content.Intent#EXTRA_DOCK_STATE_LE_DESK}, or
     * {@link android.content.Intent#EXTRA_DOCK_STATE_HE_DESK}.
     */
    public static final String EXTRA_DOCK_STATE = "android.intent.extra.DOCK_STATE";

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is not in any dock.
     */
    public static final int EXTRA_DOCK_STATE_UNDOCKED = 0;

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is in a desk dock.
     */
    public static final int EXTRA_DOCK_STATE_DESK = 1;

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is in a car dock.
     */
    public static final int EXTRA_DOCK_STATE_CAR = 2;

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is in a analog (low end) dock.
     */
    public static final int EXTRA_DOCK_STATE_LE_DESK = 3;

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is in a digital (high end) dock.
     */
    public static final int EXTRA_DOCK_STATE_HE_DESK = 4;

    /**
     * Boolean that can be supplied as meta-data with a dock activity, to
     * indicate that the dock should take over the home key when it is active.
     */
    public static final String METADATA_DOCK_HOME = "android.dock_home";

    /**
     * Used as a parcelable extra field in {@link #ACTION_APP_ERROR}, containing
     * the bug report.
     */
    public static final String EXTRA_BUG_REPORT = "android.intent.extra.BUG_REPORT";

    /**
     * Used in the extra field in the remote intent. It's astring token passed with the
     * remote intent.
     */
    public static final String EXTRA_REMOTE_INTENT_TOKEN =
            "android.intent.extra.remote_intent_token";

    /**
     * @deprecated See {@link #EXTRA_CHANGED_COMPONENT_NAME_LIST}; this field
     * will contain only the first name in the list.
     */
    @Deprecated public static final String EXTRA_CHANGED_COMPONENT_NAME =
            "android.intent.extra.changed_component_name";

    /**
     * This field is part of {@link android.content.Intent#ACTION_PACKAGE_CHANGED},
     * and contains a string array of all of the components that have changed.  If
     * the state of the overall package has changed, then it will contain an entry
     * with the package name itself.
     */
    public static final String EXTRA_CHANGED_COMPONENT_NAME_LIST =
            "android.intent.extra.changed_component_name_list";

    /**
     * This field is part of
     * {@link android.content.Intent#ACTION_EXTERNAL_APPLICATIONS_AVAILABLE},
     * {@link android.content.Intent#ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE},
     * {@link android.content.Intent#ACTION_PACKAGES_SUSPENDED},
     * {@link android.content.Intent#ACTION_PACKAGES_UNSUSPENDED}
     * and contains a string array of all of the components that have changed.
     */
    public static final String EXTRA_CHANGED_PACKAGE_LIST =
            "android.intent.extra.changed_package_list";

    /**
     * This field is part of
     * {@link android.content.Intent#ACTION_EXTERNAL_APPLICATIONS_AVAILABLE},
     * {@link android.content.Intent#ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE}
     * and contains an integer array of uids of all of the components
     * that have changed.
     */
    public static final String EXTRA_CHANGED_UID_LIST =
            "android.intent.extra.changed_uid_list";

    /**
     * @hide
     * Magic extra system code can use when binding, to give a label for
     * who it is that has bound to a service.  This is an integer giving
     * a framework string resource that can be displayed to the user.
     */
    public static final String EXTRA_CLIENT_LABEL =
            "android.intent.extra.client_label";

    /**
     * @hide
     * Magic extra system code can use when binding, to give a PendingIntent object
     * that can be launched for the user to disable the system's use of this
     * service.
     */
    public static final String EXTRA_CLIENT_INTENT =
            "android.intent.extra.client_intent";

    /**
     * Extra used to indicate that an intent should only return data that is on
     * the local device. This is a boolean extra; the default is false. If true,
     * an implementation should only allow the user to select data that is
     * already on the device, not requiring it be downloaded from a remote
     * service when opened.
     *
     * @see #ACTION_GET_CONTENT
     * @see #ACTION_OPEN_DOCUMENT
     * @see #ACTION_OPEN_DOCUMENT_TREE
     * @see #ACTION_CREATE_DOCUMENT
     */
    public static final String EXTRA_LOCAL_ONLY =
            "android.intent.extra.LOCAL_ONLY";

    /**
     * Extra used to indicate that an intent can allow the user to select and
     * return multiple items. This is a boolean extra; the default is false. If
     * true, an implementation is allowed to present the user with a UI where
     * they can pick multiple items that are all returned to the caller. When
     * this happens, they should be returned as the {@link #getClipData()} part
     * of the result Intent.
     *
     * @see #ACTION_GET_CONTENT
     * @see #ACTION_OPEN_DOCUMENT
     */
    public static final String EXTRA_ALLOW_MULTIPLE =
            "android.intent.extra.ALLOW_MULTIPLE";

    /**
     * The integer userHandle carried with broadcast intents related to addition, removal and
     * switching of users and managed profiles - {@link #ACTION_USER_ADDED},
     * {@link #ACTION_USER_REMOVED} and {@link #ACTION_USER_SWITCHED}.
     *
     * @hide
     */
    public static final String EXTRA_USER_HANDLE =
            "android.intent.extra.user_handle";

    /**
     * The UserHandle carried with broadcasts intents related to addition and removal of managed
     * profiles - {@link #ACTION_MANAGED_PROFILE_ADDED} and {@link #ACTION_MANAGED_PROFILE_REMOVED}.
     */
    public static final String EXTRA_USER =
            "android.intent.extra.USER";

    /**
     * Extra used in the response from a BroadcastReceiver that handles
     * {@link #ACTION_GET_RESTRICTION_ENTRIES}. The type of the extra is
     * <code>ArrayList&lt;RestrictionEntry&gt;</code>.
     */
    public static final String EXTRA_RESTRICTIONS_LIST = "android.intent.extra.restrictions_list";

    /**
     * Extra sent in the intent to the BroadcastReceiver that handles
     * {@link #ACTION_GET_RESTRICTION_ENTRIES}. The type of the extra is a Bundle containing
     * the restrictions as key/value pairs.
     */
    public static final String EXTRA_RESTRICTIONS_BUNDLE =
            "android.intent.extra.restrictions_bundle";

    /**
     * Extra used in the response from a BroadcastReceiver that handles
     * {@link #ACTION_GET_RESTRICTION_ENTRIES}.
     */
    public static final String EXTRA_RESTRICTIONS_INTENT =
            "android.intent.extra.restrictions_intent";

    /**
     * Extra used to communicate a set of acceptable MIME types. The type of the
     * extra is {@code String[]}. Values may be a combination of concrete MIME
     * types (such as "image/png") and/or partial MIME types (such as
     * "audio/*").
     *
     * @see #ACTION_GET_CONTENT
     * @see #ACTION_OPEN_DOCUMENT
     */
    public static final String EXTRA_MIME_TYPES = "android.intent.extra.MIME_TYPES";

    /**
     * Optional extra for {@link #ACTION_SHUTDOWN} that allows the sender to qualify that
     * this shutdown is only for the user space of the system, not a complete shutdown.
     * When this is true, hardware devices can use this information to determine that
     * they shouldn't do a complete shutdown of their device since this is not a
     * complete shutdown down to the kernel, but only user space restarting.
     * The default if not supplied is false.
     */
    public static final String EXTRA_SHUTDOWN_USERSPACE_ONLY
            = "android.intent.extra.SHUTDOWN_USERSPACE_ONLY";

    /**
     * Optional boolean extra for {@link #ACTION_TIME_CHANGED} that indicates the
     * user has set their time format preferences to the 24 hour format.
     *
     * @hide for internal use only.
     */
    public static final String EXTRA_TIME_PREF_24_HOUR_FORMAT =
            "android.intent.extra.TIME_PREF_24_HOUR_FORMAT";

    /** {@hide} */
    public static final String EXTRA_REASON = "android.intent.extra.REASON";

    /** {@hide} */
    public static final String EXTRA_WIPE_EXTERNAL_STORAGE = "android.intent.extra.WIPE_EXTERNAL_STORAGE";

    /**
     * Optional {@link android.app.PendingIntent} extra used to deliver the result of the SIM
     * activation request.
     * TODO: Add information about the structure and response data used with the pending intent.
     * @hide
     */
    public static final String EXTRA_SIM_ACTIVATION_RESPONSE =
            "android.intent.extra.SIM_ACTIVATION_RESPONSE";

    /**
     * Optional index with semantics depending on the intent action.
     *
     * <p>The value must be an integer greater or equal to 0.
     */
    public static final String EXTRA_INDEX = "android.intent.extra.INDEX";

    /**
     * Optional boolean extra indicating whether quiet mode has been switched on or off.
     * When a profile goes into quiet mode, all apps in the profile are killed and the
     * profile user is stopped. Widgets originating from the profile are masked, and app
     * launcher icons are grayed out.
     */
    public static final String EXTRA_QUIET_MODE = "android.intent.extra.QUIET_MODE";

    /**
     * Used as an int extra field in {@link #ACTION_MEDIA_RESOURCE_GRANTED}
     * intents to specify the resource type granted. Possible values are
     * {@link #EXTRA_MEDIA_RESOURCE_TYPE_VIDEO_CODEC} or
     * {@link #EXTRA_MEDIA_RESOURCE_TYPE_AUDIO_CODEC}.
     *
     * @hide
     */
    public static final String EXTRA_MEDIA_RESOURCE_TYPE =
            "android.intent.extra.MEDIA_RESOURCE_TYPE";

    /**
     * Used as an int value for {@link #EXTRA_MEDIA_RESOURCE_TYPE}
     * to represent that a video codec is allowed to use.
     *
     * @hide
     */
    public static final int EXTRA_MEDIA_RESOURCE_TYPE_VIDEO_CODEC = 0;

    /**
     * Used as an int value for {@link #EXTRA_MEDIA_RESOURCE_TYPE}
     * to represent that a audio codec is allowed to use.
     *
     * @hide
     */
    public static final int EXTRA_MEDIA_RESOURCE_TYPE_AUDIO_CODEC = 1;
}
