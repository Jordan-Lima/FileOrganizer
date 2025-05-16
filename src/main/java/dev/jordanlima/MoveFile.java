package dev.jordanlima;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

public class MoveFile {

    private final String userHome = System.getProperty("user.home");
    private final String baseDestDir = userHome + File.separator;

    public static String capitalize(String input) {
        if (input == null || input.isBlank()) return input;

        input = input.toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public void moveFile(Map<StatusOrganizer, List<File>> archivesToMove) {
        if (archivesToMove.isEmpty()) {
            System.out.println("Nenhum arquivo encontrado para mover.");
            return;
        }

        for (Map.Entry<StatusOrganizer, List<File>> entry : archivesToMove.entrySet()) {
            StatusOrganizer type = entry.getKey();
            List<File> files = entry.getValue();

            if (files.isEmpty()) continue;

            Path destinationDir = Paths.get(baseDestDir, capitalize(type.name()));

            try {
                Files.createDirectories(destinationDir); // Cria o diretório se não existir
            } catch (IOException e) {
                System.out.println("Erro ao criar diretório para: " + capitalize(type.name()));
                e.printStackTrace();
                continue;
            }

            for (File file : files) {
                Path sourcePath = file.toPath();
                Path targetPath = destinationDir.resolve(file.getName());

                try {
                    Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Movido: " + file.getName() + " → " + destinationDir);
                } catch (IOException e) {
                    System.out.println("Erro ao mover o arquivo: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }
}
