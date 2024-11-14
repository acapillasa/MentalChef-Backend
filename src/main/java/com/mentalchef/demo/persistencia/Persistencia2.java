// package com.mentalchef.demo.persistencia;

// import java.util.List;

// import org.hibernate.Hibernate;
// import org.hibernate.Session;
// import org.hibernate.SessionFactory;

// import com.mentalchef.demo.modelos.Categoria;
// import com.mentalchef.demo.modelos.Comentario;
// import com.mentalchef.demo.modelos.Pregunta;

// import lombok.AllArgsConstructor;

// @AllArgsConstructor
// public class Persistencia2<T> implements IPersistencia<T> {

//     private SessionFactory sessionFactory;
//     private Class<T> classType;

//     @Override
//     public boolean guardar(T t) {

//         Session session = null;

//         try {

//             session = sessionFactory.openSession();

//             session.beginTransaction();
//             session.persist(t);
//             session.getTransaction().commit();
//             session.close();
//             return true;
//         } catch (Exception e) {

//             if (session != null) {
//                 session.getTransaction().rollback();
//                 session.close();
//             }
//             return false;
//         }
//     }

//     @Override
//     public T obtener(Object id) {
//         Session session = sessionFactory.openSession();
//         try {
//             return session.get(classType, id);
//         } finally {
//             session.close();
//         }
//     }

//     @Override
//     public boolean actualizar(T t) {
//         Session session = sessionFactory.openSession();
//         try {
//             session.beginTransaction();
//             session.merge(t);
//             session.getTransaction().commit();
//             return true;
//         } catch (Exception e) {
//             session.getTransaction().rollback();
//             return false;
//         } finally {
//             session.close();
//         }
//     }

//     @Override
//     public boolean eliminar(T t) {
//         Session session = sessionFactory.openSession();
//         try {
//             session.beginTransaction();
//             session.remove(t);
//             session.getTransaction().commit();
//             return true;
//         } catch (Exception e) {
//             session.getTransaction().rollback();
//             return false;
//         } finally {
//             session.close();
//         }
//     }

//     @Override
//     public List<T> obtenerTodos() {
//         Session session = sessionFactory.openSession();
//         List<T> entities = null;
//         try {
//             while (session.getTransaction().isActive()) {
//                 wait(10);
//             }
//             session.beginTransaction();
//             entities = session.createQuery("from " + classType.getName(), classType).list();
//             return entities;
//         } catch (Exception e) {
//             e.printStackTrace();
//             return entities;
//         } finally {
//             session.close();
//         }
//     }

//     @Override
//     public List<T> obtenerPorNombre(String nombre) {
//         Session session = sessionFactory.openSession();
//         try {
//             return session.createQuery("from " + classType.getName() + " where nombre = :nombre", classType)
//                     .setParameter("nombre", nombre).list();
//         } finally {
//             session.close();
//         }
//     }

//     @Override
//     public List<T> query(String key, String value) {
//         Session session = sessionFactory.openSession();
//         try {
//             return session.createQuery("from " + classType.getName() + " where " + key + " = :value", classType)
//                     .setParameter("value", value).list();
//         } finally {
//             session.close();
//         }
//     }

//     @Override
//     public List<Comentario> obtenerComentariosPorPregunta(Long preguntaId) {
//         Session sesion = sessionFactory.openSession();
//         String hql = "FROM Comentario WHERE pregunta.id = :preguntaId";
//         return sesion.createQuery(hql, Comentario.class)
//                 .setParameter("preguntaId", preguntaId)
//                 .getResultList();
//     }

//     @Override
//     public List<Comentario> obtenerComentariosPorUsuario(Long usuarioId) {
//         Session sesion = sessionFactory.openSession();
//         String hql = "FROM Comentario WHERE usuario.id = :usuarioId";
//         return sesion.createQuery(hql, Comentario.class)
//                 .setParameter("usuarioId", usuarioId)
//                 .getResultList();
//     }

//     @Override
//     public Pregunta obtenerPreguntaConRespuestasAleatoriaDiaria() {
//         try {
//             Session sesion = sessionFactory.openSession();
//             sesion.beginTransaction();

//             String hqlPregunta = "FROM Pregunta p WHERE p.categoria.categoria = :categoria ORDER BY RAND()";
//             Pregunta pregunta = sesion.createQuery(hqlPregunta, Pregunta.class)
//                     .setParameter("categoria", "DIARIA")
//                     .setMaxResults(1)
//                     .uniqueResult();

//             if (pregunta != null) {
//                 Hibernate.initialize(pregunta.getRespuestas());
//             }

//             sesion.getTransaction().commit();
//             return pregunta;
//         } catch (Exception e) {
//             Session sesion = sessionFactory.openSession();
//             sesion.getTransaction().rollback();
//             e.printStackTrace();
//             return null;
//         }
//     }

//     @Override
//     public List<Pregunta> obtenerPreguntasPorCategoria(String nombreCategoria) {
//         try {
//             Session sesion = sessionFactory.openSession();
//             sesion.beginTransaction();

//             String hql = "FROM Pregunta p WHERE p.categoria.categoria = :categoria";
//             List<Pregunta> preguntas = sesion.createQuery(hql, Pregunta.class)
//                     .setParameter("categoria", nombreCategoria)
//                     .getResultList();

//             for (Pregunta pregunta : preguntas) {
//                 Hibernate.initialize(pregunta.getRespuestas());
//             }

//             sesion.getTransaction().commit();
//             return preguntas;
//         } catch (Exception e) {
//             Session sesion = sessionFactory.openSession();
//             sesion.getTransaction().rollback();
//             e.printStackTrace();
//             return null;
//         }
//     }

//     @Override
//     public Pregunta obtenerPreguntaConRespuestasAleatoria() {
//         try {
//             Session sesion = sessionFactory.openSession();
//             sesion.beginTransaction();

//             // Verifica si hay preguntas disponibles
//             String countHql = "SELECT COUNT(p) FROM Pregunta p";
//             Long totalPreguntas = sesion.createQuery(countHql, Long.class).uniqueResult();

//             if (totalPreguntas == 0) {
//                 System.out.println("No hay preguntas disponibles en la base de datos.");
//                 sesion.getTransaction().commit();
//                 return null;
//             }

//             // Si hay preguntas, selecciona una al azar
//             String hqlPregunta = "FROM Pregunta p JOIN FETCH p.respuestas ORDER BY RAND()";
//             Pregunta pregunta = sesion.createQuery(hqlPregunta, Pregunta.class)
//                     .setMaxResults(1)
//                     .uniqueResult();

//             // Verificación adicional para asegurarse de que la pregunta no sea nula
//             if (pregunta == null) {
//                 System.out.println("La consulta aleatoria no devolvió una pregunta.");
//             } else {
//                 System.out.println("Pregunta obtenida: " + pregunta.getId());
//             }

//             sesion.getTransaction().commit();
//             return pregunta;
//         } catch (Exception e) {
//             Session sesion = sessionFactory.openSession();
//             if (sesion.getTransaction() != null) {
//                 sesion.getTransaction().rollback();
//             }
//             e.printStackTrace();
//             return null;
//         }
//     }

//     @Override
//     public Categoria obtenerCategoriaPorNombre(String nombreCategoria) {
//         try {
//             Session sesion = sessionFactory.openSession();
//             sesion.beginTransaction();

//             String hql = "FROM Categoria WHERE categoria = :categoria";
//             Categoria categoria = sesion.createQuery(hql, Categoria.class)
//                     .setParameter("categoria", nombreCategoria).uniqueResult();

//             sesion.getTransaction().commit();
//             return categoria;
//         } catch (Exception e) {
//             Session sesion = sessionFactory.openSession();
//             sesion.getTransaction().rollback();
//             e.printStackTrace();
//             return null;
//         }
//     }

//     @Override
//     public boolean eliminarRespuestasDePregunta(Long id) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'eliminarRespuestasDePregunta'");
//     }

// }
