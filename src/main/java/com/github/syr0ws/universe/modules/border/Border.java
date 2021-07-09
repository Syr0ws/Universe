package com.github.syr0ws.universe.modules.border;

public interface Border {

    void setDamages(double damages);

    void setSize(double size);

    void setSize(int size, int seconds);

    void setCenter(double x, double z);

    void setCenter(double x, double z, int seconds);

    void setSafeZoneDistance(double distance);

    void setWarningDistance(int distance);

    void setWarningTime(int seconds);

    String getWorld();

    double getCenterX();

    double getCenterZ();

    double getSize();

    double getDamages();

    double getSafeZoneDistance();

    int getWarningDistance();

    int getWarningTime();
}
