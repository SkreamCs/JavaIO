package org.abdul.crudApp.controller;

import org.abdul.crudApp.model.Label;
import org.abdul.crudApp.repository.GsonLabelRepositoryImpl;

import java.util.Scanner;

public class LabelController {
    private final GsonLabelRepositoryImpl gsonLabelRepositoryImpl;
    private final Scanner scanner = new Scanner(System.in);

    public LabelController(GsonLabelRepositoryImpl gsonLabelRepositoryImpl) {
        this.gsonLabelRepositoryImpl = gsonLabelRepositoryImpl;
    }

    public void createObject() {
        System.out.println("Введите имя для name:");
        String name = scanner.next();
        Label label = new Label(name);
        gsonLabelRepositoryImpl.save(label);
        System.out.println("Обьект успешно создан");
        System.out.println("ID для созданого обьекта Label: " + label.getId());
    }

    public void editObject() {
        System.out.println("Введите id сущности, которую хотите изменить:");
        int id = scanner.nextInt();
        Label existingLabel = gsonLabelRepositoryImpl.getById(id);
        if (existingLabel != null) {
            boolean end = true;
            while (end) {
                System.out.println("Вы в режиме редактирования сущности типа Label");
                System.out.println(
                        "1.Изменить firstName\n" +
                                "2.Выйти из режима редактирования\n" +
                                "Выберите пункт который хотите изменить");
                int paragraph = scanner.nextInt();
                switch (paragraph) {
                    case 1: {
                        System.out.println("Введите новое значение для свойства name:");
                        String name = scanner.next();
                        existingLabel.setName(name);
                        break;
                    }
                    case 2: {
                        gsonLabelRepositoryImpl.update(existingLabel);
                        System.out.println("Обьект изменен");
                        end = false;
                        break;
                    }
                    default: {
                        System.out.println("Такой пункт не существуюет" + paragraph);
                    }
                }
            }

        } else {
            System.out.println("Сущность с id " + id + " не найдена.");
        }
    }
}
