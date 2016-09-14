package ch.ielse.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Set;

public class SpfsUtils {

    public static String ROOT_NAME = "app";

    public static void write(Context context, String key, Object value) {
        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        Editor editor = spfs.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Double) {
            editor.putString(key, String.valueOf(((Double) value).doubleValue()));
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            throw new IllegalArgumentException("not support type");
        }
        editor.commit();
    }

    public static String readString(Context context, String key) {
        return readString(context, key, null);
    }

    public static String readString(Context context, String key, String defValue) {
        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        return spfs.getString(key, defValue);
    }

    public static int readInt(Context context, String key) {
        return readInt(context, key, 0);
    }

    public static int readInt(Context context, String key, int defValue) {
        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        return spfs.getInt(key, defValue);
    }

    public static float readFloat(Context context, String key) {
        return readFloat(context, key, 0f);
    }

    public static float readFloat(Context context, String key, float defValue) {
        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        return spfs.getFloat(key, defValue);
    }

    public static boolean readBoolean(Context context, String key) {
        return readBoolean(context, key, false);
    }

    public static boolean readBoolean(Context context, String key, boolean defValue) {

        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        return spfs.getBoolean(key, defValue);
    }

    public static long readLong(Context context, String key) {
        return readLong(context, key, 0l);
    }

    public static long readLong(Context context, String key, long defValue) {
        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        return spfs.getLong(key, defValue);
    }

    public static Set<String> readStringSet(Context context, String key) {
        return readStringSet(context,key, null);
    }

    public static Set<String> readStringSet(Context context, String key, Set<String> defValues) {
        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        return spfs.getStringSet(key, defValues);
    }

    public static boolean contains(Context context, String key) {
        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        return spfs.contains(key);
    }

    public static boolean remove(Context context, String key) {
        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        return spfs.edit().remove(key).commit();
    }

    public boolean clear(Context context) {
        SharedPreferences spfs = context.getApplicationContext().getSharedPreferences(ROOT_NAME, Context.MODE_PRIVATE);
        return spfs.edit().clear().commit();
    }
}
