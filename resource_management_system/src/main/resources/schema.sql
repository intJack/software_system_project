DROP DATABASE  IF EXISTS ResourceDB;
CREATE DATABASE ResourceDB;

USE ResourceDB;

# 设计用户登录表
CREATE TABLE wx_user(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        user_name VARCHAR(20) NOT NULL,
                        user_password VARCHAR(32) NOT NULL,
                        email VARCHAR(50) NOT NULL,
                        phone_number VARCHAR(11) NOT NULL
);

INSERT INTO wx_user(user_name, user_password, email, phone_number)
VALUES('root', '123','11111@qq.com','12345678901');

# 设计后台管理员登录表
CREATE TABLE root_admin(
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           admin_name VARCHAR(20) NOT NULL,
                           admin_password VARCHAR(32) NOT NULL
);

INSERT INTO root_admin(admin_name, admin_password) values ('root', '123456');

# 设计tag标签表
CREATE TABLE tag(
                    tag_name VARCHAR(20) PRIMARY KEY, -- java javEE javaSE python
                    description TEXT,  -- 标签描述
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新时间
);
# 添加几行数据
INSERT INTO tag VALUES
  ('C++','C++在游戏引擎方面很有优势')
  ('Java','Java在Android方面很常见')
  ('Python','Python在人工智能领域很热门');

# 设计语言表
CREATE TABLE language(
                         language_name VARCHAR(20) PRIMARY KEY, -- java python c++
                         description TEXT,  -- 语言描述
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新时间
);
# 添加几行数据
INSERT INTO tag language
  ('Chinese','全世界都在讲中国话')
  ('English','英语是一门国际化的语言')
  ('Japanese','日本人在讲');
  ('Russian','俄语是俄罗斯人的母语')
CREATE TABLE tag_language(
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             tag_name VARCHAR(20) NOT NULL,
                             language_name VARCHAR(20) NOT NULL ,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
                             FOREIGN KEY(tag_name) REFERENCES tag(tag_name) ON DELETE CASCADE,
                             FOREIGN KEY(language_name) REFERENCES language(language_name) ON DELETE CASCADE
);
INSERT INTO tag_language(tag_name,language_name)
SELECT t.tag_name,l.language_name
FROM tag t,language l;

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
INSERT INTO resource(tag_language_id, resource_title, resource_url, resource_description) VALUES(1, 'java基础', 'https://www.java.com', 'java基础学习资源');

CREATE TABLE resource_description(
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     resource_id INT NOT NULL,-- 关联resource表
                                     description TEXT,  -- 资源描述
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
                                     FOREIGN KEY (resource_id) REFERENCES resource(id) ON DELETE CASCADE
);

# 存储轮播图的表
CREATE TABLE carousel(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         carousel_url VARCHAR(255) NOT NULL,  -- 轮播图链接
                         carousel_description TEXT,  -- 轮播图描述
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新时间
);

INSERT INTO carousel(carousel_url,carousel_description)values('https://www.bing.com/th/id/OIP.iIXOmGDzrtTJmdwbn7cGMwHaEJ','java图片');
INSERT INTO carousel(carousel_url, carousel_description) VALUES('https://www.bing.com/th/id/OIP.dJToM1TiZiJA0GYwzDHwjQHaHY?w=218&h=217&c=7&r=0&o=5&dpr=1.5&pid=1.7', 'python图片');





