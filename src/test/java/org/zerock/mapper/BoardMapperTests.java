package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList() {
		
		log.info("------------------------------------------------------------------");
		boardMapper.getList();
	}
	//성능면에서 얘가 우수함
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("Test 테스트");
		board.setContent("Content 테스트");
		board.setWriter("tester");
		
		boardMapper.insert(board);
		log.info("-----------------------------------------------------------------");
		log.info("after insert " + board.getBno());
	}
	
	//몇번 글이 등록되었습니다 문구 표현할 때
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("AAATest 테스트");
		board.setContent("AAAContent 테스트");
		board.setWriter("tester");
		
		boardMapper.insertSelectKey(board);
		
		log.info("-----------------------------------------------------------------");
		log.info("after insert selectkey " + board.getBno());
	}
	
	@Test
	public void testRead() {
		
		BoardVO vo = boardMapper.read(7L);
		log.info(vo);
	}
	
	@Test
	public void testDelete() {
		
		int count = boardMapper.delete(1L);
		
		log.info("count " + count);
	}
	
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(7L);
		vo.setTitle("Update Title");
		vo.setContent("Update Content");
		vo.setWriter("user00");
		
		log.info("count: " + boardMapper.update(vo));
	}
}
