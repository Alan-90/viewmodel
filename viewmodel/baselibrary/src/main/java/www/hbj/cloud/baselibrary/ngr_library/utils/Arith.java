package www.hbj.cloud.baselibrary.ngr_library.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Arith
 */
public final class Arith {
    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 2;

    // 这个类不能实例化
    private Arith() {
    }

    /**
     * 解决double运算后小数点位数过长
     * @param d double数值
     * @return 保留两位小数的金额，
     */
    public static String formatTwo(double d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(d);
    }

    /**
     * 解决double运算后小数点位数过长
     * @param d double数值
     * @return 保留两位小数的金额，
     */
    public static String formatOne(double d) {
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(d);
    }

    /**
     * 解决double运算后小数点位数过长
     * @param d double数值
     * @return 保留两位小数的金额，
     */
    public static String formatNoDecimal(double d) {
        DecimalFormat df = new DecimalFormat("#");
        return df.format(d);
    }

    /**
     * 返回float小数点保留2位
     * @param f 传入的数值
     * @return
     */
    public static String formatTwo(float f) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String resule = fnum.format(f);
        return resule;
    }

    /**
     * 判断double是否是整数
     * @param d
     * @return
     */
    public static boolean isIntegerForDouble(double d) {
        double eps = 1e-3;  // 精度范围
        return d - Math.floor(d) < eps;
    }

    /**
     * 如果传入的double是整数，返回整数，如果不是，返回小数点后两位
     * @param d double数值
     * @return 保留两位小数的金额，
     */
    public static String formatNoDecimalIfIsInteger(double d) {
        if (isIntegerForDouble(d)) {
            return formatNoDecimal(d);
        } else {
            return formatTwo(d);
        }
    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}