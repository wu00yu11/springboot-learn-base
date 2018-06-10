package com.learn.example.dao;

import com.learn.example.model.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jingjing.zhang
 */
@Repository
public interface StaffMapper {

    /**
     * 获取所有符合条件的信息
     * @param staff
     * @return
     */
    List<Staff> getStaffList(Staff staff);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    Staff getStaffById(String id);

    /**
     * 添加信息
     * @param staff
     */
    void add(Staff staff);

    /**
     * 更新信息
     * @param id
     * @param staff
     */
    void update(@Param("id") String id, @Param("staff") Staff staff);

    /**
     * 删除信息
     * @param id
     */
    void delete(String id);
}
