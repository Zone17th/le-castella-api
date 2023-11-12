package store.bakery.resource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.bakery.exception.UnAuthorizedException;
import store.bakery.model.dto.LoginRequestDTO;
import store.bakery.model.dto.LoginResponseDTO;
import store.bakery.model.entity.Account;
import store.bakery.security.TokenProvider;
import store.bakery.service.AccountService;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationResource {
    private final AccountService accountService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    public AuthenticationResource(AccountService accountService, AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider) {
        this.accountService = accountService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        Optional<Account> accountOptional = accountService.findByUsername(loginRequest.getUsername());
        if (accountOptional.isEmpty()) {
            throw new UnAuthorizedException();
        }
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = tokenProvider.generateAccessToken(authentication);
            Account account = accountOptional.get();
            LoginResponseDTO loginResponse = new LoginResponseDTO();
            loginResponse.setUsername(account.getUsername());
            loginResponse.setFullName(account.getFullName());
            loginResponse.setAccessToken(accessToken);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            throw new UnAuthorizedException();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            SecurityContextHolder.clearContext();
        }
        return ResponseEntity.noContent().build();
    }
}
