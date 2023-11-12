package store.bakery.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import store.bakery.exception.UnAuthorizedException;
import store.bakery.model.appEnum.RoleEnum;

import java.util.Optional;

@Component
public class SecurityUtil {
    public static Optional<String> getCurrentAccountOpt() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Object object = authentication.getPrincipal();
        if (object instanceof User) {
            return Optional.of(((User) object).getUsername());
        }
        return Optional.empty();
    }

    public static String getCurrentAccount() {
        return getCurrentAccountOpt().orElseThrow(UnAuthorizedException::new);
    }

    public static Optional<RoleEnum> getRoleCurrentAccountOpt() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        // ROLE_ADMIN, ROLE_STAFF
        return authentication.getAuthorities().stream()
                .map(authority -> RoleEnum.valueOf(
                        authority.getAuthority().replaceAll("ROLE_", "")))
                .findFirst();
    }

    public static RoleEnum getRoleCurrentAccount() {
        return getRoleCurrentAccountOpt().orElseThrow(UnAuthorizedException::new);
    }

    public static boolean isAdmin() {
        return getRoleCurrentAccount() == RoleEnum.ADMIN;
    }

    public static boolean isStaff() {
        return getRoleCurrentAccount() == RoleEnum.STAFF;
    }

}