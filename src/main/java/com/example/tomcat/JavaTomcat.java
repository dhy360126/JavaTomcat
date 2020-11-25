package com.example.tomcat;

import com.example.servlet.IndexServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class JavaTomcat {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        //设置端口
        tomcat.setPort(8080);

        //是否设置自动部署
        tomcat.getHost().setAutoDeploy(false);
        //创建上下文
        StandardContext standardContext = new StandardContext();
        //path上下文路径
        standardContext.setPath("/index");
        //监听上下文
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        //tomcat容器添加standardContenxt上下文
        tomcat.getHost().addChild(standardContext);
        //创建servlet
        tomcat.addServlet("/index","indexServlet",new IndexServlet());
        //servlet url映射
        standardContext.addServletMappingDecoded("/duanhy","indexServlet");

        tomcat.start();
        System.out.println("java语言创建tomcat启动成功");
        //异步进行接收请求
        tomcat.getServer().await();
    }
}
