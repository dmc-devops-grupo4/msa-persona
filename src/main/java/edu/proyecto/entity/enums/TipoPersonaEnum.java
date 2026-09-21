package edu.proyecto.entity.enums;

public enum TipoPersonaEnum {
    NATURAL(1),
    JURIDICA(2);

    private int value;

    TipoPersonaEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TipoPersonaEnum fromValue(int value) {
        for (TipoPersonaEnum tipo : TipoPersonaEnum.values()) {
            if (tipo.value == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}