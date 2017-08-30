package me.peschard.lavene.entities;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;

public class Order {
    public String code;
    public String name;
    public String user;
    public Date date;
    public Boolean open;
    public int tote;
    public double cost;
    public DatabaseReference dRef;

    public  Order() {

    }
    public Order(String code, String name, String user, Date date, Boolean open, int tote, double cost) {
        this.code = code;
        this.name = name;
        this.user = user;
        this.date = date;
        this.open = open;
        this.tote = tote;
        this.cost = cost;
        this.dRef = null;
    }
    /*
    public Order(DataSnapshot snapshot) {
        this.code = snapshot.getKey();
        Dictionary<String, Object> snapshotValue = (Dictionary<String, Object>) snapshot.getValue();
        this.code = (String) snapshotValue["code"];
        this.name = (String) snapshotValue["name"];
        this.user = (String) snapshotValue["user"];
        this.date = (Date) snapshotValue["date"];
        this.open = (boolean) snapshotValue["open"];
        this.tote = (int) snapshotValue["tote"];
        this.cost = (double) snapshotValue["cost"];
        this.dRef = snapshot.getRef();
    }
    */

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public int getTote() {
        return tote;
    }

    public void setTote(int tote) {
        this.tote = tote;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public DatabaseReference getdRef() {
        return dRef;
    }

    public void setdRef(DatabaseReference dRef) {
        this.dRef = dRef;
    }
}

