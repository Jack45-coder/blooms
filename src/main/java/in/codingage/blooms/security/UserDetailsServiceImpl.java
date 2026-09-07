package in.codingage.blooms.security;

import in.codingage.blooms.models.User;
import in.codingage.blooms.repository.UserRepository;
import in.codingage.blooms.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException{
        User user = userRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException("User not found with phone: " + phone));

        return org.springframework.security.core.userdetails.User.withUsername(user.getPhone())
                .password(user.getPassword())
                .authorities(user.getRole().stream().map(Role::getName).toArray(String[]::new))
                .build();
    }
}
