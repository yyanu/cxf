package cxf.client.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.junit.Test;

import cxf.client.service.authinterceptor.AuthInterceptor;
import cxf.client.service.cxf.service.CxfFileWrapper;
import cxf.client.service.cxf.service.HelloWs;


public class Call {
	
	@Test
	public void testCXFClient() throws Exception {
//		1.创建JaxWsProxyFactoryBean的对象，用于接收服务
		JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
//		2.设置服务的发布地址，表示去哪里过去服务
		client.setAddress("http://localhost:8080/ws/sayHelloServiceImpl");
//		3.设置服务的发布接口，使用本地的代理接口
		client.setServiceClass(HelloWs.class);
		
		//WS-Security输出拦截器
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		//添加用户名
		outProps.put(WSHandlerConstants.USER, "wss4jAdmin");
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallbackHandler.class.getName());
		 		
		// ========================拦截器=================================
		client.getOutInterceptors().add(new LoggingOutInterceptor()); // 日志拦截器
		client.getInInterceptors().add(new LoggingInInterceptor());// 日志拦截器
//		client.getOutInterceptors().add(new AuthInterceptor("admin", "1")); 
		client.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
		// =========================================================
		
//		4.通过create方法返回接口代理实例
		HelloWs service = (HelloWs) client.create();
		
//		5.调用远程方法
		System.out.println(service.sayHello("小明"));
	}
	
	@Test
	public void testFileUpload() throws Exception {
//		1.创建JaxWsProxyFactoryBean的对象，用于接收服务
		JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
//		2.设置服务的发布地址，表示去哪里过去服务
		client.setAddress("http://localhost:8080/ws/sayHelloServiceImpl");
//		3.设置服务的发布接口，使用本地的代理接口
		client.setServiceClass(HelloWs.class);
		
		// ========================拦截器=================================
		client.getOutInterceptors().add(new LoggingOutInterceptor()); // 日志拦截器
		client.getOutInterceptors().add(new AuthInterceptor("admin", "0")); 
		client.getInInterceptors().add(new LoggingInInterceptor());// 日志拦截器
		// =========================================================
		
//		4.通过create方法返回接口代理实例
		HelloWs service = (HelloWs) client.create();
		
		//把数据封装到CxfFileWrapper对象中   文件名  文件后缀名
		CxfFileWrapper fileWrapper = new CxfFileWrapper();
		fileWrapper.setFileName("WebService1.xls");
		fileWrapper.setFileExtension("xls");
		
		//把需要上传的文件转成二进制
		String filePath = "D:\\BaiduYunDownload\\WebService.xls";
		DataSource source = new FileDataSource(new File(filePath));
		fileWrapper.setFile(new DataHandler(source));

		boolean success = service.upload(fileWrapper);
		System.out.println(success ? "上传成功！" : "上传失败！");
		
//		5.调用远程方法
//		System.out.println(service.sayHello("小明"));
	}
	
	@Test
	public void testFileDownload() throws Exception {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(HelloWs.class);
		factory.setAddress("http://localhost:8080/ws/sayHelloServiceImpl");
		
		//WS-Security输出拦截器
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		//添加用户名
		outProps.put(WSHandlerConstants.USER, "wss4jAdmin");
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallbackHandler.class.getName());
		// ========================拦截器=================================
		factory.getOutInterceptors().add(new LoggingOutInterceptor()); // 日志拦截器
//		factory.getOutInterceptors().add(new AuthInterceptor("admin", "0")); 
		factory.getInInterceptors().add(new LoggingInInterceptor());// 日志拦截器
		factory.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
		// =========================================================
		HelloWs helloWs = (HelloWs)factory.create();
		CxfFileWrapper fileWrapper = helloWs.download();
		OutputStream os = null;
		InputStream is = null;
		BufferedOutputStream bos = null;
		try {
		    is = fileWrapper.getFile().getInputStream();
		    // 文件在客户端的保存位置
		    File dest = new File("d:\\BaiduYunDownload\\download\\" + fileWrapper.getFileName());

		    os = new FileOutputStream(dest);
		    bos = new BufferedOutputStream(os);

		    byte[] buffer = new byte[1024];
		    int len = 0;
		    while ((len = is.read(buffer)) != -1) {
		        bos.write(buffer, 0, len);
		    }

		    bos.flush();
		    System.out.println("下载完成");
		} catch (IOException e) {
		    e.printStackTrace();
		}finally{
		    if(bos != null){
		        try{
		            bos.close();
		        }catch(Exception e){
		        }
		    }

		    if(os != null){
		        try{
		            os.close();
		        }catch(Exception e){
		        }
		    }

		    if(is != null){
		        try{
		            is.close();
		        }catch(Exception e){
		        }
		    }
		}
	}
}
