package org.springframework.issues;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class MyOtherService {
	private final MyOtherService2 other2;

	MyOtherService(MyOtherService2 other2) {
		this.other2 = Objects.requireNonNull(other2);
	}

	public void doOther() {
		other2.doOther2();
	}
	

	public void doOtherFail() {
		other2.doFail2();
	}
}

@Service
@Transactional(transactionManager = "txManager2")
class MyOtherService2 {
	public void doOther2() {

	}

	public void doFail2() {
		throw new RuntimeException("failed!");
	}
}
