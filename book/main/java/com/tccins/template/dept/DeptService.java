package com.tccins.template.dept;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.dept.mapper.DeptMapper;
import com.tccins.template.paging.Pagination;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {
	
	private final DeptMapper deptMapper;
	
	/**
	 * 부서리스트 조회
	 * @param SearchDto
	 * @return list
	 * @throw Exception
	 */
	public PagingResponse<DeptVO> deptList(SearchDto params) throws Exception{
		
		int count = deptMapper.deptCount(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<DeptVO> list =  deptMapper.deptList(params);
        
        return new PagingResponse<>(list, pagination);
	}
	
    /**
     * 부서코드 중복 체크
     * @param id - deptCd
     * @return int
     * @throw Exception
     */
    public int checkDeptCd(final String deptCd) throws Exception{
    	int checkCd = deptMapper.checkDeptCd(deptCd);
    	return checkCd;
    }
    
    
	 /**
     * 부서 상세정보 조회
     * @param deptCd
     * @return DeptVO
	 * @throws Exception 
     */
    public DeptVO findDeptByDeptCd(final String deptCd) throws Exception {
        return deptMapper.findDeptByDeptCd(deptCd);
    }

    /**
     * 부서 저장
     * @param  DeptVO
     * @return message
     */
    public String saveDept(final DeptVO params) throws Exception {
    	String message = "";
    	
    	try {
    		String dpetCd = params.getDeptCd();
	    	int resultNum = checkDeptCd(dpetCd);
	    	
	    	if(resultNum>0) {
	    		message = "중복된 부서코드 입니다.";
	    	}else {
	    		deptMapper.saveDept(params);
	    		message = "부서 등록이 완료되었습니다.";
	    	}
	    	
    	}catch(Exception e){
			e.printStackTrace();
			throw e;
    	}
    	return message;
    }

    /**
     * 부서 수정
     * @param DeptVO
     * @return message
     */
    @Transactional
    public String updateDept(final DeptVO param) throws Exception {
    	String message = "";
    	
    	try {
    		deptMapper.updateDept(param);
    		message = "부서 수정이 완료되었습니다.";
    	}catch(Exception e){
			e.printStackTrace();
			throw e;
    	}
    	return message;
    }
    
    /**
     * 부서 삭제
     * @param deptCd
     * @return message
     */
    public String deleteDept(final String deptCd) throws Exception {
    	String message = "";
    	
    	try {
    		deptMapper.deleteDept(deptCd);
    		message = "부서 삭제가 완료되었습니다.";
    	}catch(Exception e){
			e.printStackTrace();
			throw e;
    	}
    	return message;
    }
//    
    
}
