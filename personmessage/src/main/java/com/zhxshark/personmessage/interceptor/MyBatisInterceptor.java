//package com.zhxshark.personmessage.interceptor;
//
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.executor.parameter.ParameterHandler;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//import org.apache.ibatis.session.RowBounds;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import sun.plugin2.main.server.ResultHandler;
//
//import java.sql.Connection;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @author zhuxin
// * @date 2019/9/29 17:04
// */
//
//@Component
//@Intercepts({ @Signature(type = StatementHandler.class,
//        method = "prepare", args = { Connection.class, Integer.class }) })
//public class MyBatisInterceptor implements Interceptor {
//    //每页显示的条目数
//    private int pageSize;
//    //当前的页数
//    private int pageNo;
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        //获取StatementHandler，默认是RoutingStatementHandler
//        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//        //获取statementHandler包装类
//        MetaObject MetaObjectHandler = SystemMetaObject.forObject(statementHandler);
//
//        //分离代理对象链
//        while (MetaObjectHandler.hasGetter("h")) {
//            Object obj = MetaObjectHandler.getValue("h");
//            MetaObjectHandler = SystemMetaObject.forObject(obj);
//        }
//
//        while (MetaObjectHandler.hasGetter("target")) {
//            Object obj = MetaObjectHandler.getValue("target");
//            MetaObjectHandler = SystemMetaObject.forObject(obj);
//        }
//
//        //获取连接对象
//        //Connection connection = (Connection) invocation.getArgs()[0];
//
//
//        //object.getValue("delegate");  获取StatementHandler的实现类
//
//        //获取查询接口映射的相关信息
//        MappedStatement mappedStatement = (MappedStatement) MetaObjectHandler.getValue("delegate.mappedStatement");
//        String mapId = mappedStatement.getId();
//
//        //statementHandler.getBoundSql().getParameterObject();
//
//        //拦截以.ByPage结尾的请求，分页功能的统一实现
//        if (mapId.matches(".+ByPage$")) {
//            //获取进行数据库操作时管理参数的handler
//            ParameterHandler parameterHandler = (ParameterHandler) MetaObjectHandler.getValue("delegate.parameterHandler");
//            //获取请求时的参数
////            Map<String, Object> paraObject = (Map<String, Object>) parameterHandler.getParameterObject();
//            //也可以这样获取
//            //paraObject = (Map<String, Object>) statementHandler.getBoundSql().getParameterObject();
//
//            //参数名称和在service中设置到map中的名称一致
////            pageNo = (int) paraObject.get("PageNo");
////            pageSize = (int) paraObject.get("pageSize");
//            pageNo = 1 ;
//            pageSize = 5 ;
//            String sql = (String) MetaObjectHandler.getValue("delegate.boundSql.sql");
//            //也可以通过statementHandler直接获取
//            //sql = statementHandler.getBoundSql().getSql();
//
//            //构建分页功能的sql语句
//            String limitSql;
//            sql = sql.trim();
//            limitSql = sql + " limit " + (pageNo - 1) * pageSize + "," + pageSize;
//
//            //将构建完成的分页sql语句赋值个体'delegate.boundSql.sql'，偷天换日
//            MetaObjectHandler.setValue("delegate.boundSql.sql", limitSql);
//        }
//        //调用原对象的方法，进入责任链的下一级
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target,this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//        this.pageNo = 0;
//        this.pageSize = 5;
//        //如果项目中分页的pageSize是统一的，也可以在这里统一配置和获取，这样就不用每次请求都传递pageSize参数了。参数是在配置拦截器时配置的。
////        String limit1 = properties.getProperty("limit", "10");
////        this.pageSize = Integer.valueOf(limit1);
//    }
//}
