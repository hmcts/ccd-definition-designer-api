package uk.gov.hmcts.ccd.definition.designer;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
public class AuthCheckerConfiguration {

    @VisibleForTesting
    protected static final String ROLE_CCD_IMPORT = "ccd-import";

    @Value("#{'${casedefinitionstore.authorised.services}'.split(',')}")
    private List<String> authorisedServices;

    @Bean
    public Function<HttpServletRequest, Collection<String>> authorizedServicesExtractor() {
        return request -> authorisedServices;
    }

    @Bean
    public Function<HttpServletRequest, Collection<String>> authorizedRolesExtractor() {
        return request -> Collections.emptyList();
    }

    @Bean
    public Function<HttpServletRequest, Optional<String>> userIdExtractor() {
        return request -> Optional.empty();
    }
}
