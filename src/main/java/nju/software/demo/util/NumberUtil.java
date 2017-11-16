package nju.software.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 数值相关的工具类
 */
public class NumberUtil {

	/**
	 * 数值保留两位小数
	 */
	public static String changeNumberType(BigDecimal big) {

		big.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("##0.00");
		return format.format(big);
	}

	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是浮点数
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是数字
	 */
	public static boolean isNumber(String value) {
		if(StringUtil.isEmpty(value)){
			return false;
		}
		return isInteger(value) || isDouble(value);
	}
}
