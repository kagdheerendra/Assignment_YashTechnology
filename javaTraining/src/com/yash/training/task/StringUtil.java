package com.yash.training.task;

import java.util.Arrays;

public final class StringUtil {
	private static String value;
	public StringUtil(String value) {
		this.value = value;
	}

	public static int length() {
		int count =0;
		char ch[] = StringUtil.value.toCharArray();
		for(@SuppressWarnings("unused") char c : ch) {
			count++;
		}
		return count;
	}
	
	public static String toLowerCase() {
		char ch[] = StringUtil.value.toCharArray();
		for(int i=0; i<ch.length; i++) {
			if(ch[i] >= 65 && ch[i] <= 90) {
				ch[i] = (char)( (ch[i] + 32) ); 
			}
		}
		return String.valueOf(ch);
	}
}
