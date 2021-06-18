package com.servicesilo.one.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;
import java.util.List;

/**
 * @ClassName StringUtil
 * @Description: 字符对象工具类（字符判断、字符十六进制转换、字符验证、字符转utf-8编码格式、首字母大小写修改）
 * @Author John
 * @Date 2020/06/01
 * @Version V2.0.0
 **/
public class StringUtil {

	/**
	 * @param string  字符串
	 * @param another 另一个字符串
	 * @Method equals
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断两个字符串是否相等，包括为null的情况。
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:27
	 */
	public static boolean equals(String string, String another) {
		if (string != null) {
			return string.equals(another);
		} else if (another != null) {
			return another.equals(string);
		}
		return true;
	}

	/**
	 * @param string  字符串
	 * @param another 另一个字符串
	 * @Method equalsIgnoreCase
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断两个字符串忽略大小写后是否相等，包括为null的情况。
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:28
	 */
	public static boolean equalsIgnoreCase(String string, String another) {
		if (string != null) {
			return string.equalsIgnoreCase(another);
		} else if (another != null) {
			return another.equalsIgnoreCase(string);
		}
		return true;
	}

	/**
	 * @param strToFind 要查找的字符串
	 * @param strings   字符串数组
	 * @Method contains
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断一个字符串是否包含在一个字符串数组中，包括为null的情况。
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:28
	 */
	public static boolean contains(String strToFind, String... strings) {
		for (String s : strings) {
			if (equals(s, strToFind)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param strToFind 要查找的字符串
	 * @param strings   字符串数组
	 * @Method containsIgnoreCase
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断一个字符串是否包含在一个字符串数组中，忽略大小写，包括为null的情况。
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:29
	 */
	public static boolean containsIgnoreCase(String strToFind, String... strings) {
		for (String s : strings) {
			if (equalsIgnoreCase(s, strToFind)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param s 字符串
	 * @Method isEmpty
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断字符串是否为null或去空格后长度为0
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:30
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * @param s 字符串
	 * @Method isEmpty
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断字符串数组是否为null或去空格后长度为0
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:30
	 */
	public static boolean isEmpty(String[] s) {
		return s == null || s.length == 0;
	}

	/**
	 * @param ss 字符串数组
	 * @Method isEmptyAny
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断至少一个字符串是否为null或去空格后长度为0
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:31
	 */
	public static boolean isEmptyAny(String... ss) {
		for (String s : ss) {
			if (isEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param ss 字符串数组
	 * @Method isEmptyAll
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断所有字符串是否为null或去空格后长度为0
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:32
	 */
	public static boolean isEmptyAll(String... ss) {
		for (String s : ss) {
			if (isNotEmpty(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param s 字符串
	 * @Method isNotEmpty
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断字符串是否不为null或去空格后长度为0
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:32
	 */
	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	/**
	 * @param ss 字符串数组
	 * @Method isNotEmptyAny
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断至少一个字符串是否不为null或去空格后长度为0
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:33
	 */
	public static boolean isNotEmptyAny(String... ss) {
		for (String s : ss) {
			if (isNotEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param ss 字符串数组
	 * @Method isNotEmptyAll
	 * @Author John
	 * @Version 2.0.0
	 * @Description 判断所有字符串是否不为null或去空格后长度为0
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:37
	 */
	public static boolean isNotEmptyAll(String... ss) {
		for (String s : ss) {
			if (isEmpty(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param s       字符串
	 * @param charset 字符集
	 * @Method getBytes
	 * @Author John
	 * @Version 2.0.0
	 * @Description 获得指定字符集的字节数组
	 * @Return
	 * @Exception UnsupportedEncodingException
	 * @Date 2020/07/16 上午 9:39
	 */
	public static byte[] getBytes(String s, String charset) {
		try {
			return s.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @param bytes   字节数组
	 * @param charset 字符集
	 * @Method getString
	 * @Author John
	 * @Version 2.0.0
	 * @Description 根据指定字符集将字节数组转换为字符串
	 * @Return
	 * @Exception UnsupportedEncodingException
	 * @Date 2020/07/16 上午 9:40
	 */
	public static String getString(byte[] bytes, String charset) {
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @param hexString 16进制协议头字符串
	 * @Method hexStringToByteArray
	 * @Author John
	 * @Version 2.0.0
	 * @Description 将16进制字符串转换为字节数组
	 * @Return
	 * @Exception IllegalArgumentException
	 * @Date 2020/07/16 上午 9:40
	 */
	public static byte[] hexStringToByteArray(String hexString) {
		if (hexString.length() % 2 != 0) {
			throw new IllegalArgumentException("16进制数据长度不为2的倍数：" + hexString);
		}
		StringReader stringReader = new StringReader(hexString);
		byte[] bytes = new byte[hexString.length() / 2];
		char[] chars = new char[2];
		try {
			for (int i = 0; stringReader.read(chars) != -1; i++) {
				bytes[i] = (byte) Integer.parseInt(String.valueOf(chars), 16);
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return bytes;
	}

	/**
	 * @param string  字符串
	 * @param charset 字符集
	 * @Method stringToHexString
	 * @Author John
	 * @Version 2.0.0
	 * @Description 字符串转换为16进制字符串
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:41
	 */
	public static String stringToHexString(String string, String charset) {
		byte[] bytes = getBytes(string, charset);
		return byteArrayToHexString(bytes);
	}

	/**
	 * @param bytes 字节数组
	 * @Method byteArrayToHexString
	 * @Author John
	 * @Version 2.0.0
	 * @Description 字节数组转为16进制字符串
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:44
	 */
	public static String byteArrayToHexString(byte[] bytes) {
		Formatter fmt = new Formatter(new StringBuilder(bytes.length * 2));
		for (byte b : bytes) {
			fmt.format("%02x", b);
		}
		return fmt.toString();
	}

	/**
	 * @param bytes 字节数组
	 * @Method byteArrayToHexZeroString
	 * @Author John
	 * @Version 2.0.0
	 * @Description 字节数组转为16进制字符串(0不转换)
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:20
	 */
	public static String byteArrayToHexZeroString(byte[] bytes) {
		Formatter fmt = new Formatter(new StringBuilder(bytes.length * 2));
		for (byte b : bytes) {
			if (b != 0) {
				fmt.format("%02x", b);
			}
		}
		return fmt.toString();
	}

	/**
	 * @param s 字符串
	 * @Method isDigit
	 * @Author John
	 * @Version 2.0.0
	 * @Description 是否是数字格式的字符串
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 9:47
	 */
	public static boolean isDigit(String s) {
		return s.matches("^\\d+$");
	}

	/**
	 * @param s 字符串
	 * @Method isAlpha
	 * @Author John
	 * @Version 2.0.0
	 * @Description 是否是26个大小写英文字符组成的字符串
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:13
	 */
	public static boolean isAlpha(String s) {
		return s.matches("^[a-zA-Z]+$");
	}

	/**
	 * @param s 字符串
	 * @Method isUpper
	 * @Author John
	 * @Version 2.0.0
	 * @Description 是否只含有大写英文字符
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:13
	 */
	public static boolean isUpper(String s) {
		return s.matches("^[A-Z]+$");
	}

	/**
	 * @param s 字符串
	 * @Method isLower
	 * @Author John
	 * @Version 2.0.0
	 * @Description 是否只含有小写英文字符
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:14
	 */
	public static boolean isLower(String s) {
		return s.matches("^[a-z]+$");
	}

	/**
	 * @param s 字符串
	 * @Method isAlnum
	 * @Author John
	 * @Version 2.0.0
	 * @Description 是否只含有26个大小写英文字符和数字字符的字符串
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:14
	 */
	public static boolean isAlnum(String s) {
		return s.matches("^[a-zA-Z\\d]+$");
	}

	/**
	 * @Method isInt
	 * @Author John
	 * @Version 2.0.0
	 * @Description 是否是整数
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:15
	 */
	public static boolean isInt(String s) {
		return s.matches("^[+-]?\\d+$");
	}

	/**
	 * @param s 字符串
	 * @Method isFloat
	 * @Author John
	 * @Version 2.0.0
	 * @Description 是否是浮点数
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:17
	 */
	public static boolean isFloat(String s) {
		return s.matches("^[+-]?(0\\.\\d+|0|[1-9]\\d*(\\.\\d+)?)$");
	}

	/**
	 * @param s 字符串
	 * @Method isEmail
	 * @Author John
	 * @Version 2.0.0
	 * @Description 是否是邮件地址格式
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:17
	 */
	public static boolean isEmail(String s) {
		return s.matches("^[a-zA-Z0-9._-]+@([a-zA-Z0-9_-])+(\\.[a-zA-Z0-9_-]+)+$");
	}

	/**
	 * @param s 字符串
	 * @Method isIP
	 * @Author John
	 * @Version 2.0.0
	 * @Description 是否是IP地址格式（用点"."分割的四组数字中，第一组数字范围1-223，其他三组数字范围0-255）
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:18
	 */
	public static boolean isIP(String s) {
		return s.matches("^(0?0?[1-9]|0?[1-9]\\d|1\\d\\d|2[01]\\d|22[0-3])(\\.([01]?\\d?\\d|2[0-4]\\d|25[0-5])){3}$");
	}

	/**
	 * @param str 字符串
	 * @Method unCapitalize
	 * @Author John
	 * @Version 2.0.0
	 * @Description 首字母改大写写（原首字母大写、空对象、空字符、非字母开头 不发生改变）
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:27
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * @param str 字符串
	 * @Method unCapitalize
	 * @Author John
	 * @Version 2.0.0
	 * @Description 首字母改小写（原首字母小写、空对象、空字符、非字母开头 不发生改变）
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:27
	 */
	public static String unCapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	/**
	 * @param str        字符串
	 * @param capitalize 首字母是否修改大写（true 大写；false 小写）
	 * @Method unCapitalize
	 * @Author John
	 * @Version 2.0.0
	 * @Description 首字母修改（原首字母符合要求、空对象、空字符、非字母开头 不发生改变）
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:27
	 */
	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		if (capitalize) {
			sb.append(Character.toUpperCase(str.charAt(0)));
		} else {
			sb.append(Character.toLowerCase(str.charAt(0)));
		}
		sb.append(str.substring(1));
		return sb.toString();
	}

	/**
	 * @param param 请求参数
	 * @Method String2UTF8
	 * @Author John
	 * @Version 2.0.0
	 * @Description 转换为utf-8编码格式
	 * @Return
	 * @Exception UnsupportedEncodingException
	 * @Date 2020/07/16 上午 10:34
	 */
	public static String String2UTF8(String param) throws UnsupportedEncodingException {
		byte[] bs = param.getBytes();
		return new String(bs, "UTF-8");
	}

	/**
	 * @param strings 被拼接字符
	 * @Method getStringByList
	 * @Author John
	 * @Version 2.0.0
	 * @Description 英文逗号拼接字符
	 * @Return
	 * @Exception
	 * @Date 2020/07/16 上午 10:40
	 */
	public static String getStringByList(List<String> strings) {
		StringBuilder ids1 = new StringBuilder();
		for (String s : strings
		) {
			ids1.append("," + s);
		}
		String idStr = ids1.toString();
		if (StringUtil.isEmpty(idStr)) {
			return "";
		} else {
			return idStr.substring(1);
		}
	}

	public static void main(String[] args) {
//		LOG.info(isInt("2"));
//		LOG.info(isEmail("luzhen@163.com"));
		System.out.println(changeFirstCharacterCase("我wjywgg_RERTYYH", true));
	}

}
