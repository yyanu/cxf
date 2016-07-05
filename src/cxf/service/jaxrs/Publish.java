package cxf.service.jaxrs;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class Publish {
		public static void main(String[] args) {
			JAXRSServerFactoryBean client = new JAXRSServerFactoryBean();
			client.setResourceClasses(LibraryService.class);
			client.setResourceProvider(LibraryService.class, new SingletonResourceProvider(new LibraryService()));
			client.setAddress("http://localhost:8080/rest/");
			client.create();
		}
}
