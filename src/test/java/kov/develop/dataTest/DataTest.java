package kov.develop.dataTest;

import kov.develop.model.Depart;
import kov.develop.model.EmployerForUi;
import kov.develop.model.Meeting;
import kov.develop.model.MeetingForUi;

import java.time.LocalDate;
import java.time.LocalDateTime;;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DataTest {

    private static Depart[] DEPARTS = {new Depart(1, "Бухгалтерия"), new Depart(2, "Производство"), new Depart(3, "Бездельники"), new Depart(4, "Руководство")};


    public static final MeetingForUi[] MEETING_FOR_UIS = {new MeetingForUi(1, LocalDateTime.parse("2017-10-28T13:00:00"), "Увеличение продаж", "Руководство", "Ибрагимович Златан Иванович", 4),
            new MeetingForUi(2, LocalDateTime.parse("2017-10-29T15:00:00"), "Уменьшение зарплат", "Руководство", "Коваленко Андрей Александрович", 2),
            new MeetingForUi(3, LocalDateTime.parse("2017-10-15T10:00:00"), "Ситуация в мире", "Бухгалтерия", "Иванова Гадя Александровна", 2),
            new MeetingForUi(4, LocalDateTime.parse("2017-01-11T13:00:00"), "Празднование Нового года", "Бездельники", "Коваленко Юлия Владимировна", 3),
            new MeetingForUi(5, LocalDateTime.parse("2017-05-12T13:00:00"), "Что делать?", "Производство", "Ефимов Артем Петрович", 4),
            new MeetingForUi(6, LocalDateTime.parse("2017-07-05T13:00:00"), "Как делать план?", "Производство", "Кучеров Никита Андреевич", 3)};

    public static final EmployerForUi[] EMPLOYER_FOR_UIS = {new EmployerForUi(1, "Коваленко Андрей Александрович", LocalDate.parse("1977-03-17"), "Руководство"),
            new EmployerForUi(2, "Коваленко Юлия Владимировна", LocalDate.parse("1980-08-24"), "Бездельники"),
            new EmployerForUi(3, "Ибрагимович Златан Иванович", LocalDate.parse("1974-07-12"), "Руководство"),
            new EmployerForUi(4, "Кучеров Никита Андреевич", LocalDate.parse("1992-09-07"), "Производство"),
            new EmployerForUi(5, "Петров Иван Иванович", LocalDate.parse("1979-01-01"), "Бухгалтерия"),
            new EmployerForUi(6, "Иванова Гадя Александровна", LocalDate.parse("2000-04-23"), "Бухгалтерия"),
            new EmployerForUi(7, "Михайлов Антон Антонович", LocalDate.parse("1998-06-28"), "Бухгалтерия"),
            new EmployerForUi(8, "Ефимов Артем Петрович", LocalDate.parse("1995-07-30"), "Производство"),
    };

    private static Integer[] a = {2, 4, 8};
    public static final Meeting SAVED_MEETING = new Meeting(1, "Огромное увеличение продаж", LocalDateTime.parse("2017-10-28T13:00:00"), 2, new HashSet<Integer>(Arrays.asList(a)));

/*
    INSERT INTO members (meet_id, employer_id) VALUES
  (1, 5),
          (1, 2),
          (1, 8),
          (1, 4),
          (2, 5),
          (2, 6),
          (3, 7),
          (3, 8),
          (4, 1),
          (4, 5),
          (4, 3),
          (5, 4),
          (5, 5),
          (5, 6),
          (5, 7),
          (6, 1),
          (6, 3),
          (6, 2);

}*/
}