package model;

import javax.persistence.*;

@Entity
@Table(name= "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id_destination;

    @Column
    private String name;

    public Destination(String name) {
        this.name = name;
    }

    public Destination(){

    }

    public int getId_destination() {
        return id_destination;
    }

    public void setId_destination(int id_destination) {
        this.id_destination = id_destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
