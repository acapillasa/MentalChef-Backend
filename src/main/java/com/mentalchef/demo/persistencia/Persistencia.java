package com.mentalchef.demo.persistencia;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.mentalchef.demo.modelos.Categoria;
import com.mentalchef.demo.modelos.Comentario;
import com.mentalchef.demo.modelos.Pregunta;
import com.mentalchef.demo.modelos.Respuesta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Persistencia<T> implements IPersistencia<T> {

    private Session sesion;

    private Class<T> tipoEntidad;

    @Override
    public boolean guardar(Object t) {

        try {
            sesion.beginTransaction();

            sesion.persist(t);

            sesion.getTransaction().commit();

            return true;

        } catch (Exception e) {
            sesion.getTransaction().rollback();
            return false;
        }

    }

    @Override
    public T obtener(Object id) {

        try {
            sesion.getTransaction().begin();

            T objeto = sesion.get(tipoEntidad, (Long) id);

            sesion.getTransaction().commit();
            return objeto;
        } catch (Exception e) {

            sesion.getTransaction().rollback();

            return null;
        }

    }

    @Override
    public boolean actualizar(Object t) {

        try {
            sesion.getTransaction().begin();

            sesion.merge(t);

            sesion.getTransaction().commit();

            return true;

        } catch (Exception e) {
            sesion.getTransaction().rollback();

            return false;
        }

    }

    @Override
    public boolean eliminar(Object t) {

        try {
            sesion.getTransaction().begin();

            sesion.remove(t);

            sesion.getTransaction().commit();

            return true;

        } catch (Exception e) {
            sesion.getTransaction().rollback();

            return false;
        }
    }

    @Override
    public List<T> obtenerTodos() {
        try {
            sesion.getTransaction().begin();

            String hql = "from " + tipoEntidad.getSimpleName();
            List<T> resultado = sesion.createQuery(hql, tipoEntidad).list();

            sesion.getTransaction().commit();

            return resultado;

        } catch (Exception e) {
            sesion.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<T> obtenerPorNombre(String nombre) {

        try {
            sesion.beginTransaction();

            String hql = "from " + tipoEntidad.getSimpleName() + " where nombre = :nombre";
            List<T> resultados = sesion.createQuery(hql, tipoEntidad)
                    .setParameter("nombre", nombre)
                    .list();

            sesion.getTransaction().commit();
            return resultados;

        } catch (Exception e) {
            sesion.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<T> query(String key, String value) {
        try {
            sesion.beginTransaction();

            String hql = "from " + tipoEntidad.getSimpleName() + " where " + key + " = :value";
            List<T> resultados = sesion.createQuery(hql, tipoEntidad)
                    .setParameter("value", value)
                    .getResultList();

            sesion.getTransaction().commit();
            return resultados;
        } catch (Exception e) {

            sesion.getTransaction().rollback();

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comentario> obtenerComentariosPorPregunta(Long preguntaId) {
        String hql = "FROM Comentario WHERE pregunta.id = :preguntaId";
        return sesion.createQuery(hql, Comentario.class)
                .setParameter("preguntaId", preguntaId)
                .getResultList();
    }

    @Override
    public List<Comentario> obtenerComentariosPorUsuario(Long usuarioId) {
        String hql = "FROM Comentario WHERE usuario.id = :usuarioId";
        return sesion.createQuery(hql, Comentario.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    @Override
    public Pregunta obtenerPreguntaConRespuestasAleatoriaDiaria() {
        try {
            sesion.beginTransaction();

            String hqlPregunta = "FROM Pregunta p WHERE p.categoria.categoria = :categoria ORDER BY RAND()";
            Pregunta pregunta = sesion.createQuery(hqlPregunta, Pregunta.class)
                    .setParameter("categoria", "diaria")
                    .setMaxResults(1)
                    .uniqueResult();

            if (pregunta != null) {
                Hibernate.initialize(pregunta.getRespuestas());
            }

            sesion.getTransaction().commit();
            return pregunta;
        } catch (Exception e) {
            sesion.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pregunta> obtenerPreguntasPorCategoria(String nombreCategoria) {
        try {
            sesion.beginTransaction();

            String hql = "FROM Pregunta p WHERE p.categoria.categoria = :categoria";
            List<Pregunta> preguntas = sesion.createQuery(hql, Pregunta.class)
                    .setParameter("categoria", nombreCategoria)
                    .getResultList();

            for (Pregunta pregunta : preguntas) {
                Hibernate.initialize(pregunta.getRespuestas());
            }

            sesion.getTransaction().commit();
            return preguntas;
        } catch (Exception e) {
            sesion.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Pregunta obtenerPreguntaConRespuestasAleatoria() {
        try {
            sesion.beginTransaction();

            String hqlPregunta = "FROM Pregunta p ORDER BY RAND()";
            Pregunta pregunta = sesion.createQuery(hqlPregunta, Pregunta.class)
                    .setMaxResults(1)
                    .uniqueResult();

            if (pregunta != null) {
                Hibernate.initialize(pregunta.getRespuestas());
            }

            sesion.getTransaction().commit();
            return pregunta;
        } catch (Exception e) {
            sesion.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Categoria obtenerCategoriaPorNombre(String nombreCategoria) {
        try {
            sesion.beginTransaction();

            String hql = "FROM Categoria WHERE categoria = :categoria";
            Categoria categoria = sesion.createQuery(hql, Categoria.class)
                    .setParameter("categoria", nombreCategoria).uniqueResult();

            sesion.getTransaction().commit();
            return categoria;
        } catch (Exception e) {
            sesion.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

}
