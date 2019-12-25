package sec.project.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // no real security at the moment
        
        http.csrf().disable();
//        http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository());
        http.authorizeRequests()
                .anyRequest().permitAll();
    }

    
}
