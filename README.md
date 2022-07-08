# 🌟  HangHua 🌟 &emsp; 基于SpringCloud实现的行业信息问答社区   &emsp; &emsp; [![chat](https://img.shields.io/badge/chat-weChat-blue.svg)](https://cdn.jsdelivr.net/gh/zhaojie777/picture_repository/2021-4-29/1619695898789-%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20200913000038.jpg)


<p align="center"> 
 <img src="https://img.shields.io/badge/build-passing-success.svg" alt="Build Status">
 <img src="https://img.shields.io/github/license/zhaojie777/HangHua-Base?style=flat-square" alt="license">
 <img src="https://img.shields.io/badge/SpringBoot-2.3.10.RELEASE-green.svg" alt="springbooot version">
 <img src="https://img.shields.io/badge/SpringCloud-Hoxton.SR11-green.svg" alt="springcloud version">
 <img src="https://img.shields.io/github/stars/zhaojie777/HangHua-Base?label=star&style=social" alt="GitHub Repo stars">
</p>


## 1. 项目简介

- #### 1.1 技术选型
      微服务组件：Spring Cloud Alibaba(注册中心/配置中心：Nacos、限流/降级/熔断：Sentinel)、
                 Spring Cloud(服务调用：OpenFeign、客户端负载：内嵌的Ribbon)
                 网关：Nginx + Spring Cloud Gateway
      
      各服务框架：SpringBoot
 
      持久层框架：MyBatis Plus
      
      安全框架：Spring Security
 
      数据库：MySQL
 
      缓存：Redis
 
- #### 1.2 系统架构
 ![架构图](https://cdn.jsdelivr.net/gh/zhaojie777/picture_repository@main//hexoBlog/202207071446186.png)
 
- #### 1.3 项目结构
      hanghua-server
       |-- hanghua-account           -- 账户信息服务
       |-- hanghua-admin             -- 后台管理系统
       |-- hanghua-article           -- 文章内容服务
       |-- hanghua-auth              -- 授权服务器
       |-- hanghua-comment           -- 评论服务
       |-- hanghua-common            -- 通用工具
       |-- hanghua-gateway           -- 网关，集成swagger3、资源服务器、鉴权认证
       |-- hanghua-im                -- 社交通信
       |-- hanghua-mail              -- 邮件服务
       |-- pom.xml

 
- #### 1.4 环境要求
      操作系统：CentOS 7.0、
      JDK版本：Java 1.8.0_311、
      SDK版本：Android 10、
      版本控制：git 2.33.1、
      依赖控制：Maven 3.6.3、
 
 
 

## 2. 服务端(Java端)

- GateWay整合Swagger3
    实现统一API接口文档管理

- GateWay整合Oauth2.0
    实现统一鉴权认证中心

- #### 3.1 文章服务
  待完善
   
- #### 3.2 评论服务   
  待完善 
  
- #### 3.3 IM通信服务
  待完善

- #### 3.4 邮件服务
  待完善

- #### 3.5 授权服务
  待完善
  
- #### 3.6 账户信息服务
  待完善  




## 3. 后台管理系统(Web端)
  待完善






## 4. 客户端(Android端)

  - #### 2.1 界面设计
    待完善
    
  - #### 2.2 数据交互




 
 
## 4. 环境部署
 
 
## 5. 总结
 





