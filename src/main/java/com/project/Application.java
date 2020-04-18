package com.project;

import com.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAsync
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new Application().configure(new SpringApplicationBuilder(Application.class)).run(args);
    }

    @Autowired
    public void testAuthWire(OrderService orderService){
        System.out.println("Можно удалить , пример того , как на этапе создания бинов можно что-то сетать в бин");
        orderService.toString();
    };

    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
// Эти изменения в ветке , в которой разрабатывается какая-то фича (это например нам не надо , можно удалить при сливании, но я оставлю)
// фича
// какие-то изменения в мастере