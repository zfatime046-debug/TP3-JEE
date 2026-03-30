package config;

import dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:app.properties")
public class PropertyDrivenConfig {

    @Value("${dao.target:dao}")
    private String target;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "dao")
    public IDao selectedDao(DaoImpl dao, DaoImpl2 dao2, DaoFile daoFile, DaoApi daoApi) {
        return switch (target.trim()) {
            case "dao"     -> dao;
            case "dao2"    -> dao2;
            case "daoFile" -> daoFile;
            case "daoApi"  -> daoApi;
            default -> throw new IllegalArgumentException(
                    "Implémentation inconnue: " + target
            );
        };
    }
}