package cn.china.user.service;


import cn.china.common.entity.User;

public interface UserService {
    int deleteByPrimaryKey(Integer id);
    int insert(User record);
    int insertSelective(User record);
    User selectByPrimaryKey(Integer id);
    User selectByUsername(String username);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);
}
