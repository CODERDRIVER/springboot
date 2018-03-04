package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.*;
import com.example.springbootdemo.service.BlogService;
import com.example.springbootdemo.service.BloggerService;
import com.example.springbootdemo.service.CategoryService;
import com.example.springbootdemo.service.ManagerService;
import com.example.springbootdemo.utils.ImageUtil;
import com.example.springbootdemo.utils.JwtHelper;
import com.example.springbootdemo.utils.SendEmail;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 博主的控制器
 */
@RestController
@RequestMapping("/blogger")
public class BloggerController {

    @Autowired
    private BloggerService bloggerService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private Audience audience;



    //用户注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Record register(String username,String password)
    {
        Record record = new Record();
        Blogger blogger = new Blogger(username,password);
        bloggerService.registerBlogger(blogger);

        record.setCode(0);
        record.setMsg("注册成功");
        return record;
    }

    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Record login(@RequestParam(name = "username",required = true) String username,
                        @RequestParam(name = "password",required = true) String password)
    {
        List<Blogger> bloggers = bloggerService.findAllBlogger();
        Record record = new Record();
        for(Blogger blogger:bloggers)
        {
            if(blogger.getUsername().equals(username))
            {
                if(blogger.getPassword().equals(password))
                {
                    record.setCode(0);
                    record.setMsg("登陆成功");

                    blogger = bloggerService.findBloggerByUsername(username);

                    String token = JwtHelper.createJWT(
                            username,
                            blogger.getId()+"",
                            null,
                            audience.getClientId(),
                            audience.getName(),
                            audience.getExpiresSecond()*1000,
                            audience.getBase64Secret()
                            );
                    String jwtStr = "jwt;"+token;
                    record.setToken(jwtStr);
                    return record;
                }else{
                    record.setCode(0);
                    record.setMsg("密码错误");
                }
            }else{
                record.setCode(0);
                record.setMsg("用户名错误");
            }

        }
        return record;
    }
    //发表评论
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.POST)
    public Record submitBlog(@PathVariable("id")Long id, String content, Date createTime)
    {
        System.out.println(content);
        Record record = new Record();
        //查询是否存在该博主
        Blogger blogger = managerService.findBloggerById(id);
        if(blogger==null)
        {
            record.setCode(1);
            record.setMsg("该博主不存在");
            return record;
        }
        Comment comment = new Comment();
        comment.setBlogger(blogger);
        comment.setContent(content);
        comment.setCreateTime(createTime);
        bloggerService.submitComment(comment);
        record.setMsg("发表成功");
        record.setCode(0);
        return record;
    }
    //删除评论
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.DELETE)
    public Record deleteComment(@PathVariable("id")Long id)
    {
        Record record = new Record();
        bloggerService.deleteComment(id);
        record.setCode(0);
        record.setMsg("删除成功");
        return record;
    }

    //发表博客
    /**
     *
     * @param theme 博客主题
     * @param content 博客内容
     * @param type  博客类型
     * @param id 博主id
     * @return
     */
    @RequestMapping(value = "/blog",method = RequestMethod.POST)
    public Record submitBlog(String theme,String content,String type,Long id)
    {
        Record record = new Record();
        Blog blog = new Blog();
        Blogger blogger = managerService.findBloggerById(id);   //查询是否有该博主
        if(blogger==null)
        {
            record.setCode(1);
            record.setMsg("该博主不存在");
        }else{
            blog.setBlogger(blogger);
            blog.setTheme(theme);
            blog.setContent(content);
            Category category = categoryService.findCategoryByName(type);
            if(category==null){
                //该类别不存在
                category = new Category(type);
            }
            blog.setCategory(category);
            bloggerService.submitBlog(category,blog);
        }
        return record;
    }

    //删除博客
    @RequestMapping(value = "/blog/{id}",method = RequestMethod.DELETE)
    public Record deleteBlog(@PathVariable("id")Long id)
    {
        Record record = new Record();
        if(bloggerService.deleteBlog(id)){
            record.setCode(0);
            record.setMsg("删除成功");
            return record;
        }else{
            record.setCode(1);
            record.setMsg("删除失败");
            return record;
        }
    }

    //更改博客的信息

    /**
     * 需要知道
     *      博主的id
     *      修改的内容
     *
     * @return
     */
    public Record updateBlog(Long id,String theme,String type,String content){
        Blog blog = new Blog();
        Record record = new Record();
        Blogger blogger = managerService.findBloggerById(id);
        if(blogger==null)
        {
            record.setCode(0);
            record.setMsg("该博主不存在");
            return record;
        }else{
            blog.setBlogger(blogger);
            blog.setContent(content);
            blog.setTheme(theme);
            //查询是否有该种类型
            Category category = categoryService.findCategoryByName(type);
            if(category==null)
            {
                categoryService.addCatatory(category);
            }
            blog.setCategory(category);
            bloggerService.updateBlog(blog);
            record.setCode(0);
            record.setMsg("更新成功");
            return record;
        }
    }


    //查询所有的博客
    @RequestMapping(value = "/blogs",method = RequestMethod.GET)
    public List<Blog> getAllBlogs(){
        return blogService.getAllBlog();
    }

    //查询某个博主的所有博客
    @RequestMapping(value = "/blogs/{id}",method = RequestMethod.GET)
    public List<Blog> getAllBlogsByBloggerId(@PathVariable("id") Long id)
    {
        return blogService.getAllBlogByBloggerId(id);
    }

    //发送邮件
    @RequestMapping(value = "/mail",method = RequestMethod.POST)
    public Record sendMail(String destMail,String subject,String content)
    {
        Record record = new Record();
        if(!SendEmail.validateMail(destMail)){
            record.setMsg("邮箱不正确");
            record.setCode(1);
        }else{
            try {
                SendEmail.sendEmailWithoutAffix(destMail,subject,content);
                record.setCode(0);
                record.setMsg("发送成功");
                return record;
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
                record.setCode(0);
                record.setMsg("发送失败");
            }
        }
        return record;
    }

    //图片上传
    @RequestMapping(value = "/image/{id}",method = RequestMethod.POST)
    public Record uploadImg(@PathVariable Long id, MultipartFile file){
        Record record = new Record();
        if(file==null){
            record.setCode(1);
            record.setMsg("请添加一个图片");
            return record;
        }
        //将图片写入服务器
        if(ImageUtil.saveImg(file)){
            String filename = file.getOriginalFilename();
            bloggerService.uploadImg(id,filename);
            record.setCode(0);
            record.setMsg("上传成功");
            return record;

        }
        record.setCode(1);
        record.setMsg("上传失败");
        return record;
    }


}
