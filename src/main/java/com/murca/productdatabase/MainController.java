package com.murca.productdatabase;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("")
    public String ShowPage()
    {
        return "index";
    }
}
