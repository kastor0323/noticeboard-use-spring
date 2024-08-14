package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	
	List<BoardVO> getList();
	
	//insert만 실행되고, 생성된 pk값을 알 필요가 없는 경우
	void insert(BoardVO board);
	
	//insert문이 실행되고, 생성된 pk값을 알아야 하는 경우
	void insertSelectKey(BoardVO board);
	
	//읽기
	BoardVO read(long bno);
	
	//삭제
	int delete(long bno);
	
	int update(BoardVO board);
	
	List<BoardVO> getListWithPaging(Criteria cri);
}
