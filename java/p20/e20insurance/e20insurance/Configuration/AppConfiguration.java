  package p20.e20insurance.e20insurance.Configuration;

import org.springframework.beans.factory.annotation.Value;

// ref: https://zetcode.com/springboot/cors/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {  

    @Value("${E20CORSAllowedSourceUrl:notSet}")
    String corsSourceUrl; 

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(corsSourceUrl)
                .allowedMethods("ALL");
    }

}  
