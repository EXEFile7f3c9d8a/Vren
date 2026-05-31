package vren.VrenMap;

import vren.VrenDevTools.VrenDevTools;
import java.util.*;

import static vren.VrenDevTools.VrenDevTools.toBinaryCode;
import static vren.VrenDevTools.VrenDevTools.toHex;

public class VrenMap {
    public static final byte BINARY_IN_REPORT_TRUE = 0;
    public static final byte BINARY_IN_REPORT_FALSE = 1;
    public static final byte HEX_IN_REPORT_TRUE = 2;
    public static final byte HEX_IN_REPORT_FALSE = 3;
    private final HashMap<Object, Object> tagStorage = new HashMap<>();
    private static final class Tags {
        public HashMap<Object, Object> Value = new HashMap<>();
        public Object LockTag = null;
        public Tags(Object tag, Object[] valueName, Object[] value){
            if(tag == null)return;
            if(valueName == null && value != null){
                for(int i = 0; i < value.length; i++){
                    int temp;
                    if(value[i]!=null) temp = value[i].hashCode();
                    else temp = 0;
                    this.Value.put(new Object[]{null, temp}, value[i]);
                }
                this.LockTag = tag;
            }else if(value == null){
                this.LockTag = tag;
            }else{
                for(int i = 0; i < value.length; i++){
                    this.Value.put(new Object[]{valueName[i], value[i].hashCode()}, value[i]);
                }
                this.LockTag = tag;
            }
        }
        public void add(Object[] valueName, Object[] value){
            if(valueName == null && value != null){
                for(int i = 0; i < value.length; i++){
                    int temp;
                    if(value[i]!=null) temp = value[i].hashCode();
                    else temp = 0;
                    this.Value.put(new Object[]{null, temp}, value[i]);
                }
            }else if(value == null){
                return;
            }else{
                for(int i = 0; i < value.length; i++){
                    this.Value.put(new Object[]{valueName[i], value[i].hashCode()}, value[i]);
                }
            }
        }
    }
    public VrenMap(){}//not yet
    public void Setting(byte set){//not yet

    }
    public void addTag(Object tag){
        addTag(tag, null, null);
    }
    public void addTag(Object tag, Object[] value){
        addTag(tag, null,  value);
    }
    public void addTag(Object tag, Object[] valueName, Object[] value){
        Object temp = new Tags(tag, valueName, value);
        tagStorage.put(tag, temp);
    }
    public void addValue(Object tag, Object[] valueName, Object[] value){
        Tags tags = (Tags) tagStorage.get(tag);
        tags.add(valueName, value);
    }
    public void setValue(){//not yet
    }
    public Object getValueOf(Object tag){//not yet
        return getValueOf(tag, null);
    }
    public Object getValueOf(Object tag, String hash){//not yet
        return null;
    }
    public Object getTag(Object tag){//not yet
        return ((Tags)tagStorage.get(tag)).Value;
    }
    public Object getTagFormatted(Object tag){//not yet
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<2; i++){

        }
        return sb.toString();
    }
    public String getTagReport(Object tag){
        if(tagStorage.get(tag) == null)return null;
        Map<Object, Object> tagCopy = ((Tags)tagStorage.get(tag)).Value;
        if(tagCopy.isEmpty()) return null;
        List<Map.Entry<Object, Object>> entries = new ArrayList<>(tagCopy.entrySet());
        StringBuilder sb = new StringBuilder();

        sb.append("====================Tag Report====================");
        sb.append(System.lineSeparator());
        for(int i = 0; i < tagCopy.size(); i++){
            Map.Entry<Object, Object> entry = entries.get(i);
            Object[] currentKey = (Object[]) entry.getKey();
            Object currentValue = entry.getValue();
            sb      .append("    Current Key Name ─► ").append(currentKey[0])
                    .append(System.lineSeparator())
                    .append("    Current Key Hash ─► ").append(currentKey[1])
                    .append("     Key in Hex ")
                    .append(System.lineSeparator())
                    .append("       │   └►").append(toHex(currentKey[0]))
                    .append(System.lineSeparator())
                    .append("     Value")
                    .append(System.lineSeparator())
                    .append("       ├──Value Class Name ─► ").append(VrenDevTools.getClass(currentValue))
                    .append(System.lineSeparator())
                    .append("       │")
                    .append(System.lineSeparator())
                    .append("       ├──Value in Binary Code")
                    .append(System.lineSeparator())
                    .append("       │   └► ").append(Arrays.toString(toBinaryCode(currentValue)))
                    .append(System.lineSeparator())
                    .append("       │")
                    .append(System.lineSeparator())
                    .append("       └──Value in Hex")
                    .append(System.lineSeparator())
                    .append("           └► ").append(toHex(currentValue))
                    .append(System.lineSeparator())
                    .append("--------------------------------------------------")
                    .append(System.lineSeparator());
        }
        sb.delete(sb.length() - (50 + System.lineSeparator().length()), sb.length());
        return sb.toString();
    }
    public String getReport(){//not yet
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
    public String toString(){//not yet
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
    public int tagCount(){//not yet
        return tagStorage.size();
    }
    public int valueCount(){//not yet
        return tagStorage.size();
    }
    public int valueCount(Object tag){//not yet
        if(tag != null){
            try{
                return ((Tags) tagStorage.get(tag)).Value.size();
            }catch (NullPointerException e){
                return 0;
            }
        }else{
            return 0;
        }
    }
}

