package edu.badpals;

import edu.badpals.enums.TypeEnum;
import edu.badpals.model.Card;
import edu.badpals.model.Course;
import edu.badpals.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            TypeEnum typeFP = TypeEnum.FP;
            TypeEnum typeESO = TypeEnum.ESO;
            Card card1 = new Card("_123", typeFP, null);
            Card card2 = new Card("_456", typeESO, null);

            Course course = new Course("Ciencias", null, null);

            Student student1 = new Student("Francisco Garrido", "23456789A", "123456789", card1);
            Student student2 = new Student("Ana Fernandez", "00000000T", "897878787", card2);

            student1.getCourses().add(course);
            student2.getCourses().add(course);
            course.getStudents().add(student1);
            course.getStudents().add(student2);

            em.persist(card1);
            em.persist(card2);
            em.persist(course);
            em.persist(student1);
            em.persist(student2);

            em.getTransaction().commit();

            System.out.println("Estudiantes creados:");
            System.out.println(student1);
            System.out.println(student2);

/*            em.getTransaction().begin();
            em.remove(student1);
            em.getTransaction().commit();

            System.out.println("Estudiante 1 eliminado");
            System.out.println("Su carta: " + card1);*/

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
