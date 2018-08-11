package gx.common.entity.user;

import gx.common.entity.BaseEntity;
import gx.common.enums.DetailedRegister;
import gx.common.enums.Sex;

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
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Long creationtime) {
        this.creationtime = creationtime;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getZfbaccount() {
        return zfbaccount;
    }

    public void setZfbaccount(String zfbaccount) {
        this.zfbaccount = zfbaccount == null ? null : zfbaccount.trim();
    }

    public String getWxaccount() {
        return wxaccount;
    }

    public void setWxaccount(String wxaccount) {
        this.wxaccount = wxaccount == null ? null : wxaccount.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
    }

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public DetailedRegister getDetailedRegister() {
		return detailedRegister;
	}

	public void setDetailedRegister(DetailedRegister detailedRegister) {
		this.detailedRegister = detailedRegister;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
    
}