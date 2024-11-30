package edu.just.resource_management_system;

import edu.just.resource_management_system.mapper.CarouselMapper;
import edu.just.resource_management_system.mapper.LanguageMapper;
import edu.just.resource_management_system.mapper.TagMapper;
import edu.just.resource_management_system.mapper.UserMapper;
import edu.just.resource_management_system.pojo.Admin;
import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.AdminService;
import edu.just.resource_management_system.service.UserService;
import edu.just.resource_management_system.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan(basePackages = "edu.just.resource_management_system.mapper")
@SpringBootTest
class springbootApplicationTests {


    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    /**
     * 这是user登录接口的测试
     */
    @Test
    void UserMapperLoginTest(){
        /**
         * 从前端接收到参数username password
         * 返回user 登录成功user不为空 登录失败为空
         */
        User user = userMapper.login("...", "...");
        if (user == null){
            //重新输入
        }
        else {
            //登录成功的业务
        }
    }

    /**
     *这是用户注册的接口
     * 不用担心 springmvc 会自动将表单数据转换成user
     * 你要做的就是在controller形参添加 User user
     */
    @Test
    void UserMapperSetPwdTest(){
        userMapper.register(new User());
    }

    @Test
    void testUserMapper(){
        User user = userService.findUserById(1L);
        System.out.println(user);
        user.setUserPassword(MD5Util.encryptToMD5("789"));
        user.setPhoneNumber("98765432101");
        userService.modifyUserInfo(user);
        System.out.println(userService.findUserById(1L));
        user.setUserPassword(MD5Util.encryptToMD5(user.getUserPassword()));
        user.setPhoneNumber("12345678901");
        userService.modifyUserInfo(user);

        User newUser = userService.login("root", MD5Util.encryptToMD5("123"));
        assert (newUser != null);
        User u = new User("jack", MD5Util.encryptToMD5("456"), "123456@qq.com", "12345678901");
        userService.saveUser(u);
    }
    @Autowired
    private AdminService adminService;
    @Test
    void testAdminMapper(){
        adminService.login("root","123456");
        Admin admin = adminService.selectAdminById(1L);
        assert (admin!=null);
        admin.setAdminPassword(MD5Util.encryptToMD5(admin.getAdminPassword()));
        adminService.modifyAdminInfo(admin);
        Admin admin0 = new Admin("admin", "654");
        adminService.saveAdmin(admin0);
    }

    @Autowired
    CarouselMapper carouselMapper;
    @Test
    void testCarouselMapper(){
        carouselMapper.SelectTopNCarousels(2).forEach(System.out::println);
    }
    @Autowired
    LanguageMapper languageMapper;
    @Test
    void testLanguageMapper(){
        languageMapper.selectAllLanguages().forEach(System.out::println);
    }
    @Autowired
    TagMapper tagMapper;
    @Test
    void testTagMapper(){
        tagMapper.selectAllTags().forEach(System.out::println);
    }


}
