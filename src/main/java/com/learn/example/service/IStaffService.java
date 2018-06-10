package com.learn.example.service;

import com.learn.example.model.Staff;

import java.util.List;

/**
 * @author jingjing.zhang
 */
public interface IStaffService {
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
    void update(String id, Staff staff);

    /**
     * 删除信息
     * @param id
     */
    void delete(String id);
}
