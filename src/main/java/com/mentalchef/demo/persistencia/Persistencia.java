package com.mentalchef.demo.persistencia;

import java.util.List;

import org.hibernate.Session;

import com.mentalchef.demo.modelos.Comentario;

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

}
