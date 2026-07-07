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

    private static final byte BINARY_VALUE_IN_REPORT = 0;
    private static final byte HEX_VALUE_IN_REPORT    = 1;
    private static final byte CLEAR_STORAGE_IN_RESET = 2;
    private static final byte CLEAR_SETTING_IN_RESET = 3;
    private static final byte COMPARE_SETTINGS_IN_EQUALS = 4;
    private static final byte COMPARE_PLUGINS_IN_EQUALS  = 5;
    private static final byte INCLUDE_SETTINGS_IN_HASHCODE = 6;
    private static final byte INCLUDE_PLUGINS_IN_HASHCODE  = 7;
    public static final byte SETTINGS_COUNT = 8;
    private final boolean[] settings = new boolean[SETTINGS_COUNT];

    private static final byte SETTING_ACTIVE_RUNNABLE_PLUGIN = 0;
    public static final byte PLUGIN_COUNT = 1;
//    Structure/Format: plugin<settings active plugin<plugins<plugin, pluginEX>>,...
    private final List<List<plugins>> plugin = new ArrayList<>();
    private final HashMap<Object, Tags> tagStorage = new HashMap<>();
    private static final class Values{
        public Object value;
        public int hash;
        public Values setValue(Object value){
            this.value = value; return this;
        }
        public Values setHash(int hash){
            this.hash = hash; return this;
        }
    }
    private static final class Tags{
//      Format: HashMap<OBJValueName, Values<OBJValueHash, OBJValue>>
        public HashMap<Object, Values> Value = new HashMap<>();
        public Object LockTag = null;
        public Tags(Object tag){
            this.LockTag = tag;
        }
        public int[] add(Object[] valueName, Object[] value){
            int[] tempHash;
            if(value == null){
                return new int[]{0};
            }else{
                tempHash = new int[value.length];
                for(int i = 0; i < value.length; i++){
                    int temp = Objects.hash(value[i]);
                    Values v = new Values();
                    v.hash = temp;
                    v.value = value[i];
                    this.Value.put(valueName[i], v);
                    tempHash[i] = temp;
                }
            }
            return tempHash;
        }
//        Format: <tagClassName,tagToHex>|[valueName,valueHash,valueToHex]|...
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("<").append(getClassName(LockTag)).append(",").append(toHex(LockTag)).append(">|");
            List<Map.Entry<Object, Values>> entries = new ArrayList<>(Value.entrySet());
            for(int i = 0; i < Value.size(); i++){
                Map.Entry<Object, Values> entry = entries.get(i);
                Object currentKey = entry.getKey();
                Values currentValue = entry.getValue();
                sb.append("[").append(currentKey).append(",").append(currentValue.hash).append(",").append(toHex(currentValue.value)).append("]|");
            }
            sb.deleteCharAt(!sb.isEmpty() && sb.charAt(sb.length()-1) == '|' ? sb.length() - 1 : sb.length());
            return sb.toString();
        }
    }
    private static final class plugins{
        public List<Runnable> plugin = new ArrayList<>();
        public List<Runnable> pluginEX = new ArrayList<>();
        public plugins(){//not yet
        }
        public plugins(Runnable code){
            add(code);
        }
        public void add(Runnable code){
            add(code, null);
        }
        public void add(Runnable code, Runnable exWay){
            plugin.add(code);
            if(exWay == null){
                pluginEX.add(() -> {
                    for(int i = 0; i < this.plugin.size(); i++){
                        this.plugin.get(i).run();
                    }
                });
            }else pluginEX.add(exWay);
        }
        public void run(){
            for(int i = 0; i < pluginEX.size(); i++){
                pluginEX.get(i).run();
            }
        }
    }
    public VrenMap(){
        resetSetting();
        {
            for(int i = 0; i < PLUGIN_COUNT; i++){
                plugin.add(new ArrayList<>());
            }
            for(int i = 0; i < SETTINGS_COUNT; i++){
                plugin.get(VrenMap.SETTING_ACTIVE_RUNNABLE_PLUGIN).add(new plugins());
            }
        }
        Title.addAll(List.of(
                "      .-----------------.      "   ,
                "     /                   \\     "  ,
                "    /                     \\    " ,
                "   |\\--[___]---------[___]/|   "   ,
                "   |                       |   "  ,
                "   |                       |   "   ,
                "   |                       |   "   ,
                "    \\        \\____/       /    " ,
                "     \\___________________/     "
        ));
    }
    public void reset(){
        List<Runnable> temp = new ArrayList<>();
        if(settings[VrenMapSettings.CLEAR_STORAGE_IN_RESET.getValue()]){
            temp.add(tagStorage::clear);
        }
        if(settings[VrenMapSettings.CLEAR_SETTING_IN_RESET.getValue()]){
            temp.add(this::resetSetting);
        }
        for(int i = 0; i < temp.size(); i++){
            temp.get(i).run();
        }
    }
    public void resetSetting(){
        settings[VrenMap.BINARY_VALUE_IN_REPORT] = false;//binary in report
        settings[VrenMap.HEX_VALUE_IN_REPORT]    = true; //hex in report
        settings[VrenMap.CLEAR_STORAGE_IN_RESET] = true; //reset the values stored
        settings[VrenMap.CLEAR_SETTING_IN_RESET] = true; //reset the settings
        settings[VrenMap.COMPARE_SETTINGS_IN_EQUALS] = false;
        settings[VrenMap.COMPARE_PLUGINS_IN_EQUALS]  = false;
        settings[VrenMap.INCLUDE_SETTINGS_IN_HASHCODE] = false;
        settings[VrenMap.INCLUDE_PLUGINS_IN_HASHCODE]  = false;

    }
    public void enable(VrenMapSettings set){
        byte temp = set.getValue();
        if(temp < SETTINGS_COUNT) settings[temp] = true;
        if(plugin.get(VrenMap.SETTING_ACTIVE_RUNNABLE_PLUGIN).size() > temp)plugin.get(VrenMap.SETTING_ACTIVE_RUNNABLE_PLUGIN).get(temp).run();
    }
    public void disable(VrenMapSettings set){
        byte temp = set.getValue();
        if(temp < SETTINGS_COUNT) settings[temp] = false;
    }
    public <T> void pluginsAdd(VrenMapPlugins<T> type, T set, Runnable code){
        byte temp = type.getValue();
        switch (temp){
            case VrenMap.SETTING_ACTIVE_RUNNABLE_PLUGIN: {
                pluginsAdd((VrenMapSettings) set, code);
                break;
            }
            default:{
                throw new RuntimeException("Plugin type not found");
            }
        }
    }
    public void pluginsAdd(VrenMapSettings set, Runnable code){
        if(code == null) throw new IllegalArgumentException("Null Runnable");
        plugin.get(VrenMap.SETTING_ACTIVE_RUNNABLE_PLUGIN).get(set.getValue()).add(code);
    }
    public void put(Object tag){
        tagStorage.put(tag, new Tags(tag));
    }
    public int put(Object tag, Object valueName, Object value){
        return put(tag, new Object[]{valueName}, new Object[]{value})[0];
    }
    public int[] put(Object tag, Object[] valueName, Object[] value){
        if(tag == null)throw new IllegalArgumentException("Null tag");
        if(valueName == null)throw new IllegalArgumentException("Null name");
        Tags temp = new Tags(tag);
        tagStorage.put(tag, temp);
        return temp.add(valueName, value);
    }
    public int add(Object tag, Object valueName, Object value){
        return add(tag, new Object[]{valueName}, new Object[]{value})[0];
    }
    public int[] add(Object tag, Object[] valueName, Object[] value){
        if(tag == null)throw new IllegalArgumentException("Null tag");
        if(valueName == null)throw new IllegalArgumentException("Null name");
        try{return tagStorage.get(tag).add(valueName, value);
        }catch (NullPointerException e){throw new NullPointerException("Tag not found");}
    }
    public void setValue(Object tag, Object name, Object value){
        if(name == null)throw new IllegalArgumentException("Null name");
        tagStorage.get(tag).Value.replace(name, new Values().setValue(value).setHash(Objects.hash(value)));
    }
    public Object getValueOf(Object tag){
        return getValueOf(tag, null);
    }
    public Object getValueOf(Object tag, String name){
        if(name == null) return getTag(tag);
        Tags tags = tagStorage.get(tag);
        if(tags == null)return null;
        return tags.Value.get(name).value;
    }
    public String getTag(Object tag){
        return tagStorage.get(tag).toString();
    }
    public String getTagReport(Object tag){
        if(tag == null)throw new NullPointerException("Tag is null");
        if(tagStorage.get(tag) == null)return null;
        Map<Object, Values> tagCopy = tagStorage.get(tag).Value;
        if(tagCopy.isEmpty()) return null;
        List<Map.Entry<Object, Values>> entries = new ArrayList<>(tagCopy.entrySet());
        StringBuilder sb = new StringBuilder();

        sb.append("====================Tag Report====================");
        sb.append(System.lineSeparator());
        for(int i = 0; i < tagCopy.size(); i++){
            Map.Entry<Object, Values> entry = entries.get(i);
            Object currentKey = entry.getKey();
            Values currentValue = entry.getValue();
            sb      .append("    Current Key Name ─► ").append(currentKey)
                    .append(System.lineSeparator())
                    .append("    Current Key Hash ─► ").append(currentValue.hash)
                    .append(System.lineSeparator())
                    .append("     Key in Hex ")
                    .append(System.lineSeparator())
                    .append("       │   └►").append(toHex(currentKey))
                    .append(System.lineSeparator())
                    .append("     Value")
                    .append(System.lineSeparator());
            List<String[]> render = new ArrayList<>();
            render.add(new String[]{"Value Class Name ─► " + getClassName(currentValue.value), null});
            if(settings[VrenMapSettings.BINARY_VALUE_IN_REPORT.getValue()])
                render.add(new String[]{"Value in Binary Code", Arrays.toString(toBinary(currentValue.value))});
            if(settings[VrenMapSettings.HEX_VALUE_IN_REPORT.getValue()])
                render.add(new String[]{"Value in Hex", toHex(currentValue.value)});
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
        List<Map.Entry<Object, Tags>> entries = new ArrayList<>(tagStorage.entrySet());
        if(!tagStorage.isEmpty()){
            for(int o = 0; o < Title.size(); o++){
                sb.append(Title.get(o)).append(System.lineSeparator());
            }
        }else return null;
        for(int i = 0; i < tagStorage.size(); i++) {
            Map.Entry<Object, Tags> entry = entries.get(i);
            Object currentKey = entry.getKey();
            sb.append(getTagReport(currentKey));
        }
        return sb.toString();
    }
    public int size(){
        return tagStorage.size();
    }
    public int totalSize(){
        int total = 0;
        List<Map.Entry<Object, Tags>> entries = new ArrayList<>(tagStorage.entrySet());
        for(int i = 0; i < tagStorage.size(); i++){
            total += entries.get(i).getValue().Value.size();
        }
        return total;
    }
    public int tagSize(Object tag){
        return tagStorage.get(tag).Value.size();
    }
    @Override
    public String toString(){
        if(this.tagStorage.isEmpty())return null;
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<Object, Tags>> entries = new ArrayList<>(tagStorage.entrySet());
        sb.append("{");
        for(int i = 0; i < tagStorage.size(); i++){
            Map.Entry<Object, Tags> entry = entries.get(i);
            Tags currentValue = entry.getValue();
            sb.append(currentValue.toString()).append("-");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }
    @Override
    public int hashCode(){
        int temp = 0;
        if(this.settings[INCLUDE_SETTINGS_IN_HASHCODE])temp += Arrays.hashCode(this.settings);
        if(this.settings[INCLUDE_PLUGINS_IN_HASHCODE]) temp += plugin.hashCode();
        return Objects.hash(tagStorage, temp, this.totalSize());
    }
    //return null;
    @Override
    public boolean equals(Object vm){
        if(this == vm)return true;
        if(vm == null)return false;
        if(!vm.getClass().getName().equals(this.getClass().getName()))return false;
        if(this.settings[COMPARE_SETTINGS_IN_EQUALS]&&!Arrays.equals(((VrenMap)vm).settings, this.settings))return false;
        if(this.settings[COMPARE_PLUGINS_IN_EQUALS]&&((VrenMap)vm).plugin != this.plugin)return false;
        return Objects.equals(((VrenMap)vm).tagStorage, this.tagStorage);
    }
}
