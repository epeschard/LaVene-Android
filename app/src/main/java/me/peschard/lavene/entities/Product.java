package me.peschard.lavene.entities;

import com.google.firebase.database.DatabaseReference;

public class Product {
    public String pKey;
    public String name;
    public String desc;
    public String pack;
    public String code;
    public double prix;
    public double gram;
    public int size;
    public DatabaseReference dRef;

    public  Product() {

    }
    public Product(String code, String name, String desc, String pack, double prix, double gram, int size, String pKey) {
        this.pKey = pKey;
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.pack = pack;
        this.prix = prix;
        this.gram = gram;
        this.size = size;
        this.dRef = null;
    }
    /*
        public Product(DataSnapshot snapshot) {
            this.pKey = snapshot.getKey();
            Dictionary<String, Object> snapshotValue = (Dictionary<String, Object>) snapshot.getValue();
            this.code = (String) snapshotValue["code"];
            this.name = (String) snapshotValue["name"];
            this.desc = (String) snapshotValue["desc"];
            this.pack = (String) snapshotValue["pack"];
            this.prix = (double) snapshotValue["prix"];
            this.gram = (double) snapshotValue["gram"];
            this.size = (int) snapshotValue["size"];
            this.dRef = snapshot.getRef();
        }
    */
    public String getpKey() {
        return pKey;
    }

    public void setpKey(String pKey) {
        this.pKey = pKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getGram() {
        return gram;
    }

    public void setGram(double gram) {
        this.gram = gram;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public DatabaseReference getdRef() {
        return dRef;
    }

    public void setdRef(DatabaseReference dRef) {
        this.dRef = dRef;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
