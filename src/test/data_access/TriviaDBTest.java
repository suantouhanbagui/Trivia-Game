package test.data_access;

import main.data_access.TriviaDB;
import main.entities.QuestionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static test.TestingHelperFunctions.getPrivateVariableHelper;
import static test.TestingHelperFunctions.instantiatePrivateClass;

class TriviaDBTest {
    TriviaDB triviaDB;

    @BeforeEach
    void setUp() {
        triviaDB = new TriviaDB();
    }

//    @Test
//    void getQuestionsTestAmounts() throws InterruptedException {
//        for (Integer i = 1; i < 33; i++) {
//            QuestionList newList = triviaDB.getQuestions(i, "Entertainment: Cartoon & Animations", "Any Difficulty", "Multiple Choice");
//            assertEquals(i, newList.getQuestions().size());
//            TimeUnit.SECONDS.sleep(5);
//        }
//    }

    @Test
    void getQuestionsTestAmountInvalid() {
        assertThrows(RuntimeException.class, () -> triviaDB.getQuestions(100, "Art", "Hard", "Multiple Choice"));
    }

    @Test
    void getQuestionsTestCategories() throws NoSuchFieldException, IllegalAccessException, InterruptedException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Object temp = instantiatePrivateClass(TriviaDB.class, "APIParameterConverter", new Class[]{}, new Object[]{});
        HashMap tempCategories = getPrivateVariableHelper(temp, "categoryMap");
        List<String> categoryList = new ArrayList<>(tempCategories.keySet());
        for (String s : categoryList) {
            QuestionList newList = triviaDB.getQuestions(1, s, "Any Difficulty", "Multiple Choice");
            assertEquals(s, newList.getCategory());
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    void getQuestionsTestCategoriesInvalid() {
        assertThrows(RuntimeException.class, () -> triviaDB.getQuestions(1, "Math", "Hard", "Multiple Choice"));
    }

    @Test
    void getQuestionsTestDifficulty() throws InterruptedException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Object temp = instantiatePrivateClass(TriviaDB.class, "APIParameterConverter", new Class[]{}, new Object[]{});
        HashMap tempDifficulties = getPrivateVariableHelper(temp, "difficultyMap");
        List<String> difficultyList = new ArrayList<>(tempDifficulties.keySet());
        for (int i = 0; i < difficultyList.size(); i++) {
            QuestionList newList = triviaDB.getQuestions(1, "Any Category", difficultyList.get(i), "Multiple Choice");
            assertEquals(difficultyList.get(i), newList.getDifficulty());
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    void getQuestionsTestDifficultyInvalid(){
        assertThrows(RuntimeException.class, () -> triviaDB.getQuestions(1, "Art", "Insane", "Multiple Choice"));

    }

    @Test
    void getQuestionsTestTypes() throws NoSuchFieldException, IllegalAccessException, InterruptedException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Object temp = instantiatePrivateClass(TriviaDB.class, "APIParameterConverter", new Class[]{}, new Object[]{});
        HashMap tempTypes = getPrivateVariableHelper(temp, "typeMap");
        List<String> typesList = new ArrayList<>(tempTypes.keySet());
        for (int i = 0; i < typesList.size(); i++) {
            QuestionList newList = triviaDB.getQuestions(1, "Any Category", "Any Difficulty", typesList.get(i));
            assertEquals(typesList.get(i), getPrivateVariableHelper(newList, "type"));
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    void getQuestionsTestTypesInvalid(){
        assertThrows(RuntimeException.class, () -> triviaDB.getQuestions(1, "Art", "Hard", "Elimination"));
    }

}

// NOTE: I can't figure out how to get Mockito to work. I've spent way too long trying.
// I'm going to come back to try to figure it out later if possible. I can't test token retrieval until I do.
// Ignore below for now.


//    private TriviaDB mockTriviaDB;
//
//
//    @BeforeEach
//    public void setUp() throws IOException {
//        mockTriviaDB =  mock(TriviaDB.class);
//    }
//
//
//    @Test
//    public void retrieveTokenTest() throws Exception {
////        when(callPrivateMethod(mockTriviaDB, "retrieveToken", new Class<?>[]{})).thenReturn("hello");
//        when(mockTriviaDB.retrieveToken()).thenReturn("hello");
//        assertEquals("hello", getPrivateVariableHelper(mockTriviaDB, "token"));
//    }
//
////    @Test(expected = RuntimeException.class)
////    public void retrieveTokenTestError() throws IOException {
////    }
////
////    @Test
////    public void testRefreshToken() throws IOException {
////    }
////
////    @Test(expected = RuntimeException.class)
////    public void testRefreshTokenWithError() throws IOException {
////    }
////
////    @Test
////    public void testGetQuestions() throws IOException {
////    }
////
////    @Test(expected = RuntimeException.class)
////    public void testGetQuestionsWithError() throws IOException {
////    }
//}