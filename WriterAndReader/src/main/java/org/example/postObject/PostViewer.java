package org.example.postObject;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PostViewer {
    public void postStart() {
        Scanner scanner = new Scanner(System.in);
        GsonPostRepository repository = new GsonPostRepository("posts.json");
        PostController controller = new PostController(repository);
        File file = new File("/Users/mustafatumgoev/WriterAndReader/posts.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean end = true;
        while (end) {
            System.out.println("Вы работаете с сущностью Post");
            System.out.println("Выберите нужный пункт для сущности Post\n" +
                    "1 создать обьект\n" +
                    "2 Изменить обьект\n" +
                    "3.Получить обьект\n" +
                    "4.Удалить обьект\n" +
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
                    System.out.println("Введите id обьекта который хотите получить");
                    int id = scanner.nextInt();
                    Post postJsonObject = repository.findGsonObject(id);
                    System.out.println(postJsonObject.toString());
                    repository.save(postJsonObject);
                    break;
                }
                case 4: {
                    System.out.println("Введите id обьекта");
                    int id = scanner.nextInt();
                    repository.delete(id);
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
