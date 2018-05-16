package com.jingling.userservice.mapper;

import com.jingling.basic.po.Manager;
import com.jingling.basic.po.UserRole;
import com.jingling.userservice.vo.ManagerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Description: 管理员数据访问接口
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-04 17:25
 * @Since: version 1.0
 **/
@Mapper
public interface ManagerMapper {

    /**
     * 获取所有管理员信息
     *
     * @param roleId
     * @param start
     * @param size
     * @return
     */
    List<ManagerVo> getAllManager(@Param("roleId") Integer roleId, @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 获取管理员数量
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
    List<ManagerVo> getCollectorInfoByIds(Integer[] ids);

    /**
     * 根据id获取管理员信息
     *
     * @param managerId
     * @return
     */
    ManagerVo getManagerInfoById(Integer managerId);

    /**
     * 更新管理员信息
     *
     * @param managerVo
     * @return
     */
    long updateManager(ManagerVo managerVo);

    /**
     * 更新权限信息
     *
     * @param managerId
     * @param status
     * @return
     */
    long updateRole(@Param("managerId") Integer managerId, @Param("status") Integer status);

    /**
     * 根据id删除管理员
     *
     * @param managerId
     * @return
     */
    long deleteManagerById(Integer managerId);

    /**
     * 批量删除管理员
     *
     * @param ids
     * @return
     */
    long deleteManagerByIds(Integer[] ids);

    /**
     * 根据id删除其权限
     *
     * @param userIds
     * @return
     */
    long deleteUserRoleByUserIds(Integer[] userIds);

    /**
     * 添加管理员
     *
     * @param manager
     * @return
     */
    long insertManager(Manager manager);

    /**
     * 为管理员添加权限
     *
     * @param userRole
     * @return
     */
    long insertUserRole(UserRole userRole);
}
