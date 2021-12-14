package com.chan.link.domain.link.service;

import com.chan.link.domain.link.dto.LinkDto;
import com.chan.link.domain.link.dto.PageDto;
import com.chan.link.domain.link.dto.ResponsePostDto;
import com.chan.link.global.vo.PostVO;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface LinkService {

    ResponsePostDto LinkRecentAll (PageDto pageDto); // 링크 최신 순 모두 출력
    ResponsePostDto LinkBestRecentAll (PageDto pageDto); // 링크 최신 일주일 간 추천수 높은 순
    ResponsePostDto  LinkHashTagSearch(String tag, PageDto pageDtoZ); // link 검색 (HashTag)
    ResponsePostDto  LinkTitleSearch(String title, PageDto pageDto); // link 검색 (title)
    ResponsePostDto  LinkUserAll(PageDto pageDto); // 나의 링크 조회
    PostVO LinkAdd(LinkDto linkDto); // 링크 추가
    PostVO LinkUpdate(String id, LinkDto linkDto); // 링크 수정
    void LinkDel(String id); // 링크 삭제
    PostVO LinkFollow(); // 링크 팔로우
}
