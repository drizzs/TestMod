package com.drizzs.testmod.api;

public interface IPressureReceiver
{

    public boolean isFull();

    public void receivePressure(int pressure);

    public boolean canReceivePressure();

    public int getCurrentPressure();

}
