package net.koreate.dao;

import java.util.List;

import net.koreate.vo.BoardVO;
import net.koreate.vo.Criteria;

public interface BoardDao {

	int create(BoardVO vo) throws Exception;

	List<BoardVO> listAll() throws Exception;

	BoardVO read(int bno)throws Exception;

	void modify(BoardVO vo)throws Exception;

	void delete(int bno) throws Exception;

	List<BoardVO> listPage(Criteria cri);

	int totalCount() throws Exception;

}
