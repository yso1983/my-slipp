package net.slipp.web;

import javax.servlet.http.HttpSession;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	//private List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		System.out.println("[login] : " + user);
		
		if(user == null) {
			System.out.println("login failed!");
			return "redirect:/users/loginForm";
		}
		
		if(!user.matchPassword(password)) {
			System.out.println("login failed!");
			return "redirect:/users/loginForm"; 
		}
		
		System.out.println("login success!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sesstion) {
		sesstion.removeAttribute(("user"));
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@PostMapping()
	public String create(User user) {
		System.out.println(user);
		//users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping()
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		//User user = userRepository.findOne(id);
		
		User sessionedUser = (User)HttpSessionUtils.getUserFromSession(session);
		
		if(sessionedUser == null) {
			return "redirect:/users/loginForm";
		}
		
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException("You can't update anther user");
		}
		
		User user = userRepository.findById(id).get();
		System.out.println("[updateForm] : " + user);
		model.addAttribute("user", user);
		
		return "/user/updateForm";
	}
	
	//@PostMapping("/{id}")
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		User sessionedUser = (User)HttpSessionUtils.getUserFromSession(session);
		
		if(sessionedUser == null) {
			return "redirect:/users/loginForm";
		}
		
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException("You can't update anther user");
		}
		
		User user = userRepository.findById(id).get();
		
		user.update(updatedUser);
		System.out.println("[update] : " + user);;
		userRepository.save(user);
		return "redirect:/users";
	}
	
}
