package com.tccins.template.userAuth;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tccins.template.auth.AuthVO;
import com.tccins.template.common.CamelMap;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.paging.Pagination;
import com.tccins.template.paging.PagingResponse;
import com.tccins.template.userAuth.mapper.UserAuthMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final UserAuthMapper userAuthMapper;
    
    /**
     * 유저 리스트 조회
     * @return 권한 리스트
     */
    public PagingResponse<UserAuthVO> findAllUser(final SearchDto params) {
    	int count = userAuthMapper.userCount(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<UserAuthVO> list = userAuthMapper.findAllUser(params);
        return new PagingResponse<>(list, pagination);
    }
    
    /**
     * 권한 리스트 조회
     * @param params
     * @return
     */
    public List<AuthVO> authList(final SearchDto params) {
    	
    	List<AuthVO> authList = userAuthMapper.authList(params);
    	
    	return authList;
    }
    
    /**
     * 유져 권한 수정
     * @param params
     * @return void
     * @throws Exception
     */
    @Transactional
    public void userAuthUpdate(Map<String, Object> commandMap) throws Exception {
    	String userIdArr[] = commandMap.get("userIdArr").toString().split(",");
    	String authorArr[] = commandMap.get("authorArr").toString().split(",");
    	
    	CamelMap updateMap = new CamelMap();
    	
    	for(int i = 0; i < userIdArr.length; i++) {
    		// 데이터 쌓기
    		updateMap.put("userId", userIdArr[i]);
    		updateMap.put("authorCode", authorArr[i]);
    		
    		// 권한 수정
    		userAuthMapper.userAuthUpdate(updateMap);
    	}
    }
}
