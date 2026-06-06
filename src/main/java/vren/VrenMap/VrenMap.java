package vren.VrenMap;

import java.util.*;
import java.util.function.Consumer;

import static vren.VrenDevTools.VrenDevTools.*;

public class VrenMap {
    private static final List<String> Title = new ArrayList<>(); //HAHAHAHAAAAAAAAAA
    private static final String BLOB = "--------------------------------------------------";
    private static final String indent = "       ";
    private static final String lineContinue = "├";
    private static final String lineEnd = "└";
    private static final String vertical = "│";
    private static final String horizontal = "──";
    private static final String endArrow = "└►";

    public static final byte BINARY_VALUE_IN_REPORT = 0;
    public static final byte HEX_VALUE_IN_REPORT = 1;

    public static final short settingsCount = 2;
    private final boolean[] settings = new boolean[settingsCount];
    private static final List<Consumer<Byte>> startUp = new ArrayList<>();
    private final HashMap<Object, Object> tagStorage = new HashMap<>();
    public int length = 0;
    public int lengthTotal = 0;
    private static final class Tags{
//      Format: HashMap<List(valueName, valueHash), value>
        public int length = 0;
        public HashMap<List<Object>, Object> Value = new HashMap<>();
        public Object LockTag = null;
        public Tags(VrenMap clas, Object tag, Object[] valueName, Object[] value){
            if(tag != null)this.LockTag = tag;
            add(clas, valueName, value);
        }
        public void add(VrenMap clas, Object[] valueName, Object[] value){
            if(valueName == null && value != null){
                for(int i = 0; i < value.length; i++){
                    int temp = value[i] != null ? Objects.hash(value[i]) : 0;
                    this.Value.put(Arrays.asList(null, temp), value[i]);
                    clas.lengthTotal++;
                    this.length++;
                }
            }else if(value == null){
                clas.length++;
                return;
            }else{
                for(int i = 0; i < value.length; i++){
                    this.Value.put(Arrays.asList(valueName[i], Objects.hash(value[i])), value[i]);
                    clas.lengthTotal++;
                    this.length++;
                }
            }
            clas.length++;
        }
//        Format: [tagClassName,tagToHex]|[valueName,valueHash,valueToHex]|...
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(getClassName(LockTag)).append(",").append(toHex(LockTag)).append("]|");
            List<Map.Entry<List<Object>, Object>> entries = new ArrayList<>(Value.entrySet());
            for(int i = 0; i < this.length; i++){
                Map.Entry<List<Object>, Object> entry = entries.get(i);
                List<Object> currentKey = entry.getKey();
                Object currentValue = entry.getValue();
                sb.append("[").append(currentKey.getFirst()).append(currentKey.get(1)).append(toHex(currentValue)).append("]|");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }
    public VrenMap(){
        settings[0] = false;//binary in report
        settings[1] = true; //hex in report
        Title.addAll(List.of(
                "      .-----------------.      "   ,
                "     /                   \\     "  ,
                "    /                     \\    " ,
                "   |\\--[___]---------[___]/|   "   ,
                "   |                       |   "  ,
                "   |                       |   "   ,
                "   |          _____        |   "   ,
                "    \\        \\____/       /    " ,
                "     \\___________________/     "
        ));
        startUp.add(s -> {
            //this is supposed to be extra binary setting execution
        });
    }
    public void enable(byte set){
        if(set < settingsCount) settings[set] = true;
        if(startUp.size() - 1 > set) startUp.get(set).accept(set);
    }
    public void disable(byte set){
        if(set < settingsCount)settings[set] = false;
    }
    public void addTag(Object tag){
        addTag(tag, null, null);
    }
    public void addTag(Object tag, Object[] value){
        addTag(tag, null,  value);
    }
    public void addTag(Object tag, Object[] valueName, Object[] value){
        Object temp = new Tags(this, tag, valueName, value);
        tagStorage.put(tag, temp);
    }
    public void addValue(Object tag, Object[] valueName, Object[] value){
        Tags tags = (Tags) tagStorage.get(tag);
        tags.add(this, valueName, value);
    }
    public void setValue(){//not yet

    }
    public Object getValueOf(Object tag, String name){//not yet
        return null;
    }
    public Object getTag(Object tag){
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
            if(settings[BINARY_VALUE_IN_REPORT]) render.add(new String[]{"Value in Binary Code", Arrays.toString(toBinary(currentValue))});
            if(settings[HEX_VALUE_IN_REPORT]) render.add(new String[]{"Value in Hex", toHex(currentValue)});
//            ([title], [content]) if [content] == null then its one line instead of two
//            every one more setting is one more if statement
            for(int l = 0; l < render.size(); l++){
                boolean isLast = l == render.size()-1;
                String connect = isLast ? indent + lineEnd + horizontal : indent + lineContinue + horizontal;
                String vertical = isLast ? indent : indent + lineContinue;
                String title = render.get(l)[0];
                String content = render.get(l)[1];
                sb.append(connect).append(title).append(System.lineSeparator());
                sb.append(vertical).append(content == null ? "" : "   " + endArrow + " " + content).append(System.lineSeparator());
            }
            sb.append(System.lineSeparator()).append(BLOB).append(System.lineSeparator());
        }
        sb.delete(sb.length() - (BLOB.length() + System.lineSeparator().length()), sb.length());
        return sb.toString();
    }
    public String getReport(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Title.size(); i++){
            sb.append(Title.get(i)).append(System.lineSeparator());
        }
        List<Map.Entry<Object, Object>> entries = new ArrayList<>(tagStorage.entrySet());
        for(int i = 0; i < this.length; i++) {
            Map.Entry<Object, Object> entry = entries.get(i);
            Object currentKey = entry.getKey();
            sb.append(getTagReport(currentKey));
        }
        return sb.toString();
    }
    public int size(){
        return length;
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
}

