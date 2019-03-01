package gx.common.entity.user;

import gx.common.entity.BaseEntity;
import gx.common.enums.DetailedRegister;
import gx.common.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4826146424336569943L;
	/**
	 * 创建时间
	 */
	private Long creationtime;
	/**
	 * 性别
	 */
    private Sex sex;
    /**
     * 用户状态
     */
    private String state;
    /**
     * 最后更新资料时间
     */
    private Long updatetime;
    /**
     * 用户昵称
     */
    private String username;
    /**
     * 登录名
     */
    private String loginname;
    /**
     * 登陆密码
     */
    private String password;
    /**
     * 支付宝收款账号
     */
    private String zfbaccount;
    /**
     * 微信收款账号
     */
    private String wxaccount;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 电话号码
     */
    private Long phonenumber;
    /**
     * 个人专长
     */
    private String skill;
    /**
     * 最后登陆时间
     */
    private Long loginTime;
    /**
     * 是否完整注册
     */
    private DetailedRegister detailedRegister;
    /**
     * 用户头像
     */
    private String portrait;

}