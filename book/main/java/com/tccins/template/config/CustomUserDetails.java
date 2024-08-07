package com.tccins.template.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomUserDetails implements UserDetails{
    /**
	 * 
	 */
	private static final long serialVersionUID = 809890801483033862L;
	private String userPk;
    private String userId;
    private String userNm;
    private String userPassword;
    private String deptCd;
    private String deptNm;
    private String useYn;
    private int    failCount;
    private int    loginFailCount;
    private String lockTime;
    private int    unLockTime;
    private String isLock;
    private String loginFailLockTime;
    private String authNm;
    private String regDate;
    
    private ArrayList<GrantedAuthority> authorities;
    private boolean enabled;
    
	@Override
	public String getPassword() {
		return userPassword;
	}

	@Override
	public String getUsername() {
		return userId;
	}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(authorities);
        return authList;
    }

}
