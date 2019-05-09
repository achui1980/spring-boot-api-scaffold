## 简介
Spring Boot API Scaffold 是一个基于Spring Boot &
MyBatis的脚手架项目，用于快速构建中小型API、RESTful
API项目，使我们摆脱那些重复劳动，专注于业务代码的编写。

## 特征&提供
- 最佳实践的项目结构、配置文件、精简的POM
- 统一响应结果封装及生成工具
- 统一异常处理
- 使用FastJsonHttpMessageConverter，提高JSON序列化速度
- 集成MyBatis Plus插件，实现单表业务零SQL
- 提供代码生成器根据表名生成对应的Model、Mapper、MapperXML、Service、ServiceImpl、Controller等基础代码

## 快速开始
1. 克隆项目
2. 对```test```包内的代码生成器```CodeGenerator```进行配置，主要是JDBC，因为要根据表名来生成代码
3. 如果只是想根据上面的演示来亲自试试的话可以使用```test
   resources```目录下的```demo-schema.sql```，```demo-data.sql```,否则忽略该步
4. 输入表名，运行```CodeGenerator.main()```方法，生成基础代码（可能需要刷新项目目录才会出来）
5. 根据业务在基础代码上进行扩展

## 开发建议
- 表名，建议使用小写，多个单词使用下划线拼接
- Model内成员变量建议与表字段数量对应，如需扩展成员变量（比如连表查询）建议创建DTO，否则需在扩展的成员变量上加```@Transient```注解，详情见[通用Mapper插件文档说明](https://mapperhelper.github.io/docs/2.use/)
- 建议业务失败直接使用```ServiceException("message")```抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new ServiceException("该手机号已被注册")```，会直接被封装为```{"code":400,"message":"该手机号已被注册"}```返回，无需自己处理，尽情抛出
- 需要工具类的话建议先从```apache-commons-*```和```guava```中找，实在没有再造轮子或引入类库，尽量精简项目
- 开发规范建议遵循阿里巴巴Java开发手册（[最新版下载](https://github.com/alibaba/p3c))
- 建议在公司内部使用[ShowDoc](https://github.com/star7th/showdoc)、[SpringFox-Swagger2](https://github.com/springfox/springfox) 、[RAP](https://github.com/thx/RAP)等开源项目来编写、管理API文档

## 技术选型&文档
- Spring Boot（[查看Spring Boot学习&使用指南](http://www.jianshu.com/p/1a9fd8936bd8)）
- MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatis Plus插件（[查看官方中文文档](https://mp.baomidou.com/)）
- Fastjson（[查看官方中文文档](https://github.com/Alibaba/fastjson/wiki/%E9%A6%96%E9%A1%B5)）
- Lombok 插件（[查看官方中文文档](https://projectlombok.org/)）

## 感谢
本工程参考项目 **spring-boot-api-project-seed**
https://github.com/lihengming/spring-boot-api-project-seed/

## 后续
1. 加入代码覆盖率检测（jacoco）
2. 加入PMD， checkstyle检测
3. 结合前端UI （iView）生成简单的前端CURD模板