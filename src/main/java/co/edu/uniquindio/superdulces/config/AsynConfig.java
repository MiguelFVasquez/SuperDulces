package co.edu.uniquindio.superdulces.config;


import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class AsynConfig implements AsyncConfigurer {
}

