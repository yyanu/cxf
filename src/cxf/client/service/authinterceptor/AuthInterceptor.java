package cxf.client.service.authinterceptor;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private String username;
	private String password;

//	public AuthInterceptor(){
//		super(Phase.PREPARE_SEND); 
//	}
	
	public AuthInterceptor(String username,String password) {
		super(Phase.PREPARE_SEND);// 在发送之前
		this.username = username;
		this.password = password;
	}

	/**
	 * <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
			<soap:Header>
				<authInfo>
					<username>admin</username>
					<password>0</password>
				</authInfo>
			</soap:Header>
			<soap:Body>
				<ns2:sayHello xmlns:ns2="http://service.cxf.itsource.cn/">
					<arg0>小明</arg0>
				</ns2:sayHello>
			</soap:Body>
		</soap:Envelope>
	 */
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		// 准备dom工具
		Document document = DOMUtils.createDocument();
		// 1、 创建三个元素
		Element authInfoEl = document.createElement("authInfo");
		Element usernameEl = document.createElement("username");
		Element passwordEl = document.createElement("password");
		
		// 2、 把内容封装到元素中
		usernameEl.setTextContent(username);
		passwordEl.setTextContent(password);
		
		// 3、 确定元素的父子关系
		authInfoEl.appendChild(usernameEl);
		authInfoEl.appendChild(passwordEl);
		
		// 4、 把authInfo添加到header下
		Header header = new Header(new QName("XXX"), authInfoEl);
		message.getHeaders().add(header );
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
