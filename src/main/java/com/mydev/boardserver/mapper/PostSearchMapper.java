package com.mydev.boardserver.mapper;

import com.mydev.boardserver.dto.PostDTO;
import com.mydev.boardserver.dto.request.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostSearchMapper {

    public List<PostDTO> selectPosts(PostSearchRequest postSearchRequest);
    public List<PostDTO> getPostByTag(String tagName);
}
