package vren.vrenmap;

public interface VrenMapPlugins<T>{
    VrenMapPlugins<VrenMapSettings> SETTING_ACTIVE_RUNNABLE_PLUGIN = () -> (byte) 0;

    byte getValue();
}
