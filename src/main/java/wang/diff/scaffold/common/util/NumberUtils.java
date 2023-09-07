package wang.diff.scaffold.common.util;

public class NumberUtils {


    /**
     * 十进制转十六进制 兼容正负数
     * @param decNumber 十进制数字
     * @return 十六进制
     */
    public static String decToHex(int decNumber) {
//        return Integer.toHexString(dec);
        char[] arr={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] res = new char[8];
        int pos = res.length;

        while(decNumber != 0)
        {
            int tmp = decNumber & 15;
            // System.out.println(arr[tmp]);
            res[--pos] = arr[tmp];
            decNumber = decNumber >>> 4;
        }
        final StringBuilder stringBuffer = new StringBuilder();
        for (char re : res) {
            stringBuffer.append(re);
        }
        return stringBuffer.toString();
    }



}
