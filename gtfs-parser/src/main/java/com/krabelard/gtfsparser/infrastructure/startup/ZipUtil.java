package com.krabelard.gtfsparser.infrastructure.startup;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipInputStream;

@Slf4j
public class ZipUtil {
    private static final int MiB = 1_048_576;

    /**
     * Unzips .zip archive into the directory it's in
     * @param zipFile Name of the .zip archive from project's resources directory
     * @throws FileNotFoundException If specified .zip archive not found
     * @throws IOException Rethrown from {@link FileInputStream} if it throws
     */
    public static void unzip(String zipFilePath, String destDirPath) throws IOException {
        var classLoader = ZipUtil.class.getClassLoader();
        var destDir = new File(destDirPath);
        if (!destDir.exists()) {
            throw new FileNotFoundException(String.format("%s cannot be found", destDir.toString()));
        }

        var buffer = new byte[100 * MiB];
        try (
                var fileInputStream = new FileInputStream(zipFilePath);
                var zipInputStream = new ZipInputStream(fileInputStream)
        ) {
            var zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                var fileName = zipEntry.getName();
                var newFile = new File(String.format("%s%s%s", destDir, File.separator, fileName));
                log.info("Unzipping {}", newFile.getAbsoluteFile());
                try (var fileOutputStream = new FileOutputStream(newFile)) {
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                }
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
        }
    }

    /**
     * Deletes all files with chosen extension in chosen directory. Used to clean up unzipped files after processing them.
     * @param destDirPath Directory to clean
     * @param extension File extension to look for
     * @throws IOException Rethrown from underlying {@link SimpleFileVisitor}
     */
    public static void cleanUp(String destDirPath, String extension) throws IOException {
        Files.walkFileTree(Path.of(destDirPath), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.getFileName().toString().endsWith(extension)) {
                    Files.delete(file);
                    log.info("Deleted file: {}", file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
