package net.koreate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.CommentDao;
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;
import net.koreate.vo.CommentVo;

@Service
public class CommentServiceImpl implements CommentService {

  @Inject
  private CommentDao dao;

  @Override
  public void addComment(CommentVo vo) throws Exception {

    dao.create(vo);
  }

  @Override
  public List<CommentVo> listComment(Integer bno) throws Exception {

    return dao.list(bno);
  }

  @Override
  public void modifyComment(CommentVo vo) throws Exception {

    dao.update(vo);
  }

  @Override
  public void removeComment(int cno) throws Exception {

    dao.delete(cno);
  }

  @Override
  public List<CommentVo> listCommentPage(int bno, Criteria cri)  throws Exception {
	  Map<String,Object> map = new HashMap<>();
	  map.put("bno", bno);
	  map.put("cri", cri);
    return dao.listPage(map);
  }
  
  @Override
	public PageMaker getPageMaker(int page,int bno) throws Exception {
		  Criteria cri = new Criteria();
	      cri.setPage(page);
	      PageMaker pageMaker = new PageMaker();
	      pageMaker.setCri(cri);
	      int replyCount = dao.count(bno);
	      pageMaker.setTotalCount(replyCount);
		return pageMaker;
	}
}
