package com.github.syr0ws.universe.sdk.modules.border.impl;

import com.github.syr0ws.universe.sdk.modules.border.*;

import java.util.Collection;

public class CraftBorderService implements BorderService {

    private final BorderModel model;
    private final BorderDAO dao;

    public CraftBorderService(BorderModel model, BorderDAO dao) {

        if(model == null)
            throw new IllegalArgumentException("BorderModel cannot be null.");

        if(dao == null)
            throw new IllegalArgumentException("BorderDAO cannot be null.");

        this.model = model;
        this.dao = dao;
    }

    @Override
    public void loadBorders() {

        try {

            Collection<? extends Border> borders = this.dao.loadBorders();
            borders.forEach(this.model::addBorder);

        } catch (BorderException e) { e.printStackTrace(); }
    }
}
