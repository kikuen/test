package net.koreate.service;

import java.util.List;

import net.koreate.vo.BoardVO;
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;

public interface BoardService {

	String create(BoardVO vo) throws Exception;

	BoardVO read(int bno) throws Exception;

	void modify(BoardVO vo) throws Exception;

	void delete(int bno) throws Exception;

	List<BoardVO> listPage(Criteria cri) throws Exception;

	PageMaker getPageMaker(Criteria cri) throws Exception;

}
