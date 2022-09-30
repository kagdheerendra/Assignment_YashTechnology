package com.yash.assignment.array;

/**
 * An Array contain the n numbers you have to find out combination which satisfy Pythagoras 
   Template. ( Pythagoras templates:- 3*3+4*4==5*5)
 * @author dheerendra.kag
 *
 */
public class PythagorasTemplateTask4 {
   public static void main(String args[]) {
	   int ar[] = { 3, 1, 4, 6, 5 };
	   int n = ar.length;
       for (int i = 0; i < n; i++) {
           for (int j = i + 1; j < n; j++) {
               for (int k = j + 1; k < n; k++) {
                   int x = ar[i] * ar[i], y = ar[j] * ar[j], z = ar[k] * ar[k];
                   if (x + y == z) {
                	  System.out.println(ar[i]+"*"+ar[i]+"+"+ar[j]+"*"+ar[j]+"=="+ar[k]+"*"+ar[k]);
                   }else if(y + z == x) {
                	  System.out.println(ar[j]+"*"+ar[j]+"+"+ar[k]+"*"+ar[k]+"=="+ar[i]+"*"+ar[i]);
                   }else if(z + x == y) {
                 	  System.out.println(ar[k]+"*"+ar[k]+"+"+ar[i]+"*"+ar[i]+"=="+ar[j]+"*"+ar[j]);
                   }
               }
           }
       }
   }
}
