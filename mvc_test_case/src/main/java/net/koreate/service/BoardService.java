package net.koreate.service;

import java.util.List;

import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.SearchCriteria;


public interface BoardService {
	
	public void registReply(ReplyBoardVo board) throws Exception;
	public List<ReplyBoardVo> listReplyCriteria(SearchCriteria cri) throws Exception;
	public int listReplyCount(SearchCriteria cri) throws Exception;
	public ReplyBoardVo readReply(int bno)  throws Exception;
	public void updateReplyCnt(int bno)  throws Exception;
	public void replyRegister(ReplyBoardVo board) throws Exception;
	
	public void modify(ReplyBoardVo board)throws Exception;
	public void remove(int bno)throws Exception;
	public List<String> getAttach(int bno) throws Exception;
	public ReplyBoardVo checkAuth(int uno, int bno) throws Exception;
	
}
