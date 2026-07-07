let firstLine;
let functions;
let doc;
let putF = false;

export function putFunctions(data, docs, first){
    if(data == null)console.log("data is null");
    else if(docs == null)console.log("docs == null");
    else if(first == null)console.log("first == null");
    firstLine = first;
    functions = data;
    doc = docs;
    putF = true;
}
export function run(){
    if(!putF){
        console.log("null data, refused to put the table")
    }
    const table = doc.getElementById("docsTable");
    const firstL = doc.getElementById("FirstLine");

    const uniHeight = "30px";
    const uniColor = "#e0e0e0";
    
    table.style.width = (window.innerWidth * 0.9807).toString() + "px";
    table.style.tableLayout =  "fixed";
    table.style.overflowWrap =  "break-word";
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
    let information = [
        [0, "7.64%", uniHeight, "Functions"],
        [1, "6.83%", uniHeight, "Has output?"],
        [2, "9.29%", uniHeight, "Format of output"],
        [3, "9.29%", uniHeight, "Output class type"],
        [4, "6.83%", uniHeight, "Has input?"],
        [5, "9.29%", uniHeight, "Format of input"],
        [6, "9.29%", uniHeight, "Input class type"],
        [7, "8.52%", uniHeight, "Function it calls"],
        [8, "33.02%", uniHeight, "More info"]
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
            tabTr.className = "Lines";
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
}