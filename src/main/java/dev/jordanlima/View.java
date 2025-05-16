package dev.jordanlima;


import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

public class View{
    Scanner sc = new Scanner(System.in);
    public void start() {
        System.out.println("----------------- Bem-vindo ao organizador de arquivos -----------------");
        System.out.print("Digite o nome da pasta que deseja organizar (ex: Downloads): ");

        String inputFolder = sc.nextLine();

        SeparatorFile separator = new SeparatorFile();
        Map<StatusOrganizer, List<File>> filesToMove = separator.listFiles(inputFolder);

        if (filesToMove.isEmpty()) {
            System.out.println("Nenhum arquivo foi encontrado ou a pasta é inválida.");
            return;
        }

        MoveFile mover = new MoveFile();
        mover.moveFile(filesToMove);

        System.out.println("Organização concluída com sucesso!");
    }
}
