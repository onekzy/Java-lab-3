import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentTest {

    @org.junit.Test
    public void setId() {
        Student st = new Student(2, "Ярцев Михаил Вадимович");
        int id = 23;
        st.setId(id);
        assertEquals(id, st.getId());
    }

    @org.junit.Test
    public void setFio() {
        Student st = new Student(2, "Ярцев Михаил Вадимович");
        String fio = "Коренёв Рубен Пахомович";
        st.setFio(fio);
        assertEquals(fio, st.getFio());
    }

    @org.junit.Test
    public void setGroup() {
        Student st = new Student(2, "Ярцев Михаил Вадимович");
        Group gr = new Group("Group 1");
        st.setGroup(gr);
        assertEquals(gr, st.getGroup());
    }

    @org.junit.Test
    public void addMark() {
        Student st = new Student(2, "Ярцев Михаил Вадимович");
        int mark = 4;
        st.addMark(mark);
        int res = st.getMarks().get(0);
        assertEquals(mark, res);
    }

    @org.junit.Test
    public void getId() {
        Student st = new Student(2, "Ярцев Михаил Вадимович");
        int id = 15;
        st.setId(id);
        assertEquals(id, st.getId());
    }

    @org.junit.Test
    public void getFio() {
        Student st = new Student(2, "Ярцев Михаил Вадимович");
        String fio = "Стаин Савелий Мартьянович";
        st.setFio(fio);
        assertEquals(fio, st.getFio());
    }

    @org.junit.Test
    public void getGroup() {
        Student st = new Student(23, "Ярцев Михаил Вадимович");
        Group gr = new Group("Group 15");
        st.setGroup(gr);
        assertEquals(gr, st.getGroup());
    }

    @org.junit.Test
    public void getMarks() {
        Student st = new Student(2, "Ярцев Михаил Вадимович");
        st.addMark(5);
        st.addMark(4);
        st.addMark(4);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(5);
        arr.add(4);
        arr.add(4);
        assertEquals(true, arr.equals(st.getMarks()));
    }

    @org.junit.Test
    public void clearData() {
        Student st = new Student(2, "Ярцев Михаил Вадимович");
        Group gr = new Group("Group 23");
        st.setGroup(gr);
        st.addMark(5);
        st.addMark(4);
        st.clearData();
        assertEquals(-1, st.getId());
        assertEquals(null, st.getGroup());
        assertEquals(null, st.getMarks());
    }

    @org.junit.Test
    public void calcAverMark() {
        Student st = new Student(2, "Ярцев Михаил Вадимович");
        st.addMark(5);
        st.addMark(4);
        st.addMark(4);
        st.addMark(5);
        assertEquals(4.5, st.calcAverMark(),0.00001);
    }
}