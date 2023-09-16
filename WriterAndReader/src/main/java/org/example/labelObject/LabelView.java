package org.example.labelObject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LabelView {
    public void labelStart() {
        Scanner scannerLabel = new Scanner(System.in);
        File file = new File("/Users/mustafatumgoev/WriterAndReader/labels.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        GsonLabelRepository repository = new GsonLabelRepository("labels.json");
        LabelController controller = new LabelController(repository);
        boolean end = true;
        while (end) {
            System.out.println("Вы работаете с сущностью Label");
            System.out.println("Выберите нужный пункт\n" +
                    "1 создать обьект\n" +
                    "2 Изменить обьект\n" +
                    "3 Удалить обьект\n" +
                    "4.Получить обьект\n" +
                    "5.Выход в меню");
            int paragrafh = scannerLabel.nextInt();
            switch (paragrafh) {
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
                    int id = scannerLabel.nextInt();
                    repository.delete(id);
                    System.out.println("Обьект успешно удален");
                    break;
                }
                case 4: {
                    System.out.println("Введите id обьекта который хотите получить");
                    int id = scannerLabel.nextInt();
                    Label labelJsonObject = repository.findGsonObject(id);
                    System.out.println(labelJsonObject.toString());
                    repository.save(labelJsonObject);
                    break;
                }
                case 5: {
                    end = false;
                    break;
                }
                default: {
                    System.out.println("Такого пункта не существует!" + paragrafh);
                }
            }
        }

    }
}
