package com.topica.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.topica.model.UserEntity;
import com.topica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.topica.model.EngtovieEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String showLogin(){
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse reps){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int result = userService.checkUser(username, password);
        if(result == -1) {
            return "redirect:/login";
        } else if(result == 1) {
            return "redirect:/admin1";
        } else {
            return "redirect:/user";
        }

    }

    @GetMapping("/user")
    public String getViewUser(Model model) {
        model.addAttribute("word", new EngtovieEntity());
        return "user/user";
    }

    @GetMapping("/admin1")
    public String list(Model model) {
        model.addAttribute("word", userService.listEng());
        return "admin/admin1";
    }
    @GetMapping("/admin2")
    public String addNewWord(Model model, @RequestParam(value = "id", required = false) Integer id) {
        if(id == null){
            model.addAttribute("word", new EngtovieEntity());
        } else {
            model.addAttribute("word", userService.getEng(id));
        }
        return "admin/admin2";
    }

    @PostMapping("/word-save")
    public String saveWord(@ModelAttribute("word") EngtovieEntity word, @RequestParam("type") int type){
        if (type == 1){
            userService.saveEng(word);
        }
        return "redirect:admin1";
    }

    @PostMapping("word-search")
    public String search(Model model, @RequestParam("key") String key){
        EngtovieEntity w = userService.searchEng(key);
        model.addAttribute("word", w);
        return "user/user";
    }

    @GetMapping("word-delete")
    public String delete(@RequestParam("id") int id) {
        System.out.println(id);
        userService.delete(id);
        return "redirect:admin1";
    }


}
