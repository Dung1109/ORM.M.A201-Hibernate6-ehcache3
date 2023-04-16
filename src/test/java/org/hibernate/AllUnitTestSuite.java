package org.hibernate;

import org.hibernate.movieDAO.MovieDAOImplTest;
import org.hibernate.movietypeDAO.MovieTypeDAOImplTest;
import org.hibernate.typeDAO.TypeDAOImplTest;
import org.hibernate.typeSecondLevelCache.TypeSecondLevelCacheTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        MovieDAOImplTest.class,
        TypeDAOImplTest.class,
        MovieTypeDAOImplTest.class,
        TypeSecondLevelCacheTest.class
})
public class AllUnitTestSuite {
}
