package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.BoardService;
import net.koreate.vo.BoardVO;
import net.koreate.vo.Criteria;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	@RequestMapping("/register")
	public String register() {
		System.out.println("git test change code start");
		System.out.println("글쓰기 요청");
		System.out.println("git test change code end");
		return "board/register";
	}
	
	@RequestMapping(value="/registerBoard", method=RequestMethod.POST)
	public String registerBoard(@ModelAttribute("boardVO") BoardVO vo,
								RedirectAttributes rttr) throws Exception{
		System.out.println(vo);
		String result = service.create(vo);
		rttr.addFlashAttribute("result",result);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public String listPage(@ModelAttribute("cri") Criteria cri,Model model) throws Exception {
		List<BoardVO> boardList = null;
		boardList = service.listPage(cri);
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageMaker",service.getPageMaker(cri));
		return "/board/listPage";
	}
	
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public String read(@RequestParam("bno") int bno,Model model) throws Exception {
		System.out.println("bno : " + bno);
		BoardVO boardVO = service.read(bno);
		model.addAttribute("boardVO",boardVO);
		return "/board/read";
	}
	
	@RequestMapping(value="/modify")
	public String modify(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute("boardVO",service.read(bno));
		return "/board/modify";
	}
	
	@RequestMapping(value="/modifyAccept",method=RequestMethod.POST)
	public String modifyAccept(BoardVO vo) throws Exception {
		System.out.println(vo);
		service.modify(vo);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam("no") int bno) throws Exception{
		System.out.println("delete bno : " + bno);
		service.delete(bno);
		return "redirect:/board/listPage";
	}

}



