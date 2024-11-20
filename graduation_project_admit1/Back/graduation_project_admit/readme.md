# 教室预约管理系统

## 项目介绍
本项目是一个基于Spring Boot的教室预约管理系统,采用前后端分离架构,包含微信小程序端和管理员Web端。系统主要用于管理教室预约、审核等业务。

## 技术栈
- 后端: Spring Boot 3.x
- 数据库: MySQL 8.x
- 前端: 微信小程序 + Vue.js
- 工具: Maven, Git, Lombok

## 功能特性
1. 用户管理
   - 微信用户登录
   - 管理员登录
   - 用户信息管理

2. 教室管理
   - 教室信息维护
   - 教室状态管理
   - 教室查询

3. 预约管理
   - 创建预约
   - 取消预约
   - 预约审核
   - 预约记录查询

## 项目结构

## 核心模块说明

### 1. 用户模块
- 微信登录接口

## API文档

### 微信端接口
1. 用户登录
- POST /user/login
- 参数: code (微信code)
- 返回: openid, session_key, userInfo

2. 教室预约
- GET /user/rooms/available - 获取可用教室
- POST /user/reservation - 创建预约
- GET /user/reservations - 获取预约记录
- POST /user/reservation/{id}/cancel - 取消预约

### 管理端接口
1. 教室管理
- GET /room - 获取教室列表
- POST /room - 添加教室
- DELETE /room - 删除教室

2. 预约审核
- GET /admit/reservations/pending - 获取待审核列表
- POST /admit/reservations/{id}/audit - 审核预约
- GET /admit/statistics - 获取统计数据

## 数据模型

### Room (教室)
