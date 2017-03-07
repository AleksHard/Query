package net.hive.controller;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Created by kharlashkin on 03.03.2017.
 *
 */
class FileWorker {
    String readUsingFiles(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        //читаем построчно
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.equals("jdbc:firebirdsql:192.168.99.239/3050:")){
                System.out.println(line);
            }
            return line;
        }

        return fileName;
    }
}
