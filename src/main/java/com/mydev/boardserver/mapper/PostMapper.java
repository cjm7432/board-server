package com.mydev.boardserver.mapper;

import com.mydev.boardserver.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    public void register(PostDTO postDTO);
    public List<PostDTO> selectMyPosts(int accountId);
    public void updatePost(PostDTO postDTO);
    public void deletePost(int userId);
}
