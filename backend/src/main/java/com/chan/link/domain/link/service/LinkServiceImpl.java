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
        PostVO post = linkDto.toPost(linkDto);
        linkRepository.save(post);
        return null;
    }

    @Override
    public PostVO LinkUpdate(String id, LinkDto linkDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return linkRepository.updateLink(linkDto.getTitle(), linkDto.getContent(), linkDto.getSecret(), localDateTime, id);
    }

    @Override
    public void LinkDel(String id) {
        linkRepository.deleteAllByPostId(id);
    }

    @Override
    public PostVO LinkFollow() {
        return null;
    }
}
