package com.chan.link.domain.link.repository;

import com.chan.link.global.vo.LinkVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LinkRepository extends JpaRepository<LinkVO, Long> {
    public Page<LinkVO> findAll(Pageable pageable);
    public Page<LinkVO> findAllByCreateAtAfter(LocalDateTime createAt, Pageable pageable);
//    public Page<LinkVO> findAllByTitleLikeAndHashTagLikeAndCreate_atAfter(String title, String hashTag, LocalDateTime createAt, Pageable pageable);
    public Page<LinkVO> findAllByUserSeq(Long seq, Pageable pageable);
}
