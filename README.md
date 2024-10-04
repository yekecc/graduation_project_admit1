# 综合教室预订系统

**项目简介**：
综合教室预订系统是一个便捷的在线预订平台，支持用户预订音乐室、美术室、教室和会议室。用户端采用微信小程序实现，便于用户快捷预订所需教室。管理员端网站提供教室管理功能，包括增删改查以及查看借出日志。

**功能特点**：

### 用户端（微信小程序）
- **预订功能**：用户可预订音乐室、美术室、教室和会议室。
- **即时反馈**：预订成功后，系统生成二维码作为预订凭证。
- **界面友好**：简洁直观的用户界面，提升用户体验。

### 管理员端（网站）
- **管理功能**：管理员可对教室信息和用户信息进行增删改查。
- **日志查看**：实时查看教室借出日志，便于管理。
- **数据分析**：提供教室使用情况的统计数据。

### 技术栈

#### 用户端（微信小程序）
- **前端**：微信小程序原生开发
- **后端**：Spring Boot
- **数据库**：MySQL

#### 管理员端（网站）
- **前端**：Vue 3
- **后端**：Spring Boot 3
- **数据库**：MySQL

**开发环境**：
- **语言**：Java 17, JavaScript
- **工具**：IntelliJ IDEA, Visual Studio Code
- **依赖管理**：Maven
- **版本控制**：Git

**安装指南**：
1. 克隆项目到本地：
   ```bash
   git clone [项目地址]
