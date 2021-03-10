package com.iuh.contactapp.Controller;

import com.iuh.contactapp.Contact.entity.Contact;
import com.iuh.contactapp.Contact.entity.ContactReponsitory;
import com.iuh.contactapp.Security.UserService;
import com.iuh.contactapp.User.entity.User;
import com.iuh.contactapp.User.entity.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class TemplateController {
    @Autowired
    private ContactReponsitory contactReponsitory;
    @Autowired
    UserReponsitory userReponsitory;


    @Autowired
    UserService userService;


    @GetMapping("login")
    public String getLoginView(@RequestParam(name = "error",required = false) String error, Model model){
        if(error != null){
            if(error.equalsIgnoreCase("passwordwrong") ){
                model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");

            } else  if( error.equalsIgnoreCase("usernotexits")){
                model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");
            }
        }

        return "login";

    }

    @GetMapping("register")
    public String getRegisterView(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("register")
    public String registerUser(User user, Model model){
        if(userReponsitory.getBynumberphone(user.getNumberphone())  != null){
            model.addAttribute("error", "Số điện thoại đã được đăng ký!!!");
            model.addAttribute("user",user);
            return "register";
        }else{

            userReponsitory.save(userService.registerNewUserAccount(user));
            return "success";
        }

    }


    @GetMapping()
    public String Home(Principal principal,Model model, @Param("phone") String phone, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){
        String numberphone = principal.getName();
        User  user = userReponsitory.getBynumberphone(numberphone);



        List<User> users = userReponsitory.findAll();
        model.addAttribute("users",users);
        model.addAttribute("contact", new Contact());
        if(phone==null){
            Pageable pageable = PageRequest.of(page, size);
            Page<Contact> contactPage = contactReponsitory.findContact(pageable,user.getId());
            model.addAttribute("contacts",contactPage);
            model.addAttribute("phone",phone);
            model.addAttribute("pagenumber",contactPage.getPageable().getPageNumber());
            model.addAttribute("totalpage",contactPage.getTotalPages());
            model.addAttribute("username", user.getName());

            return "homeuser";
        }else{
            List<Contact> contacts = contactReponsitory.findByNumberPhone(phone,user.getId());
            model.addAttribute("contacts",contacts);
            model.addAttribute("phone",phone);
            return "homeuser";
        }


    }



    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Contact contact, Principal principal) {
        String numberphone = principal.getName();
        Long id = userReponsitory.getBynumberphone(numberphone).getId();
        Optional<User> user = userReponsitory.findById(id);
        contact.setUser(user.get());
        contactReponsitory.save(contact);
        return "redirect:/";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Long id) {
        Optional<Contact> contact = contactReponsitory.findById(id);
        contactReponsitory.delete(contact.get());
        return "redirect:/";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long id, Model model) {
        System.out.println(id);
        Optional<Contact> contact = contactReponsitory.findById(id);
        contact.ifPresent(item -> model.addAttribute("contact", contact));
        return "edituser";
    }


}
