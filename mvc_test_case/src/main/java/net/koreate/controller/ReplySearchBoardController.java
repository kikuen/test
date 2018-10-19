package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.BoardService;
import net.koreate.vo.PageMaker;
import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.SearchCriteria;

@Controller
@RequestMapping("/board/*")
public class ReplySearchBoardController {
	
	@Inject
	BoardService service;
	// /rboard/listReply
	@RequestMapping(value="/listReply", method=RequestMethod.GET)
	public void replyList(@ModelAttribute("cri") SearchCriteria cri,
			Model model)throws Exception{
		System.out.println("답변형 게시물");
		model.addAttribute("list",service.listReplyCriteria(cri));
		System.out.println("list size : " + service.listReplyCriteria(cri).size());
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		int cnt = service.listReplyCount(cri);
		pageMaker.setTotalCount(cnt);
		System.out.println("CRI cnt : " + cnt);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void registerGET(ReplyBoardVo board,Model model) throws Exception{
		
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPOST(ReplyBoardVo board,RedirectAttributes rttr) throws Exception{
		System.out.println(board.toString());
		service.registReply(board);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/board/listReply";
	}
	
	
	// paging 처리된 리드 페이지
		@RequestMapping(value="/readPage",method=RequestMethod.GET)
		public String readPage(@RequestParam("bno") int bno,@ModelAttribute("cri") SearchCriteria cri,RedirectAttributes rttr) throws Exception{
			service.updateReplyCnt(bno);
			rttr.addAttribute("bno",bno);
			rttr.addAttribute("page",cri.getPage());
			rttr.addAttribute("perPageNum",cri.getPerPageNum());
			rttr.addAttribute("searchType",cri.getSearchType());
			rttr.addAttribute("keyword",cri.getKeyword());
			return "redirect:/board/readDetail";
		}
		
		// paging 처리된 리드 페이지
		@RequestMapping(value="/readDetail",method=RequestMethod.GET)
		public String readUpdate(@ModelAttribute("bno") int bno,@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
			System.out.println(cri);
			System.out.println("bno : "+bno);
			model.addAttribute("boardVo",service.readReply(bno));
			return "/board/readPage";
		}
		
		@RequestMapping(value="/replyRegister",method=RequestMethod.GET)
		public void replyRegister(@ModelAttribute("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model)  throws Exception {
			model.addAttribute("boardVo",service.readReply(bno));
		}
		
		@RequestMapping(value="/replyRegister",method=RequestMethod.POST)
		public String replyRegister(ReplyBoardVo board, SearchCriteria cri, RedirectAttributes rttr) throws Exception{
			service.replyRegister(board);
			rttr.addFlashAttribute("msg","success");
			return "redirect:/board/listReply";
		}
		
		@RequestMapping(value="/removePage",method=RequestMethod.POST)
		public String remove(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr) throws Exception{
			service.remove(bno);
			rttr.addAttribute("page",cri.getPage());
			rttr.addAttribute("perPageNum",cri.getPerPageNum());
			rttr.addAttribute("searchType",cri.getSearchType());
			rttr.addAttribute("keyword",cri.getKeyword());
			rttr.addFlashAttribute("msg","SUCCESS");
			return "redirect:/board/listReply";
		}
		
		@RequestMapping(value="/modifyPage",method=RequestMethod.GET)
		public void modifyGET(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
			model.addAttribute("boardVo",service.readReply(bno));
		}
		
		@RequestMapping(value="/modifyPage",method=RequestMethod.POST)
		public String modifyPOST(ReplyBoardVo board, SearchCriteria cri, RedirectAttributes rttr) throws Exception{
			service.modify(board);
			rttr.addAttribute("page",cri.getPage());
			rttr.addAttribute("perPageNum",cri.getPerPageNum());
			rttr.addAttribute("searchType",cri.getSearchType());
			rttr.addAttribute("keyword",cri.getKeyword());
			rttr.addFlashAttribute("msg","SUCCESS");
			return "redirect:/board/listReply";
		}

		
		@RequestMapping("/getAttach/{bno}")
		@ResponseBody
		public List<String> getAttach(@PathVariable("bno")int bno)throws Exception{
			System.out.println("요청 들어옴");
			System.out.println(bno);
			List<String> list = service.getAttach(bno);
			System.out.println(list.size());
			return list;
		}  
}
