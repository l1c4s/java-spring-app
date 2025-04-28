package com.br.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleErrorController {

    @GetMapping("/errors")
    public String customError(@RequestParam(name = "status", required = false) String status) {
        if ("404".equals(status)) {
            return "errors/404";
        } else if ("500".equals(status)) {
            return "errors/500";
        } else {
            return "error/generic";
        }
    }
}