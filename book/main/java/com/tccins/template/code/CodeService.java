package com.tccins.template.code;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tccins.template.code.mapper.CodeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeService {
	 private final CodeMapper codeMapper;

	    /**
	     * 코드 리스트 조회
	     * @param CodeVO
	     * @return List
	     * @throws Exception 
	     */
	    public List<CodeVO> selectCodeList(CodeVO params) throws Exception{
	    	List<CodeVO> list = null;
	    	
	    	if(params.getCodeType().equals("clCode")) {
	    		list = codeMapper.selectClCodeList();
	    	}
	    	if(params.getCodeType().equals("cmCode")) {
	    		list = codeMapper.selectCmCodeList(params);
	    	}
	    	if(params.getCodeType().equals("dtCode")) {
	    		list = codeMapper.selectDtCodeList(params);
	    	}
	    	
	        return list;
	    }
	    
	    /**
	     * 코드 갯수 구하기
	     * @param CodeVO
	     * @return int
	     * @throws Exception
	     */
	    public int selectCodeCnt(CodeVO params) throws Exception {
	    	int codeCnt=0;
	    	
	    	if(params.getCodeType().equals("clCode")) {
	    		codeCnt =  codeMapper.selectClCodeCnt(params);
	    	}
	    	if(params.getCodeType().equals("cmCode")) {
	    		codeCnt =  codeMapper.selectCmCodeCnt(params);
	    	}
	    	if(params.getCodeType().equals("dtCode")) {
	    		codeCnt =  codeMapper.selectDtCodeCnt(params);
	    	}
	    	if(params.getCodeType().equals("")) {
	    		codeCnt = 0;
	    	}
	    	
	        return codeCnt;
		}

	    
	    /**
		 * 코드 저장
		 * @param CodeVO
		 * @return String
		 * @throws Exception
		 */
		public String saveCode(CodeVO params) throws Exception {
			
	    	String hdCode = params.getHdCode();
	    	String code = params.getCode();
	    	
			String ReturnMsg="";
			int returnCnt = 0;
			try {
				if(hdCode == null || hdCode=="") { //등록	
		    		if(params.getCodeType().equals("clCode")) {
		    			returnCnt = codeMapper.insertClCode(params);
			    	}
			    	if(params.getCodeType().equals("cmCode")) {
			    		returnCnt = codeMapper.insertCmCode(params);
			    	}
			    	if(params.getCodeType().equals("dtCode")) {
			    		returnCnt = codeMapper.insertDtCode(params);
			    	}
		    		
		    		if(returnCnt > 0) {
						ReturnMsg = "등록되었습니다.";
					}else {
						ReturnMsg = "등록실패";
					}
				}else { //수정
					
					CodeVO codeVO = new CodeVO();
		    		String codeType="",upperCode = "";
		    		if(params.getCodeType().equals("clCode")) {codeType = "cmCode";}
		    		if(params.getCodeType().equals("cmCode")) {codeType = "dtCode";}
		    		if(params.getCodeType().equals("dtCode")) {codeType = "";}
		    		
		    		upperCode = hdCode;
		    		codeVO.setCodeType(codeType);
		    		codeVO.setUpperCode(upperCode);
		    		
		    		int lowerMenuCnt = selectCodeCnt(codeVO); //하위목록여부
		    		
		    		if(lowerMenuCnt!=0 && !hdCode.equals(code)) {
		    			ReturnMsg = "하위목록이 있어 코드를 수정할 수 없습니다.";
	    			}else{
						if(params.getCodeType().equals("clCode")) {
			    			returnCnt = codeMapper.updateClCode(params);
				    	}
				    	if(params.getCodeType().equals("cmCode")) {
				    		returnCnt = codeMapper.updateCmCode(params);
				    	}
				    	if(params.getCodeType().equals("dtCode")) {
				    		returnCnt = codeMapper.updateDtCode(params);
				    	}
	    		
			    		if(returnCnt > 0) {
							ReturnMsg = "수정되었습니다.";
						}else {
							ReturnMsg = "수정실패";
						}
			    	}
				}
	    	}catch(Exception e) {
				ReturnMsg = "저장을 실패하였습니다.";
			}
			return ReturnMsg;
		}
		
		
	    /**
		 * 코드 삭제
		 * @param CodeVO
		 * @return String
		 * @throws Exception
		 */
		public String deleteCode(CodeVO params) throws Exception {
			
			String ReturnMsg="";
			int returnCnt = 0;
	    	try {
	    		
	     		String hdCode = params.getHdCode();
	     		
	     		CodeVO codeVO = new CodeVO();
	    		String codeType="",upperCode = "";
	    		if(params.getCodeType().equals("clCode")) {codeType = "cmCode";}
	    		if(params.getCodeType().equals("cmCode")) {codeType = "dtCode";}
	    		if(params.getCodeType().equals("dtCode")) {codeType = "";}
	    		
	    		upperCode = hdCode;
	    		codeVO.setCodeType(codeType);
	    		codeVO.setUpperCode(upperCode);
	    		int lowerMenuCnt = selectCodeCnt(codeVO);
	    		
	    		if(lowerMenuCnt!=0) {
	    			ReturnMsg = "하위목록이 있어 삭제할수 없습니다.";
	    		}else{ //하위목록이 없을때
		    		if(params.getCodeType().equals("clCode")) {
		    			returnCnt = codeMapper.deleteClCode(params);
			    	}
			    	if(params.getCodeType().equals("cmCode")) {
			    		returnCnt = codeMapper.deleteCmCode(params);
			    	}
			    	if(params.getCodeType().equals("dtCode")) {
			    		returnCnt = codeMapper.deleteDtCode(params);
			    	}
		    		
		    		if(returnCnt > 0) {
						ReturnMsg = "삭제되었습니다.";
					}else {
						ReturnMsg = "삭제실패";
					}
	    		}
	    	}catch(Exception e) {
				ReturnMsg = "삭제실패";
			}
			return ReturnMsg;
		}
	    
}
