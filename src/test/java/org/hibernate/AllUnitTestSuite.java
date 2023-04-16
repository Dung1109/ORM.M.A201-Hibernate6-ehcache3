package org.hibernate;

import org.hibernate.movieDAO.MovieDAOImplTest;
import org.hibernate.movietypeDAO.MovieTypeDAOImplTest;
import org.hibernate.typeDAO.TypeDAOImplTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        MovieDAOImplTest.class,
        TypeDAOImplTest.class,
        MovieTypeDAOImplTest.class
})
public class AllUnitTestSuite {
}
