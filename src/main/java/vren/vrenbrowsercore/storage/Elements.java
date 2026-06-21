package vren.vrenbrowsercore.storage;

import java.util.ArrayList;
import java.util.List;

public class Elements{
    private String type;
    private String href;
    private final List<Elements> childs = new ArrayList<>();
    public Elements(){

    }
    public Elements(Types type){
        this.type = type.getValue();
    }
    public Elements setHref(String href){
        this.href = href;
        return this;
    }
    public String getHref(){
        return this.href;
    }
    public Elements setType(String type){
        this.type = type;
        return this;
    }
    public String getType(){
        return this.type;
    }
    public Elements putChild(Elements ele){
        childs.add(ele);
        return this;
    }
    public List<Elements> getChilds() {
        return childs;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(type).append(href);
        for(int i = 0; i < childs.size(); i++){
            sb.append(childs.get(i).toString());
        }
        sb.append(">");
        return sb.toString();
    }
}
