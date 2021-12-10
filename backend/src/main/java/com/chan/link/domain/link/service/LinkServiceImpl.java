package com.chan.link.domain.link.service;

import com.chan.link.domain.link.dto.LinkDto;
import com.chan.link.domain.link.dto.PageDto;
import com.chan.link.domain.link.dto.ResponsePostDto;
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


    /*
     * SELECT SERVICE
     * */

    @Override
    public ResponsePostDto LinkRecentAll(PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllOrderByModifiedDesc(pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }
    @Override
    public ResponsePostDto LinkBestRecentAll(PageDto pageDto) {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(7);
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllByPostModifiedAtAfter(localDateTime, pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }

    @Override
    public ResponsePostDto  LinkHashTagSearch(String tag, PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllByHashTag(tag, pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }

    @Override
    public ResponsePostDto  LinkTitleSearch(String title, PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllByPostTitleLike(title, pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }

    @Override
    public ResponsePostDto  LinkUserAll(PageDto pageDto) {
        String user_id = SecurityUtil.getCurrentUserId();
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by("post_modified_at").descending());
        Slice<PostVO> list = linkRepository.findAllByPostUserId(user_id, pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }


    /*
    * INSERT UPDATE DELETE SERVICE
    * */
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

        PostVO post = PostVO.builder()
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

        linkRepository.save(post);
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
