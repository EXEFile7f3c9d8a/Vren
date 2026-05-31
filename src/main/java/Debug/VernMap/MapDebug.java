package Debug.VernMap;

import vren.VrenMap.VrenMap;

public class MapDebug {
    public static void main(String[] args) {
        VrenMap obj = new VrenMap();
        obj.addTag(obj, null, new Object[]{Math.random()});
        System.out.println(obj.valueCount(obj));
        System.out.println("0".hashCode());
        System.out.println(obj);
        obj.addTag("Value", null, new Object[]{"1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM~!@#$%^&*()_+中文字符测试"});
        System.out.println(obj.getTag(obj));
    }

}
