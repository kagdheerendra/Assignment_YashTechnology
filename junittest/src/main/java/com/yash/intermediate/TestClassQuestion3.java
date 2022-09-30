package com.yash.intermediate;

import java.util.Arrays;

public class TestClassQuestion3 {
    public String evaluateActionFromUrl(String s) {
    	String str[] = s.split("/");
    	String result = "";
    	for(int i=0; i<str.length; i++) {
    		if(str[i].contains(".jsp")) {
    			int index = str[i].indexOf(".jsp");
    			return str[i].substring(0, index);
    		}if(str[i].contains(".xhtml")) {
    			int index = str[i].indexOf(".xhtml");
    			return str[i].substring(0, index);
    		}if(str[i].contains(".html")) {
    			int index = str[i].indexOf(".html");
    			return str[i].substring(0, index);
    		}
    	}

    	return null;
    }
}
