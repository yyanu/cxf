package cxf.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.jws.WebService;

import cxf.dao.IHelloWsDao;
import cxf.service.upload.CxfFileWrapper;

@WebService(
		endpointInterface="cxf.service.IHelloService"
		,targetNamespace="service.cxf.service.client.cxf"
		,serviceName="helloWsService",portName="helloWsPort"
		)
public class HelloServiceImpl implements IHelloService {
	
	private IHelloWsDao helloWsDao;
	
	public String sayHello(String name) {
		return helloWsDao.sayHello(name);
	}

	public IHelloWsDao getHelloWsDao() {
		return helloWsDao;
	}

	public void setHelloWsDao(IHelloWsDao helloWsDao) {
		this.helloWsDao = helloWsDao;
	}
//	@Override
//	public String isString(String str) {
//		return "yes";
//	}

	@Override
	public boolean upload(CxfFileWrapper file) {
		boolean result = true;

        OutputStream os = null;
        InputStream is = null;
        BufferedOutputStream bos = null;

        try {
            is = file.getFile().getInputStream();
            // 鏂囦欢鍦ㄦ湇鍔″櫒涓婄殑淇濆瓨浣嶇疆
            File dest = new File("D:\\workspace\\cxf\\WebContent\\upload\\" + file.getFileName());

            os = new FileOutputStream(dest);
            bos = new BufferedOutputStream(os);

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }

            bos.flush();

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
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
        return result;
	}

	@Override
	public CxfFileWrapper download() {
		//涓嬭浇鏂囦欢鐨勮矾寰�
        String filePath = "D:\\workspace\\cxf\\WebContent\\upload\\url.txt";

        CxfFileWrapper fileWrapper = new CxfFileWrapper();
        fileWrapper.setFileName("url.txt");
        fileWrapper.setFileExtension("txt");

        DataSource source = new FileDataSource(new File(filePath));
        fileWrapper.setFile(new DataHandler(source));

        return fileWrapper;
	}

}
