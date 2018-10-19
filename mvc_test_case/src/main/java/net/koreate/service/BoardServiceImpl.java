package net.koreate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.koreate.dao.BoardDAO;
import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.SearchCriteria;


@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDAO dao;
	
	@Transactional
	@Override
	public void registReply(ReplyBoardVo board) throws Exception {
		//게시물 등록
		dao.registReply(board);
		//origin 변경
		dao.updateOrigin();
		
		String[] files = board.getFiles();
		if(files == null)return;
		for(String fullname : files) {
			dao.addAttach(fullname);
		}
	}
	
	@Override
	public List<ReplyBoardVo> listReplyCriteria(SearchCriteria cri) throws Exception {
		List<ReplyBoardVo> list = dao.listReplyCriteria(cri);
		for(ReplyBoardVo vo : list) {
			vo.setCommentCnt(dao.getCommentCnt(vo.getBno()));
		}
		return list;
	}
	
	@Override
	public int listReplyCount(SearchCriteria cri) throws Exception {
		return dao.listReplyCount(cri);
	}

	@Override
	public ReplyBoardVo readReply(int bno)  throws Exception{
		ReplyBoardVo vo = dao.readReply(bno);
		vo.setCommentCnt(dao.getCommentCnt(vo.getBno()));
		return vo;
	}
	
	@Override
	public void updateReplyCnt(int bno)  throws Exception{
		dao.updateReplyCnt(bno);
	}
	
	@Override
	public void replyRegister(ReplyBoardVo board) throws Exception {
		//  그룹번호 
		int origin = board.getOrigin();
		//  depth 자기 자신 또는 바로 부모 글
		int depth = board.getDepth();
		// board.getBno() 부모글 번호
		
		dao.updateReply(board);
		
		board.setOrigin(origin);
		board.setDepth(depth+1);
		board.setSeq(board.getSeq()+1);
		dao.replyRegister(board);
	}
	
	/*@Override
	public void modify(ReplyBoardVo board) throws Exception {
		dao.update(board);
	}*/

	@Transactional
	@Override
	public void modify(ReplyBoardVo board) throws Exception {
		dao.update(board);
		
		int bno = board.getBno();
	    
	    dao.deleteAttach(bno);
	    
	    String[] files = board.getFiles();
	    
	    if(files == null) { return; } 
	    
	    for (String fullname : files) {
	    	
	    	Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("bno", bno);
			paramMap.put("fullname", fullname);
	    	
			dao.replaceAttach(paramMap);
	    }
	}

/*	@Override
	public void remove(int bno) throws Exception {
		dao.delete(bno);
	}*/
	
	  @Transactional
	  @Override
	  public void remove(int bno) throws Exception {
	    dao.deleteAttach(bno);
	    dao.delete(bno);
	  } 

	@Override
	public List<String> getAttach(int bno) throws Exception {
		return dao.getAttach(bno);
	}

	@Override
	public ReplyBoardVo checkAuth(int uno, int bno) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("uno", uno);
		map.put("bno", bno);
		ReplyBoardVo board = dao.checkAuth(map);
		return board;
	}
	
	
	
	
   
	
	
}
