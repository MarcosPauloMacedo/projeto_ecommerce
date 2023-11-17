package model.enuns;

import lombok.Getter;

@Getter
public enum Perfil {
    ADMINISTRADOR(1, "Administrador"),
    CLIENTE(0, "Cliente");

    private final Long id;
    private final String descricao;

    Perfil(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Perfil acesso(Long id) {
        if (id == null) {
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
