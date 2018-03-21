package android.content;

import android.annotation.SdkConstant;
import android.annotation.SdkConstant.SdkConstantType;
import android.annotation.SystemApi;
import android.net.Uri;
import android.os.Bundle;

/**
 * <p>
 * Created by Yin Yong on 2018/3/15.
 */
@SuppressWarnings({"unused", "UnnecessaryInterfaceModifier", "deprecation"})
interface IntentActions {
    // ---------------------------------------------------------------------
    // ---------------------------------------------------------------------
    // Standard intent activity actions (see action variable).

    /**
     * Activity Action: Start as a main entry point, does not expect to
     * receive data.
     * <p>Input: nothing
     * <p>Output: nothing
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_MAIN = "android.intent.action.MAIN";

    /**
     * Activity Action: Display the data to the user.  This is the most common
     * action performed on data -- it is the generic action you can use on
     * a piece of data to get the most reasonable thing to occur.  For example,
     * when used on a contacts entry it will view the entry; when used on a
     * mailto: URI it will bring up a compose window filled with the information
     * supplied by the URI; when used with a tel: URI it will invoke the
     * dialer.
     * <p>Input: {@link #getData} is URI from which to retrieve data.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_VIEW = "android.intent.action.VIEW";

    /**
     * A synonym for {@link #ACTION_VIEW}, the "standard" action that is
     * performed on a piece of data.
     */
    public static final String ACTION_DEFAULT = ACTION_VIEW;

    /**
     * Activity Action: Quick view the data. Launches a quick viewer for
     * a URI or a list of URIs.
     * <p>Activities handling this intent action should handle the vast majority of
     * MIME types rather than only specific ones.
     * <p>Input: {@link #getData} is a mandatory content URI of the item to
     * preview. {@link #getClipData} contains an optional list of content URIs
     * if there is more than one item to preview. {@link #EXTRA_INDEX} is an
     * optional index of the URI in the clip data to show first.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_QUICK_VIEW = "android.intent.action.QUICK_VIEW";

    /**
     * Used to indicate that some piece of data should be attached to some other
     * place.  For example, image data could be attached to a contact.  It is up
     * to the recipient to decide where the data should be attached; the intent
     * does not specify the ultimate destination.
     * <p>Input: {@link #getData} is URI of data to be attached.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_ATTACH_DATA = "android.intent.action.ATTACH_DATA";

    /**
     * Activity Action: Provide explicit editable access to the given data.
     * <p>Input: {@link #getData} is URI of data to be edited.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_EDIT = "android.intent.action.EDIT";

    /**
     * Activity Action: Pick an existing item, or insert a new item, and then edit it.
     * <p>Input: {@link #getType} is the desired MIME type of the item to create or edit.
     * The extras can contain type specific data to pass through to the editing/creating
     * activity.
     * <p>Output: The URI of the item that was picked.  This must be a content:
     * URI so that any receiver can access it.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_INSERT_OR_EDIT = "android.intent.action.INSERT_OR_EDIT";

    /**
     * Activity Action: Pick an item from the data, returning what was selected.
     * <p>Input: {@link #getData} is URI containing a directory of data
     * (vnd.android.cursor.dir/*) from which to pick an item.
     * <p>Output: The URI of the item that was picked.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_PICK = "android.intent.action.PICK";

    /**
     * Activity Action: Creates a shortcut.
     * <p>Input: Nothing.</p>
     * <p>Output: An Intent representing the shortcut. The intent must contain three
     * extras: SHORTCUT_INTENT (value: Intent), SHORTCUT_NAME (value: String),
     * and SHORTCUT_ICON (value: Bitmap) or SHORTCUT_ICON_RESOURCE
     * (value: ShortcutIconResource).</p>
     *
     * @see #EXTRA_SHORTCUT_INTENT
     * @see #EXTRA_SHORTCUT_NAME
     * @see #EXTRA_SHORTCUT_ICON
     * @see #EXTRA_SHORTCUT_ICON_RESOURCE
     * @see android.content.Intent.ShortcutIconResource
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_CREATE_SHORTCUT = "android.intent.action.CREATE_SHORTCUT";

    /**
     * The name of the extra used to define the Intent of a shortcut.
     *
     * @see #ACTION_CREATE_SHORTCUT
     */
    public static final String EXTRA_SHORTCUT_INTENT = "android.intent.extra.shortcut.INTENT";
    /**
     * The name of the extra used to define the name of a shortcut.
     *
     * @see #ACTION_CREATE_SHORTCUT
     */
    public static final String EXTRA_SHORTCUT_NAME = "android.intent.extra.shortcut.NAME";
    /**
     * The name of the extra used to define the icon, as a Bitmap, of a shortcut.
     *
     * @see #ACTION_CREATE_SHORTCUT
     */
    public static final String EXTRA_SHORTCUT_ICON = "android.intent.extra.shortcut.ICON";
    /**
     * The name of the extra used to define the icon, as a ShortcutIconResource, of a shortcut.
     *
     * @see #ACTION_CREATE_SHORTCUT
     * @see android.content.Intent.ShortcutIconResource
     */
    public static final String EXTRA_SHORTCUT_ICON_RESOURCE =
            "android.intent.extra.shortcut.ICON_RESOURCE";

    /**
     * Activity Action: Display an activity chooser, allowing the user to pick
     * what they want to before proceeding.  This can be used as an alternative
     * to the standard activity picker that is displayed by the system when
     * you try to start an activity with multiple possible matches, with these
     * differences in behavior:
     * <ul>
     * <li>You can specify the title that will appear in the activity chooser.
     * <li>The user does not have the option to make one of the matching
     * activities a preferred activity, and all possible activities will
     * always be shown even if one of them is currently marked as the
     * preferred activity.
     * </ul>
     * <p>
     * This action should be used when the user will naturally expect to
     * select an activity in order to proceed.  An example if when not to use
     * it is when the user clicks on a "mailto:" link.  They would naturally
     * expect to go directly to their mail app, so startActivity() should be
     * called directly: it will
     * either launch the current preferred app, or put up a dialog allowing the
     * user to pick an app to use and optionally marking that as preferred.
     * <p>
     * In contrast, if the user is selecting a menu item to send a picture
     * they are viewing to someone else, there are many different things they
     * may want to do at this point: send it through e-mail, upload it to a
     * web service, etc.  In this case the CHOOSER action should be used, to
     * always present to the user a list of the things they can do, with a
     * nice title given by the caller such as "Send this photo with:".
     * <p>
     * If you need to grant URI permissions through a chooser, you must specify
     * the permissions to be granted on the ACTION_CHOOSER Intent
     * <em>in addition</em> to the EXTRA_INTENT inside.  This means using
     * {@link #setClipData} to specify the URIs to be granted as well as
     * {@link #FLAG_GRANT_READ_URI_PERMISSION} and/or
     * {@link #FLAG_GRANT_WRITE_URI_PERMISSION} as appropriate.
     * <p>
     * As a convenience, an Intent of this form can be created with the
     * {@link #createChooser} function.
     * <p>
     * Input: No data should be specified.  get*Extra must have
     * a {@link #EXTRA_INTENT} field containing the Intent being executed,
     * and can optionally have a {@link #EXTRA_TITLE} field containing the
     * title text to display in the chooser.
     * <p>
     * Output: Depends on the protocol of {@link #EXTRA_INTENT}.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_CHOOSER = "android.intent.action.CHOOSER";

    /**
     * Activity Action: Allow the user to select a particular kind of data and
     * return it.  This is different than {@link #ACTION_PICK} in that here we
     * just say what kind of data is desired, not a URI of existing data from
     * which the user can pick.  An ACTION_GET_CONTENT could allow the user to
     * create the data as it runs (for example taking a picture or recording a
     * sound), let them browse over the web and download the desired data,
     * etc.
     * <p>
     * There are two main ways to use this action: if you want a specific kind
     * of data, such as a person contact, you set the MIME type to the kind of
     * data you want and launch it with {@link Context#startActivity(Intent)}.
     * The system will then launch the best application to select that kind
     * of data for you.
     * <p>
     * You may also be interested in any of a set of types of content the user
     * can pick.  For example, an e-mail application that wants to allow the
     * user to add an attachment to an e-mail message can use this action to
     * bring up a list of all of the types of content the user can attach.
     * <p>
     * In this case, you should wrap the GET_CONTENT intent with a chooser
     * (through {@link #createChooser}), which will give the proper interface
     * for the user to pick how to send your data and allow you to specify
     * a prompt indicating what they are doing.  You will usually specify a
     * broad MIME type (such as image/* or {@literal *}/*), resulting in a
     * broad range of content types the user can select from.
     * <p>
     * When using such a broad GET_CONTENT action, it is often desirable to
     * only pick from data that can be represented as a stream.  This is
     * accomplished by requiring the {@link #CATEGORY_OPENABLE} in the Intent.
     * <p>
     * Callers can optionally specify {@link #EXTRA_LOCAL_ONLY} to request that
     * the launched content chooser only returns results representing data that
     * is locally available on the device.  For example, if this extra is set
     * to true then an image picker should not show any pictures that are available
     * from a remote server but not already on the local device (thus requiring
     * they be downloaded when opened).
     * <p>
     * If the caller can handle multiple returned items (the user performing
     * multiple selection), then it can specify {@link #EXTRA_ALLOW_MULTIPLE}
     * to indicate this.
     * <p>
     * Input: {@link #getType} is the desired MIME type to retrieve.  Note
     * that no URI is supplied in the intent, as there are no constraints on
     * where the returned data originally comes from.  You may also include the
     * {@link #CATEGORY_OPENABLE} if you can only accept data that can be
     * opened as a stream.  You may use {@link #EXTRA_LOCAL_ONLY} to limit content
     * selection to local data.  You may use {@link #EXTRA_ALLOW_MULTIPLE} to
     * allow the user to select multiple items.
     * <p>
     * Output: The URI of the item that was picked.  This must be a content:
     * URI so that any receiver can access it.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT";
    /**
     * Activity Action: Dial a number as specified by the data.  This shows a
     * UI with the number being dialed, allowing the user to explicitly
     * initiate the call.
     * <p>Input: If nothing, an empty dialer is started; else {@link #getData}
     * is URI of a phone number to be dialed or a tel: URI of an explicit phone
     * number.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_DIAL = "android.intent.action.DIAL";
    /**
     * Activity Action: Perform a call to someone specified by the data.
     * <p>Input: If nothing, an empty dialer is started; else {@link #getData}
     * is URI of a phone number to be dialed or a tel: URI of an explicit phone
     * number.
     * <p>Output: nothing.
     * <p>
     * <p>Note: there will be restrictions on which applications can initiate a
     * call; most applications should use the {@link #ACTION_DIAL}.
     * <p>Note: this Intent <strong>cannot</strong> be used to call emergency
     * numbers.  Applications can <strong>dial</strong> emergency numbers using
     * {@link #ACTION_DIAL}, however.
     * <p>
     * <p>Note: if you app targets {@link android.os.Build.VERSION_CODES#M M}
     * and above and declares as using the {@link android.Manifest.permission#CALL_PHONE}
     * permission which is not granted, then attempting to use this action will
     * result in a {@link java.lang.SecurityException}.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_CALL = "android.intent.action.CALL";
    /**
     * Activity Action: Perform a call to an emergency number specified by the
     * data.
     * <p>Input: {@link #getData} is URI of a phone number to be dialed or a
     * tel: URI of an explicit phone number.
     * <p>Output: nothing.
     *
     * @hide
     */
    public static final String ACTION_CALL_EMERGENCY = "android.intent.action.CALL_EMERGENCY";
    /**
     * Activity action: Perform a call to any number (emergency or not)
     * specified by the data.
     * <p>Input: {@link #getData} is URI of a phone number to be dialed or a
     * tel: URI of an explicit phone number.
     * <p>Output: nothing.
     *
     * @hide
     */
    public static final String ACTION_CALL_PRIVILEGED = "android.intent.action.CALL_PRIVILEGED";
    /**
     * Activity action: Activate the current SIM card.  If SIM cards do not require activation,
     * sending this intent is a no-op.
     * <p>Input: No data should be specified.  get*Extra may have an optional
     * {@link #EXTRA_SIM_ACTIVATION_RESPONSE} field containing a PendingIntent through which to
     * send the activation result.
     * <p>Output: nothing.
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_SIM_ACTIVATION_REQUEST =
            "android.intent.action.SIM_ACTIVATION_REQUEST";
    /**
     * Activity Action: Send a message to someone specified by the data.
     * <p>Input: {@link #getData} is URI describing the target.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_SENDTO = "android.intent.action.SENDTO";
    /**
     * Activity Action: Deliver some data to someone else.  Who the data is
     * being delivered to is not specified; it is up to the receiver of this
     * action to ask the user where the data should be sent.
     * <p>
     * When launching a SEND intent, you should usually wrap it in a chooser
     * (through {@link #createChooser}), which will give the proper interface
     * for the user to pick how to send your data and allow you to specify
     * a prompt indicating what they are doing.
     * <p>
     * Input: {@link #getType} is the MIME type of the data being sent.
     * get*Extra can have either a {@link #EXTRA_TEXT}
     * or {@link #EXTRA_STREAM} field, containing the data to be sent.  If
     * using EXTRA_TEXT, the MIME type should be "text/plain"; otherwise it
     * should be the MIME type of the data in EXTRA_STREAM.  Use {@literal *}/*
     * if the MIME type is unknown (this will only allow senders that can
     * handle generic data streams).  If using {@link #EXTRA_TEXT}, you can
     * also optionally supply {@link #EXTRA_HTML_TEXT} for clients to retrieve
     * your text with HTML formatting.
     * <p>
     * As of {@link android.os.Build.VERSION_CODES#JELLY_BEAN}, the data
     * being sent can be supplied through {@link #setClipData(ClipData)}.  This
     * allows you to use {@link #FLAG_GRANT_READ_URI_PERMISSION} when sharing
     * content: URIs and other advanced features of {@link ClipData}.  If
     * using this approach, you still must supply the same data through the
     * {@link #EXTRA_TEXT} or {@link #EXTRA_STREAM} fields described below
     * for compatibility with old applications.  If you don't set a ClipData,
     * it will be copied there for you when calling {@link Context#startActivity(Intent)}.
     * <p>
     * Optional standard extras, which may be interpreted by some recipients as
     * appropriate, are: {@link #EXTRA_EMAIL}, {@link #EXTRA_CC},
     * {@link #EXTRA_BCC}, {@link #EXTRA_SUBJECT}.
     * <p>
     * Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_SEND = "android.intent.action.SEND";
    /**
     * Activity Action: Deliver multiple data to someone else.
     * <p>
     * Like {@link #ACTION_SEND}, except the data is multiple.
     * <p>
     * Input: {@link #getType} is the MIME type of the data being sent.
     * get*ArrayListExtra can have either a {@link #EXTRA_TEXT} or {@link
     * #EXTRA_STREAM} field, containing the data to be sent.  If using
     * {@link #EXTRA_TEXT}, you can also optionally supply {@link #EXTRA_HTML_TEXT}
     * for clients to retrieve your text with HTML formatting.
     * <p>
     * Multiple types are supported, and receivers should handle mixed types
     * whenever possible. The right way for the receiver to check them is to
     * use the content resolver on each URI. The intent sender should try to
     * put the most concrete mime type in the intent type, but it can fall
     * back to {@literal <type>/*} or {@literal *}/* as needed.
     * <p>
     * e.g. if you are sending image/jpg and image/jpg, the intent's type can
     * be image/jpg, but if you are sending image/jpg and image/png, then the
     * intent's type should be image/*.
     * <p>
     * As of {@link android.os.Build.VERSION_CODES#JELLY_BEAN}, the data
     * being sent can be supplied through {@link #setClipData(ClipData)}.  This
     * allows you to use {@link #FLAG_GRANT_READ_URI_PERMISSION} when sharing
     * content: URIs and other advanced features of {@link ClipData}.  If
     * using this approach, you still must supply the same data through the
     * {@link #EXTRA_TEXT} or {@link #EXTRA_STREAM} fields described below
     * for compatibility with old applications.  If you don't set a ClipData,
     * it will be copied there for you when calling {@link Context#startActivity(Intent)}.
     * <p>
     * Optional standard extras, which may be interpreted by some recipients as
     * appropriate, are: {@link #EXTRA_EMAIL}, {@link #EXTRA_CC},
     * {@link #EXTRA_BCC}, {@link #EXTRA_SUBJECT}.
     * <p>
     * Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_SEND_MULTIPLE = "android.intent.action.SEND_MULTIPLE";
    /**
     * Activity Action: Handle an incoming phone call.
     * <p>Input: nothing.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_ANSWER = "android.intent.action.ANSWER";
    /**
     * Activity Action: Insert an empty item into the given container.
     * <p>Input: {@link #getData} is URI of the directory (vnd.android.cursor.dir/*)
     * in which to place the data.
     * <p>Output: URI of the new data that was created.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_INSERT = "android.intent.action.INSERT";
    /**
     * Activity Action: Create a new item in the given container, initializing it
     * from the current contents of the clipboard.
     * <p>Input: {@link #getData} is URI of the directory (vnd.android.cursor.dir/*)
     * in which to place the data.
     * <p>Output: URI of the new data that was created.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_PASTE = "android.intent.action.PASTE";
    /**
     * Activity Action: Delete the given data from its container.
     * <p>Input: {@link #getData} is URI of data to be deleted.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_DELETE = "android.intent.action.DELETE";
    /**
     * Activity Action: Run the data, whatever that means.
     * <p>Input: ?  (Note: this is currently specific to the test harness.)
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_RUN = "android.intent.action.RUN";
    /**
     * Activity Action: Perform a data synchronization.
     * <p>Input: ?
     * <p>Output: ?
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_SYNC = "android.intent.action.SYNC";
    /**
     * Activity Action: Pick an activity given an intent, returning the class
     * selected.
     * <p>Input: get*Extra field {@link #EXTRA_INTENT} is an Intent
     * used with {@link PackageManager#queryIntentActivities} to determine the
     * set of activities from which to pick.
     * <p>Output: Class name of the activity that was selected.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_PICK_ACTIVITY = "android.intent.action.PICK_ACTIVITY";
    /**
     * Activity Action: Perform a search.
     * <p>Input: {@link android.app.SearchManager#QUERY getStringExtra(SearchManager.QUERY)}
     * is the text to search for.  If empty, simply
     * enter your search results Activity with the search UI activated.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_SEARCH = "android.intent.action.SEARCH";
    /**
     * Activity Action: Start the platform-defined tutorial
     * <p>Input: {@link android.app.SearchManager#QUERY getStringExtra(SearchManager.QUERY)}
     * is the text to search for.  If empty, simply
     * enter your search results Activity with the search UI activated.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_SYSTEM_TUTORIAL = "android.intent.action.SYSTEM_TUTORIAL";
    /**
     * Activity Action: Perform a web search.
     * <p>
     * Input: {@link android.app.SearchManager#QUERY
     * getStringExtra(SearchManager.QUERY)} is the text to search for. If it is
     * a url starts with http or https, the site will be opened. If it is plain
     * text, Google search will be applied.
     * <p>
     * Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_WEB_SEARCH = "android.intent.action.WEB_SEARCH";

    /**
     * Activity Action: Perform assist action.
     * <p>
     * Input: {@link #EXTRA_ASSIST_PACKAGE}, {@link #EXTRA_ASSIST_CONTEXT}, can provide
     * additional optional contextual information about where the user was when they
     * requested the assist; {@link #EXTRA_REFERRER} may be set with additional referrer
     * information.
     * Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_ASSIST = "android.intent.action.ASSIST";

    /**
     * Activity Action: Perform voice assist action.
     * <p>
     * Input: {@link #EXTRA_ASSIST_PACKAGE}, {@link #EXTRA_ASSIST_CONTEXT}, can provide
     * additional optional contextual information about where the user was when they
     * requested the voice assist.
     * Output: nothing.
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_VOICE_ASSIST = "android.intent.action.VOICE_ASSIST";

    /**
     * An optional field on {@link #ACTION_ASSIST} containing the name of the current foreground
     * application package at the time the assist was invoked.
     */
    public static final String EXTRA_ASSIST_PACKAGE
            = "android.intent.extra.ASSIST_PACKAGE";

    /**
     * An optional field on {@link #ACTION_ASSIST} containing the uid of the current foreground
     * application package at the time the assist was invoked.
     */
    public static final String EXTRA_ASSIST_UID
            = "android.intent.extra.ASSIST_UID";

    /**
     * An optional field on {@link #ACTION_ASSIST} and containing additional contextual
     * information supplied by the current foreground app at the time of the assist request.
     * This is a {@link Bundle} of additional data.
     */
    public static final String EXTRA_ASSIST_CONTEXT
            = "android.intent.extra.ASSIST_CONTEXT";

    /**
     * An optional field on {@link #ACTION_ASSIST} suggesting that the user will likely use a
     * keyboard as the primary input device for assistance.
     */
    public static final String EXTRA_ASSIST_INPUT_HINT_KEYBOARD =
            "android.intent.extra.ASSIST_INPUT_HINT_KEYBOARD";

    /**
     * An optional field on {@link #ACTION_ASSIST} containing the InputDevice id
     * that was used to invoke the assist.
     */
    public static final String EXTRA_ASSIST_INPUT_DEVICE_ID =
            "android.intent.extra.ASSIST_INPUT_DEVICE_ID";

    /**
     * Activity Action: List all available applications.
     * <p>Input: Nothing.
     * <p>Output: nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_ALL_APPS = "android.intent.action.ALL_APPS";
    /**
     * Activity Action: Show settings for choosing wallpaper.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_SET_WALLPAPER = "android.intent.action.SET_WALLPAPER";

    /**
     * Activity Action: Show activity for reporting a bug.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_BUG_REPORT = "android.intent.action.BUG_REPORT";

    /**
     * Activity Action: Main entry point for factory tests.  Only used when
     * the device is booting in factory test node.  The implementing package
     * must be installed in the system image.
     * <p>Input: nothing
     * <p>Output: nothing
     */
    public static final String ACTION_FACTORY_TEST = "android.intent.action.FACTORY_TEST";

    /**
     * Activity Action: The user pressed the "call" button to go to the dialer
     * or other appropriate UI for placing a call.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_CALL_BUTTON = "android.intent.action.CALL_BUTTON";

    /**
     * Activity Action: Start Voice Command.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_VOICE_COMMAND = "android.intent.action.VOICE_COMMAND";

    /**
     * Activity Action: Start action associated with long pressing on the
     * search key.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_SEARCH_LONG_PRESS = "android.intent.action.SEARCH_LONG_PRESS";

    /**
     * Activity Action: The user pressed the "Report" button in the crash/ANR dialog.
     * This intent is delivered to the package which installed the application, usually
     * Google Play.
     * <p>Input: No data is specified. The bug report is passed in using
     * an {@link #EXTRA_BUG_REPORT} field.
     * <p>Output: Nothing.
     *
     * @see #EXTRA_BUG_REPORT
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_APP_ERROR = "android.intent.action.APP_ERROR";

    /**
     * Activity Action: Show power usage information to the user.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_POWER_USAGE_SUMMARY = "android.intent.action.POWER_USAGE_SUMMARY";

    /**
     * Activity Action: Setup wizard to launch after a platform update.  This
     * activity should have a string meta-data field associated with it,
     * {@link #METADATA_SETUP_VERSION}, which defines the current version of
     * the platform for setup.  The activity will be launched only if
     * {@link android.provider.Settings.Secure#LAST_SETUP_SHOWN} is not the
     * same value.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_UPGRADE_SETUP = "android.intent.action.UPGRADE_SETUP";

    /**
     * Activity Action: Start the Keyboard Shortcuts Helper screen.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_SHOW_KEYBOARD_SHORTCUTS =
            "android.intent.action.SHOW_KEYBOARD_SHORTCUTS";

    /**
     * Activity Action: Dismiss the Keyboard Shortcuts Helper screen.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_DISMISS_KEYBOARD_SHORTCUTS =
            "android.intent.action.DISMISS_KEYBOARD_SHORTCUTS";

    /**
     * Activity Action: Show settings for managing network data usage of a
     * specific application. Applications should define an activity that offers
     * options to control data usage.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_MANAGE_NETWORK_USAGE =
            "android.intent.action.MANAGE_NETWORK_USAGE";

    /**
     * Activity Action: Launch application installer.
     * <p>
     * Input: The data must be a content: or file: URI at which the application
     * can be retrieved.  As of {@link android.os.Build.VERSION_CODES#JELLY_BEAN_MR1},
     * you can also use "package:<package-name>" to install an application for the
     * current user that is already installed for another user. You can optionally supply
     * {@link #EXTRA_INSTALLER_PACKAGE_NAME}, {@link #EXTRA_NOT_UNKNOWN_SOURCE},
     * {@link #EXTRA_ALLOW_REPLACE}, and {@link #EXTRA_RETURN_RESULT}.
     * <p>
     * Output: If {@link #EXTRA_RETURN_RESULT}, returns whether the install
     * succeeded.
     * <p>
     * <strong>Note:</strong>If your app is targeting API level higher than 22 you
     * need to hold {@link android.Manifest.permission#REQUEST_INSTALL_PACKAGES}
     * in order to launch the application installer.
     * </p>
     *
     * @see #EXTRA_INSTALLER_PACKAGE_NAME
     * @see #EXTRA_NOT_UNKNOWN_SOURCE
     * @see #EXTRA_RETURN_RESULT
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_INSTALL_PACKAGE = "android.intent.action.INSTALL_PACKAGE";

    /**
     * Activity Action: Launch ephemeral installer.
     * <p>
     * Input: The data must be a http: URI that the ephemeral application is registered
     * to handle.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     * </p>
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_INSTALL_EPHEMERAL_PACKAGE
            = "android.intent.action.INSTALL_EPHEMERAL_PACKAGE";

    /**
     * Service Action: Resolve ephemeral application.
     * <p>
     * The system will have a persistent connection to this service.
     * This is a protected intent that can only be sent by the system.
     * </p>
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.SERVICE_ACTION)
    public static final String ACTION_RESOLVE_EPHEMERAL_PACKAGE
            = "android.intent.action.RESOLVE_EPHEMERAL_PACKAGE";

    /**
     * Used as a string extra field with {@link #ACTION_INSTALL_PACKAGE} to install a
     * package.  Specifies the installer package name; this package will receive the
     * {@link #ACTION_APP_ERROR} intent.
     */
    public static final String EXTRA_INSTALLER_PACKAGE_NAME
            = "android.intent.extra.INSTALLER_PACKAGE_NAME";

    /**
     * Used as a boolean extra field with {@link #ACTION_INSTALL_PACKAGE} to install a
     * package.  Specifies that the application being installed should not be
     * treated as coming from an unknown source, but as coming from the app
     * invoking the Intent.  For this to work you must start the installer with
     * startActivityForResult().
     */
    public static final String EXTRA_NOT_UNKNOWN_SOURCE
            = "android.intent.extra.NOT_UNKNOWN_SOURCE";

    /**
     * Used as a URI extra field with {@link #ACTION_INSTALL_PACKAGE} and
     * {@link #ACTION_VIEW} to indicate the URI from which the local APK in the Intent
     * data field originated from.
     */
    public static final String EXTRA_ORIGINATING_URI
            = "android.intent.extra.ORIGINATING_URI";

    /**
     * This extra can be used with any Intent used to launch an activity, supplying information
     * about who is launching that activity.  This field contains a {@link android.net.Uri}
     * object, typically an http: or https: URI of the web site that the referral came from;
     * it can also use the {@link #URI_ANDROID_APP_SCHEME android-app:} scheme to identify
     * a native application that it came from.
     * <p>
     * <p>To retrieve this value in a client, use {@link android.app.Activity#getReferrer}
     * instead of directly retrieving the extra.  It is also valid for applications to
     * instead supply {@link #EXTRA_REFERRER_NAME} for cases where they can only create
     * a string, not a Uri; the field here, if supplied, will always take precedence,
     * however.</p>
     *
     * @see #EXTRA_REFERRER_NAME
     */
    public static final String EXTRA_REFERRER
            = "android.intent.extra.REFERRER";

    /**
     * Alternate version of {@link #EXTRA_REFERRER} that supplies the URI as a String rather
     * than a {@link android.net.Uri} object.  Only for use in cases where Uri objects can
     * not be created, in particular when Intent extras are supplied through the
     * {@link #URI_INTENT_SCHEME intent:} or {@link #URI_ANDROID_APP_SCHEME android-app:}
     * schemes.
     *
     * @see #EXTRA_REFERRER
     */
    public static final String EXTRA_REFERRER_NAME
            = "android.intent.extra.REFERRER_NAME";

    /**
     * Used as an int extra field with {@link #ACTION_INSTALL_PACKAGE} and
     * {@link #ACTION_VIEW} to indicate the uid of the package that initiated the install
     *
     * @hide
     */
    @SystemApi
    public static final String EXTRA_ORIGINATING_UID
            = "android.intent.extra.ORIGINATING_UID";

    /**
     * Used as a boolean extra field with {@link #ACTION_INSTALL_PACKAGE} to install a
     * package.  Tells the installer UI to skip the confirmation with the user
     * if the .apk is replacing an existing one.
     *
     * @deprecated As of {@link android.os.Build.VERSION_CODES#JELLY_BEAN}, Android
     * will no longer show an interstitial message about updating existing
     * applications so this is no longer needed.
     */
    @Deprecated
    public static final String EXTRA_ALLOW_REPLACE
            = "android.intent.extra.ALLOW_REPLACE";

    /**
     * Used as a boolean extra field with {@link #ACTION_INSTALL_PACKAGE} or
     * {@link #ACTION_UNINSTALL_PACKAGE}.  Specifies that the installer UI should
     * return to the application the result code of the install/uninstall.  The returned result
     * code will be {@link android.app.Activity#RESULT_OK} on success or
     * {@link android.app.Activity#RESULT_FIRST_USER} on failure.
     */
    public static final String EXTRA_RETURN_RESULT
            = "android.intent.extra.RETURN_RESULT";

    /**
     * Package manager install result code.  @hide because result codes are not
     * yet ready to be exposed.
     */
    public static final String EXTRA_INSTALL_RESULT
            = "android.intent.extra.INSTALL_RESULT";

    /**
     * Activity Action: Launch application uninstaller.
     * <p>
     * Input: The data must be a package: URI whose scheme specific part is
     * the package name of the current installed package to be uninstalled.
     * You can optionally supply {@link #EXTRA_RETURN_RESULT}.
     * <p>
     * Output: If {@link #EXTRA_RETURN_RESULT}, returns whether the install
     * succeeded.
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_UNINSTALL_PACKAGE = "android.intent.action.UNINSTALL_PACKAGE";

    /**
     * Specify whether the package should be uninstalled for all users.
     *
     * @hide because these should not be part of normal application flow.
     */
    public static final String EXTRA_UNINSTALL_ALL_USERS
            = "android.intent.extra.UNINSTALL_ALL_USERS";

    /**
     * A string associated with a {@link #ACTION_UPGRADE_SETUP} activity
     * describing the last run version of the platform that was setup.
     *
     * @hide
     */
    public static final String METADATA_SETUP_VERSION = "android.SETUP_VERSION";

    /**
     * Activity action: Launch UI to manage the permissions of an app.
     * <p>
     * Input: {@link #EXTRA_PACKAGE_NAME} specifies the package whose permissions
     * will be managed by the launched UI.
     * </p>
     * <p>
     * Output: Nothing.
     * </p>
     *
     * @hide
     * @see #EXTRA_PACKAGE_NAME
     */
    @SystemApi
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_MANAGE_APP_PERMISSIONS =
            "android.intent.action.MANAGE_APP_PERMISSIONS";

    /**
     * Activity action: Launch UI to manage permissions.
     * <p>
     * Input: Nothing.
     * </p>
     * <p>
     * Output: Nothing.
     * </p>
     *
     * @hide
     */
    @SystemApi
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_MANAGE_PERMISSIONS =
            "android.intent.action.MANAGE_PERMISSIONS";

    /**
     * Activity action: Launch UI to review permissions for an app.
     * The system uses this intent if permission review for apps not
     * supporting the new runtime permissions model is enabled. In
     * this mode a permission review is required before any of the
     * app components can run.
     * <p>
     * Input: {@link #EXTRA_PACKAGE_NAME} specifies the package whose
     * permissions will be reviewed (mandatory).
     * </p>
     * <p>
     * Input: {@link #EXTRA_INTENT} specifies a pending intent to
     * be fired after the permission review (optional).
     * </p>
     * <p>
     * Input: {@link #EXTRA_REMOTE_CALLBACK} specifies a callback to
     * be invoked after the permission review (optional).
     * </p>
     * <p>
     * Input: {@link #EXTRA_RESULT_NEEDED} specifies whether the intent
     * passed via {@link #EXTRA_INTENT} needs a result (optional).
     * </p>
     * <p>
     * Output: Nothing.
     * </p>
     *
     * @hide
     * @see #EXTRA_PACKAGE_NAME
     * @see #EXTRA_INTENT
     * @see #EXTRA_REMOTE_CALLBACK
     * @see #EXTRA_RESULT_NEEDED
     */
    @SystemApi
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_REVIEW_PERMISSIONS =
            "android.intent.action.REVIEW_PERMISSIONS";

    /**
     * Intent extra: A callback for reporting remote result as a bundle.
     * <p>
     * Type: IRemoteCallback
     * </p>
     *
     * @hide
     */
    @SystemApi
    public static final String EXTRA_REMOTE_CALLBACK = "android.intent.extra.REMOTE_CALLBACK";

    /**
     * Intent extra: An app package name.
     * <p>
     * Type: String
     * </p>
     */
    public static final String EXTRA_PACKAGE_NAME = "android.intent.extra.PACKAGE_NAME";

    /**
     * Intent extra: An extra for specifying whether a result is needed.
     * <p>
     * Type: boolean
     * </p>
     *
     * @hide
     */
    @SystemApi
    public static final String EXTRA_RESULT_NEEDED = "android.intent.extra.RESULT_NEEDED";

    /**
     * Activity action: Launch UI to manage which apps have a given permission.
     * <p>
     * Input: {@link #EXTRA_PERMISSION_NAME} specifies the permission access
     * to which will be managed by the launched UI.
     * </p>
     * <p>
     * Output: Nothing.
     * </p>
     *
     * @hide
     * @see #EXTRA_PERMISSION_NAME
     */
    @SystemApi
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_MANAGE_PERMISSION_APPS =
            "android.intent.action.MANAGE_PERMISSION_APPS";

    /**
     * Intent extra: The name of a permission.
     * <p>
     * Type: String
     * </p>
     *
     * @hide
     */
    @SystemApi
    public static final String EXTRA_PERMISSION_NAME = "android.intent.extra.PERMISSION_NAME";

    // ---------------------------------------------------------------------
    // ---------------------------------------------------------------------
    // Standard intent broadcast actions (see action variable).

    /**
     * Broadcast Action: Sent when the device goes to sleep and becomes non-interactive.
     * <p>
     * For historical reasons, the name of this broadcast action refers to the power
     * state of the screen but it is actually sent in response to changes in the
     * overall interactive state of the device.
     * </p><p>
     * This broadcast is sent when the device becomes non-interactive which may have
     * nothing to do with the screen turning off.  To determine the
     * actual state of the screen, use {@link android.view.Display#getState}.
     * </p><p>
     * See {@link android.os.PowerManager#isInteractive} for details.
     * </p>
     * You <em>cannot</em> receive this through components declared in
     * manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";

    /**
     * Broadcast Action: Sent when the device wakes up and becomes interactive.
     * <p>
     * For historical reasons, the name of this broadcast action refers to the power
     * state of the screen but it is actually sent in response to changes in the
     * overall interactive state of the device.
     * </p><p>
     * This broadcast is sent when the device becomes interactive which may have
     * nothing to do with the screen turning on.  To determine the
     * actual state of the screen, use {@link android.view.Display#getState}.
     * </p><p>
     * See {@link android.os.PowerManager#isInteractive} for details.
     * </p>
     * You <em>cannot</em> receive this through components declared in
     * manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";

    /**
     * Broadcast Action: Sent after the system stops dreaming.
     * <p>
     * <p class="note">This is a protected intent that can only be sent by the system.
     * It is only sent to registered receivers.</p>
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_DREAMING_STOPPED = "android.intent.action.DREAMING_STOPPED";

    /**
     * Broadcast Action: Sent after the system starts dreaming.
     * <p>
     * <p class="note">This is a protected intent that can only be sent by the system.
     * It is only sent to registered receivers.</p>
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_DREAMING_STARTED = "android.intent.action.DREAMING_STARTED";

    /**
     * Broadcast Action: Sent when the user is present after device wakes up (e.g when the
     * keyguard is gone).
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_USER_PRESENT = "android.intent.action.USER_PRESENT";

    /**
     * Broadcast Action: The current time has changed.  Sent every
     * minute.  You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";
    /**
     * Broadcast Action: The time was set.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_TIME_CHANGED = "android.intent.action.TIME_SET";
    /**
     * Broadcast Action: The date has changed.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_DATE_CHANGED = "android.intent.action.DATE_CHANGED";
    /**
     * Broadcast Action: The timezone has changed. The intent will have the following extra values:</p>
     * <ul>
     * <li><em>time-zone</em> - The java.util.TimeZone.getID() value identifying the new time zone.</li>
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_TIMEZONE_CHANGED = "android.intent.action.TIMEZONE_CHANGED";
    /**
     * Clear DNS Cache Action: This is broadcast when networks have changed and old
     * DNS entries should be tossed.
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_CLEAR_DNS_CACHE = "android.intent.action.CLEAR_DNS_CACHE";
    /**
     * Alarm Changed Action: This is broadcast when the AlarmClock
     * application's alarm is set or unset.  It is used by the
     * AlarmClock application and the StatusBar service.
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_ALARM_CHANGED = "android.intent.action.ALARM_CHANGED";

    /**
     * Broadcast Action: This is broadcast once, after the user has finished
     * booting, but while still in the "locked" state. It can be used to perform
     * application-specific initialization, such as installing alarms. You must
     * hold the {@link android.Manifest.permission#RECEIVE_BOOT_COMPLETED}
     * permission in order to receive this broadcast.
     * <p>
     * This broadcast is sent immediately at boot by all devices (regardless of
     * direct boot support) running {@link android.os.Build.VERSION_CODES#N} or
     * higher. Upon receipt of this broadcast, the user is still locked and only
     * device-protected storage can be accessed safely. If you want to access
     * credential-protected storage, you need to wait for the user to be
     * unlocked (typically by entering their lock pattern or PIN for the first
     * time), after which the {@link #ACTION_USER_UNLOCKED} and
     * {@link #ACTION_BOOT_COMPLETED} broadcasts are sent.
     * <p>
     * To receive this broadcast, your receiver component must be marked as
     * being {@link ComponentInfo#directBootAware}.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     *
     * @see Context#createDeviceProtectedStorageContext()
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_LOCKED_BOOT_COMPLETED = "android.intent.action.LOCKED_BOOT_COMPLETED";

    /**
     * Broadcast Action: This is broadcast once, after the user has finished
     * booting. It can be used to perform application-specific initialization,
     * such as installing alarms. You must hold the
     * {@link android.Manifest.permission#RECEIVE_BOOT_COMPLETED} permission in
     * order to receive this broadcast.
     * <p>
     * This broadcast is sent at boot by all devices (both with and without
     * direct boot support). Upon receipt of this broadcast, the user is
     * unlocked and both device-protected and credential-protected storage can
     * accessed safely.
     * <p>
     * If you need to run while the user is still locked (before they've entered
     * their lock pattern or PIN for the first time), you can listen for the
     * {@link #ACTION_LOCKED_BOOT_COMPLETED} broadcast.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

    /**
     * Broadcast Action: This is broadcast when a user action should request a
     * temporary system dialog to dismiss.  Some examples of temporary system
     * dialogs are the notification window-shade and the recent tasks dialog.
     */
    public static final String ACTION_CLOSE_SYSTEM_DIALOGS = "android.intent.action.CLOSE_SYSTEM_DIALOGS";
    /**
     * Broadcast Action: Trigger the download and eventual installation
     * of a package.
     * <p>Input: {@link #getData} is the URI of the package file to download.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @deprecated This constant has never been used.
     */
    @Deprecated
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_INSTALL = "android.intent.action.PACKAGE_INSTALL";
    /**
     * Broadcast Action: A new application package has been installed on the
     * device. The data contains the name of the package.  Note that the
     * newly installed package does <em>not</em> receive this broadcast.
     * <p>May include the following extras:
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the new package.
     * <li> {@link #EXTRA_REPLACING} is set to true if this is following
     * an {@link #ACTION_PACKAGE_REMOVED} broadcast for the same package.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";
    /**
     * Broadcast Action: A new version of an application package has been
     * installed, replacing an existing version that was previously installed.
     * The data contains the name of the package.
     * <p>May include the following extras:
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the new package.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_REPLACED = "android.intent.action.PACKAGE_REPLACED";
    /**
     * Broadcast Action: A new version of your application has been installed
     * over an existing one.  This is only sent to the application that was
     * replaced.  It does not contain any additional data; to receive it, just
     * use an intent filter for this action.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MY_PACKAGE_REPLACED = "android.intent.action.MY_PACKAGE_REPLACED";
    /**
     * Broadcast Action: An existing application package has been removed from
     * the device.  The data contains the name of the package.  The package
     * that is being removed does <em>not</em> receive this Intent.
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid previously assigned
     * to the package.
     * <li> {@link #EXTRA_DATA_REMOVED} is set to true if the entire
     * application -- data and code -- is being removed.
     * <li> {@link #EXTRA_REPLACING} is set to true if this will be followed
     * by an {@link #ACTION_PACKAGE_ADDED} broadcast for the same package.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_REMOVED = "android.intent.action.PACKAGE_REMOVED";
    /**
     * Broadcast Action: An existing application package has been completely
     * removed from the device.  The data contains the name of the package.
     * This is like {@link #ACTION_PACKAGE_REMOVED}, but only set when
     * {@link #EXTRA_DATA_REMOVED} is true and
     * {@link #EXTRA_REPLACING} is false of that broadcast.
     * <p>
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid previously assigned
     * to the package.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_FULLY_REMOVED
            = "android.intent.action.PACKAGE_FULLY_REMOVED";
    /**
     * Broadcast Action: An existing application package has been changed (for
     * example, a component has been enabled or disabled).  The data contains
     * the name of the package.
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the package.
     * <li> {@link #EXTRA_CHANGED_COMPONENT_NAME_LIST} containing the class name
     * of the changed components (or the package name itself).
     * <li> {@link #EXTRA_DONT_KILL_APP} containing boolean field to override the
     * default action of restarting the application.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_CHANGED = "android.intent.action.PACKAGE_CHANGED";
    /**
     * @hide Broadcast Action: Ask system services if there is any reason to
     * restart the given package.  The data contains the name of the
     * package.
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the package.
     * <li> {@link #EXTRA_PACKAGES} String array of all packages to check.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SystemApi
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_QUERY_PACKAGE_RESTART = "android.intent.action.QUERY_PACKAGE_RESTART";
    /**
     * Broadcast Action: The user has restarted a package, and all of its
     * processes have been killed.  All runtime state
     * associated with it (processes, alarms, notifications, etc) should
     * be removed.  Note that the restarted package does <em>not</em>
     * receive this broadcast.
     * The data contains the name of the package.
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the package.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_RESTARTED = "android.intent.action.PACKAGE_RESTARTED";
    /**
     * Broadcast Action: The user has cleared the data of a package.  This should
     * be preceded by {@link #ACTION_PACKAGE_RESTARTED}, after which all of
     * its persistent data is erased and this broadcast sent.
     * Note that the cleared package does <em>not</em>
     * receive this broadcast. The data contains the name of the package.
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the package.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_DATA_CLEARED = "android.intent.action.PACKAGE_DATA_CLEARED";
    /**
     * Broadcast Action: Packages have been suspended.
     * <p>Includes the following extras:
     * <ul>
     * <li> {@link #EXTRA_CHANGED_PACKAGE_LIST} is the set of packages which have been suspended
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system. It is only sent to registered receivers.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGES_SUSPENDED = "android.intent.action.PACKAGES_SUSPENDED";
    /**
     * Broadcast Action: Packages have been unsuspended.
     * <p>Includes the following extras:
     * <ul>
     * <li> {@link #EXTRA_CHANGED_PACKAGE_LIST} is the set of packages which have been unsuspended
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system. It is only sent to registered receivers.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGES_UNSUSPENDED = "android.intent.action.PACKAGES_UNSUSPENDED";
    /**
     * Broadcast Action: A user ID has been removed from the system.  The user
     * ID number is stored in the extra data under {@link #EXTRA_UID}.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_UID_REMOVED = "android.intent.action.UID_REMOVED";

    /**
     * Broadcast Action: Sent to the installer package of an application when
     * that application is first launched (that is the first time it is moved
     * out of the stopped state).  The data contains the name of the package.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_FIRST_LAUNCH = "android.intent.action.PACKAGE_FIRST_LAUNCH";

    /**
     * Broadcast Action: Sent to the system package verifier when a package
     * needs to be verified. The data contains the package URI.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     * </p>
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_NEEDS_VERIFICATION = "android.intent.action.PACKAGE_NEEDS_VERIFICATION";

    /**
     * Broadcast Action: Sent to the system package verifier when a package is
     * verified. The data contains the package URI.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PACKAGE_VERIFIED = "android.intent.action.PACKAGE_VERIFIED";

    /**
     * Broadcast Action: Sent to the system intent filter verifier when an
     * intent filter needs to be verified. The data contains the filter data
     * hosts to be verified against.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     * </p>
     *
     * @hide
     */
    @SystemApi
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_INTENT_FILTER_NEEDS_VERIFICATION = "android.intent.action.INTENT_FILTER_NEEDS_VERIFICATION";

    /**
     * Broadcast Action: Resources for a set of packages (which were
     * previously unavailable) are currently
     * available since the media on which they exist is available.
     * The extra data {@link #EXTRA_CHANGED_PACKAGE_LIST} contains a
     * list of packages whose availability changed.
     * The extra data {@link #EXTRA_CHANGED_UID_LIST} contains a
     * list of uids of packages whose availability changed.
     * Note that the
     * packages in this list do <em>not</em> receive this broadcast.
     * The specified set of packages are now available on the system.
     * <p>Includes the following extras:
     * <ul>
     * <li> {@link #EXTRA_CHANGED_PACKAGE_LIST} is the set of packages
     * whose resources(were previously unavailable) are currently available.
     * {@link #EXTRA_CHANGED_UID_LIST} is the set of uids of the
     * packages whose resources(were previously unavailable)
     * are  currently available.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE =
            "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";

    /**
     * Broadcast Action: Resources for a set of packages are currently
     * unavailable since the media on which they exist is unavailable.
     * The extra data {@link #EXTRA_CHANGED_PACKAGE_LIST} contains a
     * list of packages whose availability changed.
     * The extra data {@link #EXTRA_CHANGED_UID_LIST} contains a
     * list of uids of packages whose availability changed.
     * The specified set of packages can no longer be
     * launched and are practically unavailable on the system.
     * <p>Inclues the following extras:
     * <ul>
     * <li> {@link #EXTRA_CHANGED_PACKAGE_LIST} is the set of packages
     * whose resources are no longer available.
     * {@link #EXTRA_CHANGED_UID_LIST} is the set of packages
     * whose resources are no longer available.
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE =
            "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";

    /**
     * Broadcast Action: preferred activities have changed *explicitly*.
     * <p>
     * <p>Note there are cases where a preferred activity is invalidated *implicitly*, e.g.
     * when an app is installed or uninstalled, but in such cases this broadcast will *not*
     * be sent.
     * <p>
     * {@link #EXTRA_USER_HANDLE} contains the user ID in question.
     *
     * @hide
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PREFERRED_ACTIVITY_CHANGED =
            "android.intent.action.ACTION_PREFERRED_ACTIVITY_CHANGED";


    /**
     * Broadcast Action:  The current system wallpaper has changed.  See
     * {@link android.app.WallpaperManager} for retrieving the new wallpaper.
     * This should <em>only</em> be used to determine when the wallpaper
     * has changed to show the new wallpaper to the user.  You should certainly
     * never, in response to this, change the wallpaper or other attributes of
     * it such as the suggested size.  That would be crazy, right?  You'd cause
     * all kinds of loops, especially if other apps are doing similar things,
     * right?  Of course.  So please don't do this.
     *
     * @deprecated Modern applications should use
     * {@link android.view.WindowManager.LayoutParams#FLAG_SHOW_WALLPAPER
     * WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER} to have the wallpaper
     * shown behind their UI, rather than watching for this broadcast and
     * rendering the wallpaper on their own.
     */
    @Deprecated
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED";
    /**
     * Broadcast Action: The current device {@link android.content.res.Configuration}
     * (orientation, locale, etc) has changed.  When such a change happens, the
     * UIs (view hierarchy) will need to be rebuilt based on this new
     * information; for the most part, applications don't need to worry about
     * this, because the system will take care of stopping and restarting the
     * application to make sure it sees the new changes.  Some system code that
     * can not be restarted will need to watch for this action and handle it
     * appropriately.
     * <p>
     * <p class="note">
     * You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @see android.content.res.Configuration
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_CONFIGURATION_CHANGED = "android.intent.action.CONFIGURATION_CHANGED";
    /**
     * Broadcast Action: The current device's locale has changed.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_LOCALE_CHANGED = "android.intent.action.LOCALE_CHANGED";
    /**
     * Broadcast Action:  This is a <em>sticky broadcast</em> containing the
     * charging state, level, and other information about the battery.
     * See {@link android.os.BatteryManager} for documentation on the
     * contents of the Intent.
     * <p>
     * <p class="note">
     * You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.  See {@link #ACTION_BATTERY_LOW},
     * {@link #ACTION_BATTERY_OKAY}, {@link #ACTION_POWER_CONNECTED},
     * and {@link #ACTION_POWER_DISCONNECTED} for distinct battery-related
     * broadcasts that are sent and can be received through manifest
     * receivers.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";
    /**
     * Broadcast Action:  Indicates low battery condition on the device.
     * This broadcast corresponds to the "Low battery warning" system dialog.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_BATTERY_LOW = "android.intent.action.BATTERY_LOW";
    /**
     * Broadcast Action:  Indicates the battery is now okay after being low.
     * This will be sent after {@link #ACTION_BATTERY_LOW} once the battery has
     * gone back up to an okay state.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_BATTERY_OKAY = "android.intent.action.BATTERY_OKAY";
    /**
     * Broadcast Action:  External power has been connected to the device.
     * This is intended for applications that wish to register specifically to this notification.
     * Unlike ACTION_BATTERY_CHANGED, applications will be woken for this and so do not have to
     * stay active to receive this notification.  This action can be used to implement actions
     * that wait until power is available to trigger.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
    /**
     * Broadcast Action:  External power has been removed from the device.
     * This is intended for applications that wish to register specifically to this notification.
     * Unlike ACTION_BATTERY_CHANGED, applications will be woken for this and so do not have to
     * stay active to receive this notification.  This action can be used to implement actions
     * that wait until power is available to trigger.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_POWER_DISCONNECTED =
            "android.intent.action.ACTION_POWER_DISCONNECTED";
    /**
     * Broadcast Action:  Device is shutting down.
     * This is broadcast when the device is being shut down (completely turned
     * off, not sleeping).  Once the broadcast is complete, the final shutdown
     * will proceed and all unsaved data lost.  Apps will not normally need
     * to handle this, since the foreground activity will be paused as well.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     * <p>May include the following extras:
     * <ul>
     * <li> {@link #EXTRA_SHUTDOWN_USERSPACE_ONLY} a boolean that is set to true if this
     * shutdown is only for userspace processes.  If not set, assumed to be false.
     * </ul>
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
    /**
     * Activity Action:  Start this activity to request system shutdown.
     * The optional boolean extra field {@link #EXTRA_KEY_CONFIRM} can be set to true
     * to request confirmation from the user before shutting down. The optional boolean
     * extra field {@link #EXTRA_USER_REQUESTED_SHUTDOWN} can be set to true to
     * indicate that the shutdown is requested by the user.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     * <p>
     * {@hide}
     */
    public static final String ACTION_REQUEST_SHUTDOWN = "android.intent.action.ACTION_REQUEST_SHUTDOWN";
    /**
     * Broadcast Action:  A sticky broadcast that indicates low memory
     * condition on the device
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_DEVICE_STORAGE_LOW = "android.intent.action.DEVICE_STORAGE_LOW";
    /**
     * Broadcast Action:  Indicates low memory condition on the device no longer exists
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_DEVICE_STORAGE_OK = "android.intent.action.DEVICE_STORAGE_OK";
    /**
     * Broadcast Action:  A sticky broadcast that indicates a memory full
     * condition on the device. This is intended for activities that want
     * to be able to fill the data partition completely, leaving only
     * enough free space to prevent system-wide SQLite failures.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     * <p>
     * {@hide}
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_DEVICE_STORAGE_FULL = "android.intent.action.DEVICE_STORAGE_FULL";
    /**
     * Broadcast Action:  Indicates memory full condition on the device
     * no longer exists.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     * <p>
     * {@hide}
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_DEVICE_STORAGE_NOT_FULL = "android.intent.action.DEVICE_STORAGE_NOT_FULL";
    /**
     * Broadcast Action:  Indicates low memory condition notification acknowledged by user
     * and package management should be started.
     * This is triggered by the user from the ACTION_DEVICE_STORAGE_LOW
     * notification.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MANAGE_PACKAGE_STORAGE = "android.intent.action.MANAGE_PACKAGE_STORAGE";
    /**
     * Broadcast Action:  The device has entered USB Mass Storage mode.
     * This is used mainly for the USB Settings panel.
     * Apps should listen for ACTION_MEDIA_MOUNTED and ACTION_MEDIA_UNMOUNTED broadcasts to be notified
     * when the SD card file system is mounted or unmounted
     *
     * @deprecated replaced by android.os.storage.StorageEventListener
     */
    @Deprecated
    public static final String ACTION_UMS_CONNECTED = "android.intent.action.UMS_CONNECTED";

    /**
     * Broadcast Action:  The device has exited USB Mass Storage mode.
     * This is used mainly for the USB Settings panel.
     * Apps should listen for ACTION_MEDIA_MOUNTED and ACTION_MEDIA_UNMOUNTED broadcasts to be notified
     * when the SD card file system is mounted or unmounted
     *
     * @deprecated replaced by android.os.storage.StorageEventListener
     */
    @Deprecated
    public static final String ACTION_UMS_DISCONNECTED = "android.intent.action.UMS_DISCONNECTED";

    /**
     * Broadcast Action:  External media has been removed.
     * The path to the mount point for the removed media is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_REMOVED = "android.intent.action.MEDIA_REMOVED";

    /**
     * Broadcast Action:  External media is present, but not mounted at its mount point.
     * The path to the mount point for the unmounted media is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_UNMOUNTED = "android.intent.action.MEDIA_UNMOUNTED";

    /**
     * Broadcast Action:  External media is present, and being disk-checked
     * The path to the mount point for the checking media is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_CHECKING = "android.intent.action.MEDIA_CHECKING";

    /**
     * Broadcast Action:  External media is present, but is using an incompatible fs (or is blank)
     * The path to the mount point for the checking media is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_NOFS = "android.intent.action.MEDIA_NOFS";

    /**
     * Broadcast Action:  External media is present and mounted at its mount point.
     * The path to the mount point for the mounted media is contained in the Intent.mData field.
     * The Intent contains an extra with name "read-only" and Boolean value to indicate if the
     * media was mounted read only.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";

    /**
     * Broadcast Action:  External media is unmounted because it is being shared via USB mass storage.
     * The path to the mount point for the shared media is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_SHARED = "android.intent.action.MEDIA_SHARED";

    /**
     * Broadcast Action:  External media is no longer being shared via USB mass storage.
     * The path to the mount point for the previously shared media is contained in the Intent.mData field.
     *
     * @hide
     */
    public static final String ACTION_MEDIA_UNSHARED = "android.intent.action.MEDIA_UNSHARED";

    /**
     * Broadcast Action:  External media was removed from SD card slot, but mount point was not unmounted.
     * The path to the mount point for the removed media is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_BAD_REMOVAL = "android.intent.action.MEDIA_BAD_REMOVAL";

    /**
     * Broadcast Action:  External media is present but cannot be mounted.
     * The path to the mount point for the unmountable media is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_UNMOUNTABLE = "android.intent.action.MEDIA_UNMOUNTABLE";

    /**
     * Broadcast Action:  User has expressed the desire to remove the external storage media.
     * Applications should close all files they have open within the mount point when they receive this intent.
     * The path to the mount point for the media to be ejected is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_EJECT = "android.intent.action.MEDIA_EJECT";

    /**
     * Broadcast Action:  The media scanner has started scanning a directory.
     * The path to the directory being scanned is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_SCANNER_STARTED = "android.intent.action.MEDIA_SCANNER_STARTED";

    /**
     * Broadcast Action:  The media scanner has finished scanning a directory.
     * The path to the scanned directory is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_SCANNER_FINISHED = "android.intent.action.MEDIA_SCANNER_FINISHED";

    /**
     * Broadcast Action:  Request the media scanner to scan a file and add it to the media database.
     * The path to the file is contained in the Intent.mData field.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_SCANNER_SCAN_FILE = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";

    /**
     * Broadcast Action:  The "Media Button" was pressed.  Includes a single
     * extra field, {@link #EXTRA_KEY_EVENT}, containing the key event that
     * caused the broadcast.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_MEDIA_BUTTON = "android.intent.action.MEDIA_BUTTON";

    /**
     * Broadcast Action:  The "Camera Button" was pressed.  Includes a single
     * extra field, {@link #EXTRA_KEY_EVENT}, containing the key event that
     * caused the broadcast.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_CAMERA_BUTTON = "android.intent.action.CAMERA_BUTTON";

    // *** NOTE: @todo(*) The following really should go into a more domain-specific
    // location; they are not general-purpose actions.

    /**
     * Broadcast Action: A GTalk connection has been established.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_GTALK_SERVICE_CONNECTED =
            "android.intent.action.GTALK_CONNECTED";

    /**
     * Broadcast Action: A GTalk connection has been disconnected.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_GTALK_SERVICE_DISCONNECTED =
            "android.intent.action.GTALK_DISCONNECTED";

    /**
     * Broadcast Action: An input method has been changed.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_INPUT_METHOD_CHANGED =
            "android.intent.action.INPUT_METHOD_CHANGED";

    /**
     * <p>Broadcast Action: The user has switched the phone into or out of Airplane Mode. One or
     * more radios have been turned off or on. The intent will have the following extra value:</p>
     * <ul>
     * <li><em>state</em> - A boolean value indicating whether Airplane Mode is on. If true,
     * then cell radio and possibly other radios such as bluetooth or WiFi may have also been
     * turned off</li>
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent by the system.</p>
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_AIRPLANE_MODE_CHANGED = "android.intent.action.AIRPLANE_MODE";

    /**
     * Broadcast Action: Some content providers have parts of their namespace
     * where they publish new events or items that the user may be especially
     * interested in. For these things, they may broadcast this action when the
     * set of interesting items change.
     * <p>
     * For example, GmailProvider sends this notification when the set of unread
     * mail in the inbox changes.
     * <p>
     * <p>The data of the intent identifies which part of which provider
     * changed. When queried through the content resolver, the data URI will
     * return the data set in question.
     * <p>
     * <p>The intent will have the following extra values:
     * <ul>
     * <li><em>count</em> - The number of items in the data set. This is the
     * same as the number of items in the cursor returned by querying the
     * data URI. </li>
     * </ul>
     * <p>
     * This intent will be sent at boot (if the count is non-zero) and when the
     * data set changes. It is possible for the data set to change without the
     * count changing (for example, if a new unread message arrives in the same
     * sync operation in which a message is archived). The phone should still
     * ring/vibrate/etc as normal in this case.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_PROVIDER_CHANGED =
            "android.intent.action.PROVIDER_CHANGED";

    /**
     * Broadcast Action: Wired Headset plugged in or unplugged.
     * <p>
     * Same as {@link android.media.AudioManager#ACTION_HEADSET_PLUG}, to be consulted for value
     * and documentation.
     * <p>If the minimum SDK version of your application is
     * {@link android.os.Build.VERSION_CODES#LOLLIPOP}, it is recommended to refer
     * to the <code>AudioManager</code> constant in your receiver registration code instead.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_HEADSET_PLUG = android.media.AudioManager.ACTION_HEADSET_PLUG;

    /**
     * <p>Broadcast Action: The user has switched on advanced settings in the settings app:</p>
     * <ul>
     * <li><em>state</em> - A boolean value indicating whether the settings is on or off.</li>
     * </ul>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @hide
     */
    //@SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_ADVANCED_SETTINGS_CHANGED
            = "android.intent.action.ADVANCED_SETTINGS";

    /**
     * Broadcast Action: Sent after application restrictions are changed.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.</p>
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_APPLICATION_RESTRICTIONS_CHANGED =
            "android.intent.action.APPLICATION_RESTRICTIONS_CHANGED";

    /**
     * Broadcast Action: An outgoing call is about to be placed.
     * <p>
     * <p>The Intent will have the following extra value:</p>
     * <ul>
     * <li><em>{@link android.content.Intent#EXTRA_PHONE_NUMBER}</em> -
     * the phone number originally intended to be dialed.</li>
     * </ul>
     * <p>Once the broadcast is finished, the resultData is used as the actual
     * number to call.  If  <code>null</code>, no call will be placed.</p>
     * <p>It is perfectly acceptable for multiple receivers to process the
     * outgoing call in turn: for example, a parental control application
     * might verify that the user is authorized to place the call at that
     * time, then a number-rewriting application might add an area code if
     * one was not specified.</p>
     * <p>For consistency, any receiver whose purpose is to prohibit phone
     * calls should have a priority of 0, to ensure it will see the final
     * phone number to be dialed.
     * Any receiver whose purpose is to rewrite phone numbers to be called
     * should have a positive priority.
     * Negative priorities are reserved for the system for this broadcast;
     * using them may cause problems.</p>
     * <p>Any BroadcastReceiver receiving this Intent <em>must not</em>
     * abort the broadcast.</p>
     * <p>Emergency calls cannot be intercepted using this mechanism, and
     * other calls cannot be modified to call emergency numbers using this
     * mechanism.
     * <p>Some apps (such as VoIP apps) may want to redirect the outgoing
     * call to use their own service instead. Those apps should first prevent
     * the call from being placed by setting resultData to <code>null</code>
     * and then start their own app to make the call.
     * <p>You must hold the
     * {@link android.Manifest.permission#PROCESS_OUTGOING_CALLS}
     * permission to receive this Intent.</p>
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_NEW_OUTGOING_CALL =
            "android.intent.action.NEW_OUTGOING_CALL";

    /**
     * Broadcast Action: Have the device reboot.  This is only for use by
     * system code.
     * <p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_REBOOT =
            "android.intent.action.REBOOT";

    /**
     * Broadcast Action:  A sticky broadcast for changes in the physical
     * docking state of the device.
     * <p>
     * <p>The intent will have the following extra values:
     * <ul>
     * <li><em>{@link #EXTRA_DOCK_STATE}</em> - the current dock
     * state, indicating which dock the device is physically in.</li>
     * </ul>
     * <p>This is intended for monitoring the current physical dock state.
     * See {@link android.app.UiModeManager} for the normal API dealing with
     * dock mode changes.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_DOCK_EVENT =
            "android.intent.action.DOCK_EVENT";

    /**
     * Broadcast Action: A broadcast when idle maintenance can be started.
     * This means that the user is not interacting with the device and is
     * not expected to do so soon. Typical use of the idle maintenance is
     * to perform somehow expensive tasks that can be postponed at a moment
     * when they will not degrade user experience.
     * <p>
     * <p class="note">In order to keep the device responsive in case of an
     * unexpected user interaction, implementations of a maintenance task
     * should be interruptible. In such a scenario a broadcast with action
     * {@link #ACTION_IDLE_MAINTENANCE_END} will be sent. In other words, you
     * should not do the maintenance work in
     * {@link BroadcastReceiver#onReceive(Context, Intent)}, rather start a
     * maintenance service by {@link Context#startService(Intent)}. Also
     * you should hold a wake lock while your maintenance service is running
     * to prevent the device going to sleep.
     * </p>
     * <p>
     * <p class="note">This is a protected intent that can only be sent by
     * the system.
     * </p>
     *
     * @hide
     * @see #ACTION_IDLE_MAINTENANCE_END
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_IDLE_MAINTENANCE_START =
            "android.intent.action.ACTION_IDLE_MAINTENANCE_START";

    /**
     * Broadcast Action:  A broadcast when idle maintenance should be stopped.
     * This means that the user was not interacting with the device as a result
     * of which a broadcast with action {@link #ACTION_IDLE_MAINTENANCE_START}
     * was sent and now the user started interacting with the device. Typical
     * use of the idle maintenance is to perform somehow expensive tasks that
     * can be postponed at a moment when they will not degrade user experience.
     * <p>
     * <p class="note">In order to keep the device responsive in case of an
     * unexpected user interaction, implementations of a maintenance task
     * should be interruptible. Hence, on receiving a broadcast with this
     * action, the maintenance task should be interrupted as soon as possible.
     * In other words, you should not do the maintenance work in
     * {@link BroadcastReceiver#onReceive(Context, Intent)}, rather stop the
     * maintenance service that was started on receiving of
     * {@link #ACTION_IDLE_MAINTENANCE_START}.Also you should release the wake
     * lock you acquired when your maintenance service started.
     * </p>
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @hide
     * @see #ACTION_IDLE_MAINTENANCE_START
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_IDLE_MAINTENANCE_END =
            "android.intent.action.ACTION_IDLE_MAINTENANCE_END";

    /**
     * Broadcast Action: a remote intent is to be broadcasted.
     * <p>
     * A remote intent is used for remote RPC between devices. The remote intent
     * is serialized and sent from one device to another device. The receiving
     * device parses the remote intent and broadcasts it. Note that anyone can
     * broadcast a remote intent. However, if the intent receiver of the remote intent
     * does not trust intent broadcasts from arbitrary intent senders, it should require
     * the sender to hold certain permissions so only trusted sender's broadcast will be
     * let through.
     *
     * @hide
     */
    public static final String ACTION_REMOTE_INTENT =
            "com.google.android.c2dm.intent.RECEIVE";

    /**
     * Broadcast Action: This is broadcast once when the user is booting after a
     * system update. It can be used to perform cleanup or upgrades after a
     * system update.
     * <p>
     * This broadcast is sent after the {@link #ACTION_LOCKED_BOOT_COMPLETED}
     * broadcast but before the {@link #ACTION_BOOT_COMPLETED} broadcast. It's
     * only sent when the {@link Build#FINGERPRINT} has changed, and it's only
     * sent to receivers in the system image.
     *
     * @hide
     */
    public static final String ACTION_PRE_BOOT_COMPLETED =
            "android.intent.action.PRE_BOOT_COMPLETED";

    /**
     * Broadcast to a specific application to query any supported restrictions to impose
     * on restricted users. The broadcast intent contains an extra
     * {@link #EXTRA_RESTRICTIONS_BUNDLE} with the currently persisted
     * restrictions as a Bundle of key/value pairs. The value types can be Boolean, String or
     * String[] depending on the restriction type.<p/>
     * The response should contain an extra {@link #EXTRA_RESTRICTIONS_LIST},
     * which is of type <code>ArrayList&lt;RestrictionEntry&gt;</code>. It can also
     * contain an extra {@link #EXTRA_RESTRICTIONS_INTENT}, which is of type <code>Intent</code>.
     * The activity specified by that intent will be launched for a result which must contain
     * one of the extras {@link #EXTRA_RESTRICTIONS_LIST} or {@link #EXTRA_RESTRICTIONS_BUNDLE}.
     * The keys and values of the returned restrictions will be persisted.
     *
     * @see RestrictionEntry
     */
    public static final String ACTION_GET_RESTRICTION_ENTRIES =
            "android.intent.action.GET_RESTRICTION_ENTRIES";

    /**
     * Sent the first time a user is starting, to allow system apps to
     * perform one time initialization.  (This will not be seen by third
     * party applications because a newly initialized user does not have any
     * third party applications installed for it.)  This is sent early in
     * starting the user, around the time the home app is started, before
     * {@link #ACTION_BOOT_COMPLETED} is sent.  This is sent as a foreground
     * broadcast, since it is part of a visible user interaction; be as quick
     * as possible when handling it.
     */
    public static final String ACTION_USER_INITIALIZE =
            "android.intent.action.USER_INITIALIZE";

    /**
     * Sent when a user switch is happening, causing the process's user to be
     * brought to the foreground.  This is only sent to receivers registered
     * through {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver}.  It is sent to the user that is going to the
     * foreground.  This is sent as a foreground
     * broadcast, since it is part of a visible user interaction; be as quick
     * as possible when handling it.
     */
    public static final String ACTION_USER_FOREGROUND =
            "android.intent.action.USER_FOREGROUND";

    /**
     * Sent when a user switch is happening, causing the process's user to be
     * sent to the background.  This is only sent to receivers registered
     * through {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver}.  It is sent to the user that is going to the
     * background.  This is sent as a foreground
     * broadcast, since it is part of a visible user interaction; be as quick
     * as possible when handling it.
     */
    public static final String ACTION_USER_BACKGROUND =
            "android.intent.action.USER_BACKGROUND";

    /**
     * Broadcast sent to the system when a user is added. Carries an extra
     * EXTRA_USER_HANDLE that has the userHandle of the new user.  It is sent to
     * all running users.  You must hold
     * {@link android.Manifest.permission#MANAGE_USERS} to receive this broadcast.
     *
     * @hide
     */
    public static final String ACTION_USER_ADDED =
            "android.intent.action.USER_ADDED";

    /**
     * Broadcast sent by the system when a user is started. Carries an extra
     * EXTRA_USER_HANDLE that has the userHandle of the user.  This is only sent to
     * registered receivers, not manifest receivers.  It is sent to the user
     * that has been started.  This is sent as a foreground
     * broadcast, since it is part of a visible user interaction; be as quick
     * as possible when handling it.
     *
     * @hide
     */
    public static final String ACTION_USER_STARTED =
            "android.intent.action.USER_STARTED";

    /**
     * Broadcast sent when a user is in the process of starting.  Carries an extra
     * EXTRA_USER_HANDLE that has the userHandle of the user.  This is only
     * sent to registered receivers, not manifest receivers.  It is sent to all
     * users (including the one that is being started).  You must hold
     * {@link android.Manifest.permission#INTERACT_ACROSS_USERS} to receive
     * this broadcast.  This is sent as a background broadcast, since
     * its result is not part of the primary UX flow; to safely keep track of
     * started/stopped state of a user you can use this in conjunction with
     * {@link #ACTION_USER_STOPPING}.  It is <b>not</b> generally safe to use with
     * other user state broadcasts since those are foreground broadcasts so can
     * execute in a different order.
     *
     * @hide
     */
    public static final String ACTION_USER_STARTING =
            "android.intent.action.USER_STARTING";

    /**
     * Broadcast sent when a user is going to be stopped.  Carries an extra
     * EXTRA_USER_HANDLE that has the userHandle of the user.  This is only
     * sent to registered receivers, not manifest receivers.  It is sent to all
     * users (including the one that is being stopped).  You must hold
     * {@link android.Manifest.permission#INTERACT_ACROSS_USERS} to receive
     * this broadcast.  The user will not stop until all receivers have
     * handled the broadcast.  This is sent as a background broadcast, since
     * its result is not part of the primary UX flow; to safely keep track of
     * started/stopped state of a user you can use this in conjunction with
     * {@link #ACTION_USER_STARTING}.  It is <b>not</b> generally safe to use with
     * other user state broadcasts since those are foreground broadcasts so can
     * execute in a different order.
     *
     * @hide
     */
    public static final String ACTION_USER_STOPPING =
            "android.intent.action.USER_STOPPING";

    /**
     * Broadcast sent to the system when a user is stopped. Carries an extra
     * EXTRA_USER_HANDLE that has the userHandle of the user.  This is similar to
     * {@link #ACTION_PACKAGE_RESTARTED}, but for an entire user instead of a
     * specific package.  This is only sent to registered receivers, not manifest
     * receivers.  It is sent to all running users <em>except</em> the one that
     * has just been stopped (which is no longer running).
     *
     * @hide
     */
    public static final String ACTION_USER_STOPPED =
            "android.intent.action.USER_STOPPED";

    /**
     * Broadcast sent to the system when a user is removed. Carries an extra EXTRA_USER_HANDLE that has
     * the userHandle of the user.  It is sent to all running users except the
     * one that has been removed. The user will not be completely removed until all receivers have
     * handled the broadcast. You must hold
     * {@link android.Manifest.permission#MANAGE_USERS} to receive this broadcast.
     *
     * @hide
     */
    public static final String ACTION_USER_REMOVED =
            "android.intent.action.USER_REMOVED";

    /**
     * Broadcast sent to the system when the user switches. Carries an extra EXTRA_USER_HANDLE that has
     * the userHandle of the user to become the current one. This is only sent to
     * registered receivers, not manifest receivers.  It is sent to all running users.
     * You must hold
     * {@link android.Manifest.permission#MANAGE_USERS} to receive this broadcast.
     *
     * @hide
     */
    public static final String ACTION_USER_SWITCHED =
            "android.intent.action.USER_SWITCHED";

    /**
     * Broadcast Action: Sent when the credential-encrypted private storage has
     * become unlocked for the target user. This is only sent to registered
     * receivers, not manifest receivers.
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String ACTION_USER_UNLOCKED = "android.intent.action.USER_UNLOCKED";

    /**
     * Broadcast sent to the system when a user's information changes. Carries an extra
     * {@link #EXTRA_USER_HANDLE} to indicate which user's information changed.
     * This is only sent to registered receivers, not manifest receivers. It is sent to all users.
     *
     * @hide
     */
    public static final String ACTION_USER_INFO_CHANGED =
            "android.intent.action.USER_INFO_CHANGED";

    /**
     * Broadcast sent to the primary user when an associated managed profile is added (the profile
     * was created and is ready to be used). Carries an extra {@link #EXTRA_USER} that specifies
     * the UserHandle of the profile that was added. Only applications (for example Launchers)
     * that need to display merged content across both primary and managed profiles need to
     * worry about this broadcast. This is only sent to registered receivers,
     * not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_ADDED =
            "android.intent.action.MANAGED_PROFILE_ADDED";

    /**
     * Broadcast sent to the primary user when an associated managed profile is removed. Carries an
     * extra {@link #EXTRA_USER} that specifies the UserHandle of the profile that was removed.
     * Only applications (for example Launchers) that need to display merged content across both
     * primary and managed profiles need to worry about this broadcast. This is only sent to
     * registered receivers, not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_REMOVED =
            "android.intent.action.MANAGED_PROFILE_REMOVED";

    /**
     * Broadcast sent to the primary user when the credential-encrypted private storage for
     * an associated managed profile is unlocked. Carries an extra {@link #EXTRA_USER} that
     * specifies the UserHandle of the profile that was unlocked. Only applications (for example
     * Launchers) that need to display merged content across both primary and managed profiles
     * need to worry about this broadcast. This is only sent to registered receivers,
     * not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_UNLOCKED =
            "android.intent.action.MANAGED_PROFILE_UNLOCKED";

    /**
     * Broadcast sent to the primary user when an associated managed profile has become available.
     * Currently this includes when the user disables quiet mode for the profile. Carries an extra
     * {@link #EXTRA_USER} that specifies the UserHandle of the profile. When quiet mode is changed,
     * this broadcast will carry a boolean extra {@link #EXTRA_QUIET_MODE} indicating the new state
     * of quiet mode. This is only sent to registered receivers, not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_AVAILABLE =
            "android.intent.action.MANAGED_PROFILE_AVAILABLE";

    /**
     * Broadcast sent to the primary user when an associated managed profile has become unavailable.
     * Currently this includes when the user enables quiet mode for the profile. Carries an extra
     * {@link #EXTRA_USER} that specifies the UserHandle of the profile. When quiet mode is changed,
     * this broadcast will carry a boolean extra {@link #EXTRA_QUIET_MODE} indicating the new state
     * of quiet mode. This is only sent to registered receivers, not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_UNAVAILABLE =
            "android.intent.action.MANAGED_PROFILE_UNAVAILABLE";

    /**
     * Sent when the user taps on the clock widget in the system's "quick settings" area.
     */
    public static final String ACTION_QUICK_CLOCK =
            "android.intent.action.QUICK_CLOCK";

    /**
     * Activity Action: Shows the brightness setting dialog.
     *
     * @hide
     */
    public static final String ACTION_SHOW_BRIGHTNESS_DIALOG =
            "android.intent.action.SHOW_BRIGHTNESS_DIALOG";

    /**
     * Broadcast Action:  A global button was pressed.  Includes a single
     * extra field, {@link #EXTRA_KEY_EVENT}, containing the key event that
     * caused the broadcast.
     *
     * @hide
     */
    public static final String ACTION_GLOBAL_BUTTON = "android.intent.action.GLOBAL_BUTTON";

    /**
     * Broadcast Action: Sent when media resource is granted.
     * <p>
     * {@link #EXTRA_PACKAGES} specifies the packages on the process holding the media resource
     * granted.
     * </p>
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     * </p>
     * <p class="note">
     * This requires {@link android.Manifest.permission#RECEIVE_MEDIA_RESOURCE_USAGE} permission.
     * </p>
     *
     * @hide
     */
    public static final String ACTION_MEDIA_RESOURCE_GRANTED =
            "android.intent.action.MEDIA_RESOURCE_GRANTED";

    /**
     * Activity Action: Allow the user to select and return one or more existing
     * documents. When invoked, the system will display the various
     * {@link DocumentsProvider} instances installed on the device, letting the
     * user interactively navigate through them. These documents include local
     * media, such as photos and video, and documents provided by installed
     * cloud storage providers.
     * <p>
     * Each document is represented as a {@code content://} URI backed by a
     * {@link DocumentsProvider}, which can be opened as a stream with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}, or queried for
     * {@link android.provider.DocumentsContract.Document} metadata.
     * <p>
     * All selected documents are returned to the calling application with
     * persistable read and write permission grants. If you want to maintain
     * access to the documents across device reboots, you need to explicitly
     * take the persistable permissions using
     * {@link ContentResolver#takePersistableUriPermission(Uri, int)}.
     * <p>
     * Callers must indicate the acceptable document MIME types through
     * {@link #setType(String)}. For example, to select photos, use
     * {@code image/*}. If multiple disjoint MIME types are acceptable, define
     * them in {@link #EXTRA_MIME_TYPES} and {@link #setType(String)} to
     * {@literal *}/*.
     * <p>
     * If the caller can handle multiple returned items (the user performing
     * multiple selection), then you can specify {@link #EXTRA_ALLOW_MULTIPLE}
     * to indicate this.
     * <p>
     * Callers must include {@link #CATEGORY_OPENABLE} in the Intent to obtain
     * URIs that can be opened with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}.
     * <p>
     * Output: The URI of the item that was picked, returned in
     * {@link #getData()}. This must be a {@code content://} URI so that any
     * receiver can access it. If multiple documents were selected, they are
     * returned in {@link #getClipData()}.
     *
     * @see DocumentsContract
     * @see #ACTION_OPEN_DOCUMENT_TREE
     * @see #ACTION_CREATE_DOCUMENT
     * @see #FLAG_GRANT_PERSISTABLE_URI_PERMISSION
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_OPEN_DOCUMENT = "android.intent.action.OPEN_DOCUMENT";

    /**
     * Activity Action: Allow the user to create a new document. When invoked,
     * the system will display the various {@link DocumentsProvider} instances
     * installed on the device, letting the user navigate through them. The
     * returned document may be a newly created document with no content, or it
     * may be an existing document with the requested MIME type.
     * <p>
     * Each document is represented as a {@code content://} URI backed by a
     * {@link DocumentsProvider}, which can be opened as a stream with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}, or queried for
     * {@link android.provider.DocumentsContract.Document} metadata.
     * <p>
     * Callers must indicate the concrete MIME type of the document being
     * created by setting {@link #setType(String)}. This MIME type cannot be
     * changed after the document is created.
     * <p>
     * Callers can provide an initial display name through {@link #EXTRA_TITLE},
     * but the user may change this value before creating the file.
     * <p>
     * Callers must include {@link #CATEGORY_OPENABLE} in the Intent to obtain
     * URIs that can be opened with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}.
     * <p>
     * Output: The URI of the item that was created. This must be a
     * {@code content://} URI so that any receiver can access it.
     *
     * @see DocumentsContract
     * @see #ACTION_OPEN_DOCUMENT
     * @see #ACTION_OPEN_DOCUMENT_TREE
     * @see #FLAG_GRANT_PERSISTABLE_URI_PERMISSION
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_CREATE_DOCUMENT = "android.intent.action.CREATE_DOCUMENT";

    /**
     * Activity Action: Allow the user to pick a directory subtree. When
     * invoked, the system will display the various {@link DocumentsProvider}
     * instances installed on the device, letting the user navigate through
     * them. Apps can fully manage documents within the returned directory.
     * <p>
     * To gain access to descendant (child, grandchild, etc) documents, use
     * {@link DocumentsContract#buildDocumentUriUsingTree(Uri, String)} and
     * {@link DocumentsContract#buildChildDocumentsUriUsingTree(Uri, String)}
     * with the returned URI.
     * <p>
     * Output: The URI representing the selected directory tree.
     *
     * @see DocumentsContract
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String
            ACTION_OPEN_DOCUMENT_TREE = "android.intent.action.OPEN_DOCUMENT_TREE";

    /**
     * Broadcast Action: List of dynamic sensor is changed due to new sensor being connected or
     * exisiting sensor being disconnected.
     * <p>
     * <p class="note">This is a protected intent that can only be sent by the system.</p>
     * <p>
     * {@hide}
     */
    @SdkConstant(SdkConstantType.BROADCAST_INTENT_ACTION)
    public static final String
            ACTION_DYNAMIC_SENSOR_CHANGED = "android.intent.action.DYNAMIC_SENSOR_CHANGED";

    /**
     * {@hide}
     */
    public static final String ACTION_MASTER_CLEAR = "android.intent.action.MASTER_CLEAR";

    /**
     * Boolean intent extra to be used with {@link ACTION_MASTER_CLEAR} in order to force a factory
     * reset even if {@link android.os.UserManager.DISALLOW_FACTORY_RESET} is set.
     *
     * @hide
     */
    public static final String EXTRA_FORCE_MASTER_CLEAR =
            "android.intent.extra.FORCE_MASTER_CLEAR";

    /**
     * Broadcast action: report that a settings element is being restored from backup.  The intent
     * contains three extras: EXTRA_SETTING_NAME is a string naming the restored setting,
     * EXTRA_SETTING_NEW_VALUE is the value being restored, and EXTRA_SETTING_PREVIOUS_VALUE
     * is the value of that settings entry prior to the restore operation.  All of these values are
     * represented as strings.
     * <p>
     * <p>This broadcast is sent only for settings provider entries known to require special handling
     * around restore time.  These entries are found in the BROADCAST_ON_RESTORE table within
     * the provider's backup agent implementation.
     *
     * @see #EXTRA_SETTING_NAME
     * @see #EXTRA_SETTING_PREVIOUS_VALUE
     * @see #EXTRA_SETTING_NEW_VALUE
     * {@hide}
     */
    public static final String ACTION_SETTING_RESTORED = "android.os.action.SETTING_RESTORED";

    /**
     * {@hide}
     */
    public static final String EXTRA_SETTING_NAME = "setting_name";
    /**
     * {@hide}
     */
    public static final String EXTRA_SETTING_PREVIOUS_VALUE = "previous_value";
    /**
     * {@hide}
     */
    public static final String EXTRA_SETTING_NEW_VALUE = "new_value";

    /**
     * Activity Action: Process a piece of text.
     * <p>Input: {@link #EXTRA_PROCESS_TEXT} contains the text to be processed.
     * {@link #EXTRA_PROCESS_TEXT_READONLY} states if the resulting text will be read-only.</p>
     * <p>Output: {@link #EXTRA_PROCESS_TEXT} contains the processed text.</p>
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_PROCESS_TEXT = "android.intent.action.PROCESS_TEXT";
    /**
     * The name of the extra used to define the text to be processed, as a
     * CharSequence. Note that this may be a styled CharSequence, so you must use
     * {@link Bundle#getCharSequence(String) Bundle.getCharSequence()} to retrieve it.
     */
    public static final String EXTRA_PROCESS_TEXT = "android.intent.extra.PROCESS_TEXT";
    /**
     * The name of the boolean extra used to define if the processed text will be used as read-only.
     */
    public static final String EXTRA_PROCESS_TEXT_READONLY =
            "android.intent.extra.PROCESS_TEXT_READONLY";

    /**
     * Broadcast action: reports when a new thermal event has been reached. When the device
     * is reaching its maximum temperatue, the thermal level reported
     * {@hide}
     */
    @SdkConstant(SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_THERMAL_EVENT = "android.intent.action.THERMAL_EVENT";

    /**
     * {@hide}
     */
    public static final String EXTRA_THERMAL_STATE = "android.intent.extra.THERMAL_STATE";

    /**
     * Thermal state when the device is normal. This state is sent in the
     * {@link #ACTION_THERMAL_EVENT} broadcast as {@link #EXTRA_THERMAL_STATE}.
     * {@hide}
     */
    public static final int EXTRA_THERMAL_STATE_NORMAL = 0;

    /**
     * Thermal state where the device is approaching its maximum threshold. This state is sent in
     * the {@link #ACTION_THERMAL_EVENT} broadcast as {@link #EXTRA_THERMAL_STATE}.
     * {@hide}
     */
    public static final int EXTRA_THERMAL_STATE_WARNING = 1;

    /**
     * Thermal state where the device has reached its maximum threshold. This state is sent in the
     * {@link #ACTION_THERMAL_EVENT} broadcast as {@link #EXTRA_THERMAL_STATE}.
     * {@hide}
     */
    public static final int EXTRA_THERMAL_STATE_EXCEEDED = 2;
}
