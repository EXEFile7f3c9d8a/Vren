package vren.vrenmap;

public interface VrenMapSettings {
    VrenMapSettings BINARY_VALUE_IN_REPORT = () -> (byte)0;
    VrenMapSettings HEX_VALUE_IN_REPORT    = () -> (byte)1;
    VrenMapSettings CLEAR_STORAGE_IN_RESET = () -> (byte)2;
    VrenMapSettings CLEAR_SETTING_IN_RESET = () -> (byte)3;

    byte getValue();
}
