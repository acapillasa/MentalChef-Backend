package com.mentalchef.demo.persistencia.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.mentalchef.demo.modelos.Categoria;
import com.mentalchef.demo.modelos.Comentario;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Progreso;
import com.mentalchef.demo.modelos.Respuesta;
import com.mentalchef.demo.modelos.Usuario;
import com.mentalchef.demo.persistencia.IPersistencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Repository
@NoArgsConstructor
@AllArgsConstructor
public class Persistencia<T> implements IPersistencia<T> {

    private SessionFactory sessionFactory;
    private Class<T> tipoEntidad;

    @Override
    public boolean guardar(Object t) {

        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.merge(t);
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

        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            T objeto = session.get(tipoEntidad, (Long) id);
            session.getTransaction().commit();
            session.close();
            return objeto;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public boolean actualizar(Object t) {

        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(Object t) {

        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(t);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<T> obtenerTodos() {

        Session session = null;
        try {

            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "from " + tipoEntidad.getSimpleName();
            List<T> resultado = session.createQuery(hql, tipoEntidad).list();
            session.getTransaction().commit();
            session.close();
            return resultado;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> obtenerPorNombre(String nombre) {

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "from " + tipoEntidad.getSimpleName() + " where nombre = :nombre";
            List<T> resultados = session.createQuery(hql, tipoEntidad)
                    .setParameter("nombre", nombre)
                    .list();
            session.getTransaction().commit();
            session.close();
            return resultados;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> query(String key, Object value) {

        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "from " + tipoEntidad.getSimpleName() + " where " + key + " = :value";
            List<T> resultados = session.createQuery(hql, tipoEntidad)
                    .setParameter("value", value)
                    .getResultList();
            session.getTransaction().commit();
            session.close();
            return resultados;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comentario> obtenerComentariosPorPregunta(Long preguntaId) {

        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            
            String hql = "FROM Comentario WHERE pregunta.id = :preguntaId";
            List<Comentario> comentarios = session.createQuery(hql, Comentario.class)
                    .setParameter("preguntaId", preguntaId)
                    .getResultList();
            session.getTransaction().commit();
            session.close();
            return comentarios;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Comentario> obtenerComentariosPorUsuario(Long usuarioId) {

        Session session = null;

        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM Comentario WHERE usuario.id = :usuarioId";

            List<Comentario> comentarios = session.createQuery(hql, Comentario.class)
                    .setParameter("usuarioId", usuarioId)
                    .getResultList();
            session.getTransaction().commit();
            session.close();
            return comentarios;
        
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Pregunta obtenerPreguntaConRespuestasAleatoriaDiaria() {

        Session session = null;
        try {
            session = sessionFactory.openSession();
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
            session.close();
            return pregunta;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pregunta> obtenerPreguntasPorCategoria(String nombreCategoria) {

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM Pregunta p WHERE p.categoria.categoria = :categoria";
            List<Pregunta> preguntas = session.createQuery(hql, Pregunta.class)
                    .setParameter("categoria", nombreCategoria)
                    .getResultList();
            for (Pregunta pregunta : preguntas) {
                Hibernate.initialize(pregunta.getRespuestas());
            }
            session.getTransaction().commit();
            session.close();
            return preguntas;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pregunta obtenerPreguntaConRespuestasAleatoria() {

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String countHql = "SELECT COUNT(p) FROM Pregunta p";
            Long totalPreguntas = session.createQuery(countHql, Long.class).uniqueResult();
            if (totalPreguntas == 0) {
                System.out.println("No hay preguntas disponibles en la base de datos.");
                session.getTransaction().commit();
                session.close();
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
            session.close();
            return pregunta;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Categoria obtenerCategoriaPorNombre(String nombreCategoria) {

        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM Categoria WHERE categoria = :categoria";
            Categoria categoria = session.createQuery(hql, Categoria.class)
                    .setParameter("categoria", nombreCategoria).uniqueResult();
            session.getTransaction().commit();
            session.close();
            return categoria;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pregunta obtenerPreguntaPorNombre(String nombrePregunta) {

        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM Pregunta WHERE pregunta = :pregunta";
            Pregunta categoria = session.createQuery(hql, Pregunta.class)
                    .setParameter("pregunta", nombrePregunta).uniqueResult();
            session.getTransaction().commit();
            session.close();
            return categoria;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean eliminarRespuestasDePregunta(Long preguntaId) {

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            int result = session.createQuery("DELETE FROM Respuesta WHERE pregunta.id = :id")
                    .setParameter("id", preguntaId)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
            return result > 0;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarPreguntaPorId(Long preguntaId) {

        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Pregunta pregunta = session.get(Pregunta.class, preguntaId);
            if (pregunta == null) {
                System.out.println("No se encontró la pregunta con id: " + preguntaId);
                return false;
            }
            session.remove(pregunta);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCategoriaPorCategoria(String categoriaNombre) {

        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Categoria categoria = session.get(Categoria.class, categoriaNombre);
            if (categoria == null) {
                System.out.println("No se encontró la categoria: " + categoria);
                return false;
            }
            session.remove(categoria);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Respuesta> obtenerRespuestasDePregunta(Long id) {
            
            Session session = null;
    
            try {
                session = sessionFactory.openSession();
                session.beginTransaction();
                String hql = "FROM Respuesta WHERE pregunta.id = :id";
                List<Respuesta> respuestas = session.createQuery(hql, Respuesta.class)
                        .setParameter("id", id)
                        .getResultList();
                session.getTransaction().commit();
                session.close();
                return respuestas;
            } catch (Exception e) {
                if (session != null) {
                    session.getTransaction().rollback();
                    session.close();
                    
                }
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public Usuario buscarPorNombreConCompras(String username) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "SELECT u FROM Usuario u LEFT JOIN FETCH u.compras WHERE u.username = :username";
            Usuario usuario = session.createQuery(hql, Usuario.class)
                    .setParameter("username", username)
                    .uniqueResult();
            session.getTransaction().commit();
            session.close();
            return usuario;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Progreso> obtenerProgresosMes() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM Progreso p WHERE "
                    + "MONTH(p.fechaRespuesta) = MONTH(CURRENT_DATE()) AND "
                    + "YEAR(p.fechaRespuesta) = YEAR(CURRENT_DATE())";
            List<Progreso> progresos = session.createQuery(hql, Progreso.class).getResultList();
            session.getTransaction().commit();
            session.close();
            return progresos;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override 
    public List<Progreso> obtenerProgresosUsuario(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM Progreso p WHERE p.id.usuario.id = :id AND "
                    + "MONTH(p.fechaRespuesta) = MONTH(CURRENT_DATE()) AND "
                    + "YEAR(p.fechaRespuesta) = YEAR(CURRENT_DATE())";
            List<Progreso> progresos = session.createQuery(hql, Progreso.class)
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            session.close();
            return progresos;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
            }
            e.printStackTrace();
            return null;
        }
    }
}
