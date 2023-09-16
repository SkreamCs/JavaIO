package org.example.postObject;

import org.example.PostStatus;
import org.example.labelObject.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostController {
    protected GsonPostRepository gsonPostRepository;
    protected Scanner scanner;

    public PostController(GsonPostRepository gsonPostRepository) {
        this.gsonPostRepository = gsonPostRepository;
        this.scanner = new Scanner(System.in);
    }

    public void createObject() {
        System.out.println("Введите id");
        int id = scanner.nextInt();
        System.out.println("Введите значение для свойства content:");
        String content = scanner.next();
        System.out.println("Введите значение для свойства created:");
        String created = scanner.next();
        System.out.println("Введите значение для свойства updated:");
        String updated = scanner.next();
        System.out.println("Введите количество создания элементов для коллеции типа Label:");
        int count = scanner.nextInt();
        List<Label> collectLabel = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("Создание обьекта номер" + (i + 1));
            System.out.println("Введите id");
            int idLabel = scanner.nextInt();
            System.out.println("Введите имя для name:");
            String name = scanner.next();
            Label label = new Label(idLabel, name);
            label.setState(PostStatus.ACTIVE);
            collectLabel.add(label);
        }
        Post post = new Post(id, content, created, updated, collectLabel);
        gsonPostRepository.save(post);
        System.out.println("Обьект успешно создан");
    }

    public void editObject() {
        System.out.println("Введите id сущности, которую хотите изменить:");
        int id = scanner.nextInt();
        Post existingPost = gsonPostRepository.findGsonObject(id);
        if (existingPost != null) {
            boolean end = true;
            while (end) {
                System.out.println(
                        "1.Изменить id обьекта\n" +
                                "2.Изменить content\n" +
                                "3.Изменить created\n" +
                                "4.Изменить updated\n" +
                                "5.Изменить коллекцию типа Label\n" +
                                "6.Выйти из режима редактирования сущности Post\n" +
                                "Выберите пункт который хотите изменить");
                int paragraph = scanner.nextInt();
                switch (paragraph) {
                    case 1: {
                        System.out.println("Введите новое значение для свойства id:");
                        int newId = scanner.nextInt();
                        existingPost.setId(newId);
                        break;
                    }
                    case 2: {
                        System.out.println("Введите новое значение для свойства content:");
                        String content = scanner.next();
                        existingPost.setContent(content);
                        break;
                    }
                    case 3: {
                        System.out.println("Введите новое значение для свойства created:");
                        String created = scanner.next();
                        existingPost.setCreated(created);
                        break;
                    }
                    case 4: {
                        System.out.println("Введите новое значение для updated:");
                        String updated = scanner.next();
                        existingPost.setUpdated(updated);
                        break;
                    }
                    case 5: {
                        System.out.println("Выберите номер обьекта который хотите изменить:");
                        for (int i = 0; i < existingPost.getLabels().size(); i++) {
                            System.out.println(i + 1 + "." + existingPost.getLabels().get(i).toString());
                        }
                        int number = scanner.nextInt();
                        Label label = existingPost.getLabels().get(number - 1);
                        boolean exit = true;
                        while (exit) {
                            System.out.println("Вы в режиме редактирования коллеции типа Label");
                            System.out.println(
                                    "1.Изменить id обьекта\n" +
                                            "2.Изменить firstName\n" +
                                            "3.Выйти из режима редактирования\n" +
                                            "Выберите пункт который хотите изменить");
                            int paragraphTwo = scanner.nextInt();
                            switch (paragraphTwo) {
                                case 1: {
                                    System.out.println("Введите новое значение для свойства id:");
                                    int newId = scanner.nextInt();
                                    label.setId(newId);
                                    break;
                                }
                                case 2: {
                                    System.out.println("Введите новое значение для свойства name:");
                                    String name = scanner.next();
                                    label.setName(name);
                                    break;
                                }
                                case 3: {
                                    label.setState(PostStatus.UNDER_REVIEW);
                                    existingPost.getLabels().set(number - 1, label);
                                    System.out.println("Обьект изменен");
                                    exit = false;
                                    break;
                                }
                                default: {
                                    System.out.println("Такой пункт не существуюет" + paragraphTwo);
                                }
                            }
                        }
                    }
                    case 6: {
                        gsonPostRepository.save(existingPost);
                        existingPost.setState(PostStatus.UNDER_REVIEW);
                        end = false;
                        break;
                    }
                    default: {
                        System.out.println("Такой пункт не существует" + paragraph);
                    }
                }
            }

        } else {
            System.out.println("Сущность с id " + id + " не найдена.");
        }
    }
}
