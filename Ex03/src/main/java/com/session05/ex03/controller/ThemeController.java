package com.session05.ex03.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThemeController {
    @GetMapping("/theme")
    public String getTheme(@CookieValue(value = "theme", required = false) String theme, Model model) {
        if (theme == null || theme.trim().isEmpty()) {
            theme = "light";
        }
        model.addAttribute("theme", theme);
        return "theme";
    }

    @PostMapping("/change-theme")
    public String setTheme(@RequestParam("theme") String theme, HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("theme", theme);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath(request.getContextPath());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "redirect:/theme";
    }
}
