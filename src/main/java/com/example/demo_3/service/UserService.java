package com.example.demo_3.service;

import com.example.demo_3.domain.User;
import com.example.demo_3.domain.UserExample;
import com.example.demo_3.exeception.BusinessException;
import com.example.demo_3.exeception.BusinessExceptionCode;
import com.example.demo_3.mapper.UserMapper;
import com.example.demo_3.req.UserLoginReq;
import com.example.demo_3.req.UserQueryReq;
import com.example.demo_3.req.UserResetPasswordReq;
import com.example.demo_3.req.UserSaveReq;
import com.example.demo_3.resp.PageResp;
import com.example.demo_3.resp.UserLoginResp;
import com.example.demo_3.resp.UserQueryResp;
import com.example.demo_3.util.CopyUtil;
import com.example.demo_3.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private SnowFlake snowFlake;

    public PageResp<UserQueryResp> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getLoginName())) {

            criteria.andLoginNameEqualTo(req.getLoginName());
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> userList = userMapper.selectByExample(userExample);


        PageInfo<User> pageInfo = new PageInfo<>(userList);
        LOG.info("总行数:{}", pageInfo.getTotal());
        LOG.info("总页数:{}", pageInfo.getPages());


//        List<UserResp> respList = new ArrayList<>();
//        for (User user : userList) {
////            UserResp userResp = new UserResp();
////            BeanUtils.copyProperties(user,userResp);
//            //对象复制
//            UserResp userResp = CopyUtil.copy(user,UserResp.class);
//            respList.add(userResp);
//        }
        List<UserQueryResp> list = CopyUtil.copyList(userList, UserQueryResp.class);
        PageResp<UserQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            User userDB = selectByLoginName(user.getLoginName());

            System.out.println("userDB-:***************                 :" + userDB);

            if (ObjectUtils.isEmpty(userDB)) {
                //插入
                System.out.println("是否需要插入");
                user.setId(snowFlake.nextId());
                userMapper.insert(user);
            } else {
                //用户名已存在
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        } else {
            //更新，现将LoginName清空，然后使用updateByPrimaryKeySelective，
            //只更新有值的属性，如果没有值的话，就不更新这个字段。
            user.setLoginName(null);
            user.setPassword(null);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }



    public void delete(Long id) {
        userMapper.deleteByPrimaryKey((id));
    }

    /**
     * 修改密码
     *
     * @param req
     */
    public void resetPassword(UserResetPasswordReq req) {
        User user = CopyUtil.copy(req, User.class);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 登录
     */
    public UserLoginResp login(UserLoginReq req) {
        User userDb = selectByLoginName(req.getLoginName());
        if (ObjectUtils.isEmpty(userDb)) {
            // 用户名不存在
            LOG.info("用户名不存在, {}", req.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        } else {
            if (userDb.getPassword().equals(req.getPassword())) {
                // 登录成功
                UserLoginResp userLoginResp = CopyUtil.copy(userDb, UserLoginResp.class);
                return userLoginResp;
            } else {
                // 密码不对
                LOG.info("密码不对, 输入密码：{}, 数据库密码：{}", req.getPassword(), userDb.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }

    //保存token
    public void saveToken(UserLoginReq req, Long token) {

        User user = CopyUtil.copy(req, User.class);
        User userDB = selectByLoginName(user.getLoginName());
        userDB.setToken(token.toString());
        userMapper.updateByPrimaryKey(userDB);
    }

    //删除token
    public void deleteToken(String token) {
        User userDB = selectByToken(token);
        userDB.setToken(null);
        userMapper.updateByPrimaryKey(userDB);
    }
    public User selectByLoginName(String LoginName) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(LoginName);
        List<User> userList = userMapper.selectByExample(userExample);

        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }
    public User selectByToken(String token) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andTokenEqualTo(token);
        List<User> userList = userMapper.selectByExample(userExample);

        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

}