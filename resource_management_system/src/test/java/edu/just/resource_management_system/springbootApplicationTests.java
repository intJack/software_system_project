package edu.just.resource_management_system;

import edu.just.resource_management_system.mapper.*;
import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.AdminService;
import edu.just.resource_management_system.service.ResourceService;
import edu.just.resource_management_system.service.TagService;
import edu.just.resource_management_system.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan(basePackages = "edu.just.resource_management_system.mapper")
@EnableTransactionManagement
@SpringBootTest
class springbootApplicationTests {


    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResourceMapper resourceMapper;


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
//        userService.submitResource("深入理解JVM虚拟机","Java","English","理解JVM的一本书");
//       userService.modifyUserInfo("zzz","155151","12345678901",6L);
//        userMapper.UpdateUserPassword("111",1L);
//        userMapper.SubmitResource();
    }
    @Autowired
    private AdminService adminService;
    @Test
    void testAdminMapper(){
//        adminService.login("root","654321");
//        Admin admin = adminService.selectAdminById(1L);
//        assert (admin!=null);
//        admin.setAdminPassword(MD5Util.encryptToMD5("99875516"));
//        adminService.modifyAdminInfo(admin);
//        Admin admin0 = new Admin("root", "654");
//        System.out.println(admin0.getAdminPassword());
//        adminService.saveAdmin(admin0);
        // 加密密码
//        String encryptedPassword = MD5Util.encryptToMD5("654");
        // 调用服务进行登录验证
//        Admin admin = Admi("root","ab233b682ec355648e7891e66c54191b");
//        Admin admin = adminService.login("root", "ab233b682ec355648e7891e66c54191b");
//        System.out.println(admin);
//        Admin admin = new Admin("admin", "654");
//        adminService.saveAdmin(admin);
        Long chinese = resourceMapper.findIdByTagNameAndLanguageName("C++", "Chinese");
        System.out.println(chinese);
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
//        resourceService.findAllResources().stream().forEach(System.out::println);
//        resourceService.findResourcesByIdsAndKeyword(List.of(1L),"C++")
//                .stream().forEach(System.out::println);
//        System.out.println(tagService.findTagNameByTagName("C++"));
//        tagMapper.insertTagName("php");
//        System.out.println(languageMapper.selectLanguageNameByLanguageName("www"));
//        languageMapper.insertLanguageName("Germans");
//        resourceMapper.insertTagNameAndLanguageName("php","Chinese");
//        Long id = resourceMapper.findIdByTagNameAndLanguageName("php", "Chinese");
//        System.out.println(id);
        resourceMapper.insertResource(3L,"Hello算法");
    }
    @Autowired
    TagService tagService;
    @Test
    void test02(){
        tagService.findIdByTagAndLanguage("C++","Chinese")
                .stream().forEach(System.out::println);
    }

}
