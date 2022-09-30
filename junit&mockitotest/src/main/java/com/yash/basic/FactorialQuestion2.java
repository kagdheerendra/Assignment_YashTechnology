package com.yash.basic;

public class FactorialQuestion2 {
	public long factorial(int num) {
        long factorial = 1;
        for(int i = 1; i <= num; ++i)
        {
            factorial *= i;
        }
        return factorial;
    }
}
