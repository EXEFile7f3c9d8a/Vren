const ph = import.meta.url;
const header = document.getElementById("topBar");
const nav = document.createElement("nav");
const imgLink = document.createElement("a");
const titleImg = document.createElement("img");
const links = document.createElement("div");
const gap = document.createElement("div");
const VrenMapLink = document.createElement("a");

titleImg.src = new URL("../../res/image/title/UniversalTitleImage.png", ph).href
titleImg.alt = "img";
titleImg.style.marginLeft = "5px";
titleImg.style.marginTop = "5px";
titleImg.style.width = "50px";
titleImg.style.height = "50px";

imgLink.appendChild(titleImg);
imgLink.href = new URL("../../", ph).href
imgLink.target = "_self";
imgLink.title = "Home Page";
imgLink.style.width = "60px";
imgLink.style.height = "60px";

VrenMapLink.text = "Vren Map";
VrenMapLink.href = new URL("../../document/VrenMapDoc/about", ph).href;
VrenMapLink.style.marginTop = "auto";
VrenMapLink.style.marginBottom = "auto";

gap.style.width = "10px";
gap.style.height = "60px";

links.appendChild(VrenMapLink);
links.appendChild(gap);
links.style.marginLeft = "auto";
links.style.display = "flex";
links.style.gap = "15px";
links.style.height = "60px";

nav.appendChild(imgLink);
nav.appendChild(links);
nav.style.display = "flex";
nav.style.width = "100%"
nav.style.justifyContent = "space-between";

header.style.display = "flex";
header.style.alignItems = "center";
header.style.flexDirection = "row";
header.style.color = "#1f1f1f";
header.style.position = "fixed";
header.style.backgroundColor = "#fdfcfc";
header.style.zIndex = "100";
header.style.top = "0";
header.style.left = "0";
header.style.right = "0";
header.style.height = "60px";
header.style.width = "100%";
header.style.boxShadow = "0 1px 0 0 rgba(0,0,0,0.2)";

header.appendChild(nav);