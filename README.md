# glinde-blog-2

一个基于Vue+SpringBoot的前后端分离个人博客项目

在之前的glinde-blog的基础上重写代码做成的新版个人博客

替换了glinde-blog的一些框架，并加入了一些新的框架

使用方便快捷的MyBatis-Plus代替MyBatis

使用MinIO对象存储服务代替SpringMVC静态资源映射

使用SpringSecurity代替普通的拦截器鉴权

作为新版本被部署到 <https://glinde.me>

Tips：

需要根据实际情况修改后端配置类CorsConfig中的allowedOrigin

只提供了表结构的sql,用户需要手动添加,理论上因为是单人博客,所以只需要新建一个用户

密码使用Bcrypt进行加密（Round为13，可以在Config类中修改），token使用SHA256进行加密

## 前端

- Vue
- axios
- Element UI
- mavon-editor ( Markdown编辑器 )
- markdown-it ( Markdown解析器 )

## 后端

- SpringBoot
- MyBatis-Plus
- SpringSecurity
- Redis
- MinIO

## 目录

- glinde-blog-springboot    后端项目文件
- glinde-blog-vue               前端项目文件
- sql                                   表结构sql
- image                             文档使用的图片

## 页面展示

首页

![](https://github.com/GlindeAL/glinde-blog-2/blob/master/image/landing.png)

博客展示

![](https://github.com/GlindeAL/glinde-blog-2/blob/master/image/homeBlog.png)

匿名信展示

![](https://github.com/GlindeAL/glinde-blog-2/blob/master/image/homeMail.png)

登录界面

![](https://github.com/GlindeAL/glinde-blog-2/blob/master/image/login.png)

博客撰写

![](https://github.com/GlindeAL/glinde-blog-2/blob/master/image/blogWrite.png)

博客管理

![](https://github.com/GlindeAL/glinde-blog-2/blob/master/image/blogEdit.png)

信件管理

![](https://github.com/GlindeAL/glinde-blog-2/blob/master/image/mailManage.png)

