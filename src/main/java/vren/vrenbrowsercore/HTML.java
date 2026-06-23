package vren.vrenbrowsercore;

import vren.vrenbrowsercore.storage.Elements;

import java.util.*;

import static vren.vrenbrowsercore.storage.Types.*;

public class HTML {
    private final String html;
    private final Deque<Elements> stack = new ArrayDeque<>();
    private final Elements tree = new Elements();

    public HTML(String html){
        this.html = html;
    }
    public HTML Solve() {
        byte status = NONE;
        char temp;
        Deque<Elements> elementStacks = new ArrayDeque<>();
        Elements tempElement = new Elements();
        StringBuilder currentText = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        mainLoop:
        for(int i = 0; i < html.length(); i++){
            temp = html.charAt(i);
            switch(status){
                case NONE:{
                    switch (temp){
                        case '<':{
                            status = IS_CODE_UNSURE;
                            continue mainLoop;
                        }
                        default:{

                            break;
                        }
                    }
                    break;
                }
                case IS_CODE_UNSURE:{
                    switch(temp){
                        case '!':{
                            status = IS_CODE_SPECIAL;
                            continue mainLoop;
                        }
                        default:{
                            status = IS_CODE;
                            currentCode.append(temp);
                            break;
                        }
                    }
                    break;
                }
                case IS_CODE:{
                    switch(temp){
                        case '>':{
                            if(currentCode.isEmpty()){
                                status = NONE;
                                break;
                            }
                            tempElement.setType(currentCode.toString());
                            currentCode.setLength(0);
                            elementStacks.add(tempElement);
                            break;
                        }
                        default:{
                            currentCode.append(temp);
                            break;
                        }
                    }
                    break;
                }
                case IS_CODE_SPECIAL:{
                    if(currentCode.toString().equals("--")){
                        status = IS_CODE_SPECIAL_NOTE;
                        break;
                    }else{
                        currentCode.append(temp);
                    }
                    break;
                }
                default:{
                    throw new RuntimeException("looks like ma code have bug manm");
                }
            }
        }
        return this;
    }
}
