package com.yash.training.task.basics;

public class Demo {

	public static void main(String[] args) {
		  int num = 27;
	      int i, j, chk;
	      for(i=2; i<=100; i++)
	      {
	         chk = 0;
	         for(j=2; j<i; j++)
	         {
	            if(i%j==0)
	            {
	               chk++;
	               break;
	            }
	            
	         }
	         if(chk==0)
	            System.out.println(i);
	      }
    }

}
