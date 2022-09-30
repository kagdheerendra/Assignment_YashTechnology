package com.yash.assignment.array;

import java.util.Arrays;

public class VowelAndConsonantTask8 {

	public static void main(String[] args) {
         String name = "yashtechnology";
         char ch[] = name.toCharArray();
         char vowel[] = new char[name.length()];
         char consonant[] = new char[name.length()];
         int k=0;
         for(int i=0; i<ch.length; i++) {
        	 if(ch[i] == 'a' || ch[i] == 'e' || ch[i] == 'i' || ch[i] == 'o' || ch[i] == 'u' ||
        			 ch[i] == 'A' || ch[i] == 'E' || ch[i] == 'I' || ch[i] == 'O' || ch[i] == 'U') {
        		 vowel[k] = ch[i];
        		 k++;
        	 }
         }
         System.out.println(Arrays.toString(vowel));
         System.out.println(k);
         for(int i=0; i<ch.length; i++) {
        	 if(ch[i] != 'a' || ch[i] != 'e' || ch[i] != 'i' || ch[i] != 'o' || ch[i] != 'u' ||
        			 ch[i] != 'A' || ch[i] != 'E' || ch[i] != 'I' || ch[i] != 'O' || ch[i] != 'U') {
        		 vowel[k] = ch[i];
        		 k++;
        	 }
         }
         System.out.println(Arrays.toString(vowel));
         
    }

}
