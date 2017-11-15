package com.miguelcr.mecaround.interfaces;

import com.miguelcr.mecaround.models.AveriaDB;

/**
 * Created by miguelcampos on 27/10/17.
 */

public interface OnAveriaInteractionListener {
    public void onAveriaClick(AveriaDB averiaDB);
    public void onAveriaEdit(AveriaDB mItem);
}
