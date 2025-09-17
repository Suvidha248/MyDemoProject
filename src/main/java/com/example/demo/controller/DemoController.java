package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DemoController {

    @GetMapping("/hello")
    @ResponseBody
    public String getPage() {
        return """
               <html>
                   <head><title>Demo</title></head>
                   <body>
                       <h1>Hello, this is HTML Page returned from a controller</h1>
                   </body>
               </html>
               """;
    }
}
