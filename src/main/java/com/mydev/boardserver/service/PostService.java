package com.mydev.boardserver.service;

import com.mydev.boardserver.dto.CommentDTO;
import com.mydev.boardserver.dto.PostDTO;
import com.mydev.boardserver.dto.TagDTO;

import java.util.List;
public interface PostService {

    void register(String id, PostDTO postDTO);
    List<PostDTO> getMyPosts(int accountId);
    void update(PostDTO postDTO);
    void delete(int userId, int postId);

    void registerComment(CommentDTO commentDTO);
    void updateComment(CommentDTO commentDTO);
    void deletePostComment(int userId, int commentId);

    void registerTag(TagDTO tagDTO);
    void updateTag(TagDTO tagDTO);
    void deletePostTag(int userId, int tagId);
}
