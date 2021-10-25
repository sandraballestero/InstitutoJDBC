package org.iesfm.instituto.jdbc;

import java.util.Objects;

public class Title {
    private Integer id;
    private String name;
    private String level;
    private String family;
    private String description;

    public Title(Integer id, String name, String level, String family, String description) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.family = family;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title = (Title) o;
        return Objects.equals(id, title.id) && Objects.equals(name, title.name) && Objects.equals(level, title.level) && Objects.equals(family, title.family) && Objects.equals(description, title.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, family, description);
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", family='" + family + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
