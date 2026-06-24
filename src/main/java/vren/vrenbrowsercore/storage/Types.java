package vren.vrenbrowsercore.storage;

public interface Types{
    byte ILLEGAL = -127;
    byte STARTED = -2;
    byte NONE = -1;
    byte IS_CODE = 0;
    byte IS_TEXT = 1;
    byte IS_CODE_SPECIAL = 2;
    byte IS_CODE_VALUE = 3;
    byte IS_CODE_ENDING = 4;
    byte IS_TEXT_SPECIAL_INPUT = 5;
    byte IS_CODE_UNSURE = 6;
    byte IS_CODE_SPECIAL_NOTE = 7;
    byte IS_CODE_SPECIAL_VALUE = 8;
    byte[] CODE_STATUS = new byte[]{
            '<',
            '/',
            '>',
            '!',
    };
}
