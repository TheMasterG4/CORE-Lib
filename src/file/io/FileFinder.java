package file.io;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class FileFinder {


    public static File[] getFiles(@NotNull String path, @NotNull String... extensions) {
        final String[] finalExtensions = Arrays.stream(extensions)
                .map(ext -> ext.startsWith(".") ? ext : "." + ext)
                .map(String::toLowerCase)
                .toArray(String[]::new);

        try (Stream<Path> files = Files.walk(Path.of(path))) {
            return files
                    .filter(Files::isRegularFile)
                    .filter(p -> {
                        String fileName = p.getFileName().toString().toLowerCase();

                        return Arrays.stream(finalExtensions)
                                .anyMatch(fileName::endsWith);
                    })
                    .map(Path::toFile)
                    .toArray(File[]::new);

        } catch (IOException e) {
            e.printStackTrace();
            return new File[0];
        }
    }

    public static File[] getFiles(@NotNull String path, boolean includeDirectories) {
        try (Stream<Path> files = Files.walk(Path.of(path))) {
            if (includeDirectories) return files
                    .map(Path::toFile)
                    .toArray(File[]::new);
            else return files.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .toArray(File[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new File[0];
        }
    }
    public static File getFile(String path, String name) {
        try (Stream<Path> files = Files.walk(Path.of(path))) {
            return files.filter(p -> Objects.equals(name, p.getFileName().toString()))
                    .map(Path::toFile)
                    .toArray(File[]::new)[0];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static File getFile(String path, String fileName, String extension) {
        final String name=fileName+extension;
        try (Stream<Path> files = Files.walk(Path.of(path))) {
            return files.filter(p -> Objects.equals(name, p.getFileName().toString()))
                    .map(Path::toFile)
                    .toArray(File[]::new)[0];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static File[] getFiles(@NotNull File file, @NotNull String... extensions) {
        final String[] finalExtensions = Arrays.stream(extensions)
                .map(ext -> ext.startsWith(".") ? ext : "." + ext)
                .map(String::toLowerCase)
                .toArray(String[]::new);

        try (Stream<Path> files = Files.walk(file.toPath())) {
            return files
                    .filter(Files::isRegularFile)
                    .filter(p -> {
                        String fileName = p.getFileName().toString().toLowerCase();

                        return Arrays.stream(finalExtensions)
                                .anyMatch(fileName::endsWith);
                    })
                    .map(Path::toFile)
                    .toArray(File[]::new);

        } catch (IOException e) {
            e.printStackTrace();
            return new File[0];
        }
    }

    public static File[] getFiles(@NotNull File file, boolean includeDirectories) {
        try (Stream<Path> files = Files.walk(file.toPath())) {
            if (includeDirectories) return files
                    .map(Path::toFile)
                    .toArray(File[]::new);
            else return files.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .toArray(File[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new File[0];
        }
    }
    public static File getFile(File file, String name) {
        try (Stream<Path> files = Files.walk(Path.of(file.getPath()))) {
            return files.filter(p -> Objects.equals(name, p.getFileName().toString()))
                    .map(Path::toFile)
                    .toArray(File[]::new)[0];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static File getFile(File file, String fileName, String extension) {
        final String name=fileName+extension;
        try (Stream<Path> files = Files.walk(Path.of(file.getPath()))) {
            return files.filter(p -> Objects.equals(name, p.getFileName().toString()))
                    .map(Path::toFile)
                    .toArray(File[]::new)[0];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}
