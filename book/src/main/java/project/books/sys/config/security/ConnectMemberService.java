package project.books.sys.config.security;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.books.sys.api.member.MemberMapper;

@Service
public class ConnectMemberService implements UserDetailsService{
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
    public ConnectMember loadUserByUsername(String connetId) throws UsernameNotFoundException {
		ConnectMember memberInfo;
		
		//Login Input 정보로 맴버정보 조회
		List<ConnectMember> connMemberList = memberMapper.selectConnetMemberInfo(connetId);
		
		//맴버정보 중복 존재
        if (connMemberList.size() > 1) {
        	throw new UsernameNotFoundException(connetId + "가 중복으로 존재합니다. 관리자에게 문의하시기 바랍니다.");
        
        //맴버정보 없음
        } else if (connMemberList.size() == 0) {
        	throw new UsernameNotFoundException(connetId + "는(은) 존재하지 않는 맴버정보입니다.");
        
        //맴버정보 셋팅
        } else {
        	memberInfo = connMemberList.get(0);
        }
        return memberInfo;
    }
}
