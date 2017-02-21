package net.hive.controller;
/**
 * Created by kharlashkin on 20.02.2017.
 * Класс для переброса данных в Excel файл.
 */
public class DataExcel {
    private String name;
    public DataExcel(){
    }

    public DataExcel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
