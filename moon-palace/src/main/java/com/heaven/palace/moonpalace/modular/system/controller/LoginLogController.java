package com.heaven.palace.moonpalace.modular.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import com.heaven.palace.moonpalace.base.controller.BaseController;
import com.heaven.palace.moonpalace.common.annotion.BussinessLog;
import com.heaven.palace.moonpalace.common.annotion.Permission;
import com.heaven.palace.moonpalace.common.constant.Const;
import com.heaven.palace.moonpalace.common.constant.factory.PageFactory;
import com.heaven.palace.moonpalace.common.persistence.model.OperationLog;
import com.heaven.palace.moonpalace.modular.system.dao.LogDao;
import com.heaven.palace.moonpalace.modular.system.warpper.LogWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 日志管理的控制器
 *
 * @author ZhouShengEn
 * @Date 2022年8月25日
 */
@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

    private static String PREFIX = "/system/log/";

    @Resource
    private LogDao logDao;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "login_log.html";
    }

    /**
     * 查询登录日志列表
     */
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName) {
        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
        List<Map<String, Object>> result = logDao.getLoginLogs(page, beginTime, endTime, logName, "createtime", false);
        page.setRecords((List<OperationLog>) new LogWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 清空日志
     */
    @BussinessLog("清空登录日志")
    @RequestMapping("/delLoginLog")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object delLog() {
        SqlRunner.db().delete("delete from login_log");
        return SUCCESS_TIP;
    }
}
