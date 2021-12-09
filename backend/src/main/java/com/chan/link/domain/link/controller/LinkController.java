package com.chan.link.domain.link.controller;

import com.chan.link.domain.link.dto.LinkDto;
import com.chan.link.domain.link.dto.PageDto;
import com.chan.link.domain.link.service.LinkService;
import com.chan.link.global.jwt.TokenProvider;
import com.chan.link.global.util.SecurityUtil;
import com.chan.link.global.vo.PostVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/link")
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/all")
    public Slice<PostVO> linkAllList(PageDto pageDto){
        return linkService.LinkRecentAll(pageDto);
    }
    @GetMapping("/best")
    public void linkBestList(){

    }
    @GetMapping("/user")
    public void linkUserList(){

    }
    @PostMapping("/user")
    public void linkAdd(@RequestBody LinkDto linkDto){
        linkService.LinkAdd(linkDto);
    }

    @PostMapping("/user/{link}/follow")
    public void linkFollow(@PathVariable String link){

    }
    @DeleteMapping("/user/{link}")
    public void linkDelete(@PathVariable String link){

    }
}
