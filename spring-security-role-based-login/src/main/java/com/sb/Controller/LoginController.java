package com.sb.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping("/postLogin")
    public String postLogin(Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));

        // 👇 Special case: has both roles → useradmin.html
        if (isAdmin && isUser) {
            return "redirect:/useradmin.html";
        } else if (isAdmin) {
            return "redirect:/admin.html";
        } else if (isUser) {
            return "redirect:/user.html";
        }

        return "redirect:/welcome.html";
    }
}
