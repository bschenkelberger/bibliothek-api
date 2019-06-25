package de.praktikant.bibliothek.api.backend.hibternate.entitys;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Genre {
    protected Long id;
    protected String name;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
