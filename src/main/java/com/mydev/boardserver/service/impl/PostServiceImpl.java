package com.mydev.boardserver.service.impl;

import com.mydev.boardserver.dto.CommentDTO;
import com.mydev.boardserver.dto.PostDTO;
import com.mydev.boardserver.dto.TagDTO;
import com.mydev.boardserver.dto.UserDTO;
import com.mydev.boardserver.exception.BoardServerException;
import com.mydev.boardserver.mapper.CommentMapper;
import com.mydev.boardserver.mapper.PostMapper;
import com.mydev.boardserver.mapper.TagMapper;
import com.mydev.boardserver.mapper.UserProfileMapper;
import com.mydev.boardserver.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final UserProfileMapper userProfileMapper;
    private final CommentMapper commentMapper;
    private final TagMapper tagMapper;

    @Override
    public void register(String id, PostDTO postDTO) {

        UserDTO memberInfo = userProfileMapper.getUserProfile(id);
        postDTO.setUserId(memberInfo.getId());
        postDTO.setCreateTime(new Date());

        if(memberInfo != null) {
            try {
                postMapper.register(postDTO);
            } catch (RuntimeException e) {
                log.error("registerPost 실패");
                throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
            Integer postId = postDTO.getId();
            for(int i = 0; i < postDTO.getTagDTOList().size(); i++) {
                TagDTO tagDTO = postDTO.getTagDTOList().get(i);
                tagMapper.register(tagDTO);
                Integer tagId = tagDTO.getId();
                tagMapper.createPostTag(tagId, postId);
            }
        } else {
            log.error("register ERROR! {}", postDTO);
            throw new RuntimeException("register ERROR! 게시판 등록 메서드를 확인해 주세요." + " params : " + postDTO);
        }
    }

    @Override
    public List<PostDTO> getMyPosts(int accountId) {

        List<PostDTO> postDTOList = null;
        try {
            postDTOList = postMapper.selectMyPosts(accountId);
        } catch (RuntimeException e) {
            log.error("getMyPosts 실패");
            throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return postDTOList;
        //return postMapper.selectMyPosts(accountId);
    }

    @Override
    public void update(PostDTO postDTO) {

        if(postDTO != null && postDTO.getId() != 0) {
            try {
                postMapper.updatePost(postDTO);
            } catch (RuntimeException e) {
                log.error("updatePost 실패");
                throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            log.error("update ERROR! {}", postDTO);
            throw new RuntimeException("update ERROR! 게시판 수정 메서드를 확인해 주세요." + " params : " + postDTO);
        }
    }

    @Override
    public void delete(int userId, int postId) {

        if(userId != 0 && postId != 0) {
            List<PostDTO> myPosts = postMapper.selectMyPosts(userId);

            if(!myPosts.isEmpty()) {
                try {
                    postMapper.deletePost(postId);
                } catch (RuntimeException e) {
                    log.error("deletePost 실패");
                    throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
            } else {
                log.info("삭제할 게시글이 없습니다. postId={}", postId);
            }

        } else {
            log.error("delete ERROR! {}", postId);
            throw new RuntimeException("delete ERROR! 게시판 삭제 메서드를 확인해 주세요." + "param : " + postId);
        }
    }

    @Override
    public void registerComment(CommentDTO commentDTO) {

        if(commentDTO != null) {
            try {
                commentMapper.register(commentDTO);
            } catch (RuntimeException e) {
                log.error("registerComment 실패");
                throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            log.error("registerComment Error! {}", commentDTO);
            throw new RuntimeException("registerComment ERROR! 댓글 등록 메서드를 확인해 주세요." + " params : " + commentDTO);
        }
    }

    @Override
    public void updateComment(CommentDTO commentDTO) {

        if(commentDTO != null) {
            try {
                commentMapper.updateComment(commentDTO);
            } catch (RuntimeException e) {
                log.error("updateComment 실패");
                throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            log.error("updateComment Error! {}", commentDTO);
            throw new RuntimeException("updateComment ERROR! 댓글 수정 메서드를 확인해 주세요." + " params : " + commentDTO);
        }
    }

    @Override
    public void deletePostComment(int userId, int commentId) {

        if(userId != 0 && commentId != 0) {
            try {
                commentMapper.deletePostComment(commentId);
            } catch (RuntimeException e) {
                log.error("deleteComment 실패");
                throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            log.error("deletePostComment Error! {}", commentId);
            throw new RuntimeException("deletePostComment ERROR! 댓글 삭제 메서드를 확인해 주세요." + "param : " + commentId);
        }
    }

    @Override
    public void registerTag(TagDTO tagDTO) {

        if(tagDTO != null) {
            try {
                tagMapper.register(tagDTO);
            } catch (RuntimeException e) {
                log.error("registerTag 실패");
                throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            log.error("registerTag Error! {}", tagDTO);
            throw new RuntimeException("registerTag ERROR! 태그 등록 메서드를 확인해 주세요." + "param : " + tagDTO);
        }
    }

    @Override
    public void updateTag(TagDTO tagDTO) {

        if(tagDTO != null) {
            try {
                tagMapper.updateTag(tagDTO);
            } catch (RuntimeException e) {
                log.error("updateTag 실패");
                throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            log.error("updateTag Error! {}", tagDTO);
            throw new RuntimeException("updateTag ERROR! 태그 수정 메서드를 확인해 주세요." + "param : " + tagDTO);
        }
    }

    @Override
    public void deletePostTag(int userId, int tagId) {

        if(userId != 0 && tagId != 0) {
            try {
                tagMapper.deletePostTag(tagId);
            } catch (RuntimeException e) {
                log.error("deleteTag 실패");
                throw new BoardServerException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            log.error("deletePostTag Error! {}", tagId);
            throw new RuntimeException("deletePostTag ERROR! 태그 삭제 메서드를 확인해 주세요." + "param : " + tagId);
        }
    }
}
