package ru.decathlon.example.test.task.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.decathlon.example.test.task.model.ErrorLog;
import ru.decathlon.example.test.task.model.User;
import ru.decathlon.example.test.task.repository.UserRepository;
import ru.decathlon.example.test.task.service.ErrorLogService;

@Log4j2
@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ErrorLogService errorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            UsernameNotFoundException ex = new UsernameNotFoundException("User doesn't exists!");
            log.error("loadUserByUsername: " + ex);
            errorService.saveErrorLog(new ErrorLog(ex));
            return ex;
        });

        return SecurityUser.fromUser(user);
    }
}
