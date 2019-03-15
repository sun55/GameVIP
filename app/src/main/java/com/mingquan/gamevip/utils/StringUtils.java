package com.mingquan.gamevip.utils;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class StringUtils {
  private final static Pattern emailer =
      Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

  private final static Pattern IMG_URL = Pattern.compile(".*?(gif|jpeg|png|jpg|bmp)");

  private final static Pattern URL =
      Pattern.compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");

  private final static ThreadLocal<SimpleDateFormat> dateFormater =
      new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
          return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
      };

  private final static ThreadLocal<SimpleDateFormat> dateFormater2 =
      new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
          return new SimpleDateFormat("yyyy-MM-dd");
        }
      };

  /**
   * 正则：汉字
   */
  public static final String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";

  /**
   * 验证汉字
   *
   * @param input 待验证文本
   * @return {@code true}: 匹配<br>{@code false}: 不匹配
   */
  public static boolean isZh(CharSequence input) {
    return isMatch(REGEX_ZH, input);
  }

  /**
   * 验证输入的是否是英文
   *
   * @param input
   * @return
   */
  public static boolean isEn(CharSequence input) {
    Pattern pattern;
    Matcher matcher;
    pattern = Pattern.compile("[a-zA-Z]+");
    matcher = pattern.matcher(input);
    if (matcher.matches()) {
      return true;
    }
    return false;
  }

  /**
   * 判断是否匹配正则
   *
   * @param regex 正则表达式
   * @param input 要匹配的字符串
   * @return {@code true}: 匹配<br>{@code false}: 不匹配
   */
  public static boolean isMatch(String regex, CharSequence input) {
    return input != null && input.length() > 0 && Pattern.matches(regex, input);
  }

  /**
   * 将字符串转位日期类型
   *
   * @param sdate
   * @return
   */
  public static Date toDate(String sdate) {
    return toDate(sdate, dateFormater.get());
  }

  public static Date toDate(String sdate, SimpleDateFormat dateFormater) {
    try {
      return dateFormater.parse(sdate);
    } catch (ParseException e) {
      return null;
    }
  }

  /**
   * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串
   * 若输入字符串为null或空字符串，返回true
   *
   * @param input
   * @return boolean
   */
  public static boolean isEmpty(String input) {
    if (input == null || "".equals(input))
      return true;

    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
        return false;
      }
    }
    return true;
  }

  /**
   * 判断一个url是否为图片url
   *
   * @param url
   * @return
   */
  public static boolean isImgUrl(String url) {
    if (url == null || url.trim().length() == 0)
      return false;
    return IMG_URL.matcher(url).matches();
  }

  /**
   * 判断是否为一个合法的url地址
   *
   * @param str
   * @return
   */
  public static boolean isUrl(String str) {
    if (str == null || str.trim().length() == 0)
      return false;
    return URL.matcher(str).matches();
  }

  /**
   * 字符串转整数
   *
   * @param str
   * @param defValue
   * @return
   */
  public static int toInt(String str, int defValue) {
    try {
      return Integer.parseInt(str);
    } catch (Exception e) {
    }
    return defValue;
  }

  /**
   * 对象转整数
   *
   * @param obj
   * @return 转换异常返回 0
   */
  public static int toInt(Object obj) {
    if (obj == null)
      return 0;
    return toInt(obj.toString(), 0);
  }

  /**
   * 对象转整数
   *
   * @param obj
   * @return 转换异常返回 0
   */
  public static long toLong(String obj) {
    try {
      return Long.parseLong(obj);
    } catch (Exception e) {
    }
    return 0;
  }

  /**
   * 字符串转布尔值
   *
   * @param b
   * @return 转换异常返回 false
   */
  public static boolean toBool(String b) {
    try {
      return Boolean.parseBoolean(b);
    } catch (Exception e) {
    }
    return false;
  }

  public static String getString(String s) {
    return s == null ? "" : s;
  }

  /**
   * 将一个InputStream流转换成字符串
   *
   * @param is
   * @return
   */
  public static String toConvertString(InputStream is) {
    StringBuffer res = new StringBuffer();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader read = new BufferedReader(isr);
    try {
      String line;
      line = read.readLine();
      while (line != null) {
        res.append(line + "<br>");
        line = read.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (null != isr) {
          isr.close();
          isr.close();
        }
        if (null != read) {
          read.close();
          read = null;
        }
        if (null != is) {
          is.close();
          is = null;
        }
      } catch (IOException e) {
      }
    }
    return res.toString();
  }

  /***
   * 截取字符串
   *
   * @param start 从那里开始，0算起
   * @param num   截取多少个
   * @param str   截取的字符串
   * @return
   */
  public static String getSubString(int start, int num, String str) {
    if (str == null) {
      return "";
    }
    int leng = str.length();
    if (start < 0) {
      start = 0;
    }
    if (start > leng) {
      start = leng;
    }
    if (num < 0) {
      num = 1;
    }
    int end = start + num;
    if (end > leng) {
      end = leng;
    }
    return str.substring(start, end);
  }

  /**
   * 获取当前时间为每年第几周
   *
   * @return
   */
  public static int getWeekOfYear() {
    return getWeekOfYear(new Date());
  }

  /**
   * 获取当前时间为每年第几周
   *
   * @param date
   * @return
   */
  public static int getWeekOfYear(Date date) {
    Calendar c = Calendar.getInstance();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.setTime(date);
    int week = c.get(Calendar.WEEK_OF_YEAR) - 1;
    week = week == 0 ? 52 : week;
    return week > 0 ? week : 1;
  }

  public static int[] getCurrentDate() {
    int[] dateBundle = new int[3];
    String[] temp = getDataTime("yyyy-MM-dd").split("-");

    for (int i = 0; i < 3; i++) {
      try {
        dateBundle[i] = Integer.parseInt(temp[i]);
      } catch (Exception e) {
        dateBundle[i] = 0;
      }
    }
    return dateBundle;
  }

  /**
   * 返回当前系统时间
   */
  public static String getDataTime(String format) {
    SimpleDateFormat df = new SimpleDateFormat(format);
    return df.format(new Date());
  }

  /**
   * 秒值转化为时分秒
   *
   * @param seconds 秒值
   * @return
   */
  public static String getTimeBySeconds(int seconds) {
    String sec = "";
    int minute = seconds / 60;
    int hour = minute / 60;
    if (seconds < 60) {
      sec = "00:00:" + formatString2Double(seconds % 60);
    } else if (minute > 0 && hour <= 0) {
      sec = "00:" + formatString2Double(minute) + ":" + formatString2Double(seconds % 60);
    } else if (hour > 0) {
      sec = formatString2Double(hour) + ":" + formatString2Double(minute % 60) + ":"
          + formatString2Double(seconds % 60);
    }
    return sec;
  }

  /**
   * 秒值转化为分秒
   *
   * @param seconds 秒值
   * @return
   */
  public static String getMinuteSecondBySecond(int seconds) {
    String sec = "";
    if (seconds < 60) {
      sec = "00:" + formatString2Double(seconds);
    } else {
      sec = formatString2Double(seconds / 60) + ":" + formatString2Double(seconds % 60);
    }
    return sec;
  }

  public static String formatString2Double(int count) {
    if (count < 10) {
      return "0" + count;
    } else {
      return count + "";
    }
  }

  /**
   * 转换数字
   *
   * @param num
   * @return
   */
  public static String transNum(String num) {
    String result = "0";
    double iNum = num == null ? 0.0 : Double.parseDouble(num);

    if (iNum / 10000 >= 1) {
      result = ((int) (iNum / 10000)) + "W";
    }

    return result;
  }

  /**
   * 获取转化后的数字 大于10000 则转化为 1
   *
   * @param num
   * @return
   */
  public static String getTransNum(int num) {
    if (num < 10000) {
      return String.valueOf(num);
    } else {
      return ((int) (num / 10000)) + "万";
    }
  }

  public static String getGameSupportDiamond(int num) {
    if (num < 10000) {
      return String.valueOf(num);
    } else {
      if ((num % 10000) == 0) {
        return (num / 10000) + "万";
      } else if ((num % 1000) == 0) {
        return (num / 10000) + "." + (num % 10000) / 1000 + "万";
      } else if ((num % 100) == 0) {
        return (num / 10000) + "." + (num % 10000) / 1000 + (num % 1000) / 100 + "万";
      } else if ((num % 10) == 0) {
        return (num / 10000) + "." + (num % 10000) / 1000 + (num % 1000) / 100 + (num % 100) / 10
            + "万";
      } else {
        return (num / 10000) + "." + (num % 10000) / 1000 + (num % 1000) / 100 + (num % 100) / 10
            + (num % 10) + "万";
      }
    }
  }

  public static String getFormatTicket(int ticket) {
    return "";
  }

  /**
   * 获取友好的时间格式显示
   * 如果小于1分钟 显示秒
   * 如果大于1分钟，显示分钟
   *
   * @return
   */
  public static String getFirendlyMinuteTime(int sec) {
    if (sec < 0) {
      return new String("0");
    } else if (sec >= 0 && sec < 60) {
      return new String(sec + "秒");
    } else if (sec < 3600) {
      return sec / 60 + "分" + sec % 60 + "秒";
    } else {
      return sec / 3600 + "时" + (sec % 3600) / 60 + "分" + sec % 60 + "秒";
    }
  }

  /**
   * 判断是否为数字
   *
   * @param str
   * @return
   */
  public static boolean isNumeric(String str) {
    if (str == null)
      return false;
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }

  public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

  /**
   * 将时间字符串转为时间戳
   * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
   *
   * @param time 时间字符串
   * @return 毫秒时间戳
   */
  public static long string2Millis(String time) {
    return string2Millis(time, DEFAULT_PATTERN);
  }

  /**
   * 将时间字符串转为时间戳
   * <p>time格式为pattern</p>
   *
   * @param time    时间字符串
   * @param pattern 时间格式
   * @return 毫秒时间戳
   */
  public static long string2Millis(String time, String pattern) {
    if (TextUtils.isEmpty(time))
      return -1;
    try {
      return new SimpleDateFormat(pattern, Locale.getDefault()).parse(time).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return -1;
  }

  /**
   * 除去一个字符串中的所有标点符号 和 空格
   *
   * @param msg
   * @return
   */
  public static String getPureWord(String msg) {
    if (msg == null) {
      return null;
    }
    String words = "";
    words = msg.replaceAll("[\\p{Punct}\\p{Space}]+", "");
    return words;
  }

  // 判断一个字符是否是中文
  public static boolean isChinese(char c) {
    return c >= 0x4E00 && c <= 0x9FA5; // 根据字节码判断
  }

  // 判断一个字符串是否含有中文
  public static boolean isChinese(String str) {
    if (str == null)
      return false;
    for (char c : str.toCharArray()) {
      if (isChinese(c))
        return true; // 有一个中文字符就返回
    }
    return false;
  }

  /**
   * 将关键字替换为*号
   */
  public static SpannableStringBuilder replaceValue(
          ArrayList<String> mKeyList, SpannableStringBuilder builder) {
    String sendMsg = builder.toString();
    for (int i = 0; i < mKeyList.size(); i++) {
      if (sendMsg.contains(mKeyList.get(i))) {
        int index = sendMsg.indexOf(mKeyList.get(i));
        builder.replace(index, index + mKeyList.get(i).length(), "*");
        break;
      }
    }
    return builder;
  }

  /**
   * 根据位置设置字体样式
   * @param a
   * @param b
   */
  public static void setTextStyle(int color, SpannableString styledText, int a, int b) {
    styledText.setSpan(new ForegroundColorSpan(color), a, b,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 正常时的样式
  }

  /**
   * 查找数字，并对样式进行设置
   * @param info
   */
  public static void initInvestInfo(int color, TextView textView, String info) {
    SpannableString styledText = new SpannableString(info);
    ArrayList<String> intStr = new ArrayList<>();
    String regex = "\\d*";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(info);
    while (m.find()) {
      intStr.add(m.group().trim());
    }
    int positionArray[][] = new int[intStr.size()][2];
    int firstPos = 0;
    int lastPos = 0;
    for (int i = 0; i < intStr.size(); i++) {
      if (!intStr.get(i).trim().equals("")) {
        // 获取到数字的位置
        firstPos = info.indexOf(intStr.get(i), lastPos);
        lastPos = firstPos + intStr.get(i).length();
        positionArray[i][0] = firstPos;
        positionArray[i][1] = lastPos;
        // 让固定位置的数字显示为红色
        setTextStyle(color, styledText, firstPos, lastPos);
      }
    }
    textView.setText(styledText);
  }

  /**
   * 匹配类似"[#0#]嘎嘎嘎[#1#]哈哈哈[#2#]"
   * 匹配结果 [#0#]，[#1#]，[#2#]
   * @param content
   */
  public static String matchImgSpan(String content) {
    String msg = content;
    if (msg.contains("[") && msg.contains("#")) {
      String regex = "\\[#[0-9]{1,}#\\]";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(content);
      List<String> mPosList = new ArrayList<>();
      while (matcher.find()) {
        String result = matcher.group();
        Log.e("IMG_SPAN", result);
        String num = getNumbers(result);
        mPosList.add(num);
        Log.e("IMG_SPAN", "num" + num);
      }
      for (int i = 0; i < mPosList.size(); i++) { //替换
        String myChar = mPosList.get(i);
        msg = content.replace(
            "[#" + myChar + "#]", "<img src=http://stickers.peppertv.cn/image/pc/ico/" + myChar + ".gif");
        content = msg;
      }
    }
    return msg;
  }

  //截取数字
  private static String getNumbers(String content) {
    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(content);
    while (matcher.find()) {
      return matcher.group(0);
    }
    return "";
  }

  public static String getGameId(String h5GameRes){
    String name=h5GameRes.substring(h5GameRes.lastIndexOf("/"));
    TLog.error("name:"+name);
    String[] subStr=name.split("\\.");
    TLog.error("subStr.length:"+subStr.length);
    TLog.error("subStr.toString:"+subStr.toString());
    for (int i=0;i<subStr.length;i++) {
      TLog.error(subStr[i]);
    }
    String gameId=subStr[1];
    TLog.error("gameId:"+gameId);
    return gameId;
  }

  public static String encodeStr(String value) {
      String encode = null;
      try {
          encode = isEmpty(value) ? value : URLEncoder.encode(value, "Utf-8");
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }
    return encode;
  }

  public static String decodeStr(String value) {
      String decode = null;
      try {
          decode = isEmpty(value) ? value : URLDecoder.decode(value, "Utf-8");
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }
    return decode;
  }
}
