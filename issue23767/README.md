# Repro project for github issue https://github.com/spring-projects/spring-framework/issues/23767

Just run the test in this project to see the issue.

When going back to 5.1.9.RELEASE, the issue goes away. With 5.2.0.RELEASE, the annotations are not parsed the same way as they used to in Spring 5.1, and it fails.
