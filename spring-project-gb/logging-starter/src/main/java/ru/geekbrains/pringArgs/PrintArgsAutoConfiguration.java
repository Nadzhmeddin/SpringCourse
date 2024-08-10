package ru.geekbrains.pringArgs;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PrintArgsProperties.class)
public class PrintArgsAutoConfiguration {

    @Bean
    public PrintArgsAspect printArgsAspect(PrintArgsProperties properties) {
        return new PrintArgsAspect(properties);
    }


}
