package edu.proyecto.entity.enums;

public enum TipoDocIdentidadEnum {
    DNI(1), // 1
    CE(2); // 2

    private int value;

    TipoDocIdentidadEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TipoDocIdentidadEnum fromValue(int value) {
        for (TipoDocIdentidadEnum tipo : TipoDocIdentidadEnum.values()) {
            if (tipo.value == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}