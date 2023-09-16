package org.example.labelObject;

import org.example.PostStatus;

import java.util.Scanner;

public class LabelController {
    protected GsonLabelRepository gsonLabelRepository;
    protected Scanner scanner;

    public LabelController(GsonLabelRepository gsonLabelRepository) {
        this.gsonLabelRepository = gsonLabelRepository;
        this.scanner = new Scanner(System.in);
    }

    public void createObject() {
        System.out.println("Введите id");
        int id = scanner.nextInt();
        System.out.println("Введите имя для name:");
        String name = scanner.next();
        Label label = new Label(id, name);
        label.setState(PostStatus.ACTIVE);
        gsonLabelRepository.save(label);
        System.out.println("Обьект успешно создан");
    }

    public void editObject() {
        System.out.println("Введите id сущности, которую хотите изменить:");
        int id = scanner.nextInt();
        Label existingLabel = gsonLabelRepository.findGsonObject(id);
        if (existingLabel != null) {
            boolean end = true;
            while (end) {
                System.out.println("Вы в режиме редактирования сущности типа Label");
                System.out.println(
                        "1.Изменить id обьекта\n" +
                                "2.Изменить firstName\n" +
                                "3.Выйти из режима редактирования\n" +
                                "Выберите пункт который хотите изменить");
                int paragraph = scanner.nextInt();
                switch (paragraph) {
                    case 1: {
                        System.out.println("Введите новое значение для свойства id:");
                        int newId = scanner.nextInt();
                        existingLabel.setId(newId);
                        break;
                    }
                    case 2: {
                        System.out.println("Введите новое значение для свойства name:");
                        String name = scanner.next();
                        existingLabel.setName(name);
                        break;
                    }
                    case 3: {
                        existingLabel.setState(PostStatus.UNDER_REVIEW);
                        gsonLabelRepository.save(existingLabel);
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
