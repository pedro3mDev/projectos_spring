package com.anluge.gestDoc.enums;

public enum EnumSexo {

    MASCULINO("M"), FEMENINO("F");

    private final String sexo;

    private EnumSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }
}