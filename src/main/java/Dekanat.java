import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.annotation.Resources;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Dekanat {
    private ArrayList<Group> groups;
    private ArrayList<Student> students;
    private Random random;

    Dekanat(String fileName) {
        Gson gson = gsonObj();
        random = new Random();
        groups = new ArrayList<Group>();
        students = new ArrayList<Student>();

        String absPath = System.class.getResource("/" + fileName).getFile();
        Type arrType = new TypeToken<ArrayList<Group>>(){}.getType();
        File file = null;
        FileReader fileReader = null;
        BufferedReader reader = null;
        try {
            file = new File(absPath);
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            groups = gson.fromJson(reader, arrType);
            for (Group gr : groups) {
                gr.setThisGroup();
                students.addAll(gr.getStudents());
                gr.assignHead();
            }
            if (!groups.isEmpty() && file.canRead() && (file.length() > 0L)) {
                System.out.println("Данные групп загружены из файла.");
            }
            else {
                System.out.println("Данные групп загружены НЕ корректно.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Данные групп НЕ загружены.");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Данные групп НЕ загружены.");
        }
    }

    public boolean saveUpdData(String fileName) {
        Gson gson = gsonObj();
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        Type arrType = new TypeToken<ArrayList<Group>>(){}.getType();
        File file = null;
        boolean res = false;
        try {
            file = new File("target\\classes\\" + fileName);
            if (file.exists()) {
                boolean r1 = file.delete();
                boolean r2 = file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            gson.toJson(groups,arrType,writer);
            writer.flush();
            writer.close();
            if ((file.length() > 0L) && file.canWrite()) {
                System.out.println("Данные групп выгружены в файл.");
                res = true;
            }
            else {
                System.out.println("Данные групп выгружены НЕ корректно.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Данные групп НЕ выгружены.");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Данные групп НЕ выгружены.");
        }
        return res;
    }

    public int getStNum() {return students.size();}

    private Gson gsonObj() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.setPrettyPrinting();
        return builder.create();
    }

    public double addMarks() {
        double aver = 0.0d;
        for (Student st : students) {
            int flag = random.nextInt(3) + 1; // 1, 2, 3
            for (int i = 0; i < 20; i++) {
                st.addMark(randMark(flag));
            }
        }
        for (Student st : students) {
            aver += st.calcAverMark();
        }
        aver /= students.size();
        return aver;
    }

    private int randMark(int flag) {
        int mark = 0;
        switch (flag) {
            case 1:
                mark = random.nextInt(3) + 2; // 2, 3, 4
                break;
            case 2:
                mark = random.nextInt(3) + 3; // 3, 4, 5
                break;
            case 3:
                mark = random.nextInt(2) + 4; // 4, 5
                break;
            default:
                mark = 2;
                break;
        }
        return mark;
    }

    public int removeBadStudents() {
        int count = 0;
        Iterator<Student> iter = null;
        for (iter = students.iterator(); iter.hasNext(); ) {
            Student st = iter.next();
            if (st.calcAverMark() < 3.0d) {
                count++;
                iter.remove();
                st.getGroup().removeStudent(st.getId());
                st.clearData();
            }
        }
        return count;
    }

    public boolean relocStudent(int id, String newGroupTitle) {
        Student st = null;
        boolean res1 = false, res2 = false;
        for (Group gr : groups) {
            st = gr.searchStudent(id);
            if (st != null) {
                gr.removeStudent(id);
                res1 = true;
                break;
            }
        }
        for (Group gr : groups) {
            if (gr.getTitle().equals(newGroupTitle) && (st != null)) {
                gr.addStudent(st);
                res2 = true;
                break;
            }
        }
        return res1 && res2;
    }

    public int getRandId() {
        int[] idArr = new int[students.size()];
        for (int i = 0; i < students.size(); i++) {
            idArr[i] = students.get(i).getId();
        }
        int idx = random.nextInt(students.size());
        return idArr[idx];
    }

    public String getAnotherGroup(int id) {
        Group[] groupArr = new Group[groups.size()-1];
        int i = 0;
        for (Group gr : groups) {
            if (gr.searchStudent(id) == null) {
                groupArr[i] = gr;
                i++;
            }
        }
        int idx = random.nextInt(groupArr.length);
        return groupArr[idx].getTitle();
    }

    public void printResults() {
        int i = 0;
        String str1 = ". ", str2 = ", ";
        System.out.println("Результаты для групп (" + groups.size() + "): ");
        for (Group gr : groups) {
            i++;
            System.out.print(i + str1 + gr.getTitle() + str2);
            System.out.printf("%.2f\n",gr.calcAverMark());
        }
        System.out.println("Результаты для студентов (" + students.size() + "): ");
        for (Student st : students) {
            System.out.print(st.getId() + str1 + st.getFio() + str2);
            System.out.printf("%.2f\n",st.calcAverMark());
        }
    }
}
