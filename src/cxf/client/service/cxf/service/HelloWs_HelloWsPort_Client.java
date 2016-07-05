
package cxf.client.service.cxf.service;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.3
 * Fri Jun 24 16:49:05 CST 2016
 * Generated source version: 2.2.3
 * 
 */

public final class HelloWs_HelloWsPort_Client {

    private static final QName SERVICE_NAME = new QName("service.cxf.service.client.cxf", "IHelloServiceService");

    private HelloWs_HelloWsPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = IHelloServiceService.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        IHelloServiceService ss = new IHelloServiceService(wsdlURL, SERVICE_NAME);
        HelloWs port = ss.getHelloWsPort();  
        
        {
        System.out.println("Invoking sayHello...");
        java.lang.String _sayHello_name = "";
        java.lang.String _sayHello__return = port.sayHello(_sayHello_name);
        System.out.println("sayHello.result=" + _sayHello__return);


        }
        {
        System.out.println("Invoking download...");
        cxf.client.service.cxf.service.CxfFileWrapper _download__return = port.download();
        System.out.println("download.result=" + _download__return);


        }
        {
        System.out.println("Invoking upload...");
        cxf.client.service.cxf.service.CxfFileWrapper _upload_file = null;
        boolean _upload__return = port.upload(_upload_file);
        System.out.println("upload.result=" + _upload__return);


        }

        System.exit(0);
    }

}
