package com.yash.assignment.array;

/**
 * WAP to find the LCM and HCF of array numbers.
 * @author dheerendra.kag
 *
 */
public class LCMandHCFTask7 {

	public static void main(String[] args) {
        int a[]={63,105,210};
        int lcm=1,fir=lcm,res=0;
        for(int i=0;i<a.length;i++)
        {
            int sec=a[i];
            lcm=(fir*sec)/hcf(fir,sec);
            fir=lcm;
        }
        for(int j=0;j<a.length;j++)
        {
            res=hcf(res,a[j]);
        }
        System.out.println("lcm is "+lcm+" "+"hcf is "+res);
    }
    
    public static int hcf(int a,int b)
    {
        if(b==0)
        {
            return a;
        }
        return hcf(b,a%b);
    }
}
