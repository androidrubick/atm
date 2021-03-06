package android.content;

import android.annotation.SdkConstant;
import android.annotation.SdkConstant.SdkConstantType;
import android.annotation.SystemApi;
import android.net.Uri;

/**
 * <p>
 * Created by Yin Yong on 2018/3/15.
 */
@SuppressWarnings({"unused", "UnnecessaryInterfaceModifier", "deprecation"})
interface IntentCategories {
    // ---------------------------------------------------------------------
    // ---------------------------------------------------------------------
    // Standard intent categories (see addCategory()).

    /**
     * Set if the activity should be an option for the default action
     * (center press) to perform on a piece of data.  Setting this will
     * hide from the user any activities without it set when performing an
     * action on some data.  Note that this is normally -not- set in the
     * Intent when initiating an action -- it is for use in intent filters
     * specified in packages.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_DEFAULT = "android.intent.category.DEFAULT";
    /**
     * Activities that can be safely invoked from a browser must support this
     * category.  For example, if the user is viewing a web page or an e-mail
     * and clicks on a link in the text, the Intent generated execute that
     * link will require the BROWSABLE category, so that only activities
     * supporting this category will be considered as possible actions.  By
     * supporting this category, you are promising that there is nothing
     * damaging (without user intervention) that can happen by invoking any
     * matching Intent.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_BROWSABLE = "android.intent.category.BROWSABLE";
    /**
     * Categories for activities that can participate in voice interaction.
     * An activity that supports this category must be prepared to run with
     * no UI shown at all (though in some case it may have a UI shown), and
     * rely on {@link android.app.VoiceInteractor} to interact with the user.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_VOICE = "android.intent.category.VOICE";
    /**
     * Set if the activity should be considered as an alternative action to
     * the data the user is currently viewing.  See also
     * {@link #CATEGORY_SELECTED_ALTERNATIVE} for an alternative action that
     * applies to the selection in a list of items.
     *
     * <p>Supporting this category means that you would like your activity to be
     * displayed in the set of alternative things the user can do, usually as
     * part of the current activity's options menu.  You will usually want to
     * include a specific label in the &lt;intent-filter&gt; of this action
     * describing to the user what it does.
     *
     * <p>The action of IntentFilter with this category is important in that it
     * describes the specific action the target will perform.  This generally
     * should not be a generic action (such as {@link #ACTION_VIEW}, but rather
     * a specific name such as "com.android.camera.action.CROP.  Only one
     * alternative of any particular action will be shown to the user, so using
     * a specific action like this makes sure that your alternative will be
     * displayed while also allowing other applications to provide their own
     * overrides of that particular action.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_ALTERNATIVE = "android.intent.category.ALTERNATIVE";
    /**
     * Set if the activity should be considered as an alternative selection
     * action to the data the user has currently selected.  This is like
     * {@link #CATEGORY_ALTERNATIVE}, but is used in activities showing a list
     * of items from which the user can select, giving them alternatives to the
     * default action that will be performed on it.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_SELECTED_ALTERNATIVE = "android.intent.category.SELECTED_ALTERNATIVE";
    /**
     * Intended to be used as a tab inside of a containing TabActivity.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_TAB = "android.intent.category.TAB";
    /**
     * Should be displayed in the top-level launcher.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_LAUNCHER = "android.intent.category.LAUNCHER";
    /**
     * Indicates an activity optimized for Leanback mode, and that should
     * be displayed in the Leanback launcher.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    /**
     * Indicates a Leanback settings activity to be displayed in the Leanback launcher.
     * @hide
     */
    @SystemApi
    public static final String CATEGORY_LEANBACK_SETTINGS = "android.intent.category.LEANBACK_SETTINGS";
    /**
     * Provides information about the package it is in; typically used if
     * a package does not contain a {@link #CATEGORY_LAUNCHER} to provide
     * a front-door to the user without having to be shown in the all apps list.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_INFO = "android.intent.category.INFO";
    /**
     * This is the home activity, that is the first activity that is displayed
     * when the device boots.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_HOME = "android.intent.category.HOME";
    /**
     * This is the home activity that is displayed when the device is finished setting up and ready
     * for use.
     * @hide
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_HOME_MAIN = "android.intent.category.HOME_MAIN";
    /**
     * This is the setup wizard activity, that is the first activity that is displayed
     * when the user sets up the device for the first time.
     * @hide
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_SETUP_WIZARD = "android.intent.category.SETUP_WIZARD";
    /**
     * This activity is a preference panel.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_PREFERENCE = "android.intent.category.PREFERENCE";
    /**
     * This activity is a development preference panel.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_DEVELOPMENT_PREFERENCE = "android.intent.category.DEVELOPMENT_PREFERENCE";
    /**
     * Capable of running inside a parent activity container.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_EMBED = "android.intent.category.EMBED";
    /**
     * This activity allows the user to browse and download new applications.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_MARKET = "android.intent.category.APP_MARKET";
    /**
     * This activity may be exercised by the monkey or other automated test tools.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_MONKEY = "android.intent.category.MONKEY";
    /**
     * To be used as a test (not part of the normal user experience).
     */
    public static final String CATEGORY_TEST = "android.intent.category.TEST";
    /**
     * To be used as a unit test (run through the Test Harness).
     */
    public static final String CATEGORY_UNIT_TEST = "android.intent.category.UNIT_TEST";
    /**
     * To be used as a sample code example (not part of the normal user
     * experience).
     */
    public static final String CATEGORY_SAMPLE_CODE = "android.intent.category.SAMPLE_CODE";

    /**
     * Used to indicate that an intent only wants URIs that can be opened with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}. Openable URIs
     * must support at least the columns defined in {@link OpenableColumns} when
     * queried.
     *
     * @see #ACTION_GET_CONTENT
     * @see #ACTION_OPEN_DOCUMENT
     * @see #ACTION_CREATE_DOCUMENT
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_OPENABLE = "android.intent.category.OPENABLE";

    /**
     * To be used as code under test for framework instrumentation tests.
     */
    public static final String CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST =
            "android.intent.category.FRAMEWORK_INSTRUMENTATION_TEST";
    /**
     * An activity to run when device is inserted into a car dock.
     * Used with {@link #ACTION_MAIN} to launch an activity.  For more
     * information, see {@link android.app.UiModeManager}.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_CAR_DOCK = "android.intent.category.CAR_DOCK";
    /**
     * An activity to run when device is inserted into a car dock.
     * Used with {@link #ACTION_MAIN} to launch an activity.  For more
     * information, see {@link android.app.UiModeManager}.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_DESK_DOCK = "android.intent.category.DESK_DOCK";
    /**
     * An activity to run when device is inserted into a analog (low end) dock.
     * Used with {@link #ACTION_MAIN} to launch an activity.  For more
     * information, see {@link android.app.UiModeManager}.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_LE_DESK_DOCK = "android.intent.category.LE_DESK_DOCK";

    /**
     * An activity to run when device is inserted into a digital (high end) dock.
     * Used with {@link #ACTION_MAIN} to launch an activity.  For more
     * information, see {@link android.app.UiModeManager}.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_HE_DESK_DOCK = "android.intent.category.HE_DESK_DOCK";

    /**
     * Used to indicate that the activity can be used in a car environment.
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_CAR_MODE = "android.intent.category.CAR_MODE";

    // ---------------------------------------------------------------------
    // ---------------------------------------------------------------------
    // Application launch intent categories (see addCategory()).

    /**
     * Used with {@link #ACTION_MAIN} to launch the browser application.
     * The activity should be able to browse the Internet.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_BROWSER = "android.intent.category.APP_BROWSER";

    /**
     * Used with {@link #ACTION_MAIN} to launch the calculator application.
     * The activity should be able to perform standard arithmetic operations.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_CALCULATOR = "android.intent.category.APP_CALCULATOR";

    /**
     * Used with {@link #ACTION_MAIN} to launch the calendar application.
     * The activity should be able to view and manipulate calendar entries.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_CALENDAR = "android.intent.category.APP_CALENDAR";

    /**
     * Used with {@link #ACTION_MAIN} to launch the contacts application.
     * The activity should be able to view and manipulate address book entries.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_CONTACTS = "android.intent.category.APP_CONTACTS";

    /**
     * Used with {@link #ACTION_MAIN} to launch the email application.
     * The activity should be able to send and receive email.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_EMAIL = "android.intent.category.APP_EMAIL";

    /**
     * Used with {@link #ACTION_MAIN} to launch the gallery application.
     * The activity should be able to view and manipulate image and video files
     * stored on the device.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_GALLERY = "android.intent.category.APP_GALLERY";

    /**
     * Used with {@link #ACTION_MAIN} to launch the maps application.
     * The activity should be able to show the user's current location and surroundings.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_MAPS = "android.intent.category.APP_MAPS";

    /**
     * Used with {@link #ACTION_MAIN} to launch the messaging application.
     * The activity should be able to send and receive text messages.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_MESSAGING = "android.intent.category.APP_MESSAGING";

    /**
     * Used with {@link #ACTION_MAIN} to launch the music application.
     * The activity should be able to play, browse, or manipulate music files
     * stored on the device.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    @SdkConstant(SdkConstantType.INTENT_CATEGORY)
    public static final String CATEGORY_APP_MUSIC = "android.intent.category.APP_MUSIC";
}
