package com.mentalchef.demo.persistencia.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.mentalchef.demo.modelos.Categoria;
import com.mentalchef.demo.modelos.Comentario;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Repository
@NoArgsConstructor
public class Persistencia<T> implements IPersistencia<T> {

    private SessionFactory sessionFactory;
    private Class<T> tipoEntidad;

    @Override
    public boolean guardar(Object t) {

        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {

            if (session != null) {
                session.getTransaction().rollback();
                session.close();
            }
            return false;
        }
    }

    @Override
    public T obtener(Object id) {
        try {
            session.beginTransaction();
            T objeto = session.get(tipoEntidad, (Long) id);
            session.getTransaction().commit();
            return objeto;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public boolean actualizar(Object t) {
        try {
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean eliminar(Object t) {
        try {
            session.beginTransaction();
            session.remove(t);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public List<T> obtenerTodos() {
        try {
            session.beginTransaction();
            String hql = "from " + tipoEntidad.getSimpleName();
            List<T> resultado = session.createQuery(hql, tipoEntidad).list();
            session.getTransaction().commit();
            return resultado;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<T> obtenerPorNombre(String nombre) {
        try {
            session.beginTransaction();
            String hql = "from " + tipoEntidad.getSimpleName() + " where nombre = :nombre";
            List<T> resultados = session.createQuery(hql, tipoEntidad)
                    .setParameter("nombre", nombre)
                    .list();
            session.getTransaction().commit();
            return resultados;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<T> query(String key, Object value) {
        try {
            session.beginTransaction();
            String hql = "from " + tipoEntidad.getSimpleName() + " where " + key + " = :value";
            List<T> resultados = session.createQuery(hql, tipoEntidad)
                    .setParameter("value", value)
                    .getResultList();
            session.getTransaction().commit();
            return resultados;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comentario> obtenerComentariosPorPregunta(Long preguntaId) {
        String hql = "FROM Comentario WHERE pregunta.id = :preguntaId";
        return session.createQuery(hql, Comentario.class)
                .setParameter("preguntaId", preguntaId)
                .getResultList();
    }

    @Override
    public List<Comentario> obtenerComentariosPorUsuario(Long usuarioId) {
        String hql = "FROM Comentario WHERE usuario.id = :usuarioId";
        return session.createQuery(hql, Comentario.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    @Override
    public Pregunta obtenerPreguntaConRespuestasAleatoriaDiaria() {
        try {
            session.beginTransaction();
            String hqlPregunta = "FROM Pregunta p WHERE p.categoria.categoria = :categoria ORDER BY RAND()";
            Pregunta pregunta = session.createQuery(hqlPregunta, Pregunta.class)
                    .setParameter("categoria", "DIARIA")
                    .setMaxResults(1)
                    .uniqueResult();
            if (pregunta != null) {
                Hibernate.initialize(pregunta.getRespuestas());
            }
            session.getTransaction().commit();
            return pregunta;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pregunta> obtenerPreguntasPorCategoria(String nombreCategoria) {
        try {
            session.beginTransaction();
            String hql = "FROM Pregunta p WHERE p.categoria.categoria = :categoria";
            List<Pregunta> preguntas = session.createQuery(hql, Pregunta.class)
                    .setParameter("categoria", nombreCategoria)
                    .getResultList();
            for (Pregunta pregunta : preguntas) {
                Hibernate.initialize(pregunta.getRespuestas());
            }
            session.getTransaction().commit();
            return preguntas;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pregunta obtenerPreguntaConRespuestasAleatoria() {
        try {
            session.beginTransaction();
            String countHql = "SELECT COUNT(p) FROM Pregunta p";
            Long totalPreguntas = session.createQuery(countHql, Long.class).uniqueResult();
            if (totalPreguntas == 0) {
                System.out.println("No hay preguntas disponibles en la base de datos.");
                session.getTransaction().commit();
                return null;
            }
            String hqlPregunta = "FROM Pregunta p JOIN FETCH p.respuestas ORDER BY RAND()";
            Pregunta pregunta = session.createQuery(hqlPregunta, Pregunta.class)
                    .setMaxResults(1)
                    .uniqueResult();
            if (pregunta == null) {
                System.out.println("La consulta aleatoria no devolvió una pregunta.");
            } else {
                System.out.println("Pregunta obtenida: " + pregunta.getId());
            }
            session.getTransaction().commit();
            return pregunta;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Categoria obtenerCategoriaPorNombre(String nombreCategoria) {
        try {
            session.beginTransaction();
            String hql = "FROM Categoria WHERE categoria = :categoria";
            Categoria categoria = session.createQuery(hql, Categoria.class)
                    .setParameter("categoria", nombreCategoria).uniqueResult();
            session.getTransaction().commit();
            return categoria;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean eliminarRespuestasDePregunta(Long preguntaId) {
        try {
            session.beginTransaction();
            int result = session.createQuery("DELETE FROM Respuesta WHERE pregunta.id = :id")
                    .setParameter("id", preguntaId)
                    .executeUpdate();
            session.getTransaction().commit();
            return result > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarPreguntaPorId(Long preguntaId) {
        try {
            session.beginTransaction();
            int result = session.createQuery("DELETE FROM Pregunta WHERE id = :id")
                    .setParameter("id", preguntaId)
                    .executeUpdate();
            session.getTransaction().commit();
            return result > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
