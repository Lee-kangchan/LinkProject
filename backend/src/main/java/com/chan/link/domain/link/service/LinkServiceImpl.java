package com.chan.link.domain.link.service;

import com.chan.link.domain.link.dto.LinkDto;
import com.chan.link.domain.link.dto.PageDto;
import com.chan.link.domain.link.repository.LinkRepository;
import com.chan.link.global.entity.HashTag;
import com.chan.link.global.util.SecurityUtil;
import com.chan.link.global.vo.PostVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service("LinkService")
@RequiredArgsConstructor
@Slf4j
public class LinkServiceImpl implements LinkService{

    private final LinkRepository linkRepository;
    @Override
    public Slice<PostVO> LinkRecentAll(PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(0,20, Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllOrderByModifiedDesc(pageRequest);
        return list;
    }

    @Override
    public Optional<PostVO> LinkBestRecentAll(PageDto pageDto) {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(7);
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllByPostModifiedAtAfter(localDateTime, pageRequest);
        return Optional.empty();
    }

    @Override
    public Optional<PostVO> LinkHashTagSearch(String tag) {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllByHashTag(tag, pageRequest);
        return Optional.empty();
    }

    @Override
    public Optional<PostVO> LinkTitleSearch(String title) {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllByPostTitleLike(title, pageRequest);
        return Optional.empty();
    }

    @Override
    public Optional<PostVO> LinkUserAll() {
        String user_id = SecurityUtil.getCurrentUserId();
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllByPostUserId(user_id, pageRequest);
        return Optional.empty();
    }

    @Override
    public PostVO LinkAdd(LinkDto linkDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Set<HashTag> hashTag = new HashSet<>();

        // JWT 데이터 불러오기
        String JwtSaveEmail = SecurityUtil.getCurrentMemberId().toString();
        String JwtSaveId = SecurityUtil.getCurrentUserId();
        String uuid = UUID.randomUUID().toString();

        // HashTag 객체 데이터로 변경하여 리스트 저장
        for(String tag : linkDto.getTagList()){
            HashTag tagData = HashTag.builder()
                    .hashtagName(tag)
                    .postId(uuid)
                    .build();
            hashTag.add(tagData);
        }

        PostVO link = PostVO.builder()
                .postId(uuid)
                .postTitle(linkDto.getTitle())
                .postContent(linkDto.getContent())
                .postImage(linkDto.getImage())
                .postSecret(1)
                .hashTag(hashTag)
                .postCreateAt(localDateTime)
                .postModifiedAt(localDateTime)
                .postUserId(JwtSaveId)
                .postLink(linkDto.getLink())
                .build();

        linkRepository.save(link);
        return null;
    }

    @Override
    public PostVO LinkUpdate() {
        return null;
    }

    @Override
    public PostVO LinkDel() {
        return null;
    }

    @Override
    public PostVO LinkFollow() {
        return null;
    }
}
