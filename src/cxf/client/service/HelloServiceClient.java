package cxf.client.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cxf.client.service.cxf.service.HelloWs;

public class HelloServiceClient {
	public static void main(String[] args) {
//      ApplicationContext context = new ClassPathXmlApplicationContext("file:D:/workspace/cxf/WebContent/WEB-INF/classes/cxf/applicationContext-client.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/cxf/applicationContext-client.xml");
        HelloWs helloService = (HelloWs) context.getBean("client",HelloWs.class);
        String response = helloService.sayHello("Peter");
        System.out.println(response);
    }
}
