package wang.diff.scaffold.common.util;

public class MapUtils {

    // 地球半径 千米
    private static final double EarthRadius = 6378.137;

    /**
     * 经纬度转化为弧度
     * @param d 经度|维度
     * @return 弧度
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }


    /**
     * 计算两个坐标点之间的距离
     * @param lat1 坐标点1的纬度 24.921897,
     * @param lng1 坐标点1的经度 102.581018
     * @param lat2 坐标点2的纬度 25.030337
     * @param lng2 坐标点2的经度 102.645966
     * @return 距离 （千米）
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        distance = distance * EarthRadius;
        distance = (double) Math.round(distance * 10000) / 10000;
        return distance;
    }

    /**
     * 计算两个坐标点的距离
     * @param startPoint 开始坐标点 24.921897,102.581018 纬度,经度
     * @param endPoint 结束坐标点  25.030337,102.645966 纬度，经度
     * @return 距离 (千米)
     */
    public static double getPointDistance(String startPoint, String endPoint) {
        double lat1 = Double.parseDouble(startPoint.split(",")[0]);
        double lng1 = Double.parseDouble(startPoint.split(",")[1]);
        double lat2 = Double.parseDouble(endPoint.split(",")[0]);
        double lng2 = Double.parseDouble(endPoint.split(",")[1]);
        return getDistance(lat1, lng1, lat2, lng2);
    }

}
