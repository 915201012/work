# work
一、简答题

**1.Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？**

答:复杂业务逻辑的sql语句拼接

  <where><if><foreach><sql>等动态标签

  执行原理：

**2.Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？**

答:是

实现原理:

**3.Mybatis都有哪些Executor执行器？它们之间的区别是什么？**

答:BatchExecutor: 重用语句并执行批量的更新

   ReuseExecutor: 重用预处理语句

   SimpleExecutor:普通的执行器，Mybatis默认的执行器

**4.简述下Mybatis的一级、二级缓存(分别从存储结构、范围、失效场景。三个方面来作答)？**

答:一级缓存的存储结构是一个HashMap，基于sqlSession级别，也是默认开启的，当在当前会话中发起了对数据库的增删改操作的时候，会清空一级缓存，也可以手动执行flushCache使一级缓存失效。

   二级缓存的存储结构也是一个HashMap，基于namespace(跨sqlSession)级别的，是需要手动进行开启的。

**5.简述Mybatis的插件运行原理，以及如何编写一个插件？**

答:插件即对Mybaits的四大和新对象(Executor,StatementHandle,ParameterHandle,ResultSetHandle)的拦截实现，用来对这几个对象进行增强。Mybatis在构建这四个对象的时候，底层会调用pluginAll()方法，对这几个对象进行包装，返回的是这几个对象的代理对象。

首先需要实现Interceptor这个接口，在plugin()方法中将插件注册到拦截器链中，而后需要在mybatis的核心配置文件中引入插件。

二、编程题

完善自定义持久层框架IPersitence，在现有代码基础上添加、修改以及删除功能。(需采用getMapper方式)

见代码
