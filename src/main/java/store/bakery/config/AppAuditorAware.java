package store.bakery.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AppAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//        try {
//            Optional<String> rs = SecurityUtil.getCurrentAccountOpt();
//            if (rs.isPresent()) {
//                return rs;
//            }
//            return Optional.of("system");
//        } catch (NullPointerException e) {
//            return Optional.of("system");
//        }

            return Optional.of("system");
    }
}
