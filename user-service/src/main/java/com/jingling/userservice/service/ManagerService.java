package com.jingling.userservice.service;

import com.jingling.basic.po.Manager;
import com.jingling.basic.po.UserRole;
import com.jingling.basic.vo.RecycleResult;
import com.jingling.userservice.vo.ManagerVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 管理员业务接口
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-04 17:24
 * @Since: version 1.0
 **/
public interface ManagerService {

    /**
     * 分页查询管理员信息
     *
     * @param roleId
     * @param page
     * @param size
     * @return
     */
    List<ManagerVo> getAllManager(Integer roleId, Integer page, Integer size);

    /**
     * 根据角色获取管理员数量
     *
     * @param roleId
     * @return
     */
    int getManagerCount(Integer roleId);

    /**
     * 根据id获取回收员信息
     *
     * @param ids
     * @return
     */
    RecycleResult getCollectorInfoByIds(Integer[] ids);

    /**
     * 修改管理员状态
     *
     * @param managerId
     * @param status
     * @return
     */
    RecycleResult updateManagerById(Integer managerId, Integer status);

    /**
     * 增加管理员
     *
     * @param managerVo
     * @return
     */
    RecycleResult addManager(ManagerVo managerVo);

    /**
     * 根据id删除管理员
     *
     * @param managerId
     * @return
     */
    RecycleResult deleteManagerById(Integer managerId);

    /**
     * 批量删除管理员
     *
     * @param managerId
     * @return
     */
    RecycleResult deleteManagerByIds(Integer[] managerId);

    /**
     * 更新身份
     *
     * @param position
     * @param managerId
     * @return
     */
    RecycleResult updateManagerPosition(Integer position, Integer managerId);

    /**
     * 后台管理登录
     *
     *
     * @param workerId
     * @param password
     * @return
     */
    ManagerVo doLogin(String workerId, String password);
}
