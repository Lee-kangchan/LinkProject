package com.chan.link.domain.link.repository;

import com.chan.link.global.vo.PostVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface LinkRepository extends JpaRepository<PostVO, Long> {
    @Query(value="SELECT * FROM post", nativeQuery = true)
    public Slice<PostVO> findAllOrderByModifiedDesc(Pageable pageable);
    public Slice<PostVO> findAllByPostModifiedAtAfter(LocalDateTime post_modified_at, Pageable pageable); // 일주일동안 상품
//    public Page<LinkVO> findAllByTitleLikeAndHashTagLikeAndCreate_atAfter(String title, String hashTag, LocalDateTime createAt, Pageable pageable);
    public Slice<PostVO> findAllByPostUserId(String id, Pageable pageable);
}
