package com.mentalchef.demo.persistencia;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
import com.mentalchef.demo.persistencia.impl.Persistencia;

import jakarta.persistence.EntityManagerFactory;

@EnableJpaRepositories(basePackages = "com.mentalchef.demo.persistencia")
@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
public class SpringPersistenciaConf {

    // JPA Configuration
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.14.1.60:3306/MentalChef");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("com.mentalchef.demo.modelos");
        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public SessionFactory sessionFactory() {
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml")
            .addAnnotatedClass(Categoria.class)
            .addAnnotatedClass(Chef.class)
            .addAnnotatedClass(Comentario.class)
            .addAnnotatedClass(Compra.class)
            .addAnnotatedClass(CompraId.class)
            .addAnnotatedClass(Pinche.class)
            .addAnnotatedClass(Pregunta.class)
            .addAnnotatedClass(Progreso.class)
            .addAnnotatedClass(ProgresoId.class)
            .addAnnotatedClass(Respuesta.class)
            .addAnnotatedClass(Tienda.class)
            .addAnnotatedClass(Usuario.class);
        return conf.buildSessionFactory();
    }

    @Bean
    public Session session(SessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }

    // Interfaces Bean
    @Bean
    public IPersistencia<Categoria> getPersistenciaCategoria(SessionFactory session) {
        Persistencia<Categoria> persistencia = new Persistencia<Categoria>(session, Categoria.class);
        return persistencia;
    }

    @Bean
    @Primary
    public IPersistencia<Usuario> getPersistenciaUsuario(Session session) {
        Persistencia<Usuario> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(Usuario.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<Chef> getPersistenciaChef(Session session) {
        Persistencia<Chef> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(Chef.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<Pinche> getPersistenciaPinche(Session session) {
        Persistencia<Pinche> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(Pinche.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<Tienda> getPersistenciaTienda(Session session) {
        Persistencia<Tienda> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(Tienda.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<Pregunta> getPersistenciaPregunta(Session session) {
        Persistencia<Pregunta> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(Pregunta.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<Respuesta> getPersistenciaRespuesta(Session session) {
        Persistencia<Respuesta> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(Respuesta.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<Comentario> getPersistenciaComentario(Session session) {
        Persistencia<Comentario> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(Comentario.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<Progreso> getPersistenciaProgreso(Session session) {
        Persistencia<Progreso> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(Progreso.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<ProgresoId> getPersistenciaProgresoId(Session session) {
        Persistencia<ProgresoId> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(ProgresoId.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<Compra> getPersistenciaCompra(Session session) {
        Persistencia<Compra> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(Compra.class);
        return persistencia;
    }

    @Bean
    public IPersistencia<CompraId> getPersistenciaCompraId(Session session) {
        Persistencia<CompraId> persistencia = new Persistencia<>();
        persistencia.setSession(session);
        persistencia.setTipoEntidad(CompraId.class);
        return persistencia;
    }
}
