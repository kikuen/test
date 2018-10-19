package net.koreate.dao;

import java.util.List;
import java.util.Map;

import net.koreate.vo.Criteria;
import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.SearchCriteria;


public interface BoardDAO {
	
	public void registReply(ReplyBoardVo board) throws Exception;
	public List<ReplyBoardVo> listReplyCriteria(SearchCriteria cri) throws Exception;
	public int listReplyCount(SearchCriteria cri) throws Exception;
	
	public ReplyBoardVo readReply(int bno) throws Exception;
	public void updateReplyCnt(int bno) throws Exception;
	
	public void replyRegister(ReplyBoardVo board) throws Exception;
	public void updateOrigin() throws Exception;
	public void updateReply(ReplyBoardVo board) throws Exception;
	public void update(ReplyBoardVo board) throws Exception;
	public void delete(int bno) throws Exception;
	public void addAttach(String fullname) throws Exception;
	public List<String> getAttach(int bno) throws Exception;
	
	public void deleteAttach(int bno) throws Exception;
	public void replaceAttach(Map<String, Object> paramMap) throws Exception;
	public int getCommentCnt(int bno) throws Exception;
	public ReplyBoardVo checkAuth(Map<String, Object> map)throws Exception;
	
}
