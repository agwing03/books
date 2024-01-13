package project.books.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper memberMapper;
	
	/**
	 * 맴버 목록 조회
	 * @param clubNo
	 * @return list
	 * @throw Exception
	 */
	public UserVO selectUserList(UserVO vo) throws Exception{
		int cnt = memberMapper.selectUserListCnt(vo);
		if(cnt > 0) {
			vo.setDataList(memberMapper.selectUserList(vo));
		}
		return vo;
	}
}
