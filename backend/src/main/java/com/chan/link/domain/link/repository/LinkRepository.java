package com.chan.link.domain.link.repository;

import com.chan.link.global.vo.LinkVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LinkRepository extends JpaRepository<LinkVO, Long> {
    public Slice<LinkVO> findAllByModifiedAtBefore(LocalDateTime modifiedAt, Pageable pageable);
    public Slice<LinkVO> findAllByModifiedAtAfter(LocalDateTime modifiedAt, Slice<LinkVO> slice); // 일주일동안 상품
//    public Page<LinkVO> findAllByTitleLikeAndHashTagLikeAndCreate_atAfter(String title, String hashTag, LocalDateTime createAt, Pageable pageable);
    public Slice<LinkVO> findAllByUserSeq(Long seq, Pageable pageable);
}
