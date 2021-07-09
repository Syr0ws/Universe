package com.github.syr0ws.universe.modules.border.impl;

import com.github.syr0ws.universe.modules.border.Border;
import com.github.syr0ws.universe.modules.border.BorderDAO;
import com.github.syr0ws.universe.modules.border.BorderModel;
import com.github.syr0ws.universe.modules.border.BorderService;

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

        Collection<? extends Border> borders = this.dao.loadBorders();
        borders.forEach(this.model::addBorder);
    }
}
