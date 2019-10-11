package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categorieID;

    @NotNull
    @Size(min=3)
    private String categorieName;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Car> cars;

    public long getCategorieID() {
        return categorieID;
    }

    public void setCategorieID(long categorieID) {
        this.categorieID = categorieID;
    }

    public String getCategorieName() {
        return categorieName;
    }

    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Categorie() {
    }
}
