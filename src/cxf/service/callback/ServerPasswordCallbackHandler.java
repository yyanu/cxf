package cxf.service.callback;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.WSSecurityException;


public class ServerPasswordCallbackHandler implements CallbackHandler{

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

        if (pc.getIdentifier().equals("wss4jAdmin")&&pc.getPassword().equals("1234")) {
            //设置密码
            //这个密码和客户端发送的密码进行比较
            //如果和客户端不同将抛出org.apache.ws.security.WSSecurityException
//            pc.setPassword("123");
        }else{
        	throw new WSSecurityException("No Permission!");
        }
	}

}
