package test.data_access;

import main.data_access.TriviaDB;
import main.data_access.TriviaDB.APIParameterConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.TestingHelperFunctions;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class APIParameterConverterTest {

    private TriviaDB.APIParameterConverter test;

    @BeforeEach
    void setup() {
        test = new TriviaDB.APIParameterConverter();
    }

    @Test
    void constructorTest() throws NoSuchFieldException, IllegalAccessException {
        HashMap categories = TestingHelperFunctions.getPrivateVariableHelper(test, "categoryMap");
        HashMap difficulties = TestingHelperFunctions.getPrivateVariableHelper(test, "difficultyMap");
        HashMap types = TestingHelperFunctions.getPrivateVariableHelper(test, "typeMap");
        assertEquals(25, categories.size());
        assertEquals(4, difficulties.size());
        assertEquals(3, types.size());
        System.out.println(categories);
    }

    @Test
    void convertCategoryTestAllCategories() throws NoSuchFieldException, IllegalAccessException {
        HashMap categories = TestingHelperFunctions.getPrivateVariableHelper(test, "categoryMap");
        for (Object key: categories.keySet()) {
            assertEquals(categories.get(key), test.convertCategory(key.toString()));
        }
    }

    @Test
    void convertCategoryTestInvalidCategory() {
        assertThrows(RuntimeException.class, () -> test.convertCategory("%#*!*"));
    }

    @Test
    void convertDifficultyTestAllDifficulties() throws NoSuchFieldException, IllegalAccessException {
        HashMap categories = TestingHelperFunctions.getPrivateVariableHelper(test, "difficultyMap");
        for (Object key: categories.keySet()) {
            assertEquals(categories.get(key), test.convertDifficulty(key.toString()));
        }
    }

    @Test
    void convertDifficultyTestInvalidDifficulty() {
        assertThrows(RuntimeException.class, () -> test.convertDifficulty("%#*!*"));
    }

    @Test
    void convertTypeTestAllTypes() throws NoSuchFieldException, IllegalAccessException {
        HashMap categories = TestingHelperFunctions.getPrivateVariableHelper(test, "TypeMap");
        for (Object key: categories.keySet()) {
            assertEquals(categories.get(key), test.convertType(key.toString()));
        }
    }

    @Test
    void convertTypeTestInvalidType() {
        assertThrows(RuntimeException.class, () -> test.convertType("%#*!*"));
    }
}