package org.hibernate.typeDAO;

import org.hibernate.Session;
import org.hibernate.entities.Type;
import org.hibernate.utils.XmlConnectionConfig;

import java.util.List;

public class TypeDAOImpl implements TypeDAO {

    /*
    * public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TYPE_ID", nullable = false)
    private Integer typeId;

    @Column(name = "TYPE_NAME", nullable = false, unique = true)
    private String typeName;

    @Column(name = "TYPE_DESCRIPTION", nullable = false)
    private String typeDescription;


    @OneToMany(mappedBy = "typeId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieType> movieTypes;
    }*/

    @Override
    public Type getTypeById(int id) {
        try (Session session = XmlConnectionConfig.getSession()) {
            Type type = session.get(Type.class, id);
            System.out.println("type = " + type);
            return type;
        }
    }

    @Override
    public List<Type> getAllTypes() {
        try (Session session = XmlConnectionConfig.getSession()) {
            return session.createQuery("from Type", Type.class).list();
        }
    }

    @Override
    public void updateTypeById(int id, Type type) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            Type type1 = session.get(Type.class, id);
            type1.setTypeName(type.getTypeName());
            type1.setTypeDescription(type.getTypeDescription());
            session.getTransaction().commit();
        }

    }

    @Override
    public void deleteTypeById(int id) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            Type type = session.get(Type.class, id);
            session.remove(type);
            session.getTransaction().commit();
        }
    }

    @Override
    public void insertType(Type type) {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            session.persist(type);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteAllTypes() {
        try (Session session = XmlConnectionConfig.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from Type").executeUpdate();
            session.createNativeQuery("DBCC CHECKIDENT (TYPE, RESEED, 0)").executeUpdate();
            session.getTransaction().commit();
        }

    }
}
