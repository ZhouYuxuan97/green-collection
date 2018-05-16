package com.jingling.userservice.controller;

import com.jingling.basic.vo.LayuiReplay;
import com.jingling.basic.vo.RecycleResult;
import com.jingling.userservice.service.ManagerService;
import com.jingling.userservice.vo.ManagerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: 管理员的控制器
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-04 17:23
 * @Since: version 1.0
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private Map<String, Object> userInfoMap;

    /**
     * 根据角色分页查询管理员信息
     *
     * @param roleId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getAllManagerInfo/{roleId}")
    public Object getAllManagerInfo(@PathVariable("roleId") Integer roleId, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        int count = managerService.getManagerCount(roleId);
        List<ManagerVo> managerList  = managerService.getAllManager(roleId, page, size);
        return new LayuiReplay<ManagerVo>(0, "OK", count, managerList);

    }

    /**
     * 根据id获取回收员的信息
     *
     * @param ids
     * @return
     */
    @GetMapping("/getCollectorInfoByIds")
    public RecycleResult getCollectorInfoByIds(Integer[] ids) {

        if (ids == null || ids.length <= 0) {
            return RecycleResult.build(500, "你传个id啊");
        }

        return managerService.getCollectorInfoByIds(ids);

    }

    /**
     * 修改回收员和管理员的状态
     *
     * @param managerId
     * @param status
     * @return
     */
    @GetMapping("/updateManager/{status}/{managerId}")
    public RecycleResult updateManagerById(@PathVariable("status") Integer status, @PathVariable("managerId") Integer managerId) {

        if (managerId == null) {
            return RecycleResult.build(500, "无有效id");
        }
        if (status == null) {
            return RecycleResult.build(500, "没选择更新操作");
        }
        return managerService.updateManagerById(managerId, status);

    }

    /**
     * 更新身份
     *
     * @param managerId
     * @return
     */
    @GetMapping("updateManagerPosition/{position}/{managerId}")
    public RecycleResult updateManagerPosition(@PathVariable("position") Integer position, @PathVariable("managerId") Integer managerId) {

        if (managerId == null) {
            return RecycleResult.build(500, "无有效id");
        }
        return managerService.updateManagerPosition(position, managerId);
    }

    /**
     * 添加管理员
     *
     * @param managerVo
     * @return
     */
    @GetMapping("/addManager")
    public RecycleResult addManager(ManagerVo managerVo) {

        if (managerVo == null) {
            return RecycleResult.build(500, "传来的信息不能为空");
        }

        return managerService.addManager(managerVo);
    }

    /**
     * 根据id删除单个管理员
     *
     * @param managerId
     * @return
     */
    @GetMapping("/deleteManager/{managerId}")
    public RecycleResult deleteManagerById(@PathVariable("managerId") Integer managerId) {

        if (managerId == null) {
            return RecycleResult.build(500, "id不能为空");
        }

        return managerService.deleteManagerById(managerId);
    }

    /**
     * 批量删除管理员
     *
     * @param managerIds
     * @return
     */
    @PostMapping("/deleteManagerByIds")
    public RecycleResult deleteManagerByIds(Integer[] managerIds) {

        if (managerIds == null || managerIds.length <= 0) {
            return RecycleResult.build(500, "id不能为空");
        }

        return managerService.deleteManagerByIds(managerIds);
    }

    /**
     * 后台管理登录
     *
     * @param workerId
     * @param password
     * @return
     */
    @PostMapping("/login")
    public RecycleResult login(String workerId, String password) {

        if (workerId == null || "".equals(workerId) || password == null || "".equals(password)) {
            return RecycleResult.build(401, "账号密码不能为空");
        }

        ManagerVo managerVo = managerService.doLogin(workerId, password);
        if (managerVo == null) {
            return RecycleResult.build(500, "用户名或密码错误");
        }

        String validate = UUID.randomUUID().toString();
        userInfoMap.put(validate, managerVo);

        return RecycleResult.ok(validate);

    }

    /**
     * 验证是否登录
     *
     * @param validate
     * @return
     */
    @GetMapping("doValidate/{validate}")
    public RecycleResult doValidate(@PathVariable("validate") String validate) {

        if (validate == null) {
            return RecycleResult.build(401, "无权限访问");
        }
        ManagerVo managerVo = (ManagerVo) userInfoMap.get(validate);
        if (managerVo == null) {
            return RecycleResult.build(401, "无权限访问");
        }

        return RecycleResult.ok(managerVo);

    }

    @GetMapping("logout/{validate}")
    public RecycleResult logout(@PathVariable("validate") String validate) {

        userInfoMap.remove(validate);

        return RecycleResult.ok();
    }

}
