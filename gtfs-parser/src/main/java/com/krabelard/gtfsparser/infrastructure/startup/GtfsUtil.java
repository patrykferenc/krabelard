package com.krabelard.gtfsparser.infrastructure.startup;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.zip.ZipInputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class GtfsUtil {

	private static final int MiB = 1_048_576;

	private final ResourceLoader resourceLoader;

	/**
	 * Unzips .zip archive into the directory it's in
	 * @param zipFile Name of the .zip archive from project's resources directory
	 * @throws FileNotFoundException If specified .zip archive not found
	 * @throws IOException Rethrown from {@link FileInputStream} if it throws
	 */
	public void unzip(String zipFile) throws IOException {
		var zipResource = resourceLoader.getResource(
			String.format("classpath:%s", zipFile)
		);
		try (
			var inputStream = zipResource.getInputStream();
			var zipInputStream = new ZipInputStream(inputStream)
		) {
			var destDir = zipResource.getFile().getParentFile();
			log.info("Detected zip resource: {}", zipResource.getFilename());
			log.info("Detected resource dir: {}", destDir);
			if (!destDir.exists()) {
				throw new FileNotFoundException(
					String.format("%s cannot be found", destDir)
				);
			}
			// warsaw.zip has ~62MiB so little overhead just in case
			var buffer = new byte[65 * MiB];

			var zipEntry = zipInputStream.getNextEntry();
			while (zipEntry != null) {
				var fileName = zipEntry.getName();
				var newFile = new File(
					String.format("%s%s%s", destDir, File.separator, fileName)
				);
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
	 * Parses CSV file
	 * @param fileName CSV file to parse
	 * @return List of parsed records
	 * @throws IOException Rethrown from {@link InputStream} reading
	 */
	public List<CSVRecord> parseCsv(String fileName) throws IOException {
		var csvResource = resourceLoader.getResource(
			String.format("classpath:%s", fileName)
		);
		try (
			var inputStream = csvResource.getInputStream();
			var inputStreamReader = new InputStreamReader(
				inputStream,
				StandardCharsets.UTF_8
			);
			var bufferedReader = new BufferedReader(inputStreamReader);
			var csvParser = new CSVParser(
				bufferedReader,
				CSVFormat.EXCEL.withHeader()
			)
		) {
			return csvParser.getRecords();
		}
	}

	/**
	 * Deletes all files with chosen extension in chosen directory. Used to clean up unzipped files after processing them.
	 * @param destDirPath Directory to clean
	 * @param extension File extension to look for
	 * @throws IOException Rethrown from underlying {@link SimpleFileVisitor}
	 */
	public void cleanUp(String destDirPath, String extension) throws IOException {
		Files.walkFileTree(
			Path.of(destDirPath),
			new SimpleFileVisitor<>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
					throws IOException {
					if (file.getFileName().toString().endsWith(extension)) {
						Files.delete(file);
						log.info("Deleted file: {}", file);
					}
					return FileVisitResult.CONTINUE;
				}
			}
		);
	}

	/**
	 * Parses format from gtfs time format which can go beyond 24-hour format, for trips
	 * where the transit started the day before.
	 * @param gtfsTime String representing time in 'hh:mm:ss' format where hh can be greater than 24
	 * @return String in 'hh:mm:ss' format but hh will comply with 24-hour format.
	 */
	public String toIsoLocalTime(String gtfsTime) {
		var arrivalHour = Integer.parseInt(gtfsTime.substring(0, 2));
		return arrivalHour >= 24
			? "%02d%s".formatted(arrivalHour % 24, gtfsTime.substring(2))
			: gtfsTime;
	}

	public String defaultBlanks(String numericalValue) {
		return numericalValue.isEmpty() ? "0" : numericalValue;
	}
}
