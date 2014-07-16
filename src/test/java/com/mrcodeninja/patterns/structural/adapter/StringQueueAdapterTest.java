package com.mrcodeninja.patterns.structural.adapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class StringQueueAdapterTest {
    private static final String TEST_DATA = "Test data";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private Queue<String> queue;

    private Writer<String> sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new StringQueueAdapter(queue);
    }

    @SuppressWarnings("unchecked")
    @After
    public void tearDown() throws Exception {
        reset(queue);
    }

    @Test
    public void structuralInspection() throws IOException {
        given(queue.offer(anyString())).willReturn(true);

        sut.write(StringQueueAdapterTest.TEST_DATA);

        verify(queue).offer(eq(StringQueueAdapterTest.TEST_DATA));
    }

    @Test
    public void throwsExceptionOnCapacityRestriction() throws IOException {
        thrown.expect(IOException.class);
        thrown.expectMessage(is("Failed to write the data due to capacity restrictions."));
        given(queue.offer(anyString())).willReturn(false);

        sut.write(StringQueueAdapterTest.TEST_DATA);
    }

    @Test
    public void throwsExceptionGivenQueueFailure() throws IOException {
        thrown.expect(IOException.class);
        thrown.expectMessage(is("Failed to write the data."));
        given(queue.offer(eq(StringQueueAdapterTest.TEST_DATA))).willThrow(new IllegalArgumentException("Test exception"));

        sut.write(StringQueueAdapterTest.TEST_DATA);
    }
}
