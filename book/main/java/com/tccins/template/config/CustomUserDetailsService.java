package com.tccins.template.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tccins.template.user.mapper.UserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserMapper mapper;
	
	@Override
    public UserDetails loadUserByUsername(String insertedId) throws UsernameNotFoundException {
        //입력ID로 USER의 PK를 구해서 조회 (ID 변경가능성)
		String userPk = mapper.selectUserPk(insertedId);
		
		CustomUserDetails user = mapper.selectUser(userPk);
        if (user == null) {
        	// 아이디 값이 없을 경우 실패 핸들러로 리턴 시키기 위함.
        	 throw new UsernameNotFoundException(insertedId + "는(은) 존재하지 않는 ID입니다.");
        }else {
            List<String> authorities = mapper.selectAuthorities(insertedId);
            ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (String auth: authorities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(auth));
            }
        	
        	user.setAuthorities(grantedAuthorities);
        }
        
        return user;
    }
	

}
