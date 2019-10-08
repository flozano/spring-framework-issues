package org.springframework.issues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {

	private final MyDAO dao;

	public MyService(@Autowired MyDAO dao) {
		this.dao = dao;
	}

	@AnotherMyTransactional
	public void myMethod() {
		dao.findAll();
	}
}
