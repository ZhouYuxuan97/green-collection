package com.jingling.userservice.service.impl;

import com.jingling.basic.po.Manager;
import com.jingling.basic.po.UserRole;
import com.jingling.basic.vo.RecycleResult;
import com.jingling.userservice.mapper.ManagerMapper;
import com.jingling.userservice.service.ManagerService;
import com.jingling.userservice.vo.ManagerVo;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 管理员业务实现
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-04 17:25
 * @Since: version 1.0
 **/
@Service
public class ManagerServiceImpl implements ManagerService {

    private static final Integer DEFAULT_USER_INFO_SIZE = 10;
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<ManagerVo> getAllManager(Integer roleId, Integer page, Integer size) {

        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = DEFAULT_USER_INFO_SIZE;
        }
        int start = (page - 1) * size;
        return managerMapper.getAllManager(roleId, start, size);

    }

    @Override
    public int getManagerCount(Integer roleId) {
        return managerMapper.getManagerCount(roleId);
    }

    @Override
    public RecycleResult getCollectorInfoByIds(Integer[] ids) {

        List<ManagerVo> managerList = managerMapper.getCollectorInfoByIds(ids);
        if (managerList == null || managerList.size() <= 0) {
            logger.debug("没有获取回收员到数据");
            return RecycleResult.build(500, "没有获取回收员到数据");
        }

        return RecycleResult.ok(managerList);
    }

    @Override
    @Transactional
    public RecycleResult updateManagerById(Integer managerId, Integer status) {

        ManagerVo managerVo = managerMapper.getManagerInfoById(managerId);
        if (managerVo == null) {
            logger.debug("没有此用户");
            return RecycleResult.build(500, "没有此用户");
        }
        managerVo.setIsLocked(status.toString());
        managerVo.setUpdateTime(null);
        managerMapper.updateManager(managerVo);

        return RecycleResult.ok();
    }

    @Override
    @Transactional
    public RecycleResult addManager(ManagerVo managerVo) {

        try {
            Manager manager = new Manager();
            BeanUtils.copyProperties(managerVo, manager);
            managerMapper.insertManager(manager);
            UserRole userRole = new UserRole();
            userRole.setRoleId(managerVo.getRoleId());
            userRole.setUserId(manager.getManagerId());
            managerMapper.insertUserRole(userRole);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("插入失败" + e.getMessage());
            return RecycleResult.build(500, "插入失败");
        }

        return RecycleResult.ok();
    }

    @Override
    @Transactional
    public RecycleResult deleteManagerById(Integer managerId) {

        try {
            managerMapper.deleteUserRoleByUserIds(new Integer[] {managerId});
            managerMapper.deleteManagerById(managerId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("删除失败" + e.getMessage());
            return RecycleResult.build(500, "删除失败");
        }
        return RecycleResult.ok();
    }

    @Override
    @Transactional
    public RecycleResult deleteManagerByIds(Integer[] managerIds) {

        try {
            managerMapper.deleteUserRoleByUserIds(managerIds);
            managerMapper.deleteManagerByIds(managerIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("批量删除失败" + e.getMessage());
            return RecycleResult.build(500, "批量删除失败");
        }
        return RecycleResult.ok();

    }

    @Override
    public RecycleResult updateManagerPosition(Integer position, Integer managerId) {

        long result = managerMapper.updateRole(managerId, position);
        if (result <= 0) {
            return RecycleResult.build(500, "更新失败");
        }
        return RecycleResult.ok();
    }

    @Override
    public ManagerVo doLogin(String workerId, String password) {

        ManagerVo managerVo = managerMapper.getManagerInfoById(Integer.valueOf(workerId));
        if (managerVo == null || !password.equals(managerVo.getPassword())) {
            logger.debug("此用户不存在");
            return null;
        }

        managerVo.setPassword(null);
        managerVo.setUpdateTime(null);

        return managerVo;
    }
}
