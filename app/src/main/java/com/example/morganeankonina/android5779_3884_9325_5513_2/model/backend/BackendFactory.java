package com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend;

import android.content.Context;

/**
 * Class BackendFactory choose the type of DB
 */
public final class BackendFactory {
    static Backend instance = null;

    public static String mode = "fb";

    /**
     * Factory method create the instance of the backend
     * @return Backend
     */
    public final static Backend getInstance(Context context) {
        if (mode == "lists") {
            if (instance == null)
                instance = new com.example.morganeankonina.android5779_3884_9325_5513_2.model.datasource.DataBaseList();
            return instance;
        }

        if (mode == "fb") {
            if (instance == null)
                instance = new com.example.morganeankonina.android5779_3884_9325_5513_2.model.datasource.DataBaseFB();
            return instance;
        }
        return instance;
    }
}