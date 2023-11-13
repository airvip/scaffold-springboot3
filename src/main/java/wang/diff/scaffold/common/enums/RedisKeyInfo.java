package wang.diff.scaffold.common.enums;

public enum RedisKeyInfo {


    USER_LIST("user_list");

    private final String value;


    RedisKeyInfo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static String generateKey(RedisKeyInfo keyInfo,String key) {
        return keyInfo.getValue() + ":" + key;
    }

}
