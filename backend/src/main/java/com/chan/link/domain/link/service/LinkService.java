package com.chan.link.domain.link.service;

import com.chan.link.domain.link.dto.LinkDto;
import com.chan.link.domain.link.dto.PageDto;
import com.chan.link.global.vo.PostVO;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface LinkService {

    Slice<PostVO> LinkRecentAll (PageDto pageDto); // 링크 최신 순 모두 출력
    Optional<PostVO> LinkBestRecentAll(); // 링크 최신 일주일 간 추천수 높은 순
    Optional<PostVO> LinkSearch(String search); // link 검색 (HashTag, title)
    Optional<PostVO> LinkUserAll(); // 나의 링크 조회
    PostVO LinkAdd(LinkDto linkDto); // 링크 추가
    PostVO LinkUpdate(); // 링크 수정
    PostVO LinkDel(); // 링크 삭제
    PostVO LinkFollow(); // 링크 팔로우
}
