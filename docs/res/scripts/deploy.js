import {createEl} from "./element.js";
export function run(doc, box, values, version){
    {
        box.appendChild(
            createEl(doc, "div", {
                style:{
                    fontSize: "x-large",
                    fontWeight: "bold"
                }
            },[
                createEl(doc, "p", {innerText: "Install - " + version})
            ])
        );
    }
    box.style.display = "flex";
    box.style.justifyContent = "center"
    box.style.alignItems = "center";
    box.style.flexDirection = "column";
    for(let i = 0; i < values.length; i++){
        const title = values[i][0];
        const code1 = values[i][1];
        const code2 = values[i][2];
        const div = createEl(doc, "div", {
            style:{
                width: (window.innerWidth * 0.365).toString() + "px",
                borderRadius: "5px",
                border: "1px solid #d1d9e0"
            }
        });
        div.appendChild(
            createEl(doc, "div", {
                id: `${title}-box-p`,
                style:{
                    backgroundColor: "#f6f8fa",
                    borderRadius: "5px 5px 0 0",
                    borderBottom: "1px solid #d1d9e0",
                    height: "40px",
                    display: "flex",
                    justifyContent: "center",
                    flexDirection: "column"
                }
            }, [
                createEl(doc, "p", {innerText: title})
                ]
            )
        );
        div.appendChild((
            createEl(doc, "div",
                {
                    id: title + "-box-code",
                    style:{marginLeft: "15px"}
                },
                [
                    createEl(doc, "div", {
                        id: title + "-box-code-1",
                        style:{
                            textAlign: "left",
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "flex-start",
                            flexDirection: "column"
                        }
                        }, [
                            createEl(doc, "p", {innerText: code1})
                    ]),
                    createEl(doc, "div", {
                        id: title + "-box-code-2",
                        style:{
                            textAlign: "left",
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "flex-start",
                            flexDirection: "column"
                        }
                        }, [
                            createEl(doc, "p", {innerText: code2})
                    ])
            ])
            )
        );
        box.appendChild(div);
        box.appendChild(doc.createElement("br"));
    }
}
function boxCodeXXXStyle(div){
    div.style.textAlign = "left"
    div.style.display = "flex"
    div.style.justifyContent = "center"
    div.style.alignItems = "flex-start"
    div.style.flexDirection = "column";
    return div;
}