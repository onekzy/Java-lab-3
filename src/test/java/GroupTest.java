import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void setTitle() {
        Group gr = new Group("Group 1");
        String title = "Group 12";
        gr.setTitle(title);
        assertEquals(title, gr.getTitle());
    }

    @Test
    public void addStudent() {
        Group gr = new Group("Group 2");
        Student st1 = new Student(21, "Ярцев Михаил Вадимович");
        Student st2 = new Student(22, "Кузьмич Валентин Андриянович");
        ArrayList<Student> test = new ArrayList<Student>();
        test.add(st1);
        test.add(st2);
        assertEquals(false, test.equals(gr.getStudents()));
        gr.addStudent(st1);
        gr.addStudent(st2);
        assertEquals(true, test.equals(gr.getStudents()));
    }

    @Test
    public void setThisGroup() {
        Group gr = new Group("Group 2");
        Student st2 = new Student(22, "Кузьмич Валентин Андриянович");
        gr.addStudent(st2);
        gr.setThisGroup();
        assertEquals(gr, st2.getGroup());
    }

    @Test
    public void getTitle() {
        Group gr = new Group("Group 51");
        String title = "Group 68";
        gr.setTitle(title);
        assertEquals(title, gr.getTitle());
    }

    @Test
    public void getStudents() {
        Group gr = new Group("Group 1");
        Student st1 = new Student(1, "Ярцев Михаил Вадимович");
        Student st2 = new Student(2, "Кузьмич Валентин Андриянович");
        gr.addStudent(st1);
        gr.addStudent(st2);
        ArrayList<Student> test = new ArrayList<Student>();
        test.add(st1);
        test.add(st2);
        assertEquals(true, test.equals(gr.getStudents()));
    }

    @Test
    public void getHead() {
        Group gr = new Group("Group 11");
        Student st = new Student(12, "Ярцев Михаил Вадимович");
        gr.addStudent(st);
        gr.assignHead();
        assertEquals(st, gr.getHead());
    }

    @Test
    public void assignHead() {
        Group gr = new Group("Group 51");
        Student st = new Student(23, "Ярцев Михаил Вадимович");
        gr.addStudent(st);
        assertEquals(null, gr.getHead());
        gr.assignHead();
        assertEquals(st, gr.getHead());
    }

    @Test
    public void searchStudent() {
        Group gr = new Group("Group 1");
        Student st1 = new Student(1, "Ярцев Михаил Вадимович");
        Student st2 = new Student(2, "Кузьмич Валентин Андриянович");
        Student st3 = new Student(3, "Каиров Тимур Кондратиевич");
        gr.addStudent(st1);
        gr.addStudent(st2);
        assertEquals(st1, gr.searchStudent(st1.getId()));
        assertEquals(st2, gr.searchStudent(st2.getId()));
        assertEquals(null, gr.searchStudent(st3.getId()));
    }

    @Test
    public void calcAverMark() {
        Group gr = new Group("Group 1");
        Student st1 = new Student(1, "Ярцев Михаил Вадимович");
        Student st2 = new Student(2, "Кузьмич Валентин Андриянович");
        Student st3 = new Student(3, "Каиров Тимур Кондратиевич");
        gr.addStudent(st1);
        gr.addStudent(st2);
        gr.addStudent(st3);
        st1.addMark(5);
        st2.addMark(4);
        st3.addMark(4);
        assertEquals(4.3333333, gr.calcAverMark(), 0.0001);
    }

    @Test
    public void removeStudent() {
        Group gr = new Group("Group 1");
        Student st1 = new Student(1, "Ярцев Михаил Вадимович");
        Student st2 = new Student(2, "Кузьмич Валентин Андриянович");
        Student st3 = new Student(3, "Каиров Тимур Кондратиевич");
        gr.addStudent(st1);
        gr.assignHead();
        gr.addStudent(st2);
        assertEquals(st1, gr.searchStudent(st1.getId()));
        assertEquals(st2, gr.searchStudent(st2.getId()));

        assertEquals(true, gr.removeStudent(st1.getId()));
        assertEquals(false, gr.removeStudent(st3.getId()));
        assertEquals(null, gr.searchStudent(st1.getId()));
        assertEquals(st2, gr.searchStudent(st2.getId()));
    }
}