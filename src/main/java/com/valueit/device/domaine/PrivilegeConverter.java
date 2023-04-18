package com.valueit.device.domaine;

import com.valueit.device.service.model.Privilege;

import java.util.ArrayList;
import java.util.List;

public class PrivilegeConverter {
    public static PrivilegeVo toVo(Privilege bo) {
        PrivilegeVo vo = new PrivilegeVo();
        vo.setId(bo.getId());
        vo.setPrivilege(bo.getPrivilege());
        return vo;
    }

    public static Privilege toBo(PrivilegeVo vo) {
        Privilege bo = new Privilege();
        bo.setId(vo.getId());
        bo.setPrivilege(vo.getPrivilege());
        return bo;
    }

    public static List<PrivilegeVo> toVoList(List<Privilege> boList) {
        List<PrivilegeVo> voList = new ArrayList<>();
        boList.forEach(bo -> voList.add(toVo(bo)));
        return voList;
    }

    public static List<Privilege> toBoList(List<PrivilegeVo> voList) {
        List<Privilege> boList = new ArrayList<>();
        voList.forEach(vo -> boList.add(toBo(vo)));
        return boList;
    }
}
