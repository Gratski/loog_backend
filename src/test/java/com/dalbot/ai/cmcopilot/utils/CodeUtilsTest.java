package com.dalbot.ai.cmcopilot.utils;

import com.dalbot.ai.cmcopilot.exception.code.MissingUnitTestFile;
import com.dalbot.ai.cmcopilot.service.code.ProjectContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CodeUtilsTest {

    @Test
    @DisplayName("Test with a valid code extension")
    void testHasElegibleCodeExtensionWithValidExtension() {
        String filename = "test.java";
        assertTrue(CodeUtils.hasElegibleCodeExtension(filename));
    }

    @Test
    @DisplayName("Test with an invalid code extension")
    void testHasElegibleCodeExtensionWithInvalidExtension() {
        String filename = "test.jpg";
        assertFalse(CodeUtils.hasElegibleCodeExtension(filename));
    }

    @Test
    @DisplayName("Test with a filename without extension")
    void testHasElegibleCodeExtensionWithoutExtension() {
        String filename = "test";
        assertFalse(CodeUtils.hasElegibleCodeExtension(filename));
    }

    @Test
    @DisplayName("Test with a null filename")
    void testHasElegibleCodeExtensionWithNullFilename() {
        String filename = null;
        assertFalse(CodeUtils.hasElegibleCodeExtension(filename));
    }

    @Test
    @DisplayName("Test with an empty filename")
    void testHasElegibleCodeExtensionWithEmptyFilename() {
        String filename = "";
        assertFalse(CodeUtils.hasElegibleCodeExtension(filename));
    }

    @Test
    @DisplayName("Test with a filename with multiple dots")
    void testHasElegibleCodeExtensionWithMultipleDots() {
        String filename = "test.file.java";
        assertTrue(CodeUtils.hasElegibleCodeExtension(filename));
    }

    @Test
    public void testIdentifyTestFilename() throws IOException {
        ProjectContext pc = mock(ProjectContext.class);
        File projectDirectory = mock(File.class);
        Path mockPath = mock(Path.class);
        when(pc.getProjectDirectory()).thenReturn(projectDirectory);
        when(projectDirectory.toPath()).thenReturn(mockPath);

        Path expected = mock(Path.class);
        when(expected.toString()).thenReturn("testfile.txt");

        try(MockedStatic<Files> mockedStatic = Mockito.mockStatic(Files.class)) {
            mockedStatic.when(() -> Files.find(any(), anyInt(), any())).thenReturn(Stream.of(expected));
            Path result = CodeUtils.identifyTestFilename(pc, "testfile.txt");
            Assertions.assertEquals(expected, result);
        };
    }

    @Test
    public void testIdentifyTestFilename_missingFile() throws IOException {
        ProjectContext pc = mock(ProjectContext.class);
        File projectDirectory = mock(File.class);
        Path mockPath = mock(Path.class);
        when(pc.getProjectDirectory()).thenReturn(projectDirectory);
        when(projectDirectory.toPath()).thenReturn(mockPath);

        try(MockedStatic<Files> mockedStatic = Mockito.mockStatic(Files.class)) {

            mockedStatic.when(() -> Files.find(any(), anyInt(), any())).thenReturn(Stream.empty());

            Assertions.assertThrows(MissingUnitTestFile.class, () -> {
                CodeUtils.identifyTestFilename(pc, "missing.txt");
            });
        };

    }
}