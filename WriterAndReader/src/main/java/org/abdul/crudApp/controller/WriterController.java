package org.abdul.crudApp.controller;

import org.abdul.crudApp.model.Label;
import org.abdul.crudApp.model.Post;
import org.abdul.crudApp.model.PostStatus;
import org.abdul.crudApp.model.Writer;
import org.abdul.crudApp.repository.GsonWriterRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterController {
    private final GsonWriterRepositoryImpl gsonWriterRepositoryImpl;
    private final Scanner scanner = new Scanner(System.in);

    public WriterController(GsonWriterRepositoryImpl gsonWriterRepositoryImpl) {
        this.gsonWriterRepositoryImpl = gsonWriterRepositoryImpl;
    }

    public void createObject() {
        System.out.println("Введите имя для firstName:");
        String firstName = scanner.next();
        System.out.println("Введите lastName");
        String lastName = scanner.next();
        System.out.println("Введите число элементов для создания коллекции типа Post");
        int count = scanner.nextInt();
        List<Post> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("Создание обьекта Post номер " + (i + 1));
            System.out.println("Введите id");
            int idPost = scanner.nextInt();
            System.out.println("Введите значение для свойства content:");
            String content = scanner.next();
            System.out.println("Введите значение для свойства created:");
            String created = scanner.next();
            System.out.println("Введите значение для свойства updated:");
            String updated = scanner.next();
            System.out.println("Введите количество создания элементов Label для коллеции:");
            int numbers = scanner.nextInt();
            List<Label> collectLabel = new ArrayList<>();
            for (int j = 0; j < numbers; j++) {
                System.out.println("Создание обьекта Label номер " + (j + 1));
                System.out.println("Введите id");
                int idLabel = scanner.nextInt();
                System.out.println("Введите имя для name:");
                String name = scanner.next();
                Label label = new Label(name);
                label.setId(idLabel);
                label.setState(PostStatus.ACTIVE);
                collectLabel.add(label);
            }
            Post post = new Post(content, created, updated, collectLabel);
            post.setId(idPost);
            post.setState(PostStatus.ACTIVE);
            list.add(post);
        }
        Writer writer = new Writer(firstName, lastName, list);
        gsonWriterRepositoryImpl.save(writer);
        System.out.println();
        System.out.println("Обьект успешно создан");
        System.out.println("ID для созданого обьекта Writer: " + writer.getId());
    }

    public void editObject() {
        System.out.println("Введите id сущности, которую хотите изменить:");
        int id = scanner.nextInt();
        Writer existingWriter = gsonWriterRepositoryImpl.getById(id);
        if (existingWriter != null) {
            boolean end = true;
            while (end) {
                System.out.println(
                        "1.Изменить firstName\n" +
                                "2.Изменить lastName\n" +
                                "3.Изменить коллекцию типа Post\n" +
                                "4.Выйти из режима редактирования\n" +
                                "Выберите пункт который хотите изменить");
                int paragraph = scanner.nextInt();
                switch (paragraph) {
                    case 1: {
                        System.out.println("Введите новое значение для свойства firstName:");
                        String firstName = scanner.next();
                        existingWriter.setFirstName(firstName);
                        break;
                    }
                    case 2: {
                        System.out.println("Введите новое значение для свойства lastName:");
                        String lastName = scanner.next();
                        existingWriter.setLastName(lastName);
                        break;
                    }
                    case 3: {
                        System.out.println("Выберите номер обьекта Post для изменения");
                        for (int i = 0; i < existingWriter.getPosts().size(); i++) {
                            System.out.println(i + 1 + "." + existingWriter.getPosts().get(i).toString());
                        }
                        int choice = scanner.nextInt();
                        Post existingPost = existingWriter.getPosts().get(choice - 1);
                        boolean exitSwitch = true;
                        while (exitSwitch) {
                            System.out.println(
                                    "1.Изменить id обьекта\n" +
                                            "2.Изменить content\n" +
                                            "3.Изменить created\n" +
                                            "4.Изменить updated\n" +
                                            "5.Изменить коллекцию типа Label\n" +
                                            "6.Выйти из режима редактирования\n" +
                                            "Выберите пункт который хотите изменить");
                            int paragraphSwitch = scanner.nextInt();
                            switch (paragraphSwitch) {
                                case 1: {
                                    System.out.println("Введите новое значение для свойства id:");
                                    int newId = scanner.nextInt();
                                    existingWriter.setId(newId);
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
                                        int paragraphSwitchTwo = scanner.nextInt();
                                        switch (paragraphSwitchTwo) {
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
                                                System.out.println("Такой пункт не существуюет" + paragraphSwitchTwo);
                                            }
                                        }
                                    }
                                }
                                case 6: {
                                    System.out.println("Обьект успешно изменен");
                                    existingPost.setState(PostStatus.UNDER_REVIEW);
                                    exitSwitch = false;
                                    break;
                                }
                                default: {
                                    System.out.println("Такой пункт не существуюет" + paragraphSwitch);
                                }
                            }
                        }
                    }
                    case 4: {
                        gsonWriterRepositoryImpl.update(existingWriter);
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
