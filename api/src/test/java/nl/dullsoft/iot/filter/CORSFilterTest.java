package nl.dullsoft.iot.filter;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:marcel.dullaart@rws.nl">Marcel Dullaart</a>
 */
public class CORSFilterTest {

    private static final String ACCESS_CONTROL_REQUEST_HEADER = "Access-Control-Request-Headers";
    private static final String REQUESTED_HEADER = "Requested-Header";
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;

    @InjectMocks
    private CORSFilter corsFilter;

    @Test
    public void init_ShouldDoNothing() throws ServletException {
        corsFilter.init(filterConfig);
        verifyZeroInteractions(filterConfig);
    }

    @Test
    public void destroy_ShouldDoNothing() {
        corsFilter.destroy();
        verifyZeroInteractions(filterConfig);
    }

    @Test
    @Ignore
    public void doFilterGet_ShouldAddCorsHeader() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getHeader(ACCESS_CONTROL_REQUEST_HEADER)).thenReturn(REQUESTED_HEADER);

        CORSFilter
            filter = new CORSFilter();

        filter.init(filterConfig);
        filter.doFilter(request, response, chain);
        filter.destroy();

        verify(response).setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        verify(response).setHeader("Access-Control-Allow-Origin", "*");
        verify(response, never()).setHeader("Access-Control-Allow-Headers", REQUESTED_HEADER);
    }

    @Test
    @Ignore
    public void doFilterOptions_ShouldAddCorsHeader() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("OPTIONS");
        when(request.getHeader(ACCESS_CONTROL_REQUEST_HEADER)).thenReturn(REQUESTED_HEADER);

        CORSFilter filter = new CORSFilter();

        filter.init(filterConfig);
        filter.doFilter(request, response, chain);
        filter.destroy();

        verify(response).setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        verify(response).setHeader("Access-Control-Allow-Origin", "*");
        verify(response).setHeader("Access-Control-Allow-Headers", REQUESTED_HEADER);
    }
}