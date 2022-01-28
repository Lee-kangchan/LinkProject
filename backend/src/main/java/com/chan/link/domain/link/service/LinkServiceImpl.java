package com.chan.link.domain.link.service;

import com.chan.link.domain.link.dto.LinkDto;
import com.chan.link.domain.link.dto.PageDto;
import com.chan.link.domain.link.dto.ResponsePostDto;
import com.chan.link.domain.link.repository.LinkRepository;
import com.chan.link.global.util.SecurityUtil;
import com.chan.link.global.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        Slice<Post> list = linkRepository.findAllOrderByModifiedDesc(pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }
    @Override
    public ResponsePostDto LinkBestRecentAll(PageDto pageDto) {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(7);
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by("post_modified_at").descending());
        Slice<Post> list = linkRepository.findAllByPostModifiedAtAfter(localDateTime, pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }

    @Override
    public ResponsePostDto  LinkHashTagSearch(String tag, PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by("post_modified_at").descending());
        Slice<Post> list = linkRepository.findAllByHashTag(tag, pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }

    @Override
    public ResponsePostDto  LinkTitleSearch(String title, PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by("post_modified_at").descending());
        Slice<Post> list = linkRepository.findAllByPostTitleLike(title, pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }

    @Override
    public ResponsePostDto  LinkUserAll(PageDto pageDto) {
        String user_id = SecurityUtil.getCurrentUserId().get();
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), Sort.by("post_modified_at").descending());
        Slice<Post> list = linkRepository.findAllByPostUserId(user_id, pageRequest);
        return new ResponsePostDto().builder().postVO(list.getContent()).pageDto(pageDto).build();
    }


    /*
    * INSERT UPDATE DELETE SERVICE
    * */
    @Override
    public Post LinkAdd(LinkDto linkDto) {
        Post post = linkDto.toPost(linkDto);
        linkRepository.save(post);
        return null;
    }

    @Override
    public Post LinkUpdate(String id, LinkDto linkDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return linkRepository.updateLink(linkDto.getTitle(), linkDto.getContent(), linkDto.getSecret(), localDateTime, id);
    }

    @Override
    public void LinkDel(String id) {
        linkRepository.deleteAllByPostId(id);
    }

    @Override
    public Post LinkFollow() {
        return null;
    }
}
