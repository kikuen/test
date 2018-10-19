package net.koreate.service;

import java.util.List;

import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;
import net.koreate.vo.CommentVo;

public interface CommentService {

  public void addComment(CommentVo vo) throws Exception;

  public List<CommentVo> listComment(Integer bno) throws Exception;

  public void modifyComment(CommentVo vo) throws Exception;

  public void removeComment(int cno) throws Exception;

  public List<CommentVo> listCommentPage(int bno, Criteria cri) throws Exception;

  public PageMaker getPageMaker(int page,int bno) throws Exception;
}
