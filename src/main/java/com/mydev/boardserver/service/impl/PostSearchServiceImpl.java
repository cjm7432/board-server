package com.mydev.boardserver.service.impl;

import com.mydev.boardserver.dto.PostDTO;
import com.mydev.boardserver.dto.request.PostSearchRequest;
import com.mydev.boardserver.exception.BoardServerException;
import com.mydev.boardserver.mapper.PostSearchMapper;
import com.mydev.boardserver.service.PostSearchService;
import com.mydev.boardserver.service.SlackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostSearchServiceImpl implements PostSearchService {

    private final PostSearchMapper postSearchMapper;
    private final SlackService slackService;

    @Async
    @Cacheable(value = "getPosts", key = "'getPosts' + #postSearchRequest.getName() + #postSearchRequest.getCategoryId()") //성능리펙토링을 위해 키 정보를 세분화하여 redis에 저장
    @Override
    public List<PostDTO> getPosts(PostSearchRequest postSearchRequest) {

        List<PostDTO> postDTOList = null;
        try {
            postDTOList = postSearchMapper.selectPosts(postSearchRequest);
        } catch (RuntimeException re) {
            log.error("selectPosts 메서드 Error! {}", re.getMessage());
            //slackService.sendSlackMessage("selectPosts 실패 " +re.getMessage(),"error");
            throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage());
        }
        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostByTag(String tagName) {

        List<PostDTO> postDTOList = null;
        try {
            postDTOList = postSearchMapper.getPostByTag(tagName);
        } catch (RuntimeException re) {
            log.error("getPostByTag 메서드 Error! {}", re.getMessage());
            throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage());
        }
        return postDTOList;
    }


}
