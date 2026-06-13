
let classes = [
    "Functions",
    "HasOutput",
    "OutputFormat",
    "OutputType",
    "HasInput",
    "InputFormat",
    "InputType",
    "FunctionCalls",
    "Info"
];
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
let VrenMap = [
    "VrenMap()",
    "No", "No output", "No output",
    "No", "No input", "No input",
    "resetSetting()",
    "This function is a constructor function."
];
let reset = [
    "reset()",
    "No", "No output", "No output",
    "No", "No input", "No input",
    "resetSetting()",
    "This function works as well as the clear() function in HashMap, which deleted everything, however you can change that little bit by enable() and disable()."
];
let resetSetting = [
    "resetSetting()",
    "No", "No output", "No output",
    "No", "No input", "No input",
    "None",
    "This function resets all settings."
];
let enable = [
    "enable()",
    "No", "No output", "No output",
    "Yes", "(set)", "raw byte",
    "None",
    "This function enables setting by byte numbers, there is static variable choices."
];
let disable = [
    "disable()",
    "No", "No output", "No output",
    "Yes", "(set)", "raw byte",
    "None",
    "This function disables setting by byte numbers, there is static variable choices."
];
let put0 = [
    "put()",
    "Yes", "{0}", "raw int[]",
    "Yes", "(tag)", "java.lang.Object",
    "put()",
    "This function is putting a tag with no values in it and returning int[] with value 0."
];
let put1 = [
    "put()",
    "Yes", "{value.hashCode()...}", "raw int[]",
    "Yes", "(tag, value[])", "java.lang.Object, java.lang.Object[]",
    "put()",
    "This function is putting a tag with multiple values. Return based on your input."
];
let put2 = [
    "put()",
    "Yes", "value.hashCode()", "raw int",
    "Yes", "(tag, valueName, value)", "java.lang.Object*3",
    "put()",
    "This function is putting a tag, one value and one value name."
];
let put3 = [
    "put()",
    "Yes", "{value.hashCode()...}", "raw int[]",
    "Yes", "(tag, valueName[], value[])", "java.lang.Object, java.lang.Object[]*2",
    "new Tags()",
    "This function is putting a tag, multiple values and multiple value names. Return based on your input."
];
let add0 = [
    "add()",
    "Yes", "{value.hashCode()...}", "raw int[]",
    "Yes", "(tag, valueName[], value[])", "java.lang.Object, java.lang.Object[]*2",
    "new Tags()",
    "This function is find the storage by the tag,  putting multiple values and multiple value names. Return based on your input."
];
let add1 = [
    "add()",
    "Yes", "{value.hashCode()...}", "raw int[]",
    "Yes", "(tag, valueName[], value[])", "java.lang.Object, java.lang.Object[]*3",
    "Tags.add()",
    "This function is find the storage by the tag,  putting multiple values and multiple value names. Return based on your input."
];
let setValue0 = [
    "setValue()",
    "No", "No output", "No output",
    "Yes", "(tag, hash, value)", "java.lang.Object*2, raw int",
    "setValue()",
    "This function replaces the old value"
];
let setValue1 = [
    "setValue()",
    "No", "No output", "No output",
    "Yes", "(tag, name, value)", "java.lang.Object*3",
    "setValue()",
    "This function replaces the old value"
];
let setValue2 = [
    "setValue()",
    "No", "No output", "No output",
    "Yes", "(tag, hash, name, value)", "java.lang.Object*3, raw int",
    "None",
    "This function replaces the old value"
];
let getValueOf0 = [
    "getValueOf()",
    "Yes", "value", "java.lang.Object",
    "Yes", "(tag, name)", "java.lang.Object*2",
    "getValueOf()",
    "This function returns the value of the key"
];
let getValueOf1 = [
    "getValueOf()",
    "Yes", "value", "java.lang.Object",
    "Yes", "(tag, hash)", "java.lang.Object, raw int",
    "getValueOf()",
    "This function returns the value of the key"
];
let getValueOf2 = [
    "getValueOf()",
    "Yes", "value", "java.lang.Object",
    "Yes", "(tag, hash, name)", "java.lang.Object*2, raw int",
    "None",
    "This function returns the value of the key"
];
let getTag = [
    "getTag()",
    "Yes", "(<tagClass,tagHex>|[name,hash,hex]|...)", "java.lang.String",
    "Yes", "(tag)", "java.lang.Object",
    "Tags.toString",
    "This function returns String of the tag"
];
let getTagReport = [
    "getTagReport()",
    "Yes", "#See info", "java.lang.String",
    "Yes", "(tag)", "java.lang.Object",
    "None",
    "This function returns String of the tag, includes the branch-like format" +
    ", it's not for the code to cut it but for people, it's a report. Settings included that can enable and disable."
];
let getReport = [
    "getReport()",
    "Yes", "#See info", "java.lang.String",
    "No", "No input", "No input",
    "getTagReport()",
    "This function returns multiple tag reports that had combined, WITH A BIG COOL FACE HAHAHAAA." +
    "Settings included that can enable and disable."
];
let size = [
    "size()",
    "Yes", "(size)", "raw int",
    "No", "No input", "No input",
    "None",
    "This function returns how much big chunks is in it."
];
let totalSize = [
    "totalSize()",
    "Yes", "(size)", "raw int",
    "No", "No input", "No input",
    "None",
    "This function returns how much small values is in it."
];
let tagSize = [
    "tagSize()",
    "Yes", "(size)", "raw int",
    "Yes", "(tag)", "java.lang.Object",
    "None",
    "This function returns how much small values is in the tag."
];
let toString = [
    "toString()",
    "Yes", "({<tagClass,tagHex>|[name,hash,hex]|... ...})", "java.lang.String",
    "No", "No input", "No input",
    "Tags.toString()",
    "This function returns what the whole thing is like."
];
let hashCode = [
    "hashCode()",
    "Yes", "(hashCode)", "raw int",
    "No", "No input", "No input",
    "None",
    "This function returns Object.hash(this)."
];
let functions = [
    VrenMap, reset, resetSetting, enable, disable, put0, put1, put2, put3, add0, add1,
    getValueOf0, getValueOf1, getValueOf2, setValue0, setValue1, setValue2, getTag, getTagReport,
    getReport, size, totalSize, tagSize, toString, hashCode
];

const table = document.getElementById("docsTable");
const firstL = document.getElementById("FirstLine");

const uniHeight = "30px";
const uniColor = "#e0e0e0";
const tableWid = 1866;

table.style.width =  "1866px";
table.style.tableLayout =  "fixed";
table.style.overflowWrap =  "break-word";


let information = [
    [0, "135px", uniHeight, "Functions"],
    [1, "120.6px", uniHeight, "Has output?"],
    [2, "164px", uniHeight, "Format of output"],
    [3, "164px", uniHeight, "Output class type"],
    [4, "120.6px", uniHeight, "Has input?"],
    [5, "164px", uniHeight, "Format of input"],
    [6, "164px", uniHeight, "Input class type"],
    [7, "150.5px", uniHeight, "Function it calls"],
    [8, "583.5px", uniHeight, "More info"]
];


{
    let subTd = document.createElement("td");
    for(let i = 0; i < firstLine.length; i++){
        subTd = document.createElement("td");
        subTd.className = firstL.className;
        subTd.className = classes[i];
        subTd.textContent = firstLine[i];

        subTd.style.height = "40px";
        subTd.style.width = (information[i])[1];
        subTd.style.backgroundColor = "#c9c9c9";
        firstL.appendChild(subTd);
    }
}
{
    let tabTr = document.createElement("tr");
    tabTr.className = "Lines";
    let subTd = document.createElement("td");
    for(let i = 0; i < functions.length; i++){
        tabTr = document.createElement("tr");
        for(let o = 0; o < functions[i].length; o++){
            subTd = document.createElement("td");
            subTd.className = classes[o];
            subTd.textContent = (functions[i])[o];

            subTd.style.height = uniHeight;
            subTd.style.width = (information[o])[1];
            subTd.style.backgroundColor = uniColor;
            tabTr.appendChild(subTd);
        }
        table.appendChild(tabTr);
    }
}