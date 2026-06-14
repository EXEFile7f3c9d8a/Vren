package vren.vrenmap;

import java.util.*;

import static vren.vrendevtools.VrenDevTools.*;

public class VrenMap {
    private static final List<String> Title = new ArrayList<>(); //HAHAHAHAAAAAAAAAA
    private static final String BLOB = "--------------------------------------------------";
    private static final String indent = "       ";
    private static final String lineContinue = "├";
    private static final String lineEnd = "└";
    private static final String vertical = "│";
    private static final String horizontal = "──";
    private static final String endArrow = "└►";


    public static final byte SETTINGS_COUNT = 4;
    private final boolean[] settings = new boolean[SETTINGS_COUNT];


    public static final byte PLUGIN_COUNT = 1;
    private final List<List<Runnable>> plugin = new ArrayList<>();


    private static final List<Runnable> startUp = new ArrayList<>();
    private final HashMap<Object, Object> tagStorage = new HashMap<>();
    public int length = 0;
    public int lengthTotal = 0;
    private static final class Tags{
//      Format: HashMap<List(OBJValueName, OBJValueHash), OBJValue>
        public int length = 0;
        public HashMap<List<Object>, Object> Value = new HashMap<>();
        public Object LockTag = null;
        public Tags(VrenMap clas, Object tag, Object[] valueName, Object[] value){
            if(tag != null)this.LockTag = tag;
            add(clas, valueName, value);
        }
        public int[] add(VrenMap clas, Object[] valueName, Object[] value){
            int[] tempHash;
            if(valueName == null && value != null){
                tempHash = new int[value.length];
                for(int i = 0; i < value.length; i++){
                    int temp = value[i] != null ? Objects.hash(value[i]) : 0;
                    this.Value.put(Arrays.asList(null, temp), value[i]);
                    tempHash[i] = temp;
                    clas.lengthTotal++;
                    this.length++;
                }
            }else if(value == null){
                clas.length++;
                return new int[]{0};
            }else{
                tempHash = new int[value.length];
                for(int i = 0; i < value.length; i++){
                    int temp = Objects.hash(value[i]);
                    this.Value.put(Arrays.asList(valueName[i], Objects.hash(value[i])), value[i]);
                    tempHash[i] = temp;
                    clas.lengthTotal++;
                    this.length++;
                }
            }
            clas.length++;
            return tempHash;
        }
//        Format: <tagClassName,tagToHex>|[valueName,valueHash,valueToHex]|...
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("<").append(getClassName(LockTag)).append(",").append(toHex(LockTag)).append(">|");
            List<Map.Entry<List<Object>, Object>> entries = new ArrayList<>(Value.entrySet());
            for(int i = 0; i < Value.size(); i++){
                Map.Entry<List<Object>, Object> entry = entries.get(i);
                List<Object> currentKey = entry.getKey();
                Object currentValue = entry.getValue();
                sb.append("[").append(currentKey.getFirst()).append(",").append(currentKey.get(1)).append(",").append(toHex(currentValue)).append("]|");
            }
            sb.deleteCharAt(!sb.isEmpty() && sb.charAt(sb.length()-1) == '|' ? sb.length() - 1 :sb.length());
            return sb.toString();
        }
    }
    private static final class plugins{

        public plugins(){

        }
    }
    public VrenMap(){
        resetSetting();
        for(int i = 0; i < SETTINGS_COUNT; i++){
            plugin.add(new ArrayList<>());
        }
        Title.addAll(List.of(
                "      .-----------------.      "   ,
                "     /                   \\     "  ,
                "    /                     \\    " ,
                "   |\\--[___]---------[___]/|   "   ,
                "   |                       |   "  ,
                "   |                       |   "   ,
                "   |               /       |   "   ,
                "    \\        \\____/       /    " ,
                "     \\___________________/     "
        ));
        startUp.add(() -> {
            //this is supposed to be extra binary setting execution
        List<Runnable> copy = plugin.getFirst();
            for(int i = 0; i < copy.size(); i++){
                copy.get(i).run();
            }
        });
        startUp.add(() -> {
            //this is supposed to be extra hex setting execution
        List<Runnable> copy = plugin.get(1);
            for(int i = 0; i < copy.size(); i++){
                copy.get(i).run();
            }
        });
        startUp.add(() -> {
            //this is supposed to be extra storage clear setting execution
        List<Runnable> copy = plugin.get(2);
            for(int i = 0; i < copy.size(); i++){
                copy.get(i).run();
            }
        });
        startUp.add(() -> {
            //this is supposed to be extra setting clear setting execution
        List<Runnable> copy = plugin.get(3);
            for(int i = 0; i < copy.size(); i++){
                copy.get(i).run();
            }
        });
    }
    public void reset(){
        List<Runnable> temp = new ArrayList<>();
        if(settings[VrenMapSettings.CLEAR_STORAGE_IN_RESET]){
            temp.add(() -> {
                tagStorage.clear();
                length = 0;
                lengthTotal = 0;
            });
        }
        if(settings[VrenMapSettings.CLEAR_SETTING_IN_RESET]){
            temp.add(this::resetSetting);
        }
        for(int i = 0; i < temp.size(); i++){
            temp.get(i).run();
        }
    }
    public void resetSetting(){
        settings[VrenMapSettings.BINARY_VALUE_IN_REPORT] = false;//binary in report
        settings[VrenMapSettings.HEX_VALUE_IN_REPORT]    = true; //hex in report
        settings[VrenMapSettings.CLEAR_STORAGE_IN_RESET] = true; //reset the values stored
        settings[VrenMapSettings.CLEAR_SETTING_IN_RESET] = true; //reset the settings
    }
    public void enable(byte set){
        if(set < SETTINGS_COUNT) settings[set] = true;
        if(startUp.size() > set) startUp.get(set).run();
    }
    public void disable(byte set){
        if(set < SETTINGS_COUNT) settings[set] = false;
    }
    public void pluginsAdd(byte type, byte set, Runnable code){
        switch (type){
            case VrenMapPlugins.SETTING_ACTIVE_RUNNABLE_PLUGIN:{
                settingPluginsAdd(set, code);
            }break;
            default:{
                throw new RuntimeException("Plugin type not found");
            }
        }
    }
    public void settingPluginsAdd(byte set, Runnable code){
        if(code == null) throw new IllegalArgumentException("Null Runnable");
        plugin.get(set).add(code);
    }
    public int[] put(Object tag){
        return put(tag, null, null);
    }
    public int[] put(Object tag, Object[] value){
        return put(tag, null,  value);
    }
    public int[] put(Object tag, Object valueName, Object value){
        return put(tag, new Object[]{valueName}, new Object[]{value});
    }
    public int[] put(Object tag, Object[] valueName, Object[] value){
        if(tag == null)throw new IllegalArgumentException("Null tag");
        Tags temp = new Tags(this, tag, null, null);
        int[] tempHash = temp.add(this, valueName, value);
        tagStorage.put(tag, temp);
        return tempHash;
    }
    public int[] add(Object tag, Object[] valueName, Object[] value){
        return ((Tags)tagStorage.get(tag)).add(this, valueName, value);
    }
    public void setValue(Object tag, int hash, Object value){
        setValue(tag, hash, null, value);
    }
    public void setValue(Object tag, Object name, Object value){
        setValue(tag, 0, name, value);
    }
    public void setValue(Object tag, int hash, Object name, Object value){
        if(hash == 0 && name == null)throw new IllegalArgumentException("Null hash Null name, unable to find value");
        Tags temp = (Tags)tagStorage.get(tag);
        temp.Value.replace(Arrays.asList(name, hash), value);
    }
    public Object getValueOf(Object tag, String name){
        return getValueOf(tag, 0, name);
    }
    public Object getValueOf(Object tag, int hash){
        return getValueOf(tag, hash, null);
    }
    public Object getValueOf(Object tag, int hash, String name){
        if(hash == 0 && name == null) return getTag(tag);
        Tags tags = (Tags)tagStorage.get(tag);
        return (tags).Value.get(Arrays.asList(name, hash));
    }
    public String getTag(Object tag){
        return ((Tags)tagStorage.get(tag)).toString();
    }
    public String getTagReport(Object tag){
        if(tagStorage.get(tag) == null)return null;
        Map<List<Object>, Object> tagCopy = ((Tags)tagStorage.get(tag)).Value;
        if(tagCopy.isEmpty()) return null;
        List<Map.Entry<List<Object>, Object>> entries = new ArrayList<>(tagCopy.entrySet());
        StringBuilder sb = new StringBuilder();

        sb.append("====================Tag Report====================");
        sb.append(System.lineSeparator());
        for(int i = 0; i < tagCopy.size(); i++){
            Map.Entry<List<Object>, Object> entry = entries.get(i);
            List<Object> currentKey = entry.getKey();
            Object currentValue = entry.getValue();
            sb      .append("    Current Key Name ─► ").append(currentKey.getFirst())
                    .append(System.lineSeparator())
                    .append("    Current Key Hash ─► ").append(currentKey.get(1))
                    .append(System.lineSeparator())
                    .append("     Key in Hex ")
                    .append(System.lineSeparator())
                    .append("       │   └►").append(toHex(currentKey.getFirst()))
                    .append(System.lineSeparator())
                    .append("     Value")
                    .append(System.lineSeparator());
            List<String[]> render = new ArrayList<>();
            render.add(new String[]{"Value Class Name ─► " + getClassName(currentValue), null});
            if(settings[VrenMapSettings.BINARY_VALUE_IN_REPORT]) render.add(new String[]{"Value in Binary Code", Arrays.toString(toBinary(currentValue))});
            if(settings[VrenMapSettings.HEX_VALUE_IN_REPORT]) render.add(new String[]{"Value in Hex", toHex(currentValue)});
//            ([title], [content]) if [content] == null then its one line instead of two
//            every one more setting is one more if statement
            for(int l = 0; l < render.size(); l++){
                boolean isLast = l == render.size()-1;
                String connect = isLast ? indent + lineEnd + horizontal : indent + lineContinue + horizontal;
                String vertical = isLast ? indent + " " : indent + VrenMap.vertical;
                String title = render.get(l)[0];
                String content = render.get(l)[1];
                sb.append(connect).append(title).append(System.lineSeparator());
                sb.append(vertical).append(content == null ? "" : indent + endArrow + " " + content).append(System.lineSeparator());
            }
            sb.append(System.lineSeparator()).append(BLOB).append(System.lineSeparator());
        }
        sb.delete(sb.length() - (BLOB.length() + System.lineSeparator().length()*2), sb.length());
        return sb.toString();
    }
    public String getReport(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Title.size(); i++){
            sb.append(Title.get(i)).append(System.lineSeparator());
        }
        List<Map.Entry<Object, Object>> entries = new ArrayList<>(tagStorage.entrySet());
        for(int i = 0; i < tagStorage.size(); i++) {
            Map.Entry<Object, Object> entry = entries.get(i);
            Object currentKey = entry.getKey();
            sb.append(getTagReport(currentKey));
        }
        return sb.toString();
    }
    public int size(){
        return tagStorage.size() != length ? tagStorage.size() : length;
    }
    public int totalSize(){
        return lengthTotal;
    }
    public int tagSize(Object tag){
        return ((Tags)tagStorage.get(tag)).length;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<Object, Object>> entries = new ArrayList<>(tagStorage.entrySet());
        sb.append("{");
        for(int i = 0; i < tagStorage.size(); i++){
            Map.Entry<Object, Object> entry = entries.get(i);
            Object currentValue = entry.getValue();
            sb.append(((Tags)currentValue).toString()).append("-");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }
    @Override
    public int hashCode(){
        return Objects.hash(tagStorage, Arrays.hashCode(settings), lengthTotal);
    }
}
