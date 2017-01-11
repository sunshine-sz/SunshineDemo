package com.fitsleep.sunshinelibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;
import java.util.Map;

/**
 * SharedPreference 的工具类
 */
public class UtilSharedPreference {
	/**
	 * SharedPreference
	 */
	private static final String PREFERENCE_FILE_NAME = "dock_SharedPreference";

	/**
	 * 向SharedPreferece存入String
	 * 
	 * @param context
	 *            Context
	 * @param key
	 *            key
	 * @param value
	 *            存入的字符串
	 */
	public static void saveString(final Context context, final String key,
			final String value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor = preference.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 向SharedPreferences存入int
	 * 
	 * @param context
	 *            Context
	 * @param key
	 *            key
	 * @param value
	 *            int
	 */
	public static void saveInt(final Context context, String key, int value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor = preference.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 向SharedPreferences存入boolean
	 * 
	 * @param context
	 *            Context
	 * @param key
	 *            key
	 * @param value
	 *            true or false
	 */
	public static void saveBoolean(final Context context, String key,
			Boolean value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor = preference.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 向SharedPreferences获取存入的String
	 * 
	 * @param context
	 *            context
	 * @param key
	 *            key
	 * @return 返回String
	 */
	public static String getStringValue(final Context context, final String key) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		return preference.getString(key, "");
	}

	/**
	 * ��ȡMap
	 * 
	 * @param context
	 *            context
	 * @param key
	 *            key
	 * @return 返回Map集合
	 */
	public static Map<String, String> getStringValues(final Context context,
			final String key) {
		Map<String, String> map = new HashMap<String, String>();
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		map.put("key", key);
		map.put("value", preference.getString(key, ""));
		return map;
	}

	/**
	 * ��ȡBoolean
	 * 
	 * @param context
	 * @param key
	 *            ���
	 * @return ���Ӧ��ֵ������Ҳ�����Ӧ��ֵ�� �򷵻�false
	 */
	public static boolean getBooleanValue(final Context context,
			final String key) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		return preference.getBoolean(key, false);
	}

	/**
	 * ��ȡString
	 * 
	 * @param context
	 * @param key
	 *            ���
	 * @return ���Ӧ��ֵ������Ҳ�����Ӧ��ֵ�� �򷵻�0
	 */
	public static int getIntValue(final Context context, final String key) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		return preference.getInt(key, 0);
	}

	/**
	 * ���ֵ��String��Boolean��Float��Long������SharedPreference��
	 * 
	 * @param context
	 * @param key
	 * @param value
	 *            ֵ��String��Boolean��Float��Long��
	 */
	public static void saveOBj(final Context context, final String key,
			final Object value) {
		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
		Editor editor = preference.edit();
		editor = preference.edit();
		if (value instanceof String) {
			editor.putString(key, value.toString());
		} else if (value instanceof Integer) {
			editor.putInt(key, ((Integer) value).intValue());
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, ((Boolean) value).booleanValue());
		} else if (value instanceof Float) {
			editor.putFloat(key, ((Float) value).floatValue());
		} else if (value instanceof Long) {
			editor.putLong(key, ((Long) value).longValue());
		}

		editor.commit();
	}

	class Type<T> {
		private T value;

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		/*
		 * public static void main(String[] args) { Test<String> stringTest =
		 * new Test<String>(); stringTest.setName("aaa");
		 * System.out.println(stringTest.getName()); Test<Integer> integerTest =
		 * new Test<Integer>(); integerTest.setName(1111);
		 * System.out.println(integerTest.getName()); }
		 */
	}

	public static void remove(final Context context, String key) {

		SharedPreferences preference = context.getSharedPreferences(
				PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);

		Editor editor = preference.edit();
		editor.remove(key);
		editor.commit();

	}
}
