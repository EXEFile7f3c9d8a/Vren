package vren.vrenbrowsercore;

import vren.vrenbrowsercore.storage.*;

import java.util.*;

import static vren.vrenbrowsercore.storage.Types.*;

public class HTML {
    private final String html;
    private final Deque<Elements> stack = new ArrayDeque<>();
    private final Elements tree = new Elements();
    public HTML(String html){
        this.html = html;
    }
    private static final class WorkTab{
        public byte status = NONE;
        public char temp;
        public Deque<Elements> elementStacks = new ArrayDeque<>();
        public Elements tempElement = new Elements();
        public StringBuilder currentText = new StringBuilder();
        public StringBuilder currentCode = new StringBuilder();
        public StringBuilder currentCodeValues = new StringBuilder();
    }
    public HTML Solve(){
        WorkTab t = new WorkTab();
        mainLoop:
        for(int i = 0; i < html.length(); i++){
            t.temp = html.charAt(i);
            switch(t.status){
                case NONE:{
                    switch (t.temp){
                        case '<':{
                            t.status = IS_CODE_UNSURE;
                            break;
                        }
                        case ' ', '\n':{
                            break;
                        }
                        default:{
                            t.status = ILLEGAL;
                            throw new RuntimeException("Illegal HTML");
                        }
                    }
                    continue mainLoop;
                }
                case IS_CODE_UNSURE:{
                    switch(t.temp){
                        case '!':{
                            t.status = IS_CODE_SPECIAL;
                            break;
                        }
                        case '/':{
                            t.status = IS_CODE_ENDING;
                            break;
                        }
                        case ' ', '\n':{
                            break;
                        }
                        default:{
                            t.status = IS_CODE;
                            t.currentCode.append(t.temp);
                            break;
                        }
                    }
                    continue mainLoop;
                }
                case IS_CODE:{
                    switch(t.temp){
                        case '>':{
                            t.status = IS_TEXT;
                            _isCode_emptyCodeCheck(t);
                            t.tempElement.setType(t.currentCode.toString());
                            t.currentCode.setLength(0);
                            t.elementStacks.add(t.tempElement);
                            break;
                        }
                        case ' ':{
                            if(t.currentCode.isEmpty())break;
                            t.status = IS_CODE_VALUE;
                            t.tempElement.setType(t.currentCode.toString());
                            break;
                        }
                        case '\n':{
                            break;
                        }
                        default:{
                            t.currentCode.append(t.temp);
                            break;
                        }
                    }
                    continue mainLoop;
                }
                case IS_CODE_SPECIAL:{
                    if(t.currentCode.toString().equals("--")){
                        t.status = IS_CODE_SPECIAL_NOTE;
                        break;
                    }
                    switch(t.temp){
                        case '>':{
                            _isCode_emptyCodeCheck(t);
                            t.status = NONE;
                            t.tempElement.setSpecial(true);
                            t.tempElement.setType(t.currentCode.toString());
                            t.currentCode.setLength(0);
                            this.tree.putChild(t.tempElement);
                            t.tempElement = new Elements();
                            break;
                        }
                        case ' ':{
                            t.status = IS_CODE_SPECIAL_VALUE;
                            break;
                        }
                        case '\n':{
                            break;
                        }
                        default:{
                            t.currentCode.append(t.temp);
                            break;
                        }
                    }
                    continue mainLoop;
                }
                case IS_CODE_ENDING:{
                    switch(t.temp){
                        case '>':{
                            _isCode_emptyCodeCheck(t);
                            _isCode_compareELEName(t);
                            
                            break;
                        }
                    }
                    continue mainLoop;
                }
                case IS_TEXT:{
                    switch(t.temp){
                        case '<':{
                            t.elementStacks.element().setText(t.currentText.toString());
                            t.currentText.setLength(0);
                            break;
                        }
                        default:{
                            t.currentText.append(t.temp);
                            break;
                        }
                    }
                    continue mainLoop;
                }
                default:{
                    throw new RuntimeException("looks like ma code have bug manm");
                }
            }
        }
        return this;
    }
    private void _isText_end(WorkTab t){
        t.elementStacks.element().setText(t.currentText.toString());
    }
    private void _isCode_emptyCodeCheck(WorkTab t){
        if(t.currentCode.isEmpty()){
            t.status = ILLEGAL;
            throw new RuntimeException("Illegal HTML: EMPTY_CODE_FEILD");
        }
    }
    private void _isCode_compareELEName(WorkTab t){
        if(!(Objects.equals(t.elementStacks.element().getType(), t.currentCode.toString()))){
            t.status = ILLEGAL;
            throw new RuntimeException("Illegal HTML: ELEMENT_NAME_NOT_MATCH");
        }
    }
}
