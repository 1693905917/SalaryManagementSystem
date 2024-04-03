package com.example.salarymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.salarymanage.config.PageResult;
import com.example.salarymanage.dao.EmployeeDao;
import com.example.salarymanage.domain.*;
import com.example.salarymanage.service.IEmployeeService;
import com.example.salarymanage.service.IProfessionService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.service.impl
 * @Author: ASUS
 * @CreateTime: 2024-03-13  14:26
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class IEmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements IEmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private IProfessionService professionService;
    //设置工作时间
    static final Integer WorkTimes=100;
    //设置销售额
    static final Integer Market=10000;

    /*
     * @description:设置全部员工的工资信息
     * @author:  HZP
     * @date: 2024/3/20 17:04
     * @param:
     * @return:
     **/
    @Override
    public void setEmployeeSalaryList() {
        List<Employee> employeeList = employeeDao.selectList(null);
        for (Employee employee : employeeList) {
            //技术员工资
            if(employee.getPid()==2){
                employee.setSalary(WorkTimes*100);
                employeeDao.updateById(employee);
            }
            //销售员工资
            if(employee.getPid()==3){
                employee.setSalary((int)(Market*0.04));
                employeeDao.updateById(employee);
            }
        }
        //设置销售经理的工资
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.select("sum(salary) as sum_salary").eq("pid",3);
        Map<String, Object> map = employeeDao.selectMaps(employeeQueryWrapper).get(0);
        QueryWrapper<Employee> w1 = new QueryWrapper<>();
        w1.eq("pid",4);
        List<Employee> markerManager = employeeDao.selectList(w1);
        BigDecimal sum_salary = (BigDecimal) (map.get("sum_salary"));
        int sum=(int)(Integer.parseInt(sum_salary.toString())*0.005);
        for (Employee employee : markerManager) {
            employee.setSalary(sum+5000);
            employeeDao.updateById(employee);
        }
        //设置经理工资
        QueryWrapper<Employee> w2 = new QueryWrapper<>();
        w2.eq("pid",1);
        List<Employee> bossList = employeeDao.selectList(w2);
        for (Employee boss : bossList) {
            boss.setSalary(8000);
            employeeDao.updateById(boss);
        }
    }

    /*
     * @description:获取全部员工信息
     * @author:  HZP
     * @date: 2024/3/20 17:04
     * @param:
     * @return:
     **/
    @Override
    public List<EmployeeDTO> getEmployeeList() {
        List<Employee> employeeList = employeeDao.selectList(new QueryWrapper<Employee>().orderByDesc("salary"));
        List<EmployeeDTO> employeeDTOList=new ArrayList<>();
        for (Employee employee : employeeList) {
            int pid = employee.getPid();
            Profession profession = professionService.getById(pid);
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployee(employee);
            employeeDTO.setProfession(profession);
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    /*
     * @description:新增用户
     * @author:  HZP
     * @date: 2024/3/20 17:04
     * @param:
     * @return:
     **/
    @Override
    public void saveEmployee(Employee employee) {
        employeeDao.insert(employee);
    }

    /*
     * @description:员工分页查询
     * @author:  HZP
     * @date: 2024/3/20 17:04
     * @param:
     * @return:
     **/
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        Page<Employee> employeePage = new Page<>(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());
        employeeDao.selectPage(employeePage,new QueryWrapper<Employee>().orderByDesc("salary"));
        PageResult pageResult = new PageResult();
        pageResult.setTotal(employeePage.getPages()); //获取总页码
        pageResult.setRecords(employeePage.getRecords()); //获取每页显示的记录数
        return pageResult;
    }

    /*
     * @description:用户NameOrPid进行分页查询
     * @author:  HZP
     * @date: 2024/3/20 17:05
     * @param:
     * @return:
     **/
    @Override
    public PageResult pageQueryByNameOrPid(EmployeePageByNameOrPidDTO employeePageByNameOrPidDTO) {
        IPage page=new Page(employeePageByNameOrPidDTO.getPage(),employeePageByNameOrPidDTO.getPageSize());
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.like(employeePageByNameOrPidDTO.getName()!=null,"name",employeePageByNameOrPidDTO.getName());
        employeeQueryWrapper.like(employeePageByNameOrPidDTO.getPid()!=null,"pid",employeePageByNameOrPidDTO.getPid());
        employeeQueryWrapper.orderByDesc("salary");
        IPage employeePage = employeeDao.selectPage(page, employeeQueryWrapper);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(employeePage.getPages()); //获取总页码
        pageResult.setRecords(employeePage.getRecords()); //获取每页显示的记录数
        return pageResult;
    }

    /*
     * @description:各销售经理的工资计算及最终按工资举行的排序
     * @author:  HZP
     * @date: 2024/3/20 17:05
     * @param:
     * @return:
     **/
    @Override
    public PageResult pageQueryByMarketManagers(EmployeePageQueryDTO employeePageQueryDTO) {
        Page<Employee> employeePage = new Page<>(employeePageQueryDTO.getPage(),employeePageQueryDTO.getPageSize());
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.eq("pid",4);
        employeeQueryWrapper.orderByDesc("salary");
        employeeDao.selectPage(employeePage,employeeQueryWrapper);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(employeePage.getPages()); //获取总页码
        pageResult.setRecords(employeePage.getRecords()); //获取每页显示的记录数
        return pageResult;
    }

    @Override
    public IPage<Employee> getPage(int currentPage, int pageSize, EmployeeVO employeeVo) {
        Integer pid=0;
        IPage page = new Page(currentPage,pageSize);
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<Employee>();
        lqw.like(Strings.isNotEmpty(employeeVo.getName()),Employee::getName,employeeVo.getName());
        switch (employeeVo.getType()){
            case "经理":
                pid=1;break;
            case "技术员":
                pid=2;break;
            case "销售员":
                pid=3;break;
            case "销售经理":
                pid=4;break;
            default:
                break;
        }
        lqw.like(Strings.isNotEmpty(employeeVo.getType()),Employee::getPid,pid);
        return employeeDao.selectPage(page,lqw);
    }

    @Override
    public Boolean modify(Employee employee) {
        return employeeDao.updateById(employee)>0;
    }

    @Override
    public Boolean delete(Integer id) {
        return employeeDao.deleteById(id)>0;
    }
}
