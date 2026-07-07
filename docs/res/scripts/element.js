export function createEl(doc, name, attributes = {}, children){
    let element = doc.createElement(name);
    Object.assign(element, attributes);
    if(attributes.style){
        Object.assign(element.style, attributes.style);
    }
    if(children !== undefined) for(let i = 0; i < children.length; i++)element.appendChild(children[i]);
    return element;
}