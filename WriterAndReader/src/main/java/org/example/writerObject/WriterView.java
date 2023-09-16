package org.example.writerObject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WriterView {
    public void writerStart() {
        Scanner scanner = new Scanner(System.in);
        GsonWriterRepository repository = new GsonWriterRepository("writers.json");
        WriterController controller = new WriterController(repository);
        File file = new File("/Users/mustafatumgoev/WriterAndReader/writers.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean end = true;
        while (end) {
            System.out.println("Вы работаете с щусностью Writer");
            System.out.println(
                    "Выберите нужный пункт\n" +
                            "1 создать обьект\n" +
                            "2 Изменить обьект\n" +
                            "3 Удалить обьект\n" +
                            "4.Получить обьект\n" +
                            "5.Выход в меню");
            int paragraph = scanner.nextInt();
            switch (paragraph) {
                case 1: {
                    controller.createObject();
                    break;
                }
                case 2: {
                    controller.editObject();
                    break;
                }
                case 3: {
                    System.out.println("Введите id обьекта");
                    int id = scanner.nextInt();
                    repository.delete(id);
                    System.out.println("Обьект успешно удален");
                    break;
                }
                case 4: {
                    System.out.println("Введите id обьекта который хотите получить");
                    int id = scanner.nextInt();
                    Writer writerJsonObject = repository.findGsonObject(id);
                    System.out.println(writerJsonObject.toString());
                    repository.save(writerJsonObject);
                    break;
                }
                case 5: {
                    end = false;
                    break;
                }
                default: {
                    System.out.println("Такого пункта не существует!" + paragraph);
                }
            }
        }

    }
}
