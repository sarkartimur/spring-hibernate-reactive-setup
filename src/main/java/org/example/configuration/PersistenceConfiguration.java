package org.example.configuration;

import lombok.RequiredArgsConstructor;
import org.hibernate.reactive.provider.ReactivePersistenceProvider;
import org.hibernate.reactive.stage.Stage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class PersistenceConfiguration {
    @Value("${hibernate.package_to_scan}")
    private final String packageToScan;
    @Value("${spring.datasource.url}")
    private final String url;
    @Value("${spring.datasource.username}")
    private final String user;
    @Value("${spring.datasource.password:}")
    private final String pass;
    @Value("${hibernate.jdbc.batch_size}")
    private final int batchSize;
    @Value("${hibernate.generate_statistics}")
    private final boolean generateStatistics;
    @Value("${hibernate.show_sql}")
    private final boolean showSql;


    @Bean
    public LocalContainerEntityManagerFactoryBean reactiveEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPersistenceProvider(new ReactivePersistenceProvider());

        em.setPackagesToScan(packageToScan);
        em.setJpaProperties(jpaProps());

        return em;
    }

    private Properties jpaProps() {
        Properties props = new Properties();
        props.put("javax.persistence.jdbc.url", url);
        props.put("javax.persistence.jdbc.user", user);
        props.put("javax.persistence.jdbc.password", pass);
        props.put("hibernate.show_sql", showSql);
        props.put("hibernate.connection.pool_size", 20);
        props.put("hibernate.jdbc.batch_size", batchSize);
        props.put("hibernate.generate_statistics", generateStatistics);
        return props;
    }

    @Bean
    public Stage.SessionFactory reactiveSessionFactory(@Qualifier("reactiveEntityManagerFactory") EntityManagerFactory emf) {
        return emf.unwrap(Stage.SessionFactory.class);
    }
}
