/**
 * 
 */

var titleInput=document.getElementById("title1")
var contentInput=document.getElementById("content1")
var editorForm=document.getElementById("form1")
const BOARDLIST_LS = 'hospital';
var cancel=document.getElementById("cancel")
let nums=0
var starinput=document.getElementById("star");
var regioninput=document.getElementById("region");

function onEditorFormSubmit() {

if(confirm("저장하시겠습니까?")) {
const title = titleInput.value;
const content = contentInput.value;
const stars = starinput.value;
const lists = JSON.parse(localStorage.getItem(BOARDLIST_LS));
const region=regioninput.value;
let tempDate=new Date();
if (!lists) {

const objArr = [];
objArr.push({
num: `${nums}`,
title: `${title}`,
star: `${stars}`,
//img: `${img}`,
region:`${region}`,
content: `${content}`
});
 
localStorage.setItem(BOARDLIST_LS, JSON.stringify(objArr));
} else {
nums=parseInt(lists.length)
lists.push({
num: `${nums}`,
title: `${title}`,
star: `${stars}`,
//img: `${img}`,
region:`${region}`,
content: `${content}`
});
 
localStorage.setItem(BOARDLIST_LS, JSON.stringify(lists));
}
 
titleInput.value = '';
contentInput.value = '';
titleInput.focus();
location.href='\Team_tok.html';
}
else false;
}

//editorForm.addEventListener('submit', onEditorFormSubmit);
function openTok(){
	location.href='\Team_tok_write.html';
}
cancel.addEventListener("click",function(){

	
	if(confirm("취소하시겠습니까?")) location.href='\Team_tok.html';
	else false;
	
})
