/**
 * 
 */

const table = document.getElementById("notice_table")
const page = document.getElementById("count")
let list = JSON.parse(localStorage.getItem("notice"));
const Allcontent=68//38개의 글이 있다(만약)
let t=20
let cntstart=0
function autohtml(num){
	cntstart=(num-1)*20
	t=cntstart+20
	if(t>Allcontent) t=Allcontent;
	console.log(num)
	table.innerHTML='<tr><th>전체</th><th>제목</th><th>날짜</th> </tr>'
	if(!num){
		for(let i=0;i<20;i++){
			
			if(i<3){
				table.innerHTML+='<tr class="important_tr"><td>'+i+'.공지</td><td id="opa">시스템 개편사항</td><td>2023.08.07</td></tr>'
			}
			else{
				table.innerHTML+='<tr"><td>'+i+'.공지</td><td id="opa">시스템 개편사항</td><td>2023.08.07</td></tr>'
			}
		}
	}
		
	for(let i=cntstart;i<t;i++){
		
		if(i<3){
			table.innerHTML+='<tr class="important_tr"><td>'+i+'.공지</td><td id="opa">시스템 개편사항</td><td>2023.08.07</td></tr>'
		}
		else{
			table.innerHTML+='<tr"><td>'+i+'.공지</td><td id="opa">시스템 개편사항</td><td>2023.08.07</td></tr>'
		}
	}
	return cntstart;
}
//만약 38개의 글이 있다.
function pagecnts(){
	
	const pagecnt=parseInt(Allcontent/20)+1
	console.log(pagecnt)
	for(let i=1; i<=pagecnt;i++){
		page.innerHTML+='<button class="pagenum" type="button" onclick="autohtml('+i+')">'+i+'</button>'
		
	}
}
pagecnts()
autohtml()