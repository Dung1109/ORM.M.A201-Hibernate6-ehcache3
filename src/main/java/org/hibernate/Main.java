package org.hibernate;

import org.hibernate.utils.XmlConnectionConfig;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        XmlConnectionConfig.getSession();
    }
}