package com.yangxinyu.dao;

import com.yangxinyu.entity.Member;

/**
 * @BelongsProject : travel
 * @BelongsPackage : com.yangxinyu.dao
 * @Date : 2023/3/1 11:09
 * @Author : 星宇
 * @Description :
 */
public interface MemberDao {
    /**
     * 通过身份证号查询会员
     * @param member
     * @return
     */
    Member getMemberByIdCard(Member member);

    /**
     * 添加用户
     * @param member
     */
    void addMember(Member member);
}
