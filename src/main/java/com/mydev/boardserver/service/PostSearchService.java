package com.mydev.boardserver.service;

import com.mydev.boardserver.dto.PostDTO;
import com.mydev.boardserver.dto.request.PostSearchRequest;

import java.util.List;

public interface PostSearchService {

    List<PostDTO> getPosts(PostSearchRequest postSearchRequest);
    List<PostDTO> getPostByTag(String tagName);
}
