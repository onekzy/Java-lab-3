
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.print.Book;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class DekanatDemo {
    static Dekanat dekanat;

    public static void main(String[] args) throws IOException, URISyntaxException {
        dekanat = new Dekanat();

        dekanat.refreshStudentsAndGroups(dekanat.jsonList); //обработка данных из файла JSon

        dekanat.printGroup(); //вывод групп
        dekanat.printStudents(); //вывод студентов
        dekanat.printStudentsInGroup("ТС"); //вывод студентов из группы "ТС"

        new Group().addStudent(new Student("31", "Рамаев А.Д.", dekanat.groups.get(1))); //добавление студента в группу "АЭ" (в json + список студентов)

        dekanat.printStudents(); //вывод студентов (новый студент добавлен)
        dekanat.printStudentsInGroup(dekanat.groups.get(1).title); //вывод студентов из группы "АЭ" (новый студент добавлен)

        //ИНДЕКС СТУДЕНТА В МАССИВЕ = "ID" - 1

        System.out.println(dekanat.students.get(30).Fio + " " + dekanat.students.get(30).marks); //вывод оценок студента (массив пуст)
        dekanat.addRandomMarks(dekanat.students.get(30)); //добавление случайной оценки
        dekanat.addRandomMarks(dekanat.students.get(30)); //добавление случайной оценки
        dekanat.addRandomMarks(dekanat.students.get(30)); //добавление случайной оценки
        dekanat.addRandomMarks(dekanat.students.get(30)); //добавление случайной оценки
        System.out.println(dekanat.students.get(30).Fio + " " + dekanat.students.get(30).marks); //вывод оценок студента после добавления новых

        System.out.println(new Group().searchStudent("20", "Мирнов В.В.")); //проверка есть ли студент в списках (true)

        dekanat.students.get(30).addMark(1); //добавление низкой оценки для исключения
        dekanat.students.get(30).addMark(1); //добавление низкой оценки для исключения
        dekanat.students.get(30).addMark(1); //добавление низкой оценки для исключения
        dekanat.students.get(30).addMark(1); //добавление низкой оценки для исключения
        System.out.println(dekanat.students.get(30).Fio + " " + dekanat.students.get(30).marks); //оценки добавлены

        System.out.println(dekanat.students.get(30).middleMark()); //вывод средней оценки студента

        dekanat.addStatisticStu(dekanat.students.get(30)); // сбор статистики по средней оценки студента
        dekanat.printStatStudents(); //вывод статистики

        dekanat.addStatisticGroups(dekanat.groups.get(0)); // сбор статистики по средней оценки группы
        dekanat.printStatGroups(); //вывод статистики

        dekanat.deleteBadStudent(dekanat.students.get(30)); //студент удален из-за низкого балла (только из json'a)
        dekanat.refreshStudentsAndGroups(dekanat.jsonList); //обновление данных из файла JSon после исключения студента
        dekanat.printStudents(); //отставшего студента нет

        dekanat.wishHead(); //инициация выбора старосты
        dekanat.groups.get(0).choiceHead(dekanat.students.get(25)); //выбор старосты для "ТС"
        System.out.println(dekanat.groups.get(0).head.Fio+" "+dekanat.groups.get(0).head.group.title);
        dekanat.groups.get(1).choiceHead(dekanat.students.get(10)); //выбор старосты для "АЭ"
        System.out.println(dekanat.groups.get(1).head.Fio+" "+dekanat.groups.get(1).head.group.title);

        System.out.println(dekanat.students.get(11).Fio+" "+dekanat.students.get(11).group.title);
        dekanat.switchGroup(dekanat.students.get(11), dekanat.groups.get(0)); //сменили группу с АЭ на ЯР
        System.out.println(dekanat.students.get(11).Fio+" "+dekanat.students.get(11).group.title);







    }
}
