package com.equipemovies.ecommercemovies.util;

import javax.servlet.http.HttpServletRequest;

// Para não haver a necessidade de repetir esta operação toda vez que for executada uma operação
public class UtilFatec {

    public static final String COMMON_OPERATION = "operacao";

    private UtilFatec() {
        throw new UnsupportedOperationException("Util class");
    }

    // Verifica se a operação tem um parametro
    public static boolean hasParameterOperation(HttpServletRequest request) {
        return request.getParameter(COMMON_OPERATION) != null
                && !request.getParameter(COMMON_OPERATION).isEmpty();
    }

    // Pega o parmetro passado
    public static String getParameterOperation(HttpServletRequest request) {
        return request.getParameter(COMMON_OPERATION).toLowerCase();
    }

}
