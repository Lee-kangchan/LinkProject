package com.chan.link.domain.link.repository;

import com.chan.link.global.entity.HashTag;
import com.chan.link.global.vo.PostVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

//JPQL -> QueryDSL 변경
public interface LinkRepository extends JpaRepository<PostVO, Long> {
    @Query(value="SELECT * FROM post",
        countQuery = "SELECT count(*) as follow_cnt from post p join follow_link fl on fl.follow_link_post_id = p.post_id",
        nativeQuery = true)
    public Slice<PostVO> findAllOrderByModifiedDesc(Pageable pageable);
    public Slice<PostVO> findAllByPostModifiedAtAfter(LocalDateTime post_modified_at, Pageable pageable); // 일주일동안 상품

    @Query(value="SELECT * FROM post " +
            "inner join hashtag on post.post_id = hashtag.hashtag_post_id " +
            "where hashtag.hashtag_name = :tag", nativeQuery = true)
    public Slice<PostVO> findAllByHashTag(@Param("tag") String tag, Pageable pageable); // 해쉬태그 검색
    public Slice<PostVO> findAllByPostTitleLike(String title, Pageable pageable); // 제목 like 검색
    public Slice<PostVO> findAllByPostUserId(String id, Pageable pageable); // 포스트
}
