package org.springframework.issues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
class MyListener implements Ordered {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyListener.class);

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	@Transactional(propagation = Propagation.MANDATORY)
	public void doSomething(Foo event) {
		LOGGER.info("MyListener received {}", event);
	}

	@Override
	public int getOrder() {
		return 0;
	}
}

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
class MyOtherListener implements Ordered {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyOtherListener.class);

	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	@Transactional(propagation = Propagation.MANDATORY)
	public void doSomething(Foo event) {
		LOGGER.info("MyOtherListener received {}", event);
	}

	@Override
	public int getOrder() {
		return -1;
	}
}

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
class MyLastListener implements Ordered {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyLastListener.class);

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Transactional(propagation = Propagation.MANDATORY)
	public void doSomething(Foo event) {
		LOGGER.info("MyLastListener received {}", event);
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
