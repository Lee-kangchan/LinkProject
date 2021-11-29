package com.chan.link.domain.link.service;

import com.chan.link.domain.link.dto.PageDto;
import com.chan.link.domain.link.repository.LinkRepository;
import com.chan.link.global.vo.LinkVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("LinkService")
@RequiredArgsConstructor
@Slf4j
public class LinkServiceImpl implements LinkService{

    private final LinkRepository linkRepository;
    @Override
    public Slice<LinkVO> LinkRecentAll(PageDto pageDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        PageRequest pageRequest = PageRequest.of(1,20, Sort.by("linkmodifiedat").descending());
        Slice<LinkVO> list = linkRepository.findAllByModifiedAtBefore(localDateTime, pageRequest);
        return list;
    }

    @Override
    public Optional<LinkVO> LinkBestRecentAll() {

        return Optional.empty();
    }

    @Override
    public Optional<LinkVO> LinkSearch(String search) {
        return Optional.empty();
    }

    @Override
    public Optional<LinkVO> LinkUserAll() {
        return Optional.empty();
    }

    @Override
    public LinkVO LinkAdd() {
        return null;
    }

    @Override
    public LinkVO LinkUpdate() {
        return null;
    }

    @Override
    public LinkVO LinkDel() {
        return null;
    }

    @Override
    public LinkVO LinkFollow() {
        return null;
    }
}
