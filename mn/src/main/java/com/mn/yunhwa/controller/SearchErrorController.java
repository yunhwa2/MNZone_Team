package com.mn.yunhwa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SearchErrorController {

    @GetMapping(value="/searcherror")
    public String main(){

        return "yunhwa/search_error";
    }

}
