import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class StudentTest {
Student student=new Student("Molkov",0001,new Group("Test1"));
    String message;
    URL file;
    String path;
    File fileXLSX ;
    File fileJSON ;
    Group Group;
    Dekanat Dekanat;
    @Before
    public void setup() {
        try {
            file=System.class.getResource("/dekanat.xlsx");
            this.fileXLSX = new File(file.getFile());

            file=System.class.getResource("/dekanat.json");
            this.fileJSON = new File(file.getFile());
        }catch (NullPointerException e1){
            System.out.println("Not found file: "+file.getPath()+e1);
            return;
        }
    }
    @Test
    public void addMark() throws Exception {
        String message=null;
        int[] actual = new int[3];
        try {
        for (int i = 0; i < 3; i++) {
            actual[i]=1;

            }
            student.addMark(1);
        } catch (Student.AccessDeniedException e) {
            message=e.getMessage();
            Dekanat Dekanat=new Dekanat(fileJSON);
            Dekanat.setMarkToStudent(student,actual);
        }

        Assert.assertEquals("Access denied",message);

         int[]expected={1,1,1};
       Assert.assertArrayEquals(expected,actual);
    }

}