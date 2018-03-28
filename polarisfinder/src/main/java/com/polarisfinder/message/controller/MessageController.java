package com.polarisfinder.message.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.polarisfinder.dreamers.entity.Dreamerscomment;
import com.polarisfinder.message.entity.Message;
import com.polarisfinder.message.service.MessageService;
import com.polarisfinder.user.entity.CurrentUser;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.UserService;

@Controller
@RequestMapping("message")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;
	
	@PostMapping("add")
	public ResponseEntity<Void> add(
			@RequestParam("tolist") String tolist,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content
			){
		
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Message Message = new Message();
		String[] lst = tolist.split(" ");

		Message.setSend_user_id(currentUser.getUser_id());
		Message.setSubject(subject);
		Message.setContent(content);
		Message.setReg_dt(new Date());
		for(String to_user_id : lst){
			User user = userService.findByUserName(to_user_id);
			Message.setTo_user_id(user.getUser_id());
			messageService.createMessage(Message);
		}
		
		System.out.println("tolist : "+ tolist);
		System.out.println("subject : "+ subject);
		System.out.println("content : "+ content);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("getMessage")
	public ResponseEntity<List<Message>> getMessage(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Message> list = messageService.getMessage(currentUser.getUser_id(), paging);
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getMessageSent")
	public ResponseEntity<List<Message>> getMessageSent(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Message> list = messageService.getMessageSent(currentUser.getUser_id(), paging);
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getMessageStarred")
	public ResponseEntity<List<Message>> getMessageStarred(
			@RequestParam(value="paging", required = false)int paging
			) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Paging : " + paging);
		List<Message> list = messageService.getMessageStarred(currentUser.getUser_id(), paging);
		return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
	}
	
	
}