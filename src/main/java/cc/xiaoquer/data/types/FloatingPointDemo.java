package cc.xiaoquer.data.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Nicholas on 2018/8/17.
 */
public class FloatingPointDemo {
    public static void main(String[] args) {
        method1();
        method2();
        method3();
        method4();
    }

    public static void method4() {

//        int i = 1E400;
//        long l = Long.MAX_VALUE;
//        System.out.println(l+1);    //溢出负值

        // Float的MAX_VALUE是浮点类型可表示的最大值，
        // 而MIN_VALUE却不是最小负值，而是最接近0或者说最小精度的正数。体会一下
        Float f_max_1 = Float.MAX_VALUE;

        float  f_max_2 = Float.MAX_VALUE + 1;
        double f_max_3 = Float.MAX_VALUE + 1.0;
//      float  f_max_error = Float.MAX_VALUE + 1.0; //直接编译错误Incompatible types, why?
        double f_max_4 = Float.MAX_VALUE + 1.0 * Math.pow(10, 30);

        System.out.println("MAX  科学表示: " + f_max_1); //MAX_VALUE
        System.out.println("+1   科学表示: " + f_max_2); //same
        System.out.println("+1.0 科学表示: " + f_max_3); //not same
        System.out.println("+E30 科学表示: " + f_max_4); //not same
        System.out.println("");
        System.out.println("MAX  十进制: " + new BigDecimal(f_max_1).toString()); //MAX_VALUE
        System.out.println("+1   十进制: " + new BigDecimal(f_max_2).toString()); //same
        System.out.println("+1.0 十进制: " + new BigDecimal(f_max_3).toString()); //same
        System.out.println("+E30 十进制: " + new BigDecimal(f_max_4).toString()); //not same
        System.out.println("(+E30) - (+1.0) 十进制浮点数差值: " + new BigDecimal(f_max_4).subtract(new BigDecimal(f_max_3)).toString()); //not same
        System.out.println("");
        System.out.println("MAX  二进制: " + Long.toBinaryString(Double.doubleToLongBits(f_max_1))); //MAX_VALUE
        System.out.println("+1   二进制: " + Long.toBinaryString(Double.doubleToLongBits(f_max_2))); //same
        System.out.println("+1.0 二进制: " + Long.toBinaryString(Double.doubleToLongBits(f_max_3))); //same
        System.out.println("+E30 二进制: " + Long.toBinaryString(Double.doubleToLongBits(f_max_4))); //not same
        System.out.println("");

        //二进制+1bit
        float  f_max_infinity = Float.intBitsToFloat(Float.floatToIntBits(f_max_1) + 1);
        System.out.println(f_max_infinity); //输出正无穷 Infinity


        float f_min = Float.MIN_VALUE; //JAVADOC: A constant holding the smallest positive nonzero value of type
        System.out.println("MIN_VALUE - 1.0 : " + (f_min - 1.0)); //no overflow
        System.out.println("MIN_VALUE - 1bit: " + (Float.intBitsToFloat(Float.floatToIntBits(f_min) - 1))); //0.0

        System.out.println("1.1 ULP:" + new BigDecimal(Math.ulp(1.1)).toString());
        System.out.println("1.1 ULP StrictMath:" + new BigDecimal(StrictMath.ulp(1.1)).toString());

    }

    public static void method3() {
        // binary floating point arithmetic
        double a = 1.09 * 50;
        System.out.println("1.09 * 50 = " + a);
        System.out.println("1.09 * 50 = " + new BigDecimal(a));
        System.out.println("rounds to   " + Math.round(a));

        double b = 1.14 * 75;
        System.out.println("1.14 * 75 = " + new BigDecimal(b));
        System.out.println("rounds to   " + Math.round(b));

        double c = 1.05 * 0.70;
        System.out.println("1.05 * 0.70 = " + new BigDecimal(c));
        System.out.println("rounds to   " + Math.round(c));

        // exact arithmetic
        BigDecimal a1 = new BigDecimal("1.09");
        BigDecimal a2 = new BigDecimal("50");
        BigDecimal a3 = a1.multiply(a2);
        System.out.println(a3 + " " + a3.setScale(0, RoundingMode.HALF_EVEN));

        BigDecimal b1 = new BigDecimal("1.14");
        BigDecimal b2 = new BigDecimal("75");
        BigDecimal b3 = b1.multiply(b2);
        System.out.println(b3 + " " + b3.setScale(0, RoundingMode.HALF_EVEN));

//        https://blog.csdn.net/u010843421/article/details/78019794
        BigDecimal aDouble =new BigDecimal(1.22);
        System.out.println("construct with a double value: " + aDouble);
        BigDecimal aString = new BigDecimal("1.22");
        System.out.println("construct with a String value: " + aString);

    }

    public static void method2() {
        boolean b;
        double d, d1, d2, d3;
        float f;

        // IEEE 754 bit representation of -7.25
        d = -7.25;
        System.out.print("IEEE 754 representation of -7.25  = ");
        System.out.println(Long.toBinaryString(Double.doubleToLongBits(d)));

        // Double.doubleToLongBits() doesn't print the leading 0s
        d = .00625;
        System.out.print("IEEE 754 representation of .00625 = ");
        System.out.println(Long.toBinaryString(Double.doubleToLongBits(d)));


        // The values 0.9200000000000001 and 0.9200000000000002 are not representable
        System.out.println("0.9200000000000001 = " + new BigDecimal(0.9200000000000001));
        System.out.println("0.9200000000000002 = " + new BigDecimal(0.9200000000000002));
        b = (0.9200000000000001 == 0.9200000000000002);
        System.out.println("0.9200000000000001 == 0.9200000000000002: " + b);


        // 0.1 + 0.2 doesn't equal 0.3
        System.out.println("0.1       = " + 0.1);
        System.out.println("0.1       = " + new BigDecimal(0.1));
        System.out.println("0.2       = " + new BigDecimal(0.2));
        System.out.println("0.3       = " + new BigDecimal(0.3));
        System.out.println("0.4       = " + new BigDecimal(0.4));
        System.out.println("0.5       = " + new BigDecimal(0.5));
        System.out.println("0.1 + 0.2 = " + new BigDecimal(0.1 + 0.2));
        System.out.println("0.1 + 0.3 = " + new BigDecimal(0.1 + 0.3));
        b = (0.1 + 0.2 == 0.3);
        System.out.println("0.1 + 0.2 == 0.3: " + b);

        // 0.1 + 0.3 equals 0.4
        b = (0.1 + 0.3 == 0.4);
        System.out.println("0.1 + 0.3 == 0.4: " + b);

        // 0.1 + 0.1 + 0.1 = 0.30000000000000004
        d1 = 0.1 + 0.1 + 0.1;
        System.out.println("0.1 + 0.1 + 0.1 = " + d1);

        // 0.3 is not representable, but doesn't equal 0.1 + 0.1 + 0.1
        d2 = 0.3;
        if (d1 == d2) System.out.println("0.1 + 0.1 + 0.1 == 0.3");
        else          System.out.println("0.1 + 0.1 + 0.1 != 0.3");

        // 0.1 is not representable, but 0.1 + 0.1 + 0.1 + 0.1 + 0.1 does equal 0.5
        b = (0.1 + 0.1 + 0.1 + 0.1 + 0.1 == 0.5);
        System.out.println("0.1 + 0.1 + 0.1 + 0.1 + 0.1 == 0.5: " + b);

        // 3 * 0.1 doesn't equal 0.3
        b = (3 * 0.1 == 0.3);
        System.out.println("3 * 0.1 == 0.3: " + b);

        // 1138/1000.0 doesn't equal 0.001*1138
        b = ((1138/1000.0) == (0.001*1138));
        System.out.println("1138/1000.0 == 0.001*1138: " + b);

        // sqrt(x) * sqrt(x) does not equal |x|
        b = (Math.sqrt(2.0) * Math.sqrt(2.0) == 2.0);
        System.out.println("Math.sqrt(2) * Math.sqrt(2) == 2: " + b);

        // 3.0 / 7.0 is represented differently in float and double
        f = (float) (3.0 / 7.0);
        d = 3.0 / 7.0;
        b = (f == d);
        System.out.println("3.0 / 7.0 == (float) (3.0 / 7.0): " + b);

        // floating point addition is not associative
        d1 =  1.0E50;
        d2 = -1.0E50;
        d3 = 17.0E00;
        b = ((d1 + d2) + d3) == (d1 + (d2 + d3));
        System.out.println("((1.0E50 + -1.0E50) + 17) == (1.0E50 + (-1.0E50 + 17)): " + b);

        // floating point multiplication and division are not associative
        b = ((2.0 * 0.1) / 3.0) == (2.0 * (0.1 / 3.0));
        System.out.println("((2.0 * 0.1) / 3.0) == (2.0 * (0.1 / 3.0)): " + b);

        // sin^2(theta) + cos^2(theta) != 1  (though the identity holds for many values of theta)
        double theta = 0.53454545535453;   // 0.53454545535453001914305559694184921681880950927734375
        b = (Math.sin(theta) * Math.sin(theta) + Math.cos(theta) * Math.cos(theta)) == 1.0;
        System.out.println("sin^2(" + theta + ") + cos^2(" + theta + ") == 1.0: " + b);

        // special values
        System.out.println("Double.POSITIVE_INFINITY = " + Double.POSITIVE_INFINITY);
        System.out.println("Double.NEGATIVE_INFINITY = " + Double.NEGATIVE_INFINITY);
        System.out.println("Double.NaN               = " + Double.NaN);

        // getting Infinity and -Infinity
        System.out.println(" 1.0/0.0           = " + (1.0/0.0));
        System.out.println("-1.0/0.0           = " + (-1.0/0.0));
        System.out.println("Math.sqrt(1.0/0.0) = " + (Math.sqrt(1.0)/0.0));
        System.out.println("1E200 * 1E200  = " + ( 1E200 * 1E200));
        System.out.println("-1E200 * 1E200 = " + (-1E200 * 1E200));

        // getting NaN = not a number
        System.out.println("0.0 / 0.0         = " + (0.0 / 0.0));
        System.out.println("Math.sqrt(-3.0)   = " + (Math.sqrt(-3.0)));
        System.out.println("1.0/0.0 - 1.0/0.0 = " + (1.0/0.0 - 1.0/0.0));
        System.out.println("0.0 * 1.0/0.0     = " + (0.0/0.0 * 1.0/0.0));
        System.out.println("1.0 % 0.0         = " + (1.0 % 0.0));
        System.out.println("(1.0/0.0) % 1.0   = " + ((1.0/0.0) % 1.0));

        // getting 0 and -0
        System.out.println("1E-200 / 1E200 = " + (1E-200 / 1E200));
        System.out.println("-1E-200 / 1E200 = " + (-1E-200 / 1E200));

        // machine precision for double = 2^(-53) + 2^(-105) = 1.11022302462515678694266454965700950366517665087069677287701097156968899071216583251953125E-16

        // 2^-53
        d = 1.0 / (1L << 53);
        b = (1.0 == (1.0 + d));
        System.out.println("1 + 2^-53 == 1: " + b);

        // 2^-53 + 2^-105
        d1 = d + (2*d)*d;
        b = (1.0 == (1.0 + d1));
        System.out.println("1 + 2^-53 + 2^-105 == 1: " + b);
        System.out.println("Machine precision for double = 2^(-53) + 2^(-105) = " + d1);

        // 2^-53 + 2^-106 == 2^-53
        d2 = d + d*d;
        b = (d == d2);
        System.out.println("2^-53 + 2^-106 == 2^-53: " + b);

        // smallest positive integer not representable as a double = 2^53 + 1
        d1 = 1L << 53;
        d2 = d1 + 1;
        System.out.println("2^53     = " + (long) d1);
        System.out.println("2^53 + 1 = " + (long) d2);

        // 10^-21 is not representable; result of Math.pow is within 1 ulp of true answer
        System.out.println("1E-21             = " + 1.0E-21);
        System.out.println("Math.pow(10, -21) = " + Math.pow(10, -21));

        // Math.cos(pi/2) doesn't exactly equal zero
        System.out.println("Math.cos(Math.PI/2) = " + Math.cos(Math.PI/2));

    }
    public static void method1() {
        // 知识点：浮点数的 二进制 和 无穷
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println("0x7F800000的二进制串 : " + Integer.toBinaryString(0x7F800000));
        System.out.println("Integer Max的二进制串 : " + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("0x7F800000的十进制整型 : " + Integer.parseInt("7F800000", 16));
        System.out.println("Integer Max的十进制整型 : " + Integer.MAX_VALUE);


        System.out.println("Float.Min : " + Float.MIN_VALUE);
        System.out.println("Float.Max : " + Float.MAX_VALUE);
        System.out.println("Float.Max plain: " + new BigDecimal(Float.MAX_VALUE).toPlainString());
        System.out.println("Math.pow  plain: " + new BigDecimal(3.4028235*Math.pow(10, 38)).toPlainString());
        System.out.println("Math.pow  plain: " + new BigDecimal(3.4028235*Math.pow(Math.E, 38)).toPlainString());
        System.out.println("");

        System.out.println("Float.intBitsToFloat(0x7F800000) : "
                + Float.intBitsToFloat(0x7F800000));
        System.out.println("Float.intBitsToFloat(Integer.MAX_VALUE) : "
                + Float.intBitsToFloat(Integer.MAX_VALUE));

        System.out.println("0x7F800000 > Integer.MAX_VALUE : "
                + (0x7F800000 > Integer.MAX_VALUE));
        System.out.println("Float.intBitsToFloat(0x7F800000) > Integer.MAX_VALUE : "
                + (Float.intBitsToFloat(0x7F800000) > Integer.MAX_VALUE));

        System.out.println("Float.intBitsToFloat(0x7F800001) : " + Float.intBitsToFloat(0x7F800001));
        System.out.println("Float 0x7F800000 - 1 : " + (Float.intBitsToFloat(0x7F800000 - 1)));
        System.out.println("Float POSITIVE_INFINITY - 1 : " + (Float.POSITIVE_INFINITY - 1));

        System.out.println("");
        System.out.println("");

        System.out.println("0.5的二进制" + Integer.toBinaryString(Float.floatToIntBits(0.5f)));
        System.out.println("-0.5的二进制" + Integer.toBinaryString(Float.floatToIntBits(-0.5f)));
        System.out.println("0.75的二进制" + Integer.toBinaryString(Float.floatToIntBits(0.75f)));
        System.out.println("-0.75的二进制" + Integer.toBinaryString(Float.floatToIntBits(-0.75f)));

        System.out.println("打印浮点数有效位数0.2: " + (1.2f + 1.0f));

        // 知识点：不同编程语言对 Float 的处理是否不同?
        // 大家可以在线试一下不同语言的下方案例 https://ideone.com
        // 结论是 Float 的处理与语言无关
        // http://itreallymatters.net/post/386327451/floating-point-arithmetics-in-19-programming#.W5sFkC3tasp
        float a = 1.2f;
        float b = 1.0f;
        System.out.println(a - b);          //0.20000005
        System.out.println(a * 10 - b * 10);//2.0

        // 知识点：非规格化浮点数
        // https://stackoverflow.com/questions/9314534/why-does-changing-0-1f-to-0-slow-down-performance-by-10x
        // 中文文章：http://cenalulu.github.io/linux/about-denormalized-float-number/
        int N = 10000000;
        long startTime = System.currentTimeMillis();
        float x = 1.1f;
        float z = 1.123f;
        float y = x;
        for(int j = 0; j < N; j++) {
            y *= x;
            y /= z;
            y += 0.1f;
            y -= 0.1f;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时1: " + (endTime - startTime));    // 958 ms

        startTime = System.currentTimeMillis();
        x = 1.1f;
        z = 1.123f;
        y = x;
        for(int j = 0; j < N; j++) {
            y *= x;
            y /= z;
            y += 0; //这里用 0.0f 没有区别
            y -= 0;
        }
        endTime = System.currentTimeMillis();
        System.out.println("耗时2: " + (endTime - startTime));    // 11706 ms


        startTime = System.currentTimeMillis();
        for(int j = 0; j < N; j++) {
            float f = 0.1f + 0.2f;
        }
        endTime = System.currentTimeMillis();
        System.out.println("float耗时: " + (endTime - startTime));    // 10 ms

        startTime = System.currentTimeMillis();
        for(int j = 0; j < N; j++) {
            BigDecimal f = new BigDecimal(0.1).add(new BigDecimal(0.2));
        }
        endTime = System.currentTimeMillis();
        System.out.println("bigdecimal耗时: " + (endTime - startTime));    // 82511 ms

        startTime = System.currentTimeMillis();
        for(int j = 0; j < N; j++) {
            int f = 1 + 2;
        }
        endTime = System.currentTimeMillis();
        System.out.println("int 耗时: " + (endTime - startTime));    // 19 ms

        startTime = System.currentTimeMillis();
        for(int j = 0; j < N; j++) {
            BigDecimal f = new BigDecimal(1).add(new BigDecimal(2));
        }
        endTime = System.currentTimeMillis();
        System.out.println("bigdecimal耗时: " + (endTime - startTime));    // 26 ms

    }


}