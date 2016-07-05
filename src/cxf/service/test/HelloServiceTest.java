package cxf.service.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cxf.service.IHelloService;

public class HelloServiceTest {
	 ApplicationContext context = new FileSystemXmlApplicationContext("/src/cxf/applicationContext-server.xml");
     IHelloService helloService = (IHelloService) context.getBean("client",IHelloService.class);
     String response = helloService.sayHello("Peter");
}
