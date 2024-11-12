package com.gimhae.sts09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gimhae.sts09.model.DeptService;
import com.gimhae.sts09.model.DeptVo;
import com.gimhae.sts09.model.entity.Dept02;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	DeptService deptService;

	@GetMapping("/")
	public String list(Model model) {
//		List list=List.of(
//				Dept02.builder().deptno(1111).dname("테스트1").loc("loc1").build(),
//				Dept02.builder().deptno(2222).dname("테스트2").loc("loc2").build(),
//				Dept02.builder().deptno(3333).dname("테스트3").loc("loc3").build()
//				);
		List list=deptService.getList();
		
		model.addAttribute("list", list);
		return "dept/list";
	}
	
	@PostMapping("/")
	public String add(Model model,@ModelAttribute DeptVo bean) {
		log.debug("add post");
		deptService.addList(bean);
		return "redirect:./";
	}
	
	@GetMapping("/add")
	public String add() {
		return "dept/add";
	}
	
	@GetMapping("/{deptno}")
	public String detail(@PathVariable int deptno,Model model) {
		DeptVo bean=deptService.getOne(deptno);
		log.debug("GETdetail:"+bean.toString());
		model.addAttribute("bean", bean);
		return "dept/detail";
	}
	
	@ResponseBody
	@PutMapping("/{deptno}")
	public String update(@PathVariable int deptno,@RequestBody DeptVo bean) {
		log.debug("PUTdetail:"+bean.toString());
		deptService.editOne(bean);
		return "{\"result\":\"success\"}";
	}

	@ResponseBody
	@DeleteMapping("/{deptno}")
	public String delete(@PathVariable int deptno) {
		log.debug("DELETEdetail:"+deptno);
		deptService.deleteOne(deptno);
		return "{\"result\":\"success\"}";
	}
}
