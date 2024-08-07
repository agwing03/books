package com.tccins.template.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
	
    private String userId;
    private String userNm;
    private String userPassword;
    private String deptCd;
    private String regId;
    private String regDate;
    private String updId;
    private String updDate;
    private String useYn;
    private String userPk;
    private int failCount;
    private String lockTime;
    private String isLock;
    private String authorCode;
    
}
