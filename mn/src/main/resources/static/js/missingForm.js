/**
 * 
 */
var titleInput = document.getElementById("title1")
const profileimg1 = document.getElementById("img1")
var nickInput = document.getElementById("title2")
const contentimg1 = document.getElementById("img2")
var contentInput = document.getElementById("content1")
var editorForm = document.getElementById("form1")
const BOARDLIST_LS = 'missing';
var cancel = document.getElementById("cancel")
let nums = 9




function onEditorFormSubmit(e) {

if(confirm("저장하시겠습니까?")) {
	const title = titleInput.value;
	const img1 = profileimg1.value;
	const nickname = nickInput.value;
	const img2 = contentimg1.value;
	const content = contentInput.value;
	const lists = JSON.parse(localStorage.getItem(BOARDLIST_LS));
	if (!lists) {
	
		const objArr = [];
		objArr.push({
		num: `${nums}`,
		title: `${title}`,
		img1: `${img1}`,
		nickname: `${nickname}`,
		img2: `${img2}`,
		content: `${content}`
		});
		 
		localStorage.setItem(BOARDLIST_LS, JSON.stringify(objArr));
	} else {
		nums=parseInt(lists.length)+9
		lists.push({
			num: `${nums}`,
			title: `${title}`,
			img1: `${img1}`,
			nickname: `${nickname}`,
			img2: `${img2}`,
			content: `${content}`
			});
		 
		localStorage.setItem(BOARDLIST_LS, JSON.stringify(lists));
	}
	 
	titleInput.value = '';
	contentInput.value = '';
	titleInput.focus();
	location.href='\missing.html';
}
else false;
}

//editorForm.addEventListener('submit', onEditorFormSubmit);
function openTok(){
	location.href='\missingForm.html';
}
cancel.addEventListener("click",function(){

	
	if(confirm("취소하시겠습니까?")) location.href='\missing.html';
	else false;
	
})

function onClickUpload(){
	let imgbtn = document.getElementById("img_btn");
	imgbtn.click();
}












