package com.chan.link.domain.link.controller;

import com.chan.link.domain.link.dto.LinkDto;
import com.chan.link.domain.link.dto.PageDto;
import com.chan.link.domain.link.service.LinkService;
import com.chan.link.global.jwt.TokenProvider;
import com.chan.link.global.util.SecurityUtil;
import com.chan.link.global.vo.LinkVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/link")
public class LinkController {

    private final LinkService linkService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/all")
    public Slice<LinkVO> linkAllList(PageDto pageDto){
        return linkService.LinkRecentAll(pageDto);
    }
    @GetMapping("/best")
    public void linkBestList(){

    }
    @GetMapping("/user")
    public void linkUserList(){

    }
    @PostMapping("/user")
    public void linkAdd(LinkDto linkDto){
        String email = SecurityUtil.getCurrentMemberId().toString();
        Long seq = SecurityUtil.getCurrentUserSeq();
        log.info(email+"");
        log.info(seq+"");

//        linkService.LinkAdd(linkDto, 4L);
    }

    @PostMapping("/user/{link}/follow")
    public void linkFollow(@PathVariable String link){

    }
    @DeleteMapping("/user/{link}")
    public void linkDelete(@PathVariable String link){

    }
}
