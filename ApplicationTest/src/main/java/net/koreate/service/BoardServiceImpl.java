package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.BoardDao;
import net.koreate.vo.BoardVO;
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	BoardDao dao;

	@Override
	public String create(BoardVO vo) throws Exception {
		int result = dao.create(vo);
		String message ="";
		if(result != 0) {
			message = "SUCCESS";
		}else {
			message = "FAIELD";
		}
		return message;
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		return dao.listPage(cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		int totalCount = dao.totalCount();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		return pageMaker;
	}
	
	
	
	
	

}
