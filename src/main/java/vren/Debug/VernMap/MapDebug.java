package vren.Debug.VernMap;

import vren.VrenMap.VrenMap;

public class MapDebug {
    public static void main(String[] args) {
//        Tips
//        This class is just for testing the map and have nothing to do with other code
//        And there is some of my temporary test i forget to delete :p
        VrenMap obj = new VrenMap();
        obj.addTag("Value", new Object[]{"StringTest"},
                new Object[]{"VrenTest_1234567890_ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz_~!@#$%^&*()_+{}|:<>?-=[]\\\\;',./\"ΩДאم中あ가कአ\ud83d\ude80"});
        System.out.println(obj.getTagReport("Value"));
        System.out.println(System.lineSeparator());
        System.out.println(System.lineSeparator());
//        obj.disable(VrenMap.HEX_VALUE_IN_REPORT);
        obj.enable(VrenMap.BINARY_VALUE_IN_REPORT);
        obj.addValue("Value", new Object[]{"1", "2"}, new Object[]{"1-", "2-"});
        System.out.println(obj.getTagReport("Value"));
    }
}
