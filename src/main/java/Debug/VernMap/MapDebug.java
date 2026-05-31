package Debug.VernMap;

import vren.VrenMap.VrenMap;

public class MapDebug {
    public static void main(String[] args) {
        VrenMap obj = new VrenMap();
        obj.addTag(obj, null, new Object[]{Math.random()});
        System.out.println(obj.valueCount(obj));
        System.out.println("0".hashCode());
        System.out.println(obj);
        obj.addTag("Value", null,
                new Object[]{"VrenCoreTest_1234567890_ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz_~!@#$%^&*()_+{}|:<>?-=[]\\\\;',./\"ΩДאم中あ가कአ\ud83d\ude80"});
        System.out.println(obj.getTag(obj));
    }
}
