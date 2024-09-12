package project.books.sys.config.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ConnectMember implements UserDetails{
	private static final long serialVersionUID = 809890801483033862L;
	
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String email;
	private String phonNo;
	private String authorId;
	private String leaveYn;
	private int loginFailCnt;
	private String lockTime;
	private int unLockTime;
	private String lockYn;
    private ArrayList<GrantedAuthority> authorities;
    
	@Override
	public String getPassword() {
		return memberPwd;
	}

	@Override
	public String getUsername() {
		return memberId;
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
