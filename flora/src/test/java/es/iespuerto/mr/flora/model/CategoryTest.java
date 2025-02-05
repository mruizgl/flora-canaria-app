package es.iespuerto.mr.flora.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    void defaultConstructorInitializesFieldsCorrectly() {
        Category category = new Category();

        assertEquals(0, category.getId());
        assertNull(category.getName());
    }

    @Test
    void constructorWithNameInitializesFieldsCorrectly() {
        Category category = new Category("Test Category");

        assertEquals("Test Category", category.getName());
    }

    @Test
    void setIdUpdatesIdCorrectly() {
        Category category = new Category();
        category.setId(1);

        assertEquals(1, category.getId());
    }

    @Test
    void setNameUpdatesNameCorrectly() {
        Category category = new Category();
        category.setName("Updated Category");

        assertEquals("Updated Category", category.getName());
    }

    @Test
    void toStringReturnsCorrectStringRepresentation() {
        Category category = new Category("Test Category");
        category.setId(1);

        assertEquals("Category [id=1, name=Test Category]", category.toString());
    }
}