/**
 * 
 */
function nullcheck(obj){
	if(obj==""||obj==null||obj==undefined){
		return true;
	}else{
		return false;
	}
}; //null체크 함수

function keep_box(){
	if(!nullcheck($(".search_txt").val())){
		$(".search_box").css({
			"box-shadow": "0px 0px .5px 1px #51e3d4",
			"width": "282px"
		});
		$(".search_txt").css({
			"width": "240px",
			"padding": "0 6px"
		});
	}else{
		$(".search_box").css({
			"padding": "11px",
			"transform": "translate(-85%,-15%)",
			"height": "30px",
			"background-color": "#fff",
			"border": "4px solid #51e3d4",
			"border-radius": "30px",
			"transition": "0.4s",
			"width":"30px",
			"margin-left":"1450px",
		});
		$(".search_txt").css({
			"display": "flex",
			"padding": "0",
			"width": "0px",
			"border":"none",
			"background": "none",
			"outline": "none",
			"float": "left",
			"font-size": "1rem",
			"line-height": "30px",
			"transition": ".4s",
		});
	}
}//검색창 인풋시 트랜스레이트 유지

function enter_search(e){
	if(e.keyCode==13){
		search_board();
	}
}//검색창 엔터키 누르면 작동 (13번이 엔터)