package org.abdul.crudApp.view;

import java.util.Scanner;

public class GeneralView {
    public void startRun() {
        Scanner scanner = new Scanner(System.in);
        LabelView labelView = new LabelView();
        PostViewer postViewer = new PostViewer();
        WriterView writerView = new WriterView();

        System.out.println("Консольное CRUD приложение");
        while (true) {
            System.out.println("Меню");
            System.out.println(
                    "1.Работать с сущностью Writer\n" +
                            "2.Работать с сущностью Post\n" +
                            "3.Работать с сущностью Label\n" +
                            "4.Закрыть программу\n" +
                            "Выберите один из пунктов");
            int paragraphGlobal = scanner.nextInt();
            switch (paragraphGlobal) {
                case 1: {
                    writerView.writerStart();
                    break;
                }
                case 2: {
                    postViewer.postStart();
                    break;
                }
                case 3: {
                    labelView.labelStart();
                    break;
                }
                case 4: {
                    System.out.println("Завершение работы");
                    return;
                }
                default: {
                    System.out.println("Данный пункт не сущестует" + paragraphGlobal);
                }
            }
        }
    }
}
