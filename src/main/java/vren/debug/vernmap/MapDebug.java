package vren.debug.vernmap;

import vren.vrenmap.VrenMap;
import vren.vrenmap.VrenMapPlugins;
import vren.vrenmap.VrenMapSettings;

public class MapDebug {
    public static void main(String[] args) {
//        Tips
//        This class is just for testing the map and have nothing to do with other code
//        And there is some of my temporary test I forget to delete :p
        VrenMap obj = new VrenMap();
        System.out.println(obj.hashCode());
        String temp = "VrenTest_1234567890_ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz_~!@#$%^&*()_+{}|:<>?-=[]\\\\;',./\"ΩДאم中あ가कአ\ud83d\ude80";
        int hash = obj.put("Value", "StringTest", temp);
        int hash2 = obj.add("Value", "StringTest", temp);
        obj.disable(VrenMapSettings.HEX_VALUE_IN_REPORT);
        obj.enable(VrenMapSettings.HEX_VALUE_IN_REPORT);
        obj.enable(VrenMapSettings.BINARY_VALUE_IN_REPORT);
        obj.add("Value", new Object[]{"1", "2"}, new Object[]{"1-", "2-"});
        System.out.println(obj.getReport());
        obj.pluginsAdd(VrenMapPlugins.SETTING_ACTIVE_RUNNABLE_PLUGIN, VrenMapSettings.HEX_VALUE_IN_REPORT, () -> {
            System.out.println("Successful");
        });
        obj.enable(VrenMapSettings.HEX_VALUE_IN_REPORT);
    }
}
