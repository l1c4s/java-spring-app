package com.br.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HospedeController {

    @GetMapping("/mainpage")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("mainpage");
        return mv;
    }
    
}
