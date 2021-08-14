package jp.sugarcoffee.consolefiltertransplantunofficial.config;

import jp.sugarcoffee.consolefiltertransplantunofficial.ConsoleFilterTransplantUnofficial;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConfigUtil {
    public final Path CONFIG_FILE_PATH = Paths.get(ConsoleFilterTransplantUnofficial.MOD_ANNOTATION_VALUE + ".toml");
    public final PathUtil PATH_UTIL = new PathUtil(getConfigDir());

    public static final class PathUtil {
        public final Path path;

        public PathUtil(Path isDirAndExistsPath) {
            if (isDirAndExistsPath == null) {
                throw new NoSuchElementException();
            }

            this.path = isDirAndExistsPath;
            requestPath(Files::isDirectory).requestPath(Files::exists);
        }

        public PathUtil requestPath(Predicate<Path> pathPredicate) throws NoSuchElementException {
            if (!pathPredicate.test(path)) {
                throw new NoSuchElementException();
            }
            return this;
        }

        public void checkAndConsume(Path testPath, Predicate<Path> pathPredicate, Consumer<Path> pathConsumer) {
            if (pathPredicate.test(testPath)) {
                pathConsumer.accept(testPath);
            }
        }

        public boolean notFileExists(Path path) {
            return !Files.exists(path);
        }

        public void touch(Path joinPath) {
            final List<String> stringList = new ArrayList<>();
            stringList.add("# This file has a .toml extension, but it is not really a .toml file.");
            stringList.add("# Also, it is read using java.nio instead of the forge function.");
            stringList.add("# List the characters you want to filter in this file.");
            stringList.add("# Characters prefixed with \"#\" are not filtered. It is treated as a comment.");
            stringList.add("");
            stringList.add("# <");
            stringList.add("");
            stringList.add("SumpleText_ed58c1b20e05caf61ce8ab0e385108c6d1bf9b7c5fa789eede6b43eef250d795");
            for (int i = 0; i < 5; i++) {
                stringList.add("");
            }
            stringList.add("# >");

            for (int i = 0; i < 5; i++) {
                stringList.add("");
            }

//            stringList.add("# Below is the area for the \"Switch Flag\" file. Please do not delete this comment.");
//            stringList.add("# FilterMatchISACCEPT_UnMatchISDenyMode=0");
//            stringList.add("# Switch Flag Fin");

            final Path configFilePath = path.resolve(joinPath);

            checkAndConsume(configFilePath, this::notFileExists,
                    filePath -> {
                        try {
                            Files.createFile(filePath);
                            Files.write(filePath, stringList, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }

        public List<String> configRead(Path joinPath) {

            final List<String> allLines;
            final Path configFilePath = path.resolve(joinPath);
            try {
                allLines = Files.readAllLines(configFilePath, StandardCharsets.UTF_8);

//                final String optionText = "# FilterMatchISACCEPT_UnMatchISDenyMode=";
//                String modeOption = allLines.stream()
//                        .filter(s -> s.contains(optionText))
//                        .findFirst()
//                        .orElseThrow(IllegalStateException::new)
//                        .substring(optionText.length());
//
//                if (modeOption.equals("1")) {
//                    Config.setFilterMatchIsAcceptUnMatchIsDenyMode(true);
//                }

            } catch (IOException e) {
                e.printStackTrace();
                Config.LOGGER.error(e);
                return new ArrayList<>();
            }

            return allLines.stream()
                    .filter(s -> !s.matches("^#.*") && !s.isEmpty())
                    .collect(Collectors.toList());
        }

    }

    private Path getGameDir() {
        return Paths.get("./").toAbsolutePath();
    }

    public Path getConfigDir() {
        return getGameDir().normalize().resolve("config/");
    }

    public ConfigUtil createOrIgnoreConfigFile() {

        PathUtil predicatePath = new PathUtil(getConfigDir());
        predicatePath.touch(CONFIG_FILE_PATH);
        return this;
    }

    public List<String> readConfigFile() {
        return PATH_UTIL.configRead(CONFIG_FILE_PATH);
    }
}
