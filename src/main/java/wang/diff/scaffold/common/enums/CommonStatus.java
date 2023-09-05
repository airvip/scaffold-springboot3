package wang.diff.scaffold.common.enums;

public enum CommonStatus {
    NORMAL(1, "正常"),
    DELETE(0, "禁用"),
    NONE(999, "未知");

    private final Integer code;
    private final String value;

    CommonStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static CommonStatus getEnum(int code) {
        for (CommonStatus status : CommonStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return NONE;
    }
}
