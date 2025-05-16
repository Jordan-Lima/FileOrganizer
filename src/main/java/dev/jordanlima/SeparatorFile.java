package dev.jordanlima;

import java.io.File;
import java.util.*;

public class SeparatorFile {
    private final String userHome = System.getProperty("user.home");
    File fr = new File(userHome);
    private final List<String> listDir = Arrays.asList(Objects.requireNonNull(fr.list()));

    private final List<String> typeDocument = Arrays.asList(".doc", ".docx", ".pdf", ".txt", ".odt", ".rtf",
            ".xls", ".xlsx", ".csv", ".ppt", ".pptx", ".epub");

    private final List<String> typeVideo = Arrays.asList(".mp4", ".mkv", ".avi", ".mov", ".wmv", ".flv", ".webm",
            ".mpeg", ".mpg", ".3gp", ".m4v");

    private final List<String> typePicture = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp", ".tiff",
            ".svg", ".heic", ".ico");

    private final List<String> typeAudio = Arrays.asList(".mp3", ".wav", ".ogg", ".m4a", ".flac", ".aac", ".wma", ".aiff", ".alac");

    private final List<String> typeCompressed = Arrays.asList(".zip", ".rar", ".7z", ".tar", ".gz", ".bz2", ".xz", ".tar.gz", ".tar.bz2");

    private final List<String> typeExecutable = Arrays.asList(".exe", ".msi", ".bat", ".sh", ".apk", ".bin", ".cmd", ".jar", ".run", ".appimage", ".deb");

    private final List<String> typeOthers = Arrays.asList(".iso", ".db", ".log", ".tmp", ".bak", ".cfg", ".ini", ".json", ".xml", ".yml", ".yaml");

    private final Map<StatusOrganizer, List<String>> extensionsByType = Map.of(
            StatusOrganizer.DOCUMENTS, typeDocument,
            StatusOrganizer.VIDEOS, typeVideo,
            StatusOrganizer.MUSIC, typeAudio,
            StatusOrganizer.PICTURES, typePicture,
            StatusOrganizer.COMPRESSED, typeCompressed,
            StatusOrganizer.EXECUTABLE, typeExecutable,
            StatusOrganizer.OTHERS, typeOthers
    );

    private boolean pathValidator(String originDir) {
        if (originDir.isBlank()) {
            System.out.println("Não foi adicionado nada...");
            return false;
        }

        if (listDir.isEmpty()) {
            System.out.println("Não há arquivos e/ou pastas no local");
            return false;
        }

        if (!listDir.contains(originDir)) {
            System.out.println("A pasta não foi encontrada em: " + userHome);
            return false;
        }

        File dirActual = new File(fr, originDir);

        if (!dirActual.isDirectory()) {
            System.out.println("Não foi encontrado nenhuma pasta");
            return false;
        }

        return true;
    }

    private Map<StatusOrganizer, List<File>> filterFilesByExtensions(File directory) {
        File[] allFiles = directory.listFiles();
        if (allFiles == null) return Collections.emptyMap();

        Map<StatusOrganizer, List<File>> categorizedFiles = new EnumMap<>(StatusOrganizer.class);

        // Inicializa todas as categorias
        for (StatusOrganizer type : StatusOrganizer.values()) {
            categorizedFiles.put(type, new ArrayList<>());
        }

        for (File file : allFiles) {
            if (!file.isFile()) continue;

            String name = file.getName().toLowerCase();
            boolean categorized = false;

            for (Map.Entry<StatusOrganizer, List<String>> entry : extensionsByType.entrySet()) {
                for (String ext : entry.getValue()) {
                    if (name.endsWith(ext)) {
                        categorizedFiles.get(entry.getKey()).add(file);
                        categorized = true;
                        break;
                    }
                }
                if (categorized) break;
            }

            if (!categorized) {
                categorizedFiles.get(StatusOrganizer.OTHERS).add(file);
            }
        }

        return categorizedFiles;
    }

    public Map<StatusOrganizer, List<File>> listFiles(String inputDir) {
        if (!pathValidator(inputDir)) return null;

        File pathToOrganizer = new File(fr, inputDir);

        Map<StatusOrganizer, List<File>> categorized = filterFilesByExtensions(pathToOrganizer);

        for (Map.Entry<StatusOrganizer, List<File>> entry : categorized.entrySet()) {
            System.out.println("Arquivos do tipo " + entry.getKey() + ":");
            for (File file : entry.getValue()) {
                System.out.println(" - " + file.getName());
            }
        }
        return categorized;
    }
}
