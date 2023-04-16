package org.hibernate.typeSecondLevelCache;

import org.hibernate.Session;
import org.hibernate.entities.Movie;
import org.hibernate.entities.Type;
import org.hibernate.typeDAO.TypeDAO;
import org.hibernate.typeDAO.TypeDAOImpl;
import org.hibernate.utils.XmlConnectionConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TypeSecondLevelCacheTest {

    static TypeDAO typeDAO;

    @BeforeAll
    static void beforeAll() {
        typeDAO = new TypeDAOImpl();
    }

    @BeforeEach
    void setUp() {
        // insert data
        Type type = new Type();
        type.setTypeName("2D");
        type.setTypeDescription("2D");
        typeDAO.insertType(type);

    }

    @AfterEach
    void tearDown() {
        // flush all data
        typeDAO.deleteAllTypes();
    }

    @Test
    void secondLevelCacheTest() {

        // assert that session contains the movie
        try (Session session1 = XmlConnectionConfig.getSession()) {
            Type type = session1.get(Type.class, 1);
            System.out.println("type.getTypeName() = " + type.getTypeName());
            System.out.println("---------------End session1-----------------");
        }
        try (Session session2 = XmlConnectionConfig.getSession()) {
            Type type = session2.get(Type.class, 1);
            System.out.println("type.getTypeName() = " + type.getTypeName());
            System.out.println("---------------End session2-----------------");
        }
    }
}
