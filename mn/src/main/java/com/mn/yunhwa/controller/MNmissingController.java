package com.mn.yunhwa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MNmissingController {

    @GetMapping("/missing")
    public String missingmain(){
        return "yunhwa/MNmissing";
    }

    @GetMapping("/missing/write")
    public String missingWrite(){
        return "yunhwa/MNMissing_write";
    }
}
