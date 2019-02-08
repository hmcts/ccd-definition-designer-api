package uk.gov.hmcts.ccd.definition.designer;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.TelemetryContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class AppInsightsTest {
    private AppInsights classUnderTest;

    @Mock
    private TelemetryClient telemetryClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        TelemetryContext telemetryContext = new TelemetryContext();
        telemetryContext.setInstrumentationKey("some-key");
        doReturn(telemetryContext).when(telemetryClient).getContext();
        classUnderTest = new AppInsights(telemetryClient);
    }

    @Test
    void trackRequest() {
        classUnderTest.trackRequest(10L, false);
        verify(telemetryClient, times(1)).trackRequest(any());
    }


    @Test
    void trackException() {
        Exception e = new Exception();
        classUnderTest.trackException(e);
        verify(telemetryClient, times(1)).trackException(e);
    }

    @Test
    void trackDependency() {
        classUnderTest.trackDependency("some", "command", 10L, true);
        verify(telemetryClient, times(1)).trackDependency(anyString(), anyString(), any(), anyBoolean());
    }
}
