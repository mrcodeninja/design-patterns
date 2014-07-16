package com.mrcodeninja.patterns.behavioral.observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StringSubjectTest {
    private static final String TEST_MESSAGE = "Test message";

    @Mock
    private Observer<String> observer1;
    @Mock
    private Observer<String> observer2;

    private Subject<String> sut;

    @Before
    public void setUp() throws Exception {
        sut = new StringSubject();
    }

    @SuppressWarnings("unchecked")
    @After
    public void tearDown() throws Exception {
        reset(observer1);
        reset(observer2);
    }

    @Test
    public void updatesRegisteredObservers() {
        sut.attach(observer1);
        sut.attach(observer2);

        sut.notifyObservers(StringSubjectTest.TEST_MESSAGE);

        verify(observer1).update(eq(StringSubjectTest.TEST_MESSAGE));
        verify(observer2).update(eq(StringSubjectTest.TEST_MESSAGE));
    }

    @Test
    public void noUpdatesAfterDetachingObservers() {
        sut.attach(observer1);

        sut.notifyObservers(StringSubjectTest.TEST_MESSAGE);

        verify(observer1).update(eq(StringSubjectTest.TEST_MESSAGE));

        sut.detach(observer1);

        verify(observer1).update(eq(StringSubjectTest.TEST_MESSAGE));
    }
}
