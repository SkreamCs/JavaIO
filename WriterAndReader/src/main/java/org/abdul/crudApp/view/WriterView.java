package org.abdul.crudApp.view;

import org.abdul.crudApp.controller.WriterController;
import org.abdul.crudApp.model.Writer;
import org.abdul.crudApp.repository.GsonWriterRepositoryImpl;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WriterView {
    public void writerStart() {
        Scanner scanner = new Scanner(System.in);
        GsonWriterRepositoryImpl repository = new GsonWriterRepositoryImpl();
        WriterController controller = new WriterController(repository);
        File file = new File("/Users/mustafatumgoev/WriterAndReader/src/main/resources/writers.json");
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
                    repository.deleteById(id);
                    System.out.println("Обьект успешно удален");
                    break;
                }
                case 4: {
                    System.out.println("Введите id обьекта который хотите получить");
                    int id = scanner.nextInt();
                    Writer writerJsonObject = repository.getById(id);
                    System.out.println(writerJsonObject.toString());
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
