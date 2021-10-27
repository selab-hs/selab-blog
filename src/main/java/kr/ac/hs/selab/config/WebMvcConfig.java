package kr.ac.hs.selab.config;

import kr.ac.hs.selab.oauth.application.OAuth2Interceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final OAuth2Interceptor oAuth2Interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(oAuth2Interceptor)
                .addPathPatterns("/")
                .addPathPatterns("/index")
                .addPathPatterns("/auth/**")
                .excludePathPatterns("/auth/signup/social");
    }
}
