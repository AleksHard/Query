package net.hive.controller;

/**
 * Created by kharlashkin on 27.01.2017.
 */
public class Pojo {
    private String serpas;
    private String nompas;
    private String famil;
    private String name;
    private String otch;
    private String cartser;
    private String vhod;
    private String vihod;
    private String devIn;
    private String devOut;


    Pojo(String serpas, String nompas, String famil, String name, String otch, String cartser, String vhod, String vihod) {
        this.serpas = serpas;
        this.nompas = nompas;
        this.famil = famil;
        this.name = name;
        this.otch = otch;
        this.cartser = cartser;
        this.vhod = vhod;
        this.vihod = vihod;
    }
    Pojo(String serpas, String nompas, String famil, String name, String otch, String cartser, String vhod) {
        this.serpas = serpas;
        this.nompas = nompas;
        this.famil = famil;
        this.name = name;
        this.otch = otch;
        this.cartser = cartser;
        this.vhod = vhod;
    }

    public String getSerpas() {
        return serpas;
    }

    public void setSerpas(String serpas) {
        this.serpas = serpas;
    }

    public String getNompas() {
        return nompas;
    }

    public void setNompas(String nompas) {
        this.nompas = nompas;
    }

    public String getFamil() {
        return famil;
    }

    public void setFamil(String famil) {
        this.famil = famil;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public String getCartser() {
        return cartser;
    }

    public void setCartser(String cartser) {
        this.cartser = cartser;
    }

    public String getVhod() {
        return vhod;
    }

    public void setVhod(String vhod) {
        this.vhod = vhod;
    }

    public String getVihod() {
        return vihod;
    }

    public void setVihod(String vihod) {
        this.vihod = vihod;
    }

    public String getDevIn() {
        return devIn;
    }

    public void setDevIn(String devIn) {
        this.devIn = devIn;
    }

    public String getDevOut() {
        return devOut;
    }

    public void setDevOut(String devOut) {
        this.devOut = devOut;
    }
}
