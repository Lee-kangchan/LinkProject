package com.chan.link.domain.link.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {

    @GetMapping("/all")
    public void linkAllList(){

    }
    @GetMapping("/best")
    public void linkBestList(){

    }
    @GetMapping("/user")
    public void linkUserList(){

    }
    @PostMapping("/user/{link}")
    public void linkAdd(@PathVariable String link){

    }

    @PostMapping("/user/{link}/follow")
    public void linkFollow(@PathVariable String link){

    }
    @DeleteMapping("/user/{link}")
    public void linkDelete(@PathVariable String link){

    }
}
