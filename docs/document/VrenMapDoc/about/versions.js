const main = document.getElementById("version");
import {getVersion}from '../../../res/scripts/versions.js'
const versions = getVersion();
const url = new URL("../../../", import.meta.url) ;
let links = document.createElement("div");

{
    let link = document.createElement("a");
    link.href = new URL(versions[0], url).href;
    link.text = "Latest";
    link.title = "Latest Version of Vren Map";
    links.appendChild(link);
}
{
    let link = document.createElement("a");
    let p = document.createElement("p");
    for(let i = 1; i < versions.length; i++){
        link.href = new URL(versions[i], url).href;
        let temp;
        let t = versions[i].split("/");
        temp = t[t.length - 1];
        link.title = temp;
        link.text = temp;
        p.appendChild(link);
        links.appendChild(p);
        link = document.createElement("a");
        p = document.createElement("p");
    }
    main.appendChild(links);
}