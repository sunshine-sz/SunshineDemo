package com.fitsleep.sunshinelibrary.utils;

import android.text.TextUtils;
import android.text.format.Time;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sunshine
 * @Description 时间相关工具类
 */
public class TimeUtils {

	private TimeUtils() {
		throw new UnsupportedOperationException("u can't fuck me...");
	}

	/**
	 * <p>在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.</p>
	 * 格式的意义如下： 日期和时间模式 <br>
	 * <p>日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
	 * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
	 * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
	 * </p>
	 * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
	 * <table border="1" cellspacing="1" cellpadding="1" summary="Chart shows pattern letters, date/time component,
	 * presentation, and examples.">
	 * <tr>
	 * <th align="left">字母</th>
	 * <th align="left">日期或时间元素</th>
	 * <th align="left">表示</th>
	 * <th align="left">示例</th>
	 * </tr>
	 * <tr>
	 * <td><code>G</code></td>
	 * <td>Era 标志符</td>
	 * <td>Text</td>
	 * <td><code>AD</code></td>
	 * </tr>
	 * <tr>
	 * <td><code>y</code> </td>
	 * <td>年 </td>
	 * <td>Year </td>
	 * <td><code>1996</code>; <code>96</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>M</code> </td>
	 * <td>年中的月份 </td>
	 * <td>Month </td>
	 * <td><code>July</code>; <code>Jul</code>; <code>07</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>w</code> </td>
	 * <td>年中的周数 </td>
	 * <td>Number </td>
	 * <td><code>27</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>W</code> </td>
	 * <td>月份中的周数 </td>
	 * <td>Number </td>
	 * <td><code>2</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>D</code> </td>
	 * <td>年中的天数 </td>
	 * <td>Number </td>
	 * <td><code>189</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>d</code> </td>
	 * <td>月份中的天数 </td>
	 * <td>Number </td>
	 * <td><code>10</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>F</code> </td>
	 * <td>月份中的星期 </td>
	 * <td>Number </td>
	 * <td><code>2</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>E</code> </td>
	 * <td>星期中的天数 </td>
	 * <td>Text </td>
	 * <td><code>Tuesday</code>; <code>Tue</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>a</code> </td>
	 * <td>Am/pm 标记 </td>
	 * <td>Text </td>
	 * <td><code>PM</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>H</code> </td>
	 * <td>一天中的小时数（0-23） </td>
	 * <td>Number </td>
	 * <td><code>0</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>k</code> </td>
	 * <td>一天中的小时数（1-24） </td>
	 * <td>Number </td>
	 * <td><code>24</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>K</code> </td>
	 * <td>am/pm 中的小时数（0-11） </td>
	 * <td>Number </td>
	 * <td><code>0</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>h</code> </td>
	 * <td>am/pm 中的小时数（1-12） </td>
	 * <td>Number </td>
	 * <td><code>12</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>m</code> </td>
	 * <td>小时中的分钟数 </td>
	 * <td>Number </td>
	 * <td><code>30</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>s</code> </td>
	 * <td>分钟中的秒数 </td>
	 * <td>Number </td>
	 * <td><code>55</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>S</code> </td>
	 * <td>毫秒数 </td>
	 * <td>Number </td>
	 * <td><code>978</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>z</code> </td>
	 * <td>时区 </td>
	 * <td>General time zone </td>
	 * <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code> </td>
	 * </tr>
	 * <tr>
	 * <td><code>Z</code> </td>
	 * <td>时区 </td>
	 * <td>RFC 822 time zone </td>
	 * <td><code>-0800</code> </td>
	 * </tr>
	 * </table>
	 * <pre>
	 *                          HH:mm    15:44
	 *                         h:mm a    3:44 下午
	 *                        HH:mm z    15:44 CST
	 *                        HH:mm Z    15:44 +0800
	 *                     HH:mm zzzz    15:44 中国标准时间
	 *                       HH:mm:ss    15:44:40
	 *                     yyyy-MM-dd    2016-08-12
	 *               yyyy-MM-dd HH:mm    2016-08-12 15:44
	 *            yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
	 *       yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
	 *  EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
	 *       yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
	 *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
	 *   yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
	 *                         K:mm a    3:44 下午
	 *               EEE, MMM d, ''yy    星期五, 八月 12, '16
	 *          hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
	 *   yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
	 *     EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
	 *                  yyMMddHHmmssZ    160812154440+0800
	 *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
	 * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
	 * </pre>
	 */
	public static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


	/**
	 * 将时间戳转为时间字符串
	 * <p>格式为yyyy-MM-dd HH:mm:ss</p>
	 *
	 * @param milliseconds 毫秒时间戳
	 * @return 时间字符串
	 */
	public static String milliseconds2String(long milliseconds) {
		return milliseconds2String(milliseconds, DEFAULT_SDF);
	}

	/**
	 * 将时间戳转为时间字符串
	 * <p>格式为用户自定义</p>
	 *
	 * @param milliseconds 毫秒时间戳
	 * @param format       时间格式
	 * @return 时间字符串
	 */
	public static String milliseconds2String(long milliseconds, SimpleDateFormat format) {
		return format.format(new Date(milliseconds));
	}

	/**
	 * 将时间字符串转为时间戳
	 * <p>格式为yyyy-MM-dd HH:mm:ss</p>
	 *
	 * @param time 时间字符串
	 * @return 毫秒时间戳
	 */
	public static long string2Milliseconds(String time) {
		return string2Milliseconds(time, DEFAULT_SDF);
	}

	/**
	 * 将时间字符串转为时间戳
	 * <p>格式为用户自定义</p>
	 *
	 * @param time   时间字符串
	 * @param format 时间格式
	 * @return 毫秒时间戳
	 */
	public static long string2Milliseconds(String time, SimpleDateFormat format) {
		try {
			return format.parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 将时间字符串转为Date类型
	 * <p>格式为yyyy-MM-dd HH:mm:ss</p>
	 *
	 * @param time 时间字符串
	 * @return Date类型
	 */
	public static Date string2Date(String time) {
		return string2Date(time, DEFAULT_SDF);
	}

	/**
	 * 将时间字符串转为Date类型
	 * <p>格式为用户自定义</p>
	 *
	 * @param time   时间字符串
	 * @param format 时间格式
	 * @return Date类型
	 */
	public static Date string2Date(String time, SimpleDateFormat format) {
		return new Date(string2Milliseconds(time, format));
	}

	/**
	 * 将Date类型转为时间字符串
	 * <p>格式为yyyy-MM-dd HH:mm:ss</p>
	 *
	 * @param time Date类型时间
	 * @return 时间字符串
	 */
	public static String date2String(Date time) {
		return date2String(time, DEFAULT_SDF);
	}

	/**
	 * 将Date类型转为时间字符串
	 * <p>格式为用户自定义</p>
	 *
	 * @param time   Date类型时间
	 * @param format 时间格式
	 * @return 时间字符串
	 */
	public static String date2String(Date time, SimpleDateFormat format) {
		return format.format(time);
	}

	/**
	 * 将Date类型转为时间戳
	 *
	 * @param time Date类型时间
	 * @return 毫秒时间戳
	 */
	public static long date2Milliseconds(Date time) {
		return time.getTime();
	}

	/**
	 * 将时间戳转为Date类型
	 *
	 * @param milliseconds 毫秒时间戳
	 * @return Date类型时间
	 */
	public static Date milliseconds2Date(long milliseconds) {
		return new Date(milliseconds);
	}

	/**
	 * 毫秒时间戳单位转换（单位：unit）
	 *
	 * @param milliseconds 毫秒时间戳
	 * @param unit         <ul>
	 *                     <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
	 *                     <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
	 *                     <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
	 *                     <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
	 *                     <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
	 *                     </ul>
	 * @return unit时间戳
	 */
	private static long milliseconds2Unit(long milliseconds, ConstUtils.TimeUnit unit) {
		switch (unit) {
			case MSEC:
				return milliseconds / ConstUtils.MSEC;
			case SEC:
				return milliseconds / ConstUtils.SEC;
			case MIN:
				return milliseconds / ConstUtils.MIN;
			case HOUR:
				return milliseconds / ConstUtils.HOUR;
			case DAY:
				return milliseconds / ConstUtils.DAY;
		}
		return -1;
	}

	/**
	 * 获取两个时间差（单位：unit）
	 * <p>time1和time2格式都为yyyy-MM-dd HH:mm:ss</p>
	 *
	 * @param time0 时间字符串1
	 * @param time1 时间字符串2
	 * @param unit  <ul>
	 *              <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
	 *              <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
	 *              <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
	 *              <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
	 *              <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
	 *              </ul>
	 * @return unit时间戳
	 */
	public static long getIntervalTime(String time0, String time1, ConstUtils.TimeUnit unit) {
		return getIntervalTime(time0, time1, unit, DEFAULT_SDF);
	}

	/**
	 * 获取两个时间差（单位：unit）
	 * <p>time1和time2格式都为format</p>
	 *
	 * @param time0  时间字符串1
	 * @param time1  时间字符串2
	 * @param unit   <ul>
	 *               <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
	 *               <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
	 *               <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
	 *               <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
	 *               <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
	 *               </ul>
	 * @param format 时间格式
	 * @return unit时间戳
	 */
	public static long getIntervalTime(String time0, String time1, ConstUtils.TimeUnit unit, SimpleDateFormat format) {
		return Math.abs(milliseconds2Unit(string2Milliseconds(time0, format)
				- string2Milliseconds(time1, format), unit));
	}

	/**
	 * 获取两个时间差（单位：unit）
	 * <p>time1和time2都为Date类型</p>
	 *
	 * @param time0 Date类型时间1
	 * @param time1 Date类型时间2
	 * @param unit  <ul>
	 *              <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
	 *              <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
	 *              <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
	 *              <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
	 *              <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
	 *              </ul>
	 * @return unit时间戳
	 */
	public static long getIntervalTime(Date time0, Date time1, ConstUtils.TimeUnit unit) {
		return Math.abs(milliseconds2Unit(date2Milliseconds(time1)
				- date2Milliseconds(time0), unit));
	}

	/**
	 * 获取当前时间
	 *
	 * @return 毫秒时间戳
	 */
	public static long getCurTimeMills() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取当前时间
	 * <p>格式为yyyy-MM-dd HH:mm:ss</p>
	 *
	 * @return 时间字符串
	 */
	public static String getCurTimeString() {
		return date2String(new Date());
	}

	/**
	 * 获取当前时间
	 * <p>格式为用户自定义</p>
	 *
	 * @param format 时间格式
	 * @return 时间字符串
	 */
	public static String getCurTimeString(SimpleDateFormat format) {
		return date2String(new Date(), format);
	}

	/**
	 * 获取当前时间
	 * <p>Date类型</p>
	 *
	 * @return Date类型时间
	 */
	public static Date getCurTimeDate() {
		return new Date();
	}

	/**
	 * 获取与当前时间的差（单位：unit）
	 * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
	 *
	 * @param time 时间字符串
	 * @param unit <ul>
	 *             <li>{@link ConstUtils.TimeUnit#MSEC}:毫秒</li>
	 *             <li>{@link ConstUtils.TimeUnit#SEC }:秒</li>
	 *             <li>{@link ConstUtils.TimeUnit#MIN }:分</li>
	 *             <li>{@link ConstUtils.TimeUnit#HOUR}:小时</li>
	 *             <li>{@link ConstUtils.TimeUnit#DAY }:天</li>
	 *             </ul>
	 * @return unit时间戳
	 */
	public static long getIntervalByNow(String time, ConstUtils.TimeUnit unit) {
		return getIntervalByNow(time, unit, DEFAULT_SDF);
	}

	/**
	 * 获取与当前时间的差（单位：unit）
	 * <p>time格式为format</p>
	 *
	 * @param time   时间字符串
	 * @param unit   <ul>
	 *               <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
	 *               <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
	 *               <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
	 *               <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
	 *               <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
	 *               </ul>
	 * @param format 时间格式
	 * @return unit时间戳
	 */
	public static long getIntervalByNow(String time, ConstUtils.TimeUnit unit, SimpleDateFormat format) {
		return getIntervalTime(getCurTimeString(), time, unit, format);
	}

	/**
	 * 获取与当前时间的差（单位：unit）
	 * <p>time为Date类型</p>
	 *
	 * @param time Date类型时间
	 * @param unit <ul>
	 *             <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
	 *             <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
	 *             <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
	 *             <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
	 *             <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
	 *             </ul>
	 * @return unit时间戳
	 */
	public static long getIntervalByNow(Date time, ConstUtils.TimeUnit unit) {
		return getIntervalTime(getCurTimeDate(), time, unit);
	}

	/**
	 * 判断闰年
	 *
	 * @param year 年份
	 * @return {@code true}: 闰年<br>{@code false}: 平年
	 */
	public static boolean isLeapYear(int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}

	//获得当天0点时间
	public static int getTimesmorning(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis()/1000);
	}

	//获得当天24点时间
	public static int getTimesnight(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis()/1000);
	}

	/**
	 * 毫秒转换成时间
	 * @param duration 毫秒
	 * @return 时间字符串
	 */
	public static String getD(long duration) {
		StringBuffer sb = new StringBuffer();
		int f = (int) (duration / 1000 / 60);
		int h = (int) (duration / 1000 - f * 60);
		if (f == 0) {
			sb.append("00");
			if (h < 10) {
				sb.append(":0" + h);
			} else {
				sb.append(":" + h);
			}
			return sb.toString();
		} else {
			sb.append(f + "");
			if (h < 10) {
				sb.append(":0" + h);
			} else {
				sb.append(":" + h);
			}
			return sb.toString();
		}
	}
	
	
	/**
	 * 从输入日期开始计算，返回一个集合，集合中的数据为每一周的起始时间和结束时间
	 * @param startDateString 开始时间 （例：2015/05/31）
	 * @param endDateString 结束时间 （例：2015/06/20）
	 * @return 返回一个list集合
	 * （转化为数组：2015/05/31-2015/06/06 ; 2015/06/07-2015/06/13 ; 2015/06/14-2015/06/20）
	 */
	public static List<String> getDate(String startDateString,String endDateString){
		int Diffdays = ComputerDays(startDateString, endDateString);
		String date = "";
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < Diffdays; i+=7) {
			String tempString1 = startDateString;
			date = tempString1 + "-" + nextSevenDate(startDateString, 7);
			startDateString = nextSevenDate(nextSevenDate(tempString1, 7), 1);
			list.add(date);
		}
		return  list;
	}
	
	/**
	 * 根据输入的日期返回该日期所在这一周的每一天的一个集合（从上周日到周六）
	 * @param time 输入日期（例：2015-05-28）
	 * @return 一周的七天的集合
	 * （转化为数组：2015-5-24, 2015-5-25, 2015-5-26, 2015-5-27, 2015-5-28, 2015-5-29, 2015-5-30）
	 */
	public static List<String> getThisWeekDays(String time) {// 其中time为yyyy-mm-dd形式
		int year = Integer.valueOf(time.split("-")[0]);
		int mon = Integer.valueOf(time.split("-")[1]);
		int day = Integer.valueOf(time.split("-")[2]);
		Calendar oDate = Calendar.getInstance();
		oDate.set(year, mon - 1, day);
		oDate.add(Calendar.DATE, (-1) * (oDate.get(Calendar.DAY_OF_WEEK)));
		String s = new String();
		List<String> data = new ArrayList<String>();
		for (int i = 1; i < 8; i++) {
			oDate.add(Calendar.DATE, 1);
			s = oDate.get(Calendar.YEAR) + "-";
			s += (oDate.get(Calendar.MONTH) + 1) + "-" + oDate.get(Calendar.DATE);
			data.add(s);
		}
		return data;
	}
	
	/**
	 * 输入一个周时间的字符串，返回一个集合（这周每天的起始时间和结束时间00:00:00）
	 * @param time 日期 （例：2015/06/07-2015/06/13）
	 * @param bool 根据true或false返回每天的起始时间或结束时间
	 * @return 这周每天的起始时间和结束时间
	 * {2015-06-07 00:00:00,2015-06-08 00:00:00,2015-06-09 00:00:00,2015-06-10 00:00:00,2015-06-11 00:00:00,2015-06-12 00:00:00,2015-06-13 00:00:00}
	 * {2015-06-07 23:59:59,2015-06-08 23:59:59,2015-06-09 23:59:59,2015-06-10 23:59:59,2015-06-11 23:59:59,2015-06-12 23:59:59,2015-06-13 23:59:59}
	 */
	public static List<String> getWeekTime(String time, boolean bool) {
		List<String> startlist = new ArrayList<String>();
		List<String> stoplist = new ArrayList<String>();
		// "2013/12/29-2014/01/04"
		String[] timeStrs = time.split("-");
		String time0 = timeStrs[0].replace("/", "-");
		String time1 = timeStrs[1].replace("/", "-");
		int starday = Integer.parseInt(time0.substring(time0.length() - 2));
		int stopday = Integer.parseInt(time1.substring(time1.length() - 2));
		if (stopday - 7 >= 0) {// 此礼拜在同一个月
			// timeStrs[1].replace("/", ":");//2015/04/04
			for (int i = 0; i < 7; i++) {
				int day = starday + i;
				String y_m = time0.substring(0, time0.length() - 2);
				if (day < 10) {
					startlist.add(y_m + "0" + day + " 00:00:00");
					stoplist.add(y_m + "0" + day + " 23:59:59");
				} else {
					startlist.add(y_m + day + " 00:00:00");
					stoplist.add(y_m + day + " 23:59:59");
				}
			}
		} else {// 此礼拜不在同一个月
			int jiange = 7 - stopday;// jiange 开始月份的一周内所占天数stopday结束月份所占的天数
			for (int i = 0; i < jiange; i++) {
				int day = starday + i;
				String y_m = time0.substring(0, time0.length() - 2);
				startlist.add(y_m + day + " 00:00:00");
				stoplist.add(y_m + day + " 23:59:59");
			}
			for (int i = stopday; i > 0; i--) {
				int day = stopday - i + 1;
				String y_m = time1.substring(0, time1.length() - 2);
				startlist.add(y_m + "0" + day + " 00:00:00");
				stoplist.add(y_m + "0" + day + " 23:59:59");
			}
		}
		if (bool) {
			return startlist;
		} else {
			return stoplist;
		}
	}
	
	/**
	 * 根据输入的年份和月份返回该月每天的起始时间和结束时间（精确到时分秒）
	 * @param year 年份  （例：2016）
	 * @param month 月份 （例：06）
	 * @param isDayStart 根据true或false返回每天的起始时间或结束时间
	 * @return 这月每天的起始时间和结束时间
	 * {2015-06-01 00:00:00,2015-06-02 00:00:00,......,2015-06-30 00:00:00}
	 * {2015-06-01 23:59:59,2015-06-02 23:59:59,......,2015-06-30 23:59:59}
	 */
	public static String[] getMonthTime(String year, String month, boolean isDayStart) {
		int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };// 每个月的天数
		int Iyear = Integer.parseInt(year);// 年
		int Imonth = Integer.parseInt(month);// 月
		if (leapYear(Iyear)) {// 判断润年还是平年
			days[1] = 29;
		}
		int Idays = days[Imonth - 1];
		String[] dateTime = new String[Idays];
		String dayEndOrStartString;
		if (isDayStart)
			dayEndOrStartString = " 00:00:00";
		else {
			dayEndOrStartString = " 23:59:59";
		}
		for (int i = 0; i < days[Imonth - 1]; i++) {// 循环这个月
			String newMonth = Imonth < 10 ? /*"0" + */month : "" + month;
			String NewI = (i + 1) < 10 ? "0" + (i + 1) : "" + (i + 1);
			dateTime[i] = year + "-" + newMonth + "-" + NewI + dayEndOrStartString;
		}
		return dateTime;
	}
	
	/**
	 * 根据输入的年份返回该年每个月的起始时间和结束时间（精确到时分秒）
	 * @param year 年份 （例：2015）
	 * @param bool 根据true或false返回每个月的起始时间或结束时间
	 * @return 每个月的起始时间和结束时间
	 * {2015-01-01 00:00:00,2015-02-01 00:00:00,......,2015-12-01 00:00:00}
	 * {2015-01-31 23:59:59,2015-02-28 23:59:59,...,2015-06-30 23:59:59,......,2015-12-31 23:59:59}
	 */
	public static String[] getYearTime(String year,boolean bool){
		String starttime[] = new String[12];
		String endtime[] = new String[12];
		int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };// 每个月的天数
		if (leapYear(Integer.parseInt(year))) {// 判断润年还是平年
			days[1] = 29;
		}
		for(int i = 1; i <= 12; i++){
			if (i < 10) {
				starttime[i - 1] = year + "-0" + i + "-01 00:00:00";
				endtime[i - 1] = year + "-0" + i + "-" + days[i - 1] + " 23:59:59";
			} else {
				starttime[i - 1] = year + "-" + i + "-01 00:00:00";
				endtime[i - 1] = year + "-" + i + "-" + days[i - 1] + " 23:59:59";
			}
		}
		if(bool){
			return starttime;
		}else{
			return endtime;
		}
	}

	/**
	 * 根据输入的年份返回该年每个月的起始时间和结束时间（精确到时分秒）
	 * @param year 年份 （例：2015）
	 * @param bool 根据true或false返回每个月的起始时间或结束时间
	 * @return 每个月的起始时间和结束时间
	 * {2015-01-01 00:00:00,2015-02-01 00:00:00,......,2015-12-01 00:00:00}
	 * {2015-01-31 23:59:59,2015-02-28 23:59:59,...,2015-06-30 23:59:59,......,2015-12-31 23:59:59}
	 */
	public static String[] getYearTime2(int year,int month,boolean bool){
		String starttime[] = new String[12];
		String endtime[] = new String[12];
		int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };// 每个月的天数
		if (leapYear(year)) {// 判断润年还是平年
			days[1] = 29;
		}
		for(int i = 1; i <= 12; i++){
				starttime[i - 1] = year + "-" + month + "-01 00:00:00";
				endtime[i - 1] = year + "-" + month + "-" + days[month-1] + " 23:59:59";
				month--;
				if(month<=0){
					year--;
					month = 12;
					if(leapYear(year)){
						days[1] = 29;
					}else {
						days[1] = 28;
					}
				}
		}
		if(bool){
			return starttime;
		}else{
			return endtime;
		}
	}
	
	/**
	 * 根据输入的日期返回该日期所在这一周的第一天到最后一天的一个字符串（从上周日到周六）
	 * @param time 日期（例：2015-02-13）
	 * @return 这一周从周日到下周六的字符串 （例：2015/02/08-2015/02/14）
	 */
	public static String StandardWeekDays(String time){
		String firstday = getThisWeekDays(time).get(0).replace("-", "/");
		String endday = getThisWeekDays(time).get(getThisWeekDays(time).size() - 1).replace("-", "/");
		firstday = standardTime(firstday);
		endday = standardTime(endday);
		String day = firstday+"-"+endday;
		return day;
	}
	
	/**
	 * 将日期标准化
	 * @param day 日期字符串（例：2015/2/8）
	 * @return 标准时间字符串（例：2015/02/08）
	 */
	public static String standardTime(String day) {
		String[]  days = day.split("/");
		if(Integer.parseInt(days[1]) < 10){
			days[1] = "0"+ days[1];
		}
		if(Integer.parseInt(days[2]) < 10){
			days[2] = "0"+ days[2];
		}
		day = days[0]+"/"+days[1]+"/"+days[2];
		return day;
	}
	
	/**
	 * 返回输入日期距离输入年份1月1号的天数
	 * 例如输入2013/02/13就返回距离2013年1月1号的天数
	 * @param DateString 日期 （例：2013/02/13）
	 * @return 天数
	 */
	public static int getCurrentDay(String DateString){
		int a[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };// 每个月的天数
		int year = Integer.parseInt(DateString.substring(0, 4));// 年
		int month = Integer.parseInt(DateString.substring(5, 7));// 月
		int day = Integer.parseInt(DateString.substring(8, 10));// 日
		if(leapYear(year)) // 判断是否是润年
			a[1] = 29;
		int sum = 0;
		for (int i = 0; i < month - 1; i++)
			sum += a[i];
		sum += day;
		return sum;
	}
	
	/**
	 * 计算两个日期之间的天数
	 * @param startDateString 开始日期  （例：2015/02/27）
	 * @param endDateString 结束日期  （例：2015/03/05）
	 * @return 两个日期之间相隔的天数
	 */
	public static int ComputerDays(String startDateString, String endDateString) {
		int startSum = getCurrentDay(startDateString);
		int endSum  = getCurrentDay(endDateString);
		//算出两整年的天数
		for (int p = Integer.parseInt(startDateString.substring(0, 4)); p < Integer.parseInt(endDateString.substring(0, 4)); p++)
			if ((p % 400 == 0 || (p % 4 == 0 && p / 100 != 0)))
				endSum += 366;
			else {
				endSum += 365;
			}
		return endSum - startSum;
	}
	
	/**
	 * 求出距离输入日期下一天的日期或者七天后的日期
	 * @param date 日期字符串（例：2015/06/02）
	 * @param daynext （除了7以外，输入其他任意值都默认为1）
	 * @return (2015/06/08或2015/06/03)
	 */
	public static String nextSevenDate(String date, int daynext) {
		int a[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };// 每个月的天数
		String newDate = "";
		int tempDay = 0;
		int year = Integer.parseInt(date.substring(0, 4));// 年
		int month = Integer.parseInt(date.substring(5, 7));// 月
		int day = Integer.parseInt(date.substring(8, 10));// 日
		if (leapYear(year)) // 判断是否是润年
			a[1] = 29;
		if (daynext == 7)
			tempDay = (day + 6);//
		else
			tempDay = (day + 1);
		day = tempDay % a[month - 1];
		boolean exceedDay = (tempDay >= a[month - 1]);// 判断tempDay是否超过该月天数
		if (exceedDay) {
			if (tempDay == a[month - 1])
				day = a[month - 1];
			else
				month = (month + 1);
			boolean tempMonth = (month > 12);// 判断月份是否超过12月
			if (tempMonth) {
				month = 1;
				year = year + 1;
			}
		}
		String newMonth = month < 10 ? "0" + month : "" + month;
		String newDay = day < 10 ? "0" + day : "" + day;
		newDate = year + "/" + newMonth + "/" + newDay;
		return newDate;
	}
	
	/**
	 * 将标准时间转换为时间戳（即输入时间距离1970-01-01 00:00:00的秒数）
	 * @param time 标准时间 （例：2015-06-01 00:00:00）
	 * @return 时间戳，精确到秒（例：1433088000）
	 * @throws ParseException
	 */
	public static long getTimelong(String time) throws ParseException {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=simpleDateFormat .parse(time);
		return date.getTime()/1000;
	}


	/**
	 * 获取现在时间
	 *
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 将时间戳转化为标准时间
	 * @param mill 十位的时间戳（例：1433088000）
	 * @return 标准时间 （例：2015-06-01 00:00:00）
	 */
	public static String convertTotime(long mill) {
		Date date = new Date(mill * 1000);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	/**
	 * 字符串转换成时间戳
	 *
	 * @throws ParseException
	 */
	public static String getTime(String user_time)
			throws ParseException {
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date d;
		try {

			d = sdf.parse(user_time);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re_time;
	}
	/**
	 * 判断是否是润年
	 * @param year （例：2016）
	 * @return true或者false
	 */
	public static boolean leapYear(int year){
		return year % 400 == 0 || (year % 4 == 0 && year / 100 != 0);
	}


	/** 时间戳转换成字符窜 */
	public static String getDateToString(long time) {
		Date d = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		return sf.format(d);
	}

	//输入生日字符串获取年龄
	public static final String calculateDatePoor(String birthday) {
		try {
			if (TextUtils.isEmpty(birthday))
				return "0";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthdayDate = sdf.parse(birthday);
			String currTimeStr = sdf.format(new Date());
			Date currDate = sdf.parse(currTimeStr);
			if (birthdayDate.getTime() > currDate.getTime()) {
				return "0";
			}
			long age = (currDate.getTime() - birthdayDate.getTime())
					/ (24 * 60 * 60 * 1000) + 1;
			String year = new DecimalFormat("0.00").format(age / 365f);
			if (TextUtils.isEmpty(year))
				return "0";
			return String.valueOf(new Double(year).intValue());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "0";
	}


	/**
	 * 解析服务器返回的Json串时，JSONObject对象抛出了这个异常。
	 原以为是返回的json格式错误了，仔细对比看不出所以然。至少字符上看是格式没问题的。。
	 那就可能是编码的问题了。仔细比较每个字符，的确在json串头部发现字符："\ufeff" 。
	 * */
	public static final String removeBOM(String data) {
		if (TextUtils.isEmpty(data)) {
			return data;
		}

		if (data.startsWith("\ufeff")) {
			return data.substring(1);
		} else {
			return data;
		}
	}

	/**
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDay(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
			case 0:
				flag = true;
				break;
			default:
				flag = false;
				break;
		}
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day = 31;
				break;
			case 2:
				day = flag ? 29 : 28;
				break;
			default:
				day = 30;
				break;
		}
		return day;
	}


	/**
	 * 时间转Long毫秒
	 *
	 * @param pattern 匹配时间字符串（形如"yyyy年M月d日"）
	 * @param date    要转换的时间（形如"2013年1月6日",须和pattern参数对应）
	 * @return milliseconds long类型时间字符串
	 */
	public static long date2Millis(String pattern, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date d = sdf.parse(date);
//            sdf = new SimpleDateFormat("yyyy-MM-dd");
//            System.out.println(sdf.format(d));
//            System.out.println(d.getTime());
			return d.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	/**
	 * 根据日期取得星期几
	 *
	 * @param date
	 * @return "日","一","二","三","四","五","六"
	 */
	public static String getWeek(Date date) {
		String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

	/**
	 * 根据long类型时间字符串取得星期几
	 *
	 * @return "日","一","二","三","四","五","六"
	 */
	public static String getWeek(long milliseconds) {
		Date date = new Date(milliseconds);
		return getWeek(date);
	}

	public static String getStringMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
}
