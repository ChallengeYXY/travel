package com.yangxinyu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yangxinyu.dao.MemberDao;
import com.yangxinyu.entity.Member;
import com.yangxinyu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public void checkMember(Member member) {
        Member member1 = memberDao.getMemberByIdCard(member);
        if (member1==null){
            //用户不存在添加用户
            memberDao.addMember(member);
        }
    }
}
