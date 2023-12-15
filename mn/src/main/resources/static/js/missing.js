/**
 * 
 */
var nums;
var autobox=document.getElementById("auto_box");
var cnt_index=document.getElementById("tokCntPrintf");

function nullcheck(obj){
	if(obj==""||obj==null||obj==undefined){
		return true;
	}else{
		return false;
	}
}; //null체크 함수

const lists = JSON.parse(localStorage.getItem("missing"));	

//const list=[
//	{ num: "0", title:"<span style='color: red;'>[실종]</span> 부산 부암역에서 사라진 강아지 찾아요",					img1: "img/프로필_솜.jpeg",	nickname:"솜사탕",	img2: "img/실종_솜.jpeg",		content: "<b>마지막 목격장소 : 부산 부암역 8번출구<br>마지막 목격날짜 : 2023.07.12 오후 6시경<br>보호자 연락처 : 010-1234-5678<br>비숑 프리제/ 암컷 / 3살 <br>노란 옷 입었고 이름은  '솜'입니다</b> <br><br>발견하시면 꼭 연락부탁드려요ㅠㅠㅠㅠ<br>사례하겠습니다<br>"},
//	{ num: "1", title:"<span style='color: #FFBF00;'>[목격]</span> 경주 동천동에서 고양이 잃어버리신분 없나요?",		img1: "img/프로필_호두.jpeg",	nickname:"호두마루",	img2: "img/실종_냥1.jpeg",	content: "<b>목격장소 : 써니테이블 카페 앞<br>목격날짜 : 2023.07.15 오전 11시 반<br>코숏 치즈태비로 추정</b> <br><br>털이랑 발톱 관리 잘된걸로 봐서 집고양이 같아요<br>카페 사장님이 임보중이라고 하시니 카페로 가보세요!!<br>"},
//	{ num: "2", title:"<span style='color: red;',>[실종]</span> 울산 신정동에서 사라진 고양이를 찾습니다",				img1: "img/프로필_도리.jpeg",	nickname:"도리도리",	img2: "img/실종_도리.jpeg",	content: "<b>마지막 목격장소 : 신정동 신정초 근처<br>마지막 목격날짜 : 2023.07.15 오후 3시15분경<br>보호자 연락처 : 010-1234-5678<br>코숏 턱시도 / 수컷 / 1살 </b><br><br>집 창문을 열어뒀는데 그새 나간거 같아요<br>발견하시면 꼭 전화주세요 꼭이요<br>사례하겠습니다!!!<br>"},
//	{ num: "3", title:"<span style='color: red;'>[실종]</span> 울산 송정동에서 샴고양이 '구리'를 보시면 연락주세요",		img1: "img/프로필_구리.jpeg",	nickname:"너구리",	img2: "img/실종_구리.jpeg",	content: "<b>마지막 목격장소 : 울산 송정동 랑콩뜨레<br>마지막 목격날짜 : 2023.07.18 오후 2시쯤<br>보호자 연락처 : 010-1234-5678<br>샴 / 수컷 / 6살 </b><br><br>겁이 많으니 발견시 부르지 마시고<br><b>영상 or 사진</b> 제보해주세요 <br>발견하시면 꼭 제보부탁드려요 꼭..!!ㅠㅠ<br>"},
//	{ num: "4", title:"<span style='color: #2E9AFE;'>[보호]</span> 울산 일산지쪽에서 발견한 달마시안 임보중입니다",		img1: "img/프로필_군이.jpeg",	nickname:"구니군이",	img2: "img/실종_달마시안.jpeg",	content: "<b>목격장소 : 울산 동구 일산지해수욕장<br>목격날짜 : 2023.07.20 오후 8시 <br>연락처 : 010-1234-5678</b><br><br>저희집 군이랑 산책나왔다가 발견했는데<br>목줄도 있는데 주인분이 3시간째 안보이시네요ㅠㅠ<br>도로변 위험해보여서 제가 데리고 있습니다<br>"},
//	{ num: "5", title:"<span style='color: #FFBF00;'>[목격]</span> 울산 삼산동에서 고양이 찾으시는분 있나요? ",			img1: "img/프로필_몽이.jpeg",	nickname:"몽이언니",	img2: "img/실종_냥2.jpeg",	content: "<b>목격장소 : 울산 삼산동 자두공원<br>목격날짜 : 2023.07.23 오후 5시 반<br>코숏 치즈태비로 추정</b> <br><br>순하고 사람 안무서워하는게 집고양이 같아요<br>혹시나 잃어버리신 분 있으면<br> 도움되라고 글 남겨봅니다~"},
//	{ num: "6", title:"<span style='color: red;'>[실종]</span> 울산 울주군에서 비숑보셨으면 연락주세요!",				img1: "img/프로필_호빵.jpeg",	nickname:"찐빵",		img2: "img/실종_호빵.jpeg",	content: "<b>마지막 목격장소 : 울산 울주군 개들랜드 애견카페<br>마지막 목격날짜 : 2023.07.26 오후 4시경<br>보호자 연락처 : 010-1234-5678<br>비숑 프리제 / 암컷 / 2살 </b><br><br>잘 놀길래 잠시 화장실 다녀왔는데 사라졌어요ㅠㅠㅠ<br>cctv 확인해보니 다른 손님이 들어오는<br>문틈으로 나간거같아요"},
//	{ num: "7", title:"<span style='color: #2E9AFE;'>[보호]</span> 서울 회기역쪽에서 발견한 고양이 '해태' 임보중입니다",	img1: "img/프로필_뽀또.jpeg",	nickname:"뽀또맘",	img2: "img/실종_해태.jpeg",	content: "<b>목격장소 : 서울 회기역 1번출구<br>목격날짜 : 2023.08.01 오후 10시 <br>연락처 : 010-1234-5678</b><br><br>'해태'라는 명찰 목걸이 하고 있어요<br>명찰하고 있어서 산책냥인가 했는데<br>아침에도 봤는데 밤까지 밖에 있길래 보호중입니다 <br>명찰에 번호는 안받으시네요ㅠㅠ<br>"},
//	{ num: "8", title:"<span style='color: red;'>[실종]</span> 하동군 북천면쪽에서 사라진 아기강아지를 찾습니다",			img1: "img/프로필_땡이.jpeg",	nickname:"땡그랑땡",	img2: "img/실종_땡이.jpeg",	content: "<b>마지막 목격장소 : 하동군 북촌면 면사무소 앞<br>마지막 목격날짜 : 2023.08.08 오후 1시<br>보호자 연락처 : 010-1234-5678<br>프렌치 불독 / 암컷 / 3개월 / 이름 : 땡이 </b><br><br>시골이라 마당에 풀어놓고 키우는데 안돌아오네요<br>태어난지 얼마 안된 애기라 겁이 많아요<br>보시면 <b>영상 or 사진</b> 제보해주세요"}
//	]

let result_list; 

if(!nullcheck(lists)){
	result_list = list.concat(lists);
}else{
	result_list = list;
}


function print_cnt(){
	cnt = Number(result_list.length)
	cnt_index.innerText=cnt+'개의 글'
}
print_cnt()

function gotoMissingForm(){
	location.href='\missingForm.html';
}

function autoinput(){
	for(let i=result_list.length-1;i>=0;i--){
		const num = result_list[i].num;
		const img1 = result_list[i].img1;
		const nickname = result_list[i].nickname;
		const content=result_list[i].content;
		const title = result_list[i].title;
		const img2 = result_list[i].img2;
		autobox.innerHTML+='<div id="auto_inner'+num+'" class="auto_inner" ><button onclick="click_del(this.id)" class="del_btn" id="del_btn'+num+'">X</button><div id="auto_border"><div id="auto_author"><div id="auto_title">'+title+'</div><div id="auto_profile"><img id="auto_img" src="'+img1+'"><div id="auto_nick">'+nickname+'</div></div></div><div id="auto_contentimg"><img id="auto_img2" src="'+img2+'"></div><div id="auto_content">'+content+'</div></div></div>';        
	}
}


window.addEventListener("load",function(){
	autoinput()
})


function click_del(id){
	let id_num = id.substr(7);
	let div_id = "#auto_inner"+id_num;
	$(div_id).remove();
	cnt--;
	cnt_index.innerText=cnt+'개의 글'
	let new_list= [];

	for(let i=0; i<lists.length; i++){
		if(id_num==lists[i].num){
			
		}else{
			new_list.push({
				num: lists[i].num,
				title: lists[i].title,
				img1: lists[i].img1,
				nickname: lists[i].nickname,
				img2: lists[i].img2,
				content: lists[i].content
				});
					 
		}
	}
	localStorage.setItem("missing", JSON.stringify(new_list));

}



