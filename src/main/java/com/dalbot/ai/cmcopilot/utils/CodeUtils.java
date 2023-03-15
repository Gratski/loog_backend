package com.dalbot.ai.cmcopilot.utils;

import com.dalbot.ai.cmcopilot.exception.code.MissingUnitTestFile;
import com.dalbot.ai.cmcopilot.service.code.ProjectContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CodeUtils {

    private static final Map<String, Boolean> codeExtensions;

    static {
        codeExtensions = new HashMap<String, Boolean>();
        codeExtensions.put("java", true);
    }

    public static Boolean hasElegibleCodeExtension(String filename) {
        String[] tokens = filename.split("\\.");
        String fileExtension = tokens[tokens.length - 1];
        return codeExtensions.containsKey(fileExtension);
    }

    /**
     * Searches for a unit test file in the given project context directory that corresponds to the
     * specified test file name.
     *
     * @param pc the project context object containing the project directory to search in
     * @param testFilename the name of the test file to look for
     * @return the path to the unit test file
     * @throws MissingUnitTestFile if no unit test file is found for the given test file name
     * @throws IOException if an I/O error occurs during the file search
     */
    public static Path identifyTestFilename(ProjectContext pc, String testFilename) throws IOException {
        // Find the first file that ends with the specified test file name
        return Files.find(
                        pc.getProjectDirectory().toPath(),
                        Integer.MAX_VALUE,
                        (filePath, fileAttr) -> filePath.endsWith(testFilename))
                .findFirst()
                .orElseThrow(() -> new MissingUnitTestFile("Missing unit tests for file: " + testFilename));
    }

}
