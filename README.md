## 沙龙社区
+ 这是我的毕业设计——一个社区网站。  
+ 网站主要模块：博客、论坛、沙龙、资料。  
+ 网站功能有：注册登录、发布博客、参与论坛、查看资料和沙龙信息等功能。

## 资料
网课[Bilibili](https://www.bilibili.com/video/av65117012?p=14)  
模板网站[Elasticsearch](https://elasticsearch.cn/explore) [腾讯云社区](https://cloud.tencent.com/developer)  
Spring入门指导[Spring Guides](https://spring.io/guides)  
使用Spring MVC服务web内容[Spring web](https://spring.io/guides/gs/serving-web-content/)  
前端框架[Bootstrap4](http://code.z01.com/)  
模板引擎[Thymeleaf](https://www.thymeleaf.org/)  
Maven资料库[MavenRepository](https://mvnrepository.com/)  
授权应用[Github OAuth](https://developer.github.com/apps/building-oauth-apps/)  
Http客户端[OkHttp](https://square.github.io/okhttp/)  
类图[UML规范](https://mp.weixin.qq.com/s/KR2HCcCoIc-gSDLZ69azYw)  
菜鸟教程[Runoob](https://www.runoob.com/)  
MyBatis[MyBatis-Spring-Boot-Starter](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html)  
嵌入式数据库支持[SpringBoot默认连接池](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-embedded-database-support)


## 工具
[Maven](https://maven.apache.org/download.cgi)  
[Git](https://git-scm.com/)  
[Visual-paradigm](https://www.visual-paradigm.com/cn/)  
[Zico](http://ico.z01.com/)  
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)  
[Lombok](https://www.projectlombok.org/)  
[MyBatis Generator](http://mybatis.org/generator/running/runningWithMaven.html)  
[Postman]()

##开发日志
+ 2020/2/14安装IDEA、Maven、Bootstrap4、Git。  

+ 2020/2/16 初始化Github仓库，初次使用Spring Boot、Git。  

+ 2020/2/18 项目需求分析，主页页面划分，练习Bootstrap4。  

+ 2020/2/22 编写导航栏，使用Github App实现第三方登录功能，用session记录当前登录态。  

+ 2020/2/23 嵌入H2数据库，实现登录态。  

+ 2020/2/29 添加flyway migration，管理sql脚本。  

+ 2020/3/2 添加问题发布模态框和登录模态框，提交服务层出现问题。  

+ 2020/3/3 添加Lombok支持，添加头像脚本。完成问题发布功能。  

+ 2020/3/14 添加问题列表页面分页功能。  

+ 2020/3/15 添加导航片段

+ 2020/3/16 个人中心页面，问题详情页面，拦截器

+ 2020/3/17 解决提问模态框刷新问题，修复登录问题

+ 2020/3/18 实现问题编辑功能。  

+ 2020/3/22 使用MyBatis Generator重构项目，修复问答模块数据传输bug。  

+ 2020/3/23 增加异常页面，修复ask页面image标签取值的bug。

+ 2020/3/25 修复创建问题报错异常

+ 2020/3/26 添加阅读数,添加回复功能

+ 2020/3/28 添加二级评论功能

##脚本
+ git查看、添加至暂存、提交到本地仓库、上传到远端命令
```shell script
git status
git add .
git commit -m "本次操作内容简介"
git push
git config autocrlf false
git commit --amend
```
+ flyway迁移数据库命令
```shell script
mvn flyway:migrate  
```
+ MyBatis Generator将参数传递给目标
```shell script
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```



