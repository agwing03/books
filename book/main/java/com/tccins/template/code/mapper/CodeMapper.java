package com.tccins.template.code.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.code.CodeVO;

@Mapper
public interface CodeMapper {

    List<CodeVO> selectClCodeList();
    List<CodeVO> selectCmCodeList(CodeVO params);
    List<CodeVO> selectDtCodeList(CodeVO params);
    
    int selectClCodeCnt(CodeVO params);
    int selectCmCodeCnt(CodeVO params);
    int selectDtCodeCnt(CodeVO params);
 
    int insertClCode(CodeVO params);
    int insertCmCode(CodeVO params);
    int insertDtCode(CodeVO params);
    
    int updateClCode(CodeVO params);
    int updateCmCode(CodeVO params);
    int updateDtCode(CodeVO params);
    
    int deleteClCode(CodeVO params);
    int deleteCmCode(CodeVO params);
    int deleteDtCode(CodeVO params);
}
