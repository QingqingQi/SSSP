package com.software.sssp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.software.sssp.entity.Employee;
import com.software.sssp.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Transactional
	public void delete(Integer id) {
		employeeRepository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Employee getById(Integer id) {
		return employeeRepository.findOne(id);
	}
	
	@Transactional
	public void save(Employee employee) {
		if(employee.getId() == null) {
			//设置 createTime 属性
			employee.setCreateTime(new Date());
		}
		
		employeeRepository.saveAndFlush(employee);
	}
	
	@Transactional(readOnly=true)
	public Employee getByLastName(String lastName) {
		return employeeRepository.getByLastName(lastName);
	}
	
	@Transactional(readOnly=true)
	public Page<Employee> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return employeeRepository.findAll(pageable);
	}
	
}
