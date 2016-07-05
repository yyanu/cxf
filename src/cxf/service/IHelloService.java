package cxf.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import cxf.service.upload.CxfFileWrapper;;

//@MTOM  
@WebService(targetNamespace="service.cxf.service.client.cxf",name="HelloWs")
public interface IHelloService {
	@WebMethod
	public String sayHello(@WebParam(name ="name") String name);
    @WebMethod
    boolean upload(@WebParam(name = "file") CxfFileWrapper file);
    @WebMethod
    CxfFileWrapper download();
//	@WebResult(name="isStrMethod")
//	@WebMethod
//	public String isString(String str);
}
