package com.qa.opencart.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {
	
	public static String randomString() {
		String generatedStr = RandomStringUtils.randomAlphabetic(5);
		return generatedStr;
	}
	
	public static String randomNumeric() {
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}
	
	public static String randomAlphaNumeric() {
		String str = RandomStringUtils.randomAlphabetic(3);
		String num = RandomStringUtils.randomNumeric(3);
		
		return str+"@"+num;
	}

}
