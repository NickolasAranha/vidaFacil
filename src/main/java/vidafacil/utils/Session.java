package vidafacil.utils;

import vidafacil.model.Idoso;

public class Session {
    private static Idoso idosoLogado;

    public static void login(Idoso idoso) {
        idosoLogado = idoso;
    }

    public static void logout() {
        idosoLogado = null;
    }

    public static Idoso getUsuario() {
        return idosoLogado;
    }

    public static boolean isLogado() {
        return idosoLogado != null;
    }
}
