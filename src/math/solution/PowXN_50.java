package math.solution;

import java.util.Arrays;
import java.util.List;

/**
 * @author Apollo4634
 * @create 2019/07/31
 * @problem 50
 * @tag Math
 * @tag Binary Search
 * @see math.reference.PowXN_50
 * see java.lang.FdLibm.Pow
 */

public class PowXN_50 {
    static class Solution {
        public double myPow(double x, int n) {
            return Math.pow(x, n);
        }
    }


    static class Solution2 {
        public double myPow(double x, int n) {
            if (n == 0) return 1;
            if (n == 1) return x;
            if (Double.isNaN(x)) return Double.NaN;
            if (x == 0 && n < 0) return Double.NaN;
            if (x == 0 && n > 0) return 1.0;
            return calcPow(x, n);
        }

        private double calcPow(double x, int n) {
            boolean nIsPositive = n > 0;
            boolean nIsMinInteger = (n == Integer.MIN_VALUE);
            n = nIsMinInteger? Integer.MAX_VALUE : Math.abs(n);

            double ans = calc(x, n);
            if (nIsMinInteger) ans *= x;
            if (!nIsPositive) ans = 1/ans;
            return ans;
        }

        private double calc(double x, int n) {
            if (n < 3) {
                if (n == 0) return 1;
                if (n == 1) return x;
                return x * x;
            }
            double ans = calc(x, n/2);
            if (Double.isInfinite(ans)) return ans;
            ans = (n%2==1) ? x*ans*ans : ans*ans;
            return ans;
        }
    }


    static class Solution3 {
        public double myPow(double x, int n) {
            if (n == 0) return 1;
            if (n == 1) return x;
            if (x == 0 && n < 0) return Double.NaN;
            if (x == 0 && n > 0) return 1.0;
            return calcPow(x, n);
        }

        private double calcPow(double x, int n) {
            boolean nIsPositive = n > 0;
            boolean nIsMinInteger = (n == Integer.MIN_VALUE);
            n = nIsMinInteger? Integer.MAX_VALUE : Math.abs(n);

            double[] cache = new double[29]; //2 3 ... 30
            double ans = x;
            int m = 1;
            for (; m < n; m *= 2) {
                ans *= ans;
                cache[m] = ans;
            }
            m = n - m/2;

            //计算剩余部分
            double idx = Math.log(m) / Math.log(2);
            ans *= cache[(int) idx];

//            while (m > 0) {
//                double idx = Math.log(m) / Math.log(2);
//                ans *= cache[(int) idx];
//
//            }

            if (nIsMinInteger) ans *= x;
            if (!nIsPositive) ans = 1/ans;
            return ans;
        }
    }


    public static void main(String[] args) {
        double x = 2.; // -100.0 < x < 100.0
        int n = 10;
        System.out.println("Input:  " + "x = " + x);
        System.out.println("Input:  " + "n = " + n);

        long t1 = System.nanoTime();
        double ans = new Solution3().myPow(x, n);
        long t2 = System.nanoTime();

        System.out.println("Output: " + ans);
        System.out.println("Runtime: " + (t2 - t1) / 1.0E6 + " ms");
    }
}
