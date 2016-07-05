package cxf.dao.impl;

import cxf.dao.IHelloWsDao;

public class HelloWsDaoImpl implements IHelloWsDao{

	@Override
	public String sayHello(String name) {
		return name+":欢迎你！！！";
	}

}
