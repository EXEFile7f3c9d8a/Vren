package vren.vrenbrowsercore;

import vren.vrenbrowsercore.storage.*;

import java.util.*;

import static vren.vrenbrowsercore.storage.Types.*;

public class HTML {
    private final String html;
    private Elements tree = new Elements();
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
        public String tempValueType = null;
        public StringBuilder currentSpecialText = new StringBuilder();
    }
    public Elements Solve(){
        WorkTab t = new WorkTab();
        t.elementStacks.add(this.tree);
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
                    break;
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
                    break;
                }
                case IS_CODE:{
                    switch(t.temp){
                        case '>':{
                            _Code_close(t);
                        }
                        case ' ':{
                            if(t.currentCode.isEmpty())break;
                            t.status = IS_CODE_VALUE;
                            t.tempElement.setType(t.currentCode.toString());
                            _Code_resetSB(t);
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
                    break;
                }
                case IS_CODE_VALUE:{
                    switch(t.temp){
                        case ' ', '\n':{
                            break;
                        }
                        case '=':{
                            _Code_emptyCodeValueCheck(t);
                            _Code_elseAlphabet(t.currentCodeValues.toString());
                            t.status = IS_CODE_VALUE_UNKNOW_STANDARD_OR_NOT;
                            t.tempValueType = t.currentCodeValues.toString();
                            t.currentCodeValues.setLength(0);
                            break;
                        }
                        case '>':{

                        }
                        default:{
                            t.currentCodeValues.append(t.temp);
                            break;
                        }
                    }
                    break;
                }
                case IS_CODE_VALUE_UNKNOW_STANDARD_OR_NOT:{
                    switch(t.temp){
                        case ' ', '\n':{
                            break;
                        }
                        case '>':{
                            throw new RuntimeException("Unexcepted closing statement");
                        }
                        case '"':{
                            t.status = IS_CODE_VALUE_STANDARD;
                            break;
                        }
                        default:{
                            t.status = IS_CODE_VALUE_NO_QUOTATION;
                            t.currentCodeValues.append(t.temp);
                            break;
                        }
                    }
                    break;
                }
                case IS_CODE_VALUE_STANDARD:{
                    switch(t.temp){
                        case '"':{
                            _Code_emptyCodeValueCheck(t);
                            if(Types.NO_CLOSING_ELEMENT().get(t.tempElement.get("type")) != null){
                                _Code_addValueToElement(t);
                            }else{

                            }
                            break;
                        }
                        default:{
                            t.currentCodeValues.append(t.temp);
                            break;
                        }
                    }
                    break;
                }
                case IS_CODE_VALUE_NO_QUOTATION:{
                    break;
                }
                case IS_CODE_SPECIAL:{
                    if(t.currentCode.toString().equals("--")){
                        t.status = IS_CODE_SPECIAL_NOTE;
                        break;
                    }
                    switch(t.temp){
                        case '>':{
                            _Code_emptyCodeCheck(t);
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
                    break;
                }
                case IS_CODE_ENDING:{
                    switch(t.temp){
                        case '>':{
                            _Code_emptyCodeCheck(t);
                            _Code_compareELEName(t);
                            _Code_resetSB(t);
                            Elements temp = t.elementStacks.pop();
                            t.elementStacks.element().putChild(temp);
                            if(t.elementStacks.size() == 1)t.status = NONE;
                            else t.status = IS_CODE_UNKNOW_NEW_OR_END;
                            break;
                        }
                        case ' ', '\n':{
                            break;
                        }
                        default:{
                            t.currentCode.append(t.temp);
                            break;
                        }
                    }
                    break;
                }
                case IS_TEXT:{
                    switch(t.temp){
                        case '<':{
                            t.elementStacks.element().setText(t.currentText.toString());
                            t.currentText.setLength(0);
                            t.status = IS_CODE_UNKNOW_NEW_OR_END;
                            break;
                        }
                        case '&':{
                            t.status = IS_TEXT_SPECIAL_INPUT;
                            break;
                        }
                        default:{
                            t.currentText.append(t.temp);
                            break;
                        }
                    }
                    break;
                }
                case IS_TEXT_SPECIAL_INPUT:{
                    switch(t.temp){
                        case ';':{
                            Character temp = TEXT_SPECIALS.get(t.currentSpecialText.toString());
                            if(temp == null)throw new RuntimeException("Character not found");
                            t.currentText.append(temp);
                            t.currentSpecialText.setLength(0);
                            t.status = IS_TEXT;
                            break;
                        }
                        default:{
                            t.currentSpecialText.append(t.temp);
                        }
                    }
                    break;
                }
                case IS_CODE_UNKNOW_NEW_OR_END:{
                    switch(t.temp){
                        case '/':{
                            t.status = IS_CODE_ENDING;
                            break;
                        }
                        case ' ', '\n':{
                            break;
                        }
                        default:{
                            t.status = IS_CODE;
                            break;
                        }
                    }
                    break;
                }
                default:{
                    throw new RuntimeException("looks like ma code have bug manm");
                }
            }
        }
        if(t.elementStacks.size() != 1)throw new RuntimeException("looks like ma code have bug manm");
        else this.tree = t.elementStacks.element();
        return this.tree;
    }
    public Elements getTree(){
        return this.tree;
    }
    private void _Code_emptyCodeCheck(WorkTab t){
        if(t.currentCode.isEmpty())throw new RuntimeException("Illegal HTML: EMPTY_CODE_FEILD");
    }
    private void _Code_emptyCodeValueCheck(WorkTab t){
        if(t.currentCodeValues.isEmpty())throw new RuntimeException("Illegal HTML: EMPTY_CODE_FEILD");
    }
    private void _Code_compareELEName(WorkTab t){
        if(!(Objects.equals(t.elementStacks.element().getType(), t.currentCode.toString())))throw new RuntimeException("Illegal HTML: ELEMENT_NAME_NOT_MATCH");
    }
    private void _Code_resetSB(WorkTab t){
        t.currentCode.setLength(0);
        t.currentCodeValues.setLength(0);
    }
    private void _Code_elseAlphabet(String str){
        if(!containsElseThanAlphabet(str))throw new RuntimeException("contains else than alphabet");
    }
    private boolean containsElseThanAlphabet(String str){
        for(int i = 0; i < str.length(); i++){
            int temp = str.indexOf(i);
            if(!((temp >= 'a' && temp <= 'z')||(temp >= 'A' && temp <= 'Z')))return false;
        }
        return true;
    }
    private void _Code_close(WorkTab t){
        _Code_emptyCodeCheck(t);
        if(Types.NO_CLOSING_ELEMENT().get(t.currentCode.toString()) != null){
            t.status = NONE;
            t.tempElement.setType(t.currentCode.toString());
            t.elementStacks.element().putChild(t.tempElement);
        }else{
            t.status = IS_TEXT;
            t.tempElement.setType(t.currentCode.toString());
            t.elementStacks.add(t.tempElement);
        }
        t.currentCode.setLength(0);
    }
    private void _Code_addValueToElement(WorkTab t){
        t.elementStacks.element().set(t.tempValueType, t.currentCodeValues.toString());
        t.tempElement = new Elements();
        t.currentCodeValues.setLength(0);
        t.status = IS_CODE_VALUE;
    }
    private void _Code_closeElementNoClosingStatement(WorkTab t){
        t.status = IS_CODE_VALUE;
        t.tempElement.setType(t.currentCode.toString());
        t.tempElement.set(t.tempValueType, t.currentCodeValues.toString());
        t.elementStacks.element().putChild(t.tempElement);
        t.tempElement = new Elements();
        t.tempValueType = null;
        _Code_resetSB(t);
    }
}
