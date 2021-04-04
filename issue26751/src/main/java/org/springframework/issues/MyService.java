package org.springframework.issues;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyService.class);
	private final ApplicationEventPublisher publisher;
	private final MyOtherService other;

	public MyService(MyOtherService other, ApplicationEventPublisher publisher) {
		this.other = Objects.requireNonNull(other);
		this.publisher = Objects.requireNonNull(publisher);
		LOGGER.error("MyService configured");
	}

	public void doOtherThenPublish() {
		other.doOther();
		publisher.publishEvent(new Foo(new Bar()));
	}

	public void doPublishThenOther() {
		publisher.publishEvent(new Foo(new Bar()));
		other.doOther();
	}

	public void doOtherPublishThenOther() {
		other.doOther();
		publisher.publishEvent(new Foo(new Bar()));
		other.doOther();
	}

	public void doPublishThenOtherThenPublish() {
		publisher.publishEvent(new Foo(new Bar()));
		other.doOther();
		publisher.publishEvent(new Foo(new Bar()));
	}

//
	public void doOtherFailThenPublish() {
		try {
			other.doOtherFail();
		} catch (RuntimeException e) {
			LOGGER.info("doOtherFailThenPublish failure");
		}
		publisher.publishEvent(new Foo(new Bar()));
	}

	public void doPublishThenOtherFail() {
		publisher.publishEvent(new Foo(new Bar()));
		try {
			other.doOtherFail();
		} catch (RuntimeException e) {
			LOGGER.info("doPublishThenOtherFail failure");
		}
	}

	public void doOtherFailPublishThenOther() {
		try {
			other.doOtherFail();
		} catch (RuntimeException e) {
			LOGGER.info("doOtherFailPublishThenOther failure");
		}
		publisher.publishEvent(new Foo(new Bar()));
		other.doOther();
	}

	public void doPublishThenOtherFailThenPublish() {
		publisher.publishEvent(new Foo(new Bar()));
		try {
			other.doOtherFail();
		} catch (RuntimeException e) {
			LOGGER.info("doPublishThenOtherFailThenPublish failure");
		}
		publisher.publishEvent(new Foo(new Bar()));
	}

	public void doOtherPublishThenOtherFail() {
		other.doOther();
		publisher.publishEvent(new Foo(new Bar()));
		try {
			other.doOtherFail();
		} catch (RuntimeException e) {
			LOGGER.info("doOtherPublishThenOtherFail failure");
		}
	}
}
