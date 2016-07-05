package cxf.service.interceptor;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

/**
 * 服务端验证
 * @author Administrator
 *
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public AuthInterceptor() {
		super(Phase.PRE_INVOKE);//  在方法调用前
	}

	/**
	 * <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
			<soap:Header>
				<authInfo>
					<username>admin</username>
					<password>123</password>
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
		// 1、 获取header
		List<Header> headers = message.getHeaders();
		// 2、 验证header
		if(headers==null || headers.isEmpty()|| headers.get(0)==null ){
			throw new Fault(new IllegalArgumentException("认证信息不能为空！！"));
		}
		Header header = headers.get(0);
		Element authInfoEl= (Element) header.getObject();
		// 3、 取出认证信息
		String username = authInfoEl.getElementsByTagName("username").item(0).getTextContent();
		String password = authInfoEl.getElementsByTagName("password").item(0).getTextContent();
		// 4、 验证
		if(!"admin".equals(username) || !"0".equals(password)){
			throw new Fault(new IllegalArgumentException("用户名或密码错误！！"));
		}
		
	}

}
