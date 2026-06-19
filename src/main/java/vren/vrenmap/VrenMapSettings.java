package vren.vrenmap;

public interface VrenMapSettings {
    VrenMapSettings BINARY_VALUE_IN_REPORT = () -> (byte)0;
    VrenMapSettings HEX_VALUE_IN_REPORT    = () -> (byte)1;
    VrenMapSettings CLEAR_STORAGE_IN_RESET = () -> (byte)2;
    VrenMapSettings CLEAR_SETTING_IN_RESET = () -> (byte)3;
    VrenMapSettings COMPARE_SETTINGS_IN_EQUALS = () -> (byte)4;
    VrenMapSettings COMPARE_PLUGINS_IN_EQUALS  = () -> (byte)5;
    VrenMapSettings INCLUDE_SETTINGS_IN_HASHCODE = () -> (byte)6;
    VrenMapSettings INCLUDE_PLUGINS_IN_HASHCODE  = () -> (byte)7;
    byte getValue();
}
