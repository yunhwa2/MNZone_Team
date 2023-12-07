/**
 * 
 */

var titleInput = document.getElementById("title")
var editorForm = document.getElementById("form1")
const BOARDLIST_LS = 'show';
var cancel = document.getElementById("cancel")
let nums = 8


function onEditorFormSubmit(e) {

if(confirm("저장하시겠습니까?")) {
	const title = titleInput.value;
	const lists = JSON.parse(localStorage.getItem(BOARDLIST_LS));
	if (!lists) {
	
		const objArr = [];
		objArr.push({
		num: `${nums}`,
		title: `${title}`,
		});
		 
		localStorage.setItem(BOARDLIST_LS, JSON.stringify(objArr));
	} else {
		nums=parseInt(lists.length)+8
		lists.push({
			num: `${nums}`,
			title: `${title}`,
			});
		 
		localStorage.setItem(BOARDLIST_LS, JSON.stringify(lists));
	}
	 
	titleInput.value = '';
	titleInput.focus();
	location.href='\show.html';
}
else false;
}

//editorForm.addEventListener('submit', onEditorFormSubmit);
function openTok(){
	location.href='\write.html';
}
cancel.addEventListener("click",function(){

	
	if(confirm("취소하시겠습니까?")) location.href='\show.html';
	else false;
	
})

function onClickUpload(){
	let imgbtn = document.getElementById("img_btn");
	imgbtn.click();
}












