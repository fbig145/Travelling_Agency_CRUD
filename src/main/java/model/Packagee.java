package model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name= "package")
public class Packagee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id_package;

    @Column
    private String name;

    @Column
    private int destination_id;

    @Column
    private int price;

    @Column
    private Date start;

    @Column
    private Date end;

    @Column
    private String details;

    @Column
    private int spots;

    @Column
    private String status;

    public Packagee(){

    }

    public Packagee(String name, int destination_id, int price, Date start, Date end, String details, int spots, String status) {
        this.name = name;
        this.destination_id = destination_id;
        this.price = price;
        this.start = start;
        this.end = end;
        this.details = details;
        this.spots = spots;
        this.status = status;
    }

    public int getId_package() {
        return id_package;
    }

    public void setId_package(int id_package) {
        this.id_package = id_package;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getSpots() {
        return spots;
    }

    public void setSpots(int spots) {
        this.spots = spots;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
