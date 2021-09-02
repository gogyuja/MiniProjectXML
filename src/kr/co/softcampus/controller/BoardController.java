package kr.co.softcampus.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softcampus.beans.ContentBean;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx")int board_info_idx, Model model) {
		model.addAttribute("board_info_idx",board_info_idx);
		return "board/main";
	}
	
	@GetMapping("/read")
	public String read() {
		return "board/read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean")ContentBean writeCointentBean) {
		return "board/write";
	}
	
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean,BindingResult result) {
		if(result.hasErrors()) {
			return "board/write";
		}
		return "board/write_success";
	}
	
	@GetMapping("/modify")
	public String modify() {
		return "board/modify";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "board/delete";
	}
}








