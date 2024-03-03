package com.mydev.boardserver.mapper;


import com.mydev.boardserver.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserProfileMapper {
    public UserDTO getUserProfile(@Param("userId") String userId);

    int insertUserProfile(@Param("id") String id, @Param("password") String password, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);

    int updateUserProfile(@Param("id") String id, @Param("password") String password, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);

    int deleteUserProfile(@Param("userId") String userId);

    public int register(UserDTO userDTO);

    public UserDTO findByUserIdAndPassword(@Param("userId") String userId,
                                           @Param("password") String password);

    int idCheck(String userId);

    public int updatePassword(UserDTO userDTO);

    public int updateAddress(UserDTO userDTO);
}
