package com.mrcodeninja.patterns.behavioral.observer;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StringObserverTest {
    private Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    @Mock
    private Appender<ILoggingEvent> logAppender;
    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    private Observer<String> sut;

    @Before
    public void setUp() throws Exception {
        logger.addAppender(logAppender);

        sut = new StringObserver();
    }

    @SuppressWarnings("unchecked")
    @After
    public void tearDown() throws Exception {
        logger.detachAppender(logAppender);

        reset(logAppender);
    }

    @Test
    public void updateGivenAMessage() {
        sut.update("Test message");

        verify(logAppender).doAppend(captorLoggingEvent.capture());
        verifyLog(captorLoggingEvent.getValue(), Level.DEBUG, "Received message: Test message");
    }

    private void verifyLog(LoggingEvent loggingEvent, Level level, String formattedMessage) {
        assertThat(loggingEvent.getLevel(), is(level));
        assertThat(loggingEvent.getFormattedMessage(), is(formattedMessage));
    }
}
