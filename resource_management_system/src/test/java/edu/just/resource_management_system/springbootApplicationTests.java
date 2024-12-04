package edu.just.resource_management_system;

import edu.just.resource_management_system.mapper.CarouselMapper;
import edu.just.resource_management_system.mapper.LanguageMapper;
import edu.just.resource_management_system.mapper.TagMapper;
import edu.just.resource_management_system.mapper.UserMapper;
import edu.just.resource_management_system.pojo.Admin;
import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.AdminService;
import edu.just.resource_management_system.service.ResourceService;
import edu.just.resource_management_system.service.TagService;
import edu.just.resource_management_system.service.UserService;
import edu.just.resource_management_system.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@MapperScan(basePackages = "edu.just.resource_management_system.mapper")
@EnableTransactionManagement
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
//       userService.modifyUserInfo("zzz","155151","12345678901",6L);
        userMapper.UpdateUserPassword("111",1L);
    }
    @Autowired
    private AdminService adminService;
    @Test
    void testAdminMapper(){
//        adminService.login("root","654321");
        Admin admin = adminService.selectAdminById(1L);
        assert (admin!=null);
        admin.setAdminPassword(MD5Util.encryptToMD5("99875516"));
        adminService.modifyAdminInfo(admin);
//        Admin admin0 = new Admin("admin", "654");
//        adminService.saveAdmin(admin0);
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

    @Autowired
    ResourceService resourceService;
    @Test
    void test01(){
//        resourceService.findResourcesByIdsAndKeyword(List.of(1L),"C++")
//                .stream().forEach(System.out::println);
    }
    @Autowired
    TagService tagService;
    @Test
    void test02(){
        tagService.findIdByTagAndLanguage("C++","Chinese")
                .stream().forEach(System.out::println);
    }

}
