package wang.diff.scaffold.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author airvip
 * @since 2023-04-24
 */
@Getter
@Setter
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * pk
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 电话
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 出生日期
     */
    @TableField("birthday")
    private Date birthday;

    /**
     * 性别 0未知 1男 2女
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 余额
     */
    @TableField("balance")
    private BigDecimal balance;

    /**
     * 1启用 0禁用
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    public static final String ID = "id";

    public static final String MOBILE = "mobile";

    public static final String USER_NAME = "user_name";

    public static final String PASSWORD = "password";

    public static final String BIRTHDAY = "birthday";

    public static final String SEX = "sex";

    public static final String BALANCE = "balance";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";
}
