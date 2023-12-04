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

const lists = JSON.parse(localStorage.getItem("show"));	

const list=[ 
	{ num: "0", author: "몽이언니", 	img1: "img/몽이프로필.jpg", img2: "img/몽이.jpg", content: "반가워요~~ 저는 털이 몽글몽글하다고 주인님이 이름을 몽이라고 지어줬어요!! 저 완전 귀엽죠 ;) 헤헤 제 얼굴 보니 마음도 몽글몽글해지죠!? 원래 귀여운건 크게 크게 봐야한다고 했어요! 왕크왕귀인 제 사진보고 오늘 하루도 힘내세 멍!!!"},
	{ num: "1",	author: "쿵이네",		img1: "img/쿵이프로필.jpg", img2: "img/쿵이.jpg", content: "멍냥 누나 형아들~~ 저는 쿵이에요 :) 태어나서 첫 산책인데 풀향도 가득가득 맡고 너무 즐거웠어요! 주인은 집이 젤 좋다는데, 저는 집밖이 젤 좋은 거 같아요! 산책 너무 좋아요!! 내일 또 산책 할래요!!"},
	{ num: "2",	author: "심바엄마", 	img1: "img/심바프로필.jpg", img2: "img/심바.jpg", content: "오랜만에 심바 검진하러 병원가는 길이에요~ 병원가는 줄도 모르고 창밖 세상이 신기한지 냥~~ 때리면서 보고 있어요..바보심바ㅠ 검진 끝나면 맛나는 츄르 줄게~~ 제발 아프지만 말아다오 울애기ㅠㅠ"},
	{ num: "3",	author: "호두마루", 	img1: "img/호두프로필.jpg", img2: "img/호두.jpg", content: "집사야 이것 좀 당겨도 될까냥? 우리집 사고뭉치 애옹이 호두♥ 사고치기 1초 전 모습이에요.. 하지만 그래도 너무 예쁜 우리호두 귀여워서 심장 아파요ㅠ.ㅠ"},
	{ num: "4",	author: "a.tom2__", img1: "img/톰이프로필.jpg", img2: "img/톰이.jpg", content: "고생했어♥ 우리 아톰이 원래 엄살쟁이긴한데 ㅋㅋ 이렇게 목놓아 우는건 또 처음보네..ㅠㅠ 얼른 회복하고 놀러가쟈!!!"},
	{ num: "5",	author: "식빵", 		img1: "img/빵이프로필.jpg", img2: "img/빵이.jpg", content: "식빵이도 맛난거 주세요....ㅠㅠ 식빵이 표정만 보면 너무 귀여워서 더 괴롭히고 싶어요 멍냥님들도 그렇죠? 저만 그런거 아니죠? 미안하다 식빵아 그만 귀여우라고!!!"},
	{ num: "6",	author: "구니군이", 	img1: "img/군이프로필.jpg", img2: "img/군이.jpg", content: "리드줄과 바람만 있다면 군이는 어디든 갈수있다멍!!!"},
	{ num: "7",	author: "구운소금",	img1: "img/소금이프로필.jpg",img2: "img/소금이.jpg",content: "우리집 마당냥이 소금이에요^^ 산책을 즐기다 와서 꼬질꼬질해졌지만ㅠㅠ 제 눈엔 정말 예쁜 소금이랍니다! 동네에서 대장냥이에요 아무도 못이겨요 사실 저도 못이겨요...."},
]


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

function openTok(){
	location.href='\show_write.html';
}

function autoinput(){
	for(let i=result_list.length-1;i>=0;i--){
		const num = result_list[i].num;
		const img1 = result_list[i].img1;
		const author= result_list[i].author;
		const img2 = result_list[i].img2;
		const content=result_list[i].content;
		autobox.innerHTML+='<div class="auto_inner" id="auto_inner'+num+'"><div id="auto_author"><div id="auto_profile"><img id="auto_img" src="'+img1+'"></div><div id="auto_title">'+author+'</div><button onclick="click_del(this.id)" class="del_btn" id="del_btn'+num+'">X</button></div><div id="auto_contentimg"><img id="auto_img2" src="'+img2+'"></div><div id="auto_content">'+content+'</div></div>';        
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
				img1: lists[i].img1,
				author: lists[i].author,
				img2: lists[i].img2,
				content: lists[i].content
				});
					 
		}
	}
	localStorage.setItem("show", JSON.stringify(new_list));

}

/*
 * addevent. click 
 * document.getElementById("id")
 * 
 * list. key value--> 그 배열을 찾고
 * --->이 값으로 지우면돼
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */













