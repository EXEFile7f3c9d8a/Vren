let firstLine = [
    "Functions",
    "Has output?",
    "Format of output",
    "Output class type",
    "Has input?",
    "Format of input",
    "Input class type",
    "Function it calls",
    "More info"
];
let functions = [
    [
        "VrenMap()",
        "No", "No output", "No output",
        "No", "No input", "No input",
        "resetSetting()",
        "This function is a constructor function."
    ],
    [
        "reset()",
        "No", "No output", "No output",
        "No", "No input", "No input",
        "resetSetting()",
        "This function works as well as the clear() function in HashMap, " +
        "which deleted everything, however you can change that little bit by enable() and disable()."
    ],
    [
        "resetSetting()",
        "No", "No output", "No output",
        "No", "No input", "No input",
        "None",
        "This function resets all settings."
    ],
    [
        "enable()",
        "No", "No output", "No output",
        "Yes", "(set)", "VrenMapSettings",
        "None",
        "This function enables setting by VrenMapSettings."
    ],
    [

        "disable()",
        "No", "No output", "No output",
        "Yes", "(set)", "VrenMapSettings",
        "None",
        "This function disables setting by VrenMapSettings."
    ],
    [

        "pluginsAdd()",
        "No", "No output", "No output",
        "Yes", "type, set, code", "VrenMapPlugins<T>, T, Runnable",
        "None",
        "This function add a plugin that automatically executed depends on the plugin."
    ],
    [
        "pluginsAdd()",
        "No", "No output", "No output",
        "Yes", "set, code", "VrenMapSettings, Runnable",
        "None",
        "This function add a plugin that automatically executed when enable a setting."
    ],
    [
        "put()",
        "No", "No output", "No output",
        "Yes", "(tag)", "java.lang.Object",
        "put()",
        "This function is putting a tag with no values in it."
    ],
    [
        "put()",
        "Yes", "value.hashCode()", "raw int",
        "Yes", "(tag, valueName, value)", "java.lang.Object*3",
        "put()",
        "This function is putting a tag, one value and one value name."
    ],
    [
        "put()",
        "Yes", "{value.hashCode()...}", "raw int[]",
        "Yes", "(tag, valueName[], value[])", "java.lang.Object, java.lang.Object[]*2",
        "new Tags()",
        "This function is putting a tag, multiple values and multiple value names. Checks if things are null and throw error."
    ],
    [
        "add()",
        "Yes", "value.hashCode()", "raw int",
        "Yes", "(tag, valueName, value)", "java.lang.Object*3",
        "add()",
        "This function finds the storage of the tag, putting one value and one value name."
    ],
    [
        "add()",
        "Yes", "{value.hashCode()...}", "raw int[]",
        "Yes", "(tag, valueName[], value[])", "java.lang.Object, java.lang.Object[]*2",
        "Tags.add()",
        "This function finds the storage of the tag, putting multiple values and multiple value names. Checks if things are null and throw error."
    ],
    [
        "setValue()",
        "No", "No output", "No output",
        "Yes", "(tag, name, value)", "java.lang.Object*3",
        "setValue()",
        "This function replaces the old value"
    ],
    [
        "getValueOf()",
        "Yes", "value", "java.lang.Object",
        "Yes", "(tag)", "java.lang.Object",
        "getValueOf()",
        "This function returns all the values of the key"
    ],
    [
        "getValueOf()",
        "Yes", "value", "java.lang.Object",
        "Yes", "(tag, name)", "java.lang.Object*2",
        "None",
        "This function returns the value of the key"
    ],
    [
        "getTag()",
        "Yes", "(<tagClass,tagHex>|[name,hash,hex]|...)", "java.lang.String",
        "Yes", "(tag)", "java.lang.Object",
        "Tags.toString",
        "This function returns toString of the tag"
    ],
    [
        "getTagReport()",
        "Yes", "#See info", "java.lang.String",
        "Yes", "(tag)", "java.lang.Object",
        "None",
        "This function returns String of the tag, includes the branch-like format" +
        ", it's not for the code to cut it but for people, it's a report. Settings included that can enable and disable to control how return value looks."
    ],
    [
        "getReport()",
        "Yes", "#See info", "java.lang.String",
        "No", "No input", "No input",
        "getTagReport()",
        "This function returns multiple tag reports that had combined, WITH A BIG COOL FACE HAHAHAAA." +
        "Settings included that can enable and disable to control how return value looks."
    ],
    [
        "size()",
        "Yes", "(size)", "raw int",
        "No", "No input", "No input",
        "None",
        "This function returns how much tag is in it."
    ],
    [
        "totalSize()",
        "Yes", "(size)", "raw int",
        "No", "No input", "No input",
        "None",
        "This function returns how much values is inside every tag."
    ],
    [
        "tagSize()",
        "Yes", "(size)", "raw int",
        "Yes", "(tag)", "java.lang.Object",
        "None",
        "This function returns how much small values is in the tag."
    ],
    [
        "toString()",
        "Yes", "({<tagClass,tagHex>|[name,hash,hex]|... ...})", "java.lang.String",
        "No", "No input", "No input",
        "Tags.toString()",
        "This function returns what the whole thing is like. Appending toString() in Tags one by one."
    ],
    [
        "hashCode()",
        "Yes", "(hashCode)", "raw int",
        "No", "No input", "No input",
        "None",
        "This function returns Object.hash((tagStorage, (SETTINGS.hashCode()+PLUGIN.hashcode), this.totalSize())). " +
        "Settings and plugin in calculate can be turned on and off by settings"
    ],
    [
        "equals()",
        "Yes", "(true/false)", "raw boolean",
        "Yes", "vm", "java.lang.Object",
        "None",
        "This function compares \"vm\" and \"this\". " +
        "include null check, class identify, settings and plugin compare, and finally tagStorage which where values store at."
    ],
];
import {run, putFunctions}from '../../../../res/scripts/table.js'
putFunctions(functions, document, firstLine);
run();