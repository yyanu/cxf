package cxf.service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import cxf.service.interceptor.AuthInterceptor;

public class Publish {
	public static void main(String[] args) {
//		1.鍒涘缓ServerFactoryBean鐨勫璞★紝鐢ㄤ簬鍙戝竷鏈嶅姟
		JaxWsServerFactoryBean server = new JaxWsServerFactoryBean();
//		2.璁剧疆鏈嶅姟鍙戝竷鍦板潃
		server.setAddress("http://192.168.118.62:8888/hi");
//		3.璁剧疆鏈嶅姟鍙戝竷鐨勬帴鍙�
		server.setServiceClass(IHelloService.class);
//		4.璁剧疆鏈嶅姟鐨勫彂甯冨璞�
		server.setServiceBean(new HelloServiceImpl());
		// =========================鎷︽埅鍣�===================================
		server.getInInterceptors().add(new LoggingInInterceptor());
		server.getInInterceptors().add(new AuthInterceptor());
		// ============================================================
//		5.浣跨敤create鏂规硶鍙戝竷鏈嶅姟
		server.create();
		
		System.out.println("CXF鏈嶅姟鍙戝竷ok锛�");
	}
}
