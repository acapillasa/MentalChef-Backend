package com.mentalchef.demo.persistencia;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.mentalchef.demo.modelos.Categoria;
import com.mentalchef.demo.modelos.Chef;
import com.mentalchef.demo.modelos.Comentario;
import com.mentalchef.demo.modelos.Compra;
import com.mentalchef.demo.modelos.CompraId;
import com.mentalchef.demo.modelos.Pinche;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Progreso;
import com.mentalchef.demo.modelos.ProgresoId;
import com.mentalchef.demo.modelos.Respuesta;
import com.mentalchef.demo.modelos.Tienda;
import com.mentalchef.demo.modelos.Usuario;

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
public class SpringPersistenciaConf {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/MentalChef");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.mentalchef.demo.modelos"); // Ajusta esto si es necesario

        // Configurar el adaptador de proveedor de JPA
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Configurar propiedades de Hibernate
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        em.setJpaProperties(hibernateProperties);
        return em;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }


    @Bean
    public IPersistencia<Categoria> getPersistenciaCategoria(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Categoria.class);
    }

    // Bean para la persistencia de Usuario
    @Bean
    public IPersistencia<Usuario> getPersistenciaUsuario(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Usuario.class);
    }

    @Bean
    public IPersistencia<Chef> getPersistenciaChef(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Chef.class);
    }

    @Bean
    public IPersistencia<Pinche> getPersistenciaPinche(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Pinche.class);
    }

    // Bean para la persistencia de Tienda
    @Bean
    public IPersistencia<Tienda> getPersistenciaTienda(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Tienda.class);
    }

    // Bean para la persistencia de Pregunta
    @Bean
    public IPersistencia<Pregunta> getPersistenciaPregunta(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Pregunta.class);
    }

    // Bean para la persistencia de Respuesta
    @Bean
    public IPersistencia<Respuesta> getPersistenciaRespuesta(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Respuesta.class);
    }

    // Bean para la persistencia de Comentario
    @Bean
    public IPersistencia<Comentario> getPersistenciaComentario(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Comentario.class);
    }

    // Bean para la persistencia de Progreso
    @Bean
    public IPersistencia<Progreso> getPersistenciaProgreso(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Progreso.class);
    }

    @Bean
    public IPersistencia<ProgresoId> getPersistenciaProgresoId(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), ProgresoId.class);
    }

    @Bean
    public IPersistencia<Compra> getPersistenciaCompra(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), Compra.class);
    }

    @Bean
    public IPersistencia<CompraId> getPersistenciaCompraId(SessionFactory sessionFactory) {
        return new Persistencia<>(sessionFactory.openSession(), CompraId.class);
    }
}
