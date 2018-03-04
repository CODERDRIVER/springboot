package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.Blogger;
import com.example.springbootdemo.bean.Manager;
import com.example.springbootdemo.bean.Record;
import com.example.springbootdemo.dao.ManagerRepository;
import com.example.springbootdemo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    //管理员登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Record loginManager(String username, String password)
    {
        List<Manager>  lists  = managerService.findAllManager();
        Record record = new Record();
        for(Manager manager:lists)
        {
            if(manager.getUsername().equals(username))
            {
                if(manager.getPassword().equals(password))
                {
                    record.setCode(0);  //0表示成功
                    record.setMsg("登陆成功");
                }else{
                    record.setCode(1);
                    record.setMsg("登录失败，密码不正确");
                }
            }else{
                record.setCode(1);
                record.setMsg("登录失败，密码不正确");
            }
        }
        return record;
    }

    //删除博主
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Record deleteBlogger(@PathVariable("id")Long id)
    {
        Record record = new Record();
        managerService.deleteBlogger(id);
        record.setCode(0);
        record.setMsg("删除成功");
        return record;
    }

    //修改博主信息
    @RequestMapping(value = "/blogger/{id}",method = RequestMethod.PUT)
    public Record updateBlogger(@PathVariable("id")Long id,String username,String password)
    {
        Blogger blogger = new Blogger();
        blogger.setId(id);
        blogger.setUsername(username);
        blogger.setPassword(password);
        managerService.updateBlogger(blogger);
        Record record = new Record("更新成功",0);
        return record;
    }
}
