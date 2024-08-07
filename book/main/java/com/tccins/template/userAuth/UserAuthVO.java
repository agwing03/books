package com.tccins.template.userAuth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthVO {
	
	private String userId;
	private String userNm;
	private String useYn;
	private String failCount;		
	private String isLock;
	private String authorCode;
	
	private String userIdArr;
	private String authorList;
}
