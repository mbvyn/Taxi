package com.epam.taxi.db.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 *
 * @author M.-B.Vynnytskyi
 */
public class Entity implements Serializable {
    private static final long serialVersionUID = 8466257860808346236L;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
