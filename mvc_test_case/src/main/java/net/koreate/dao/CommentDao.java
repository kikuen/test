package net.koreate.dao;

import java.util.List;
import java.util.Map;

import net.koreate.vo.Criteria;
import net.koreate.vo.CommentVo;

public interface CommentDao {

	void create(CommentVo vo);

	List<CommentVo> list(Integer bno);

	void update(CommentVo vo);

	void delete(Integer rno);

	//List<ReplyVo> listPage(Integer bno, Criteria cri);

	int count(Integer bno);

	List<CommentVo> listPage(Map<String, Object> map);

}
