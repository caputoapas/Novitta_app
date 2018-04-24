package com.example.caputo.app_novitta;

import android.provider.BaseColumns;

public final class PostContract {
    private PostContract() {}

    public static class PostEntry implements BaseColumns {
        public static final String TABLE_NAME = "votacao";
        public static final String COLUMN_NAME_TIPO = "tipo";
        public static final String COLUMN_NAME_VOTO = "voto";
        public static final String COLUMN_NAME_DATA = "data";
    }
}
