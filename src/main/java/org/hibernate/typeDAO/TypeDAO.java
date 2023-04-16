package org.hibernate.typeDAO;

import org.hibernate.entities.Type;

import java.util.List;

public interface TypeDAO {
    Type getTypeById(int id);
    List<Type> getAllTypes();
    void updateTypeById(int id, Type type);
    void deleteTypeById(int id);
    void insertType(Type type);
    void deleteAllTypes();
}
