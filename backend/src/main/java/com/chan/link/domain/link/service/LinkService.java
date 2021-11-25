package com.chan.link.domain.link.service;

import com.chan.link.global.vo.LinkVO;

import java.util.Optional;

public interface LinkService {

    Optional<LinkVO> LinkRecentAll (); // 링크 최신 순 모두 출력
    Optional<LinkVO> LinkBestRecentAll(); // 링크 최신 일주일 간 추천수 높은 순
    Optional<LinkVO> LinkSearch(String search); // link 검색 (HashTag, title)
    Optional<LinkVO> LinkUserAll(); // 나의 링크 조회
    LinkVO LinkAdd(); // 링크 추가
    LinkVO LinkUpdate(); // 링크 수정
    LinkVO LinkDel(); // 링크 삭제
    LinkVO LinkFollow(); // 링크 팔로우
}
