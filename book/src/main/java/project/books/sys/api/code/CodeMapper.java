package project.books.sys.api.code;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.sys.util.CamelMap;


@Mapper
public interface CodeMapper {
	
    /**
	 * 상세 코드 목록 조회
	 * @param codeId
	 * @return list
	 * @throw Exception
     */
	List<CamelMap> selectCmmnCodeDtlList(CodeVO vo);
}
