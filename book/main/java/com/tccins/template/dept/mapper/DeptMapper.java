package com.tccins.template.dept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.dept.DeptVO;

@Mapper
public interface DeptMapper {

	/**
	 * 부서리스트 조회
	 * @param
	 * @return
	 * @throw Exception
	 */
	List<DeptVO> deptList(SearchDto params) throws Exception;
	
	/**
	 * 부서 갯수
	 * @param SearchDto
	 * @return
	 * @throw Exception
	 */
	int deptCount(SearchDto params) throws Exception;
	
    /**
     * 부서코드 중복체크 
     * @param deptCd
     * @return 권한 수
     */
    int checkDeptCd(String deptCd) throws Exception;
    
    /**
     * 부서리스트 상세정보 조회
     * @param 
     * @return 
     */
    DeptVO findDeptByDeptCd(String deptCd) throws Exception;
	
	/**
     * 부서 저장
     * @param params - 게시글 정보
     */
    void saveDept(DeptVO params) throws Exception;
    
    
    /**
     * 부서 수정
     * @param params - 권한 정보
     */
    void updateDept(DeptVO params) throws Exception;

    /**
     * 부서 삭제
     * @param id - deptCd
     */
    void deleteDept(String deptCd) throws Exception;


	
}
