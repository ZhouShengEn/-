package com.heaven.palace.purplecloudpalace.modular.system.warpper;

import com.heaven.palace.purplecloudpalace.base.warpper.BaseControllerWarpper;
import com.heaven.palace.purplecloudpalace.common.constant.factory.ConstantFactory;

import java.util.Map;

/**
 * 部门列表的包装
 *
 * @author ZhouShengEn
 * @date 2022年8月25日
 */
public class NoticeWrapper extends BaseControllerWarpper {

    public NoticeWrapper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        Integer creater = (Integer) map.get("creater");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
    }

}
