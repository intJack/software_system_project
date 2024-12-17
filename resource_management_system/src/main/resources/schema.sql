# DROP DATABASE  IF EXISTS ResourceDB;
# CREATE DATABASE ResourceDB;
#
# USE ResourceDB;
#
# # 设计用户登录表
# CREATE TABLE wx_user(
#                         id INT PRIMARY KEY AUTO_INCREMENT,
#                         user_name VARCHAR(20) NOT NULL,
#                         user_password VARCHAR(32) NOT NULL,
#                         email VARCHAR(50) NOT NULL,
#                         phone_number VARCHAR(11) NOT NULL
# );
#
# INSERT INTO wx_user(user_name, user_password, email, phone_number)
# VALUES('root', '123','11111@qq.com','12345678901');
#
# # 设计后台管理员登录表
# CREATE TABLE root_admin(
#                            id INT PRIMARY KEY AUTO_INCREMENT,
#                            admin_name VARCHAR(20) NOT NULL,
#                            admin_password VARCHAR(32) NOT NULL
# );
#
# INSERT INTO root_admin(admin_name, admin_password) values ('root', '123456');
#
# # 设计tag标签表
# CREATE TABLE tag(
#                     tag_name VARCHAR(20) PRIMARY KEY, -- java javEE javaSE python
#                     description TEXT,  -- 标签描述
#                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
#                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新时间
# );
#
#
# # 设计语言表
# CREATE TABLE language(
#                          language_name VARCHAR(20) PRIMARY KEY, -- java python c++
#                          description TEXT,  -- 语言描述
#                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
#                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新时间
# );
#
# CREATE TABLE tag_language(
#                              id INT AUTO_INCREMENT PRIMARY KEY,
#                              tag_name VARCHAR(20) NOT NULL,
#                              language_name VARCHAR(20) NOT NULL ,
#                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
#                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
#                              FOREIGN KEY(tag_name) REFERENCES tag(tag_name) ON DELETE CASCADE,
#                              FOREIGN KEY(language_name) REFERENCES language(language_name) ON DELETE CASCADE
# );
# INSERT INTO tag_language(tag_name,language_name)
# SELECT t.tag_name,l.language_name
# FROM tag t,language l;
#
CREATE TABLE resource(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         tag_language_id INT NOT NULL,-- 关联tag_language表
                         resource_title VARCHAR(50) NOT NULL,  -- 资源标题
                         resource_url VARCHAR(100) NOT NULL,  -- 资源链接
                         resource_description TEXT,  -- 资源描述
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
                         FOREIGN KEY (tag_language_id) REFERENCES tag_language(id) ON DELETE CASCADE
);
#
#
# CREATE TABLE resource_description(
#                                      id INT AUTO_INCREMENT PRIMARY KEY,
#                                      resource_id INT NOT NULL,-- 关联resource表
#                                      description TEXT,  -- 资源描述
#                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
#                                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
#                                      FOREIGN KEY (resource_id) REFERENCES resource(id) ON DELETE CASCADE
# );
#
# # 存储轮播图的表
# CREATE TABLE carousel(
#                          id INT AUTO_INCREMENT PRIMARY KEY,
#                          carousel_url VARCHAR(255) NOT NULL,  -- 轮播图链接
#                          carousel_description TEXT,  -- 轮播图描述
#                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
#                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新时间
# );
#
# INSERT INTO carousel(carousel_url,carousel_description)values('https://www.bing.com/th/id/OIP.iIXOmGDzrtTJmdwbn7cGMwHaEJ','java图片');
# INSERT INTO carousel(carousel_url, carousel_description) VALUES('https://www.bing.com/th/id/OIP.dJToM1TiZiJA0GYwzDHwjQHaHY?w=218&h=217&c=7&r=0&o=5&dpr=1.5&pid=1.7', 'python图片');
#
# # 涉及到用户上传资源,管理员审核资源.需要新建一个表resource_temp,存储待审核的资源
# CREATE TABLE resources_temp (
#                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
#                            user_id BIGINT,
#                            file_path VARCHAR(255),
#                            upload_time TIMESTAMP,
#                            status ENUM('待审核', '已通过', '已拒绝') DEFAULT '待审核',
#                            review_time TIMESTAMP,
#                            review_by BIGINT #这个是审核管理员的id
# );
#
-- 插入 tag 表的数据
INSERT INTO tag (tag_name, description) VALUES
                                            ('java', 'Java 相关的标签'),
                                            ('python', 'Python 相关的标签'),
                                            ('c++', 'C++ 相关的标签');

-- 插入 language 表的数据
INSERT INTO language (language_name, description) VALUES
                                                      ('java', 'Java 编程语言'),
                                                      ('python', 'Python 编程语言'),
                                                      ('c++', 'C++ 编程语言');

-- 插入 tag_language 表的数据
INSERT INTO tag_language (tag_name, language_name)
VALUES
    ('java', 'java'),
    ('python', 'python'),
    ('c++', 'c++');

-- 插入 resource 表的数据
INSERT INTO resource (tag_language_id, resource_title, resource_url, resource_description) VALUES
                                                                                               (1, 'Java 入门教程', 'https://example.com/java-tutorial', '适合初学者的 Java 教程'),
alter table resources_temp
    add resource_url varchar(50) not null;                                                                                    (2, 'Python 数据科学', 'https://example.com/python-data-science', 'Python 数据科学课程资源'),
                                                                                               (3, 'C++ 高级编程', 'https://example.com/cpp-advanced', '深入探讨 C++ 编程的高级特性');
