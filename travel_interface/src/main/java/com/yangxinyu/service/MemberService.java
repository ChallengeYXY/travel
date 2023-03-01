package com.yangxinyu.service;

import com.yangxinyu.entity.Member;

public interface MemberService {
    /**
     * 检查用户信息是否可以进行预约
     * @param member
     * @return
     */
     void checkMember(Member member);
}
