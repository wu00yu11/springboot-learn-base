package com.learn.example.service.impl;

import com.learn.example.dao.StaffMapper;
import com.learn.example.model.Staff;
import com.learn.example.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @describe:
 * @create 2018-03-28 14:36
 */
@Service
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public List<Staff> getStaffList(Staff staff) {
        return staffMapper.getStaffList(staff);
    }

    @Override
    public Staff getStaffById(String id){
        return staffMapper.getStaffById(id);
    }

    @Override
    public void add(Staff staff) {
        staffMapper.add(staff);
    }

    @Override
    public void update(String id, Staff staff) {
        staffMapper.update(id,staff);
    }

    @Override
    public void delete(String id) {
        staffMapper.delete(id);
    }
}
