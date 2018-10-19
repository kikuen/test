package net.koreate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.koreate.service.CommentService;
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;
import net.koreate.vo.CommentVo;

@RestController
@RequestMapping("/comments")
public class CommentController {

  @Inject
  private CommentService service;

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<String> register(@RequestBody CommentVo vo) {
	 System.out.println("Git hub Test");
    ResponseEntity<String> entity = null;
    try {
      service.addComment(vo);
      System.out.println(vo);
      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
  public ResponseEntity<List<CommentVo>> list(@PathVariable("bno") Integer bno) {

    ResponseEntity<List<CommentVo>> entity = null;
    try {
      entity = new ResponseEntity<>(service.listComment(bno), HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return entity;
  }

  @RequestMapping(value = "/{cno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
  public ResponseEntity<String> update(@PathVariable("cno") int cno, @RequestBody CommentVo vo) {

    ResponseEntity<String> entity = null;
    try {
      vo.setCno(cno);
      service.modifyComment(vo);

      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
  public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {

    ResponseEntity<String> entity = null;
    try {
      service.removeComment(rno);
      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

  @RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
  public ResponseEntity<Map<String, Object>> listPage(
      @PathVariable("bno") Integer bno,
      @PathVariable("page") Integer page) {

    ResponseEntity<Map<String, Object>> entity = null;
    try {
      PageMaker pageMaker = service.getPageMaker(page,bno);

      Map<String, Object> map = new HashMap<String, Object>();
      List<CommentVo> list = service.listCommentPage(bno, pageMaker.getCri());
      map.put("list", list);
      map.put("pageMaker", pageMaker);
      for(CommentVo vo : list) {
    	  System.out.println("vo : "+vo);
      }
      entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }

}
