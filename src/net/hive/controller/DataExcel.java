package net.hive.controller;
/**
 * Created by kharlashkin on 20.02.2017.
 * ����� ��� ��������� ������ � Excel ����.
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
