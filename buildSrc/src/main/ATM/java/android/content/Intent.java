package android.content;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/**
 * An intent is an abstract description of an operation to be performed.
 * <p>
 * <p>
 * Created by Yin Yong on 2018/3/15.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Intent implements Cloneable, IntentFlags, IntentActions, IntentCategories {

    private String mAction;
    private Uri mData;
    private String mType;
    private String mPackage;
    private ComponentName mComponent;
    private int mFlags;
    private Set<String> mCategories;
    private Bundle mExtras;

    public Intent() {

    }

    /**
     * Create an intent with a given action.  All other fields (data, type,
     * class) are null.  Note that the action <em>must</em> be in a
     * namespace because Intents are used globally in the system -- for
     * example the system VIEW action is android.intent.action.VIEW; an
     * application's custom action would be something like
     * com.google.app.myapp.CUSTOM_ACTION.
     *
     * @param action The Intent action, such as ACTION_VIEW.
     */
    public Intent(String action) {
        setAction(action);
    }

    /**
     * Create an intent with a given action and for a given data url.  Note
     * that the action <em>must</em> be in a namespace because Intents are
     * used globally in the system -- for example the system VIEW action is
     * android.intent.action.VIEW; an application's custom action would be
     * something like com.google.app.myapp.CUSTOM_ACTION.
     *
     * <p><em>Note: scheme and host name matching in the Android framework is
     * case-sensitive, unlike the formal RFC.  As a result,
     * you should always ensure that you write your Uri with these elements
     * using lower case letters, and normalize any Uris you receive from
     * outside of Android to ensure the scheme and host is lower case.</em></p>
     *
     * @param action The Intent action, such as ACTION_VIEW.
     * @param uri The Intent data URI.
     */
    public Intent(String action, Uri uri) {
        setAction(action);
        mData = uri;
    }

    public Intent(Intent o) {
        this.mAction = o.mAction;
        this.mData = o.mData;
        this.mType = o.mType;
        this.mPackage = o.mPackage;
        this.mComponent = o.mComponent;
        this.mFlags = o.mFlags;
        if (o.mCategories != null) {
            this.mCategories = new HashSet<>(o.mCategories);
        }
        if (o.mExtras != null) {
            this.mExtras = new Bundle(o.mExtras);
        }
    }

    /**
     * Create an intent for a specific component.  All other fields (action, data,
     * type, class) are null, though they can be modified later with explicit
     * calls.  This provides a convenient way to create an intent that is
     * intended to execute a hard-coded class name, rather than relying on the
     * system to find an appropriate class for you; see {@link #setComponent}
     * for more information on the repercussions of this.
     *
     * @param packageContext A Context of the application package implementing
     * this class.
     * @param cls The component class that is to be used for the intent.
     *
     * @see #setComponent
     * @see #Intent(String, android.net.Uri , Context, Class)
     */
    public Intent(Context packageContext, Class<?> cls) {
        mComponent = new ComponentName(packageContext, cls);
    }

    /**
     * Create an intent for a specific component with a specified action and data.
     * This is equivalent to using {@link #Intent(String, android.net.Uri)} to
     * construct the Intent and then calling {@link #setComponent} to set its
     * class.
     *
     * <p><em>Note: scheme and host name matching in the Android framework is
     * case-sensitive, unlike the formal RFC.  As a result,
     * you should always ensure that you write your Uri with these elements
     * using lower case letters, and normalize any Uris you receive from
     * outside of Android to ensure the scheme and host is lower case.</em></p>
     *
     * @param action The Intent action, such as ACTION_VIEW.
     * @param uri The Intent data URI.
     * @param packageContext A Context of the application package implementing
     * this class.
     * @param cls The component class that is to be used for the intent.
     *
     * @see #Intent(String, android.net.Uri)
     * @see #Intent(Context, Class)
     * @see #setComponent
     */
    public Intent(String action, Uri uri,
                  Context packageContext, Class<?> cls) {
        setAction(action);
        mData = uri;
        mComponent = new ComponentName(packageContext, cls);
    }

    /**
     * Create an intent to launch the main (root) activity of a task.  This
     * is the Intent that is started when the application's is launched from
     * Home.  For anything else that wants to launch an application in the
     * same way, it is important that they use an Intent structured the same
     * way, and can use this function to ensure this is the case.
     *
     * <p>The returned Intent has the given Activity component as its explicit
     * component, {@link #ACTION_MAIN} as its action, and includes the
     * category {@link #CATEGORY_LAUNCHER}.  This does <em>not</em> have
     * {@link #FLAG_ACTIVITY_NEW_TASK} set, though typically you will want
     * to do that through {@link #addFlags(int)} on the returned Intent.
     *
     * @param mainActivity The main activity component that this Intent will
     * launch.
     * @return Returns a newly created Intent that can be used to launch the
     * activity as a main application entry.
     *
     * @see #setComponent
     */
    public static Intent makeMainActivity(ComponentName mainActivity) {
        Intent intent = new Intent(ACTION_MAIN);
        intent.setComponent(mainActivity);
        intent.addCategory(CATEGORY_LAUNCHER);
        return intent;
    }

    /**
     * Set the general action to be performed.
     *
     * @param action An action name, such as ACTION_VIEW.  Application-specific
     *               actions should be prefixed with the vendor's package name.
     *
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     *
     * @see #getAction
     */
    public Intent setAction(String action) {
        mAction = action != null ? action.intern() : null;
        return this;
    }

    /**
     * Set the data this intent is operating on.  This method automatically
     * clears any type that was previously set by {@link #setType} or
     * {@link #setTypeAndNormalize}.
     *
     * <p><em>Note: scheme matching in the Android framework is
     * case-sensitive, unlike the formal RFC. As a result,
     * you should always write your Uri with a lower case scheme,
     * or use {@link Uri#normalizeScheme} or
     * {@link #setDataAndNormalize}
     * to ensure that the scheme is converted to lower case.</em>
     *
     * @param data The Uri of the data this intent is now targeting.
     *
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     *
     * @see #getData
     * @see #setDataAndNormalize
     * @see android.net.Uri#normalizeScheme()
     */
    public Intent setData(Uri data) {
        mData = data;
        mType = null;
        return this;
    }

    /**
     * Normalize and set the data this intent is operating on.
     *
     * <p>This method automatically clears any type that was
     * previously set (for example, by {@link #setType}).
     *
     * <p>The data Uri is normalized using
     * {@link android.net.Uri#normalizeScheme} before it is set,
     * so really this is just a convenience method for
     * <pre>
     * setData(data.normalize())
     * </pre>
     *
     * @param data The Uri of the data this intent is now targeting.
     *
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     *
     * @see #getData
     * @see #setType
     * @see android.net.Uri#normalizeScheme
     */
    public Intent setDataAndNormalize(Uri data) {
        return setData(data.normalizeScheme());
    }

    /**
     * Set an explicit MIME data type.
     *
     * <p>This is used to create intents that only specify a type and not data,
     * for example to indicate the type of data to return.
     *
     * <p>This method automatically clears any data that was
     * previously set (for example by {@link #setData}).
     *
     * <p><em>Note: MIME type matching in the Android framework is
     * case-sensitive, unlike formal RFC MIME types.  As a result,
     * you should always write your MIME types with lower case letters,
     * or use {@link #normalizeMimeType} or {@link #setTypeAndNormalize}
     * to ensure that it is converted to lower case.</em>
     *
     * @param type The MIME type of the data being handled by this intent.
     *
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     *
     * @see #getType
     * @see #setTypeAndNormalize
     * @see #setDataAndType
     * @see #normalizeMimeType
     */
    public Intent setType(String type) {
        mData = null;
        mType = type;
        return this;
    }

    /**
     * Normalize and set an explicit MIME data type.
     *
     * <p>This is used to create intents that only specify a type and not data,
     * for example to indicate the type of data to return.
     *
     * <p>This method automatically clears any data that was
     * previously set (for example by {@link #setData}).
     *
     * <p>The MIME type is normalized using
     * {@link #normalizeMimeType} before it is set,
     * so really this is just a convenience method for
     * <pre>
     * setType(Intent.normalizeMimeType(type))
     * </pre>
     *
     * @param type The MIME type of the data being handled by this intent.
     *
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     *
     * @see #getType
     * @see #setData
     * @see #normalizeMimeType
     */
    public Intent setTypeAndNormalize(String type) {
        return setType(normalizeMimeType(type));
    }

    /**
     * (Usually optional) Set the data for the intent along with an explicit
     * MIME data type.  This method should very rarely be used -- it allows you
     * to override the MIME type that would ordinarily be inferred from the
     * data with your own type given here.
     *
     * <p><em>Note: MIME type and Uri scheme matching in the
     * Android framework is case-sensitive, unlike the formal RFC definitions.
     * As a result, you should always write these elements with lower case letters,
     * or use {@link #normalizeMimeType} or {@link android.net.Uri#normalizeScheme} or
     * {@link #setDataAndTypeAndNormalize}
     * to ensure that they are converted to lower case.</em>
     *
     * @param data The Uri of the data this intent is now targeting.
     * @param type The MIME type of the data being handled by this intent.
     *
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     *
     * @see #setType
     * @see #setData
     * @see #normalizeMimeType
     * @see android.net.Uri#normalizeScheme
     * @see #setDataAndTypeAndNormalize
     */
    public Intent setDataAndType(Uri data, String type) {
        mData = data;
        mType = type;
        return this;
    }

    /**
     * (Usually optional) Normalize and set both the data Uri and an explicit
     * MIME data type.  This method should very rarely be used -- it allows you
     * to override the MIME type that would ordinarily be inferred from the
     * data with your own type given here.
     *
     * <p>The data Uri and the MIME type are normalize using
     * {@link android.net.Uri#normalizeScheme} and {@link #normalizeMimeType}
     * before they are set, so really this is just a convenience method for
     * <pre>
     * setDataAndType(data.normalize(), Intent.normalizeMimeType(type))
     * </pre>
     *
     * @param data The Uri of the data this intent is now targeting.
     * @param type The MIME type of the data being handled by this intent.
     *
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     *
     * @see #setType
     * @see #setData
     * @see #setDataAndType
     * @see #normalizeMimeType
     * @see android.net.Uri#normalizeScheme
     */
    public Intent setDataAndTypeAndNormalize(Uri data, String type) {
        return setDataAndType(data.normalizeScheme(), normalizeMimeType(type));
    }

    /**
     * Retrieve the general action to be performed, such as
     * {@link #ACTION_VIEW}.  The action describes the general way the rest of
     * the information in the intent should be interpreted -- most importantly,
     * what to do with the data returned by {@link #getData}.
     *
     * @return The action of this intent or null if none is specified.
     *
     * @see #setAction
     */
    public String getAction() {
        return mAction;
    }

    /**
     * Retrieve data this intent is operating on.  This URI specifies the name
     * of the data; often it uses the content: scheme, specifying data in a
     * content provider.  Other schemes may be handled by specific activities,
     * such as http: by the web browser.
     *
     * @return The URI of the data this intent is targeting or null.
     *
     * @see #setData
     */
    public Uri getData() {
        return mData;
    }

    /**
     * The same as {@link #getData()}, but returns the URI as an encoded
     * String.
     */
    @Nullable
    public String getDataString() {
        return mData != null ? mData.toString() : null;
    }

    /**
     * Retrieve any explicit MIME type included in the intent.  This is usually
     * null, as the type is determined by the intent data.
     *
     * @return If a type was manually set, it is returned; else null is
     *         returned.
     *
     * @see #setType
     */
    public String getType() {
        return mType;
    }

    /**
     * Retrieves a <i>new</i> map of extended data from the intent.
     *
     * @return the <i>new</i> map of all extras previously added with putExtras(),
     * or null if none have been added.
     */
    @Nullable
    public Bundle getExtras() {
        return (mExtras != null)
                ? new Bundle(mExtras)
                : null;
    }

    /**
     * Retrieve extended data from the intent.
     *
     * @param name The name of the desired item.
     * @return the value of an item that previously added with putExtras()
     * or null if none was found.
     */
    public <T> T getExtra(String name) {
        return getExtra(name, null);
    }

    /**
     * Retrieve extended data from the intent.
     *
     * @param name         The name of the desired item.
     * @param defaultValue The default value to return in case no item is
     *                     associated with the key 'name'
     * @return the value of an item that previously added with putExtras()
     * or defaultValue if none was found.
     */
    @SuppressWarnings("unchecked")
    public <T> T getExtra(String name, T defaultValue) {
        T result = defaultValue;
        if (mExtras != null) {
            T result2 = (T) mExtras.get(name);
            if (result2 != null) {
                result = result2;
            }
        }
        return result;
    }

//    /**
//     * Add extended data to the intent.  The name must include a package
//     * prefix, for example the app com.android.contacts would use names
//     * like "com.android.contacts.ShowAll".
//     *
//     * @param name  The name of the extra data, with package prefix.
//     * @param value The Object data value.
//     * @return Returns the same Intent object, for chaining multiple calls
//     * into a single statement.
//     * @see #putExtras
//     * @see #removeExtra
//     */
//    public Intent putExtra(String name, Object value) {
//        checkExtras();
//        mExtras.put(name, value);
//        return this;
//    }

    /**
     * Copy all extras in 'src' in to this intent.
     *
     * @param src Contains the extras to copy.
     */
    public Intent putExtras(Intent src) {
        if (src.mExtras != null) {
            if (mExtras == null) {
                mExtras = new Bundle(src.mExtras);
            } else {
                mExtras.putAll(src.mExtras);
            }
        }
        return this;
    }

    /**
     * Add a set of extended data to the intent.  The keys must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param extras The Bundle of extras to add to this intent.
     * @see #removeExtra
     */
    public Intent putExtras(Bundle extras) {
        if (extras != null && !extras.isEmpty()) {
            checkExtras();
            mExtras.putAll(extras);
        }
        return this;
    }

    /**
     * Completely replace the extras in the Intent with the extras in the
     * given Intent.
     *
     * @param src The exact extras contained in this Intent are copied
     *            into the target intent, replacing any that were previously there.
     */
    public Intent replaceExtras(@NonNull Intent src) {
        mExtras = src.mExtras != null ? new Bundle(src.mExtras) : null;
        return this;
    }

    /**
     * Completely replace the extras in the Intent with the given Bundle of
     * extras.
     *
     * @param extras The new set of extras in the Intent, or null to erase
     *               all extras.
     */
    public Intent replaceExtras(@Nullable Bundle extras) {
        mExtras = extras != null ? new Bundle(extras) : null;
        return this;
    }

    /**
     * Remove extended data from the intent.
     */
    public void removeExtra(@Nullable String name) {
        if (mExtras != null) {
            mExtras.remove(name);
            if (mExtras.size() == 0) {
                mExtras = null;
            }
        }
    }

    private synchronized void checkExtras() {
        if (mExtras == null) {
            mExtras = new Bundle();
        }
    }

    /**
     * Retrieve any special flags associated with this intent.  You will
     * normally just set them with {@link #setFlags} and let the system
     * take the appropriate action with them.
     *
     * @return int The currently set flags.
     * @see #setFlags
     */
    public int getFlags() {
        return mFlags;
    }

    /**
     * Set special flags controlling how this intent is handled.  Most values
     * here depend on the type of component being executed by the Intent,
     * specifically the FLAG_ACTIVITY_* flags are all for use with
     * {@link Context#startActivity Context.startActivity()} and the
     * FLAG_RECEIVER_* flags are all for use with
     * {@link Context#sendBroadcast(Intent) Context.sendBroadcast()}.
     * <p>
     * <p>See the
     * <a href="{@docRoot}guide/topics/fundamentals/tasks-and-back-stack.html">Tasks and Back
     * Stack</a> documentation for important information on how some of these options impact
     * the behavior of your application.
     *
     * @param flags The desired flags.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #getFlags
     * @see #addFlags
     * @see #FLAG_GRANT_READ_URI_PERMISSION
     * @see #FLAG_GRANT_WRITE_URI_PERMISSION
     * @see #FLAG_GRANT_PERSISTABLE_URI_PERMISSION
     * @see #FLAG_GRANT_PREFIX_URI_PERMISSION
     * @see #FLAG_DEBUG_LOG_RESOLUTION
     * @see #FLAG_FROM_BACKGROUND
     * @see #FLAG_ACTIVITY_BROUGHT_TO_FRONT
     * @see #FLAG_ACTIVITY_CLEAR_TASK
     * @see #FLAG_ACTIVITY_CLEAR_TOP
     * @see #FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
     * @see #FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
     * @see #FLAG_ACTIVITY_FORWARD_RESULT
     * @see #FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY
     * @see #FLAG_ACTIVITY_MULTIPLE_TASK
     * @see #FLAG_ACTIVITY_NEW_DOCUMENT
     * @see #FLAG_ACTIVITY_NEW_TASK
     * @see #FLAG_ACTIVITY_NO_ANIMATION
     * @see #FLAG_ACTIVITY_NO_HISTORY
     * @see #FLAG_ACTIVITY_NO_USER_ACTION
     * @see #FLAG_ACTIVITY_PREVIOUS_IS_TOP
     * @see #FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
     * @see #FLAG_ACTIVITY_REORDER_TO_FRONT
     * @see #FLAG_ACTIVITY_SINGLE_TOP
     * @see #FLAG_ACTIVITY_TASK_ON_HOME
     * @see #FLAG_RECEIVER_REGISTERED_ONLY
     */
    public Intent setFlags(int flags) {
        mFlags = flags;
        return this;
    }

    /**
     * Add additional flags to the intent (or with existing flags
     * value).
     *
     * @param flags The new flags to set.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #setFlags
     */
    public Intent addFlags(int flags) {
        mFlags |= flags;
        return this;
    }

    /**
     * Return the set of all categories in the intent.  If there are no categories,
     * returns NULL.
     *
     * @return The set of categories you can examine.  Do not modify!
     * @see #hasCategory
     * @see #addCategory
     */
    public Set<String> getCategories() {
        return mCategories;
    }

    /**
     * Add a new category to the intent.  Categories provide additional detail
     * about the action the intent performs.  When resolving an intent, only
     * activities that provide <em>all</em> of the requested categories will be
     * used.
     *
     * @param category The desired category.  This can be either one of the
     *                 predefined Intent categories, or a custom category in your own
     *                 namespace.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #hasCategory
     * @see #removeCategory
     */
    public Intent addCategory(String category) {
        if (mCategories == null) {
            mCategories = new HashSet<>();
        }
        mCategories.add(category.intern());
        return this;
    }

    /**
     * Remove a category from an intent.
     *
     * @param category The category to remove.
     * @see #addCategory
     */
    public void removeCategory(String category) {
        if (mCategories != null) {
            mCategories.remove(category);
            if (mCategories.size() == 0) {
                mCategories = null;
            }
        }
    }

    /**
     * Check if a category exists in the intent.
     *
     * @param category The category to check.
     * @return boolean True if the intent contains the category, else false.
     * @see #getCategories
     * @see #addCategory
     */
    public boolean hasCategory(String category) {
        return mCategories != null && mCategories.contains(category);
    }

    /**
     * (Usually optional) Set an explicit application package name that limits
     * the components this Intent will resolve to.  If left to the default
     * value of null, all components in all applications will considered.
     * If non-null, the Intent can only match the components in the given
     * application package.
     *
     * @param packageName The name of the application package to handle the
     * intent, or null to allow any application package.
     *
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     *
     * @see #getPackage
     */
    public Intent setPackage(String packageName) {
        mPackage = packageName;
        return this;
    }

    /**
     * (Usually optional) Explicitly set the component to handle the intent.
     * If left with the default value of null, the system will determine the
     * appropriate class to use based on the other fields (action, data,
     * type, categories) in the Intent.  If this class is defined, the
     * specified class will always be used regardless of the other fields.  You
     * should only set this value when you know you absolutely want a specific
     * class to be used; otherwise it is better to let the system find the
     * appropriate class so that you will respect the installed applications
     * and user preferences.
     *
     * @param component The name of the application component to handle the
     * intent, or null to let the system find one for you.
     *
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     *
     * @see #getComponent
     */
    public Intent setComponent(ComponentName component) {
        mComponent = component;
        return this;
    }

    /**
     * Retrieve the application package name this Intent is limited to.  When
     * resolving an Intent, if non-null this limits the resolution to only
     * components in the given application package.
     *
     * @return The name of the application package for the Intent.
     *
     * @see #setPackage
     */
    public String getPackage() {
        return mPackage;
    }

    /**
     * Retrieve the concrete component associated with the intent.  When receiving
     * an intent, this is the component that was found to best handle it (that is,
     * yourself) and will always be non-null; in all other cases it will be
     * null unless explicitly set.
     *
     * @return The name of the application component to handle the intent.
     *
     * @see #setComponent
     */
    public ComponentName getComponent() {
        return mComponent;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public Object clone() {
        return new Intent(this);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(128);

        b.append("Intent { ");
        toShortString(b, true, true, true, false);
        b.append(" }");

        return b.toString();
    }

    /**
     * @hide
     */
    public String toShortString(boolean secure, boolean comp, boolean extras, boolean clip) {
        StringBuilder b = new StringBuilder(128);
        toShortString(b, secure, comp, extras, clip);
        return b.toString();
    }

    /**
     * @hide
     */
    public void toShortString(StringBuilder b, boolean secure, boolean comp, boolean extras,
                              boolean clip) {
        boolean first = true;
        if (mAction != null) {
            b.append("act=").append(mAction);
            first = false;
        }
        if (mCategories != null) {
            if (!first) {
                b.append(' ');
            }
            first = false;
            b.append("cat=[");
            Iterator<String> ite = mCategories.iterator();
            if (ite.hasNext()) {
                b.append(ite.next());
            }
            while (ite.hasNext()) {
                b.append(',').append(ite.next());
            }
            b.append("]");
        }
        if (mData != null) {
            if (!first) {
                b.append(' ');
            }
            first = false;
            b.append("dat=");
            if (secure) {
                b.append(mData.toSafeString());
            } else {
                b.append(mData);
            }
        }
        if (mType != null) {
            if (!first) {
                b.append(' ');
            }
            first = false;
            b.append("typ=").append(mType);
        }
        if (mFlags != 0) {
            if (!first) {
                b.append(' ');
            }
            first = false;
            b.append("flg=0x").append(Integer.toHexString(mFlags));
        }
        if (mPackage != null) {
            if (!first) {
                b.append(' ');
            }
            first = false;
            b.append("pkg=").append(mPackage);
        }
        if (comp && mComponent != null) {
            if (!first) {
                b.append(' ');
            }
            first = false;
            b.append("cmp=").append(mComponent.flattenToShortString());
        }
        if (extras && mExtras != null) {
            if (!first) {
                b.append(' ');
            }
            //noinspection UnusedAssignment
            first = false;
            b.append("(has extras)");
        }
    }

    /**
     * Normalize a MIME data type.
     *
     * <p>A normalized MIME type has white-space trimmed,
     * content-type parameters removed, and is lower-case.
     * This aligns the type with Android best practices for
     * intent filtering.
     *
     * <p>For example, "text/plain; charset=utf-8" becomes "text/plain".
     * "text/x-vCard" becomes "text/x-vcard".
     *
     * <p>All MIME types received from outside Android (such as user input,
     * or external sources like Bluetooth, NFC, or the Internet) should
     * be normalized before they are used to create an Intent.
     *
     * @param type MIME data type to normalize
     * @return normalized MIME data type, or null if the input was null
     * @see #setType
     * @see #setTypeAndNormalize
     */
    public static String normalizeMimeType(String type) {
        if (type == null) {
            return null;
        }

        type = type.trim().toLowerCase(Locale.ROOT);

        final int semicolonIndex = type.indexOf(';');
        if (semicolonIndex != -1) {
            type = type.substring(0, semicolonIndex);
        }
        return type;
    }
}
