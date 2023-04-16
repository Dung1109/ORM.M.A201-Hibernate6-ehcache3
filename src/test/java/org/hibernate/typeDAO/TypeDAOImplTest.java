package org.hibernate.typeDAO;

import org.hibernate.entities.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeDAOImplTest {

    static TypeDAO typeDAO;

    @BeforeAll
    static void beforeAll() {
        typeDAO = new TypeDAOImpl();
    }

    @AfterEach
    void tearDown() {
        typeDAO.deleteAllTypes();
    }

    @Test
    void getTypeByIdTest() {
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        typeDAO.insertType(type);

        assertAll(
                () -> assertEquals("Action", typeDAO.getTypeById(1).getTypeName()),
                () -> assertEquals("Action movie", typeDAO.getTypeById(1).getTypeDescription())
        );
    }

    @Test
    void getAllTypesTest() {
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        typeDAO.insertType(type);

        Type type2 = new Type();
        type2.setTypeName("Comedy");
        type2.setTypeDescription("Comedy movie");
        typeDAO.insertType(type2);

        assertAll(
                () -> assertEquals(2, typeDAO.getAllTypes().size()),
                () -> assertEquals("Action", typeDAO.getAllTypes().get(0).getTypeName()),
                () -> assertEquals("Action movie", typeDAO.getAllTypes().get(0).getTypeDescription()),
                () -> assertEquals("Comedy", typeDAO.getAllTypes().get(1).getTypeName()),
                () -> assertEquals("Comedy movie", typeDAO.getAllTypes().get(1).getTypeDescription())
        );
    }

    @Test
    void updateTypeByIdTest() {
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        typeDAO.insertType(type);

        Type type2 = new Type();
        type2.setTypeName("Comedy");
        type2.setTypeDescription("Comedy movie");
        typeDAO.updateTypeById(1, type2);

        assertAll(
                () -> assertEquals("Comedy", typeDAO.getTypeById(1).getTypeName()),
                () -> assertEquals("Comedy movie", typeDAO.getTypeById(1).getTypeDescription())
        );
    }

    @Test
    void deleteTypeByIdTest() {
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        typeDAO.insertType(type);

        Type type2 = new Type();
        type2.setTypeName("Comedy");
        type2.setTypeDescription("Comedy movie");
        typeDAO.insertType(type2);

        typeDAO.deleteTypeById(1);

        assertAll(
                () -> assertEquals(1, typeDAO.getAllTypes().size()),
                () -> assertEquals("Comedy", typeDAO.getAllTypes().get(0).getTypeName()),
                () -> assertEquals("Comedy movie", typeDAO.getAllTypes().get(0).getTypeDescription())
        );
    }

    @Test
    void insertTypeTest() {
        Type type = new Type();
        type.setTypeName("Action");
        type.setTypeDescription("Action movie");
        assertDoesNotThrow(() -> typeDAO.insertType(type));
    }
}