$(document).ready(function(){
let name = document.getElementById("name");
let name_lb = document.getElementById("name_label");
//    let name = document.getElementById("name");
//	let name_lb = document.getElementById("name_label");
	$("#calculator_text2").hide();	//생애주기 숨기기
	
	let birthYear=document.getElementById("birth_year");	

	isYearOptionExisted = false;
	birthYear.addEventListener('focus', function () {
	  if(!isYearOptionExisted) {
	    isYearOptionExisted = true
	    for(let i = 2000; i <= 2023; i++) {
	      let YearOption = document.createElement('option')
	      YearOption.setAttribute('value', i)
	      YearOption.innerText = i
	      this.appendChild(YearOption);
	    }
	  }
	});//select년도 채우기

	let birthMonth=document.getElementById("birth_month");	

	isMonthOptionExisted = false;
	birthMonth.addEventListener('focus', function () {
	  if(!isMonthOptionExisted) {
	    isMonthOptionExisted = true
	    for(let i = 1; i <= 12; i++) {
	      let MonthOption = document.createElement('option')
	      MonthOption.setAttribute('value', i)
	      MonthOption.innerText = i
	      this.appendChild(MonthOption);
	    }
	  }
	});//select 월 채우기
	
	let birthDay=document.getElementById("birth_day");	

	isDayOptionExisted = false;
	birthDay.addEventListener('focus', function () {
	  if(!isDayOptionExisted) {
		  isDayOptionExisted = true
	    for(let i = 1; i <= 31; i++) {
	      let DayOption = document.createElement('option')
	      DayOption.setAttribute('value', i)
	      DayOption.innerText = i
	      this.appendChild(DayOption);
	    }
	  }
	});//select 일 채우기
	

	name.addEventListener("blur",function(){			
		nameNotEmpty();								
	});

	let submitBtn = $("#submit_btn");
	let ageResult = $("#age_result");
	let petname = $("#name");
	
	submitBtn.on("click",function(){
		if(objectNotEmpty()){
			$("#calculator_text2").show();
			$("#name_result").html(petname.val());	
			age();
		}
	});


});


function nameNotEmpty(){

	if(name.value.length == 0){						
		name_lb.innerHTML="이름을 알려주세요!";
		name_lb.style.color="red"
		name_lb.style.fontSize="13px"
		name_lb.style.fontWeight="blod"			
	}else{
		name_lb.innerHTML="";
	}
};//이름 유효성검사

function nullcheck(obj){
	if(obj==""||obj==null||obj==undefined){
		return true;
	}else{
		return false;
	}
}; //null체크 함수

function objectNotEmpty(){

	if(!$("#dog_check").is(':checked')&&!$("#cat_check").is(':checked')){
		return false;
	}
	if(nullcheck($("#birth_year option:selected").val())||nullcheck($("#birth_month option:selected").val())||nullcheck($("#birth_day option:selected").val())){
		return false;
	}
	return true; 
	
};//생년월일, 체크박스 유효성 

function age(){
	let year = $("#birth_year option:selected").val();
	let month = $("#birth_month option:selected").val();
	let day = $("#birth_day option:selected").val();
	
	let select_day = new Date(year, month-1, day);
	let today = new Date();
	let diff = Math.abs(select_day.getTime()- today.getTime());
	
	diff = Math.ceil(diff/(1000*60*60*24))-1;
	
	let age="";
	let years = Math.floor(diff/365);
	if(years <= 0){
		
	}else{
		if($("#dog_check").is(':checked')){
			age = (16+years*5)+"살"; 
		}else{
			age = (16+years*4)+"살"; 
		}
	}
		if(diff<=120){
			$("#age_result").html("5살 이하");
			$("#age_text").html("뽀짝뽀짝 귀여운 유년기<br>다양한 경험을 통해 세상을 배우는 시기!");
		}else if(diff>120 && diff<=180){
			$("#age_result").html("10살");
			$("#age_text").html("질풍노도 청소년기<br>넘치는 에너지를 해소해주세요!");
		}else if(diff>180 && diff<=270){
			$("#age_result").html("13살");
			$("#age_text").html("질풍노도 청소년기<br>넘치는 에너지를 해소해주세요!");
		}else if(diff>270 && diff<=365){
			$("#age_result").html("16살");
			$("#age_text").html("질풍노도 청소년기<br>넘치는 에너지를 해소해주세요!");
		}else if(diff>365 && diff<=365*2){
			$("#age_result").html(age);
			$("#age_text").html("반짝반짝 성년기<br>훌쩍 성숙해지고 독립심이 높아져요~!");
		}else if(diff>365*2 && diff<=365*3){
			$("#age_result").html(age);
			$("#age_text").html("반짝반짝 성년기<br>훌쩍 성숙해지고 독립심이 높아져요~!");
		}else if(diff>365*3 && diff<=365*4){
			$("#age_result").html(age);
			$("#age_text").html("반짝반짝 성년기<br>훌쩍 성숙해지고 독립심이 높아져요~!");
		}else if(diff>365*4 && diff<=365*5){
			$("#age_result").html(age);
			$("#age_text").html("반짝반짝 성년기<br>훌쩍 성숙해지고 독립심이 높아져요~!");
		}else if(diff>365*5 && diff<=365*6){
			$("#age_result").html(age);
			$("#age_text").html("차분함이 빛나는 중년기<br>비만을 주의해야해요!");
		}else if(diff>365*6 && diff<=365*7){
			$("#age_result").html(age);
			$("#age_text").html("차분함이 빛나는 중년기<br>비만을 주의해야해요!");
		}else if(diff>365*7 && diff<=365*8){
			$("#age_result").html(age);
			$("#age_text").html("차분함이 빛나는 중년기<br>비만을 주의해야해요!");
		}else if(diff>365*8 && diff<=365*9){
			$("#age_result").html(age);
			$("#age_text").html("차분함이 빛나는 중년기<br>비만을 주의해야해요!");
		}else if(diff>365*9 && diff<=365*10){
			$("#age_result").html(age);
			$("#age_text").html("오래오래 함께 할 노년기<br>건강검진을 꼭꼭 받아주세요!");
		}else if(diff>365*10 && diff<=365*11){
			$("#age_result").html(age);
			$("#age_text").html("오래오래 함께 할 노년기<br>건강검진을 꼭꼭 받아주세요!");
		}else if(diff>365*11 && diff<=365*12){
			$("#age_result").html(age);
			$("#age_text").html("오래오래 함께 할 노년기<br>건강검진을 꼭꼭 받아주세요!");
		}else if(diff>365*12 && diff<=365*13){
			$("#age_result").html(age);
			$("#age_text").html("오래오래 함께 할 노년기<br>건강검진을 꼭꼭 받아주세요!");
		}else if(diff>365*13 && diff<=365*14){
			$("#age_result").html(age);
			$("#age_text").html("매일매일 고마운 고령기<br>반려동물을 세심하게 보살펴주세요!");
		}else if(diff>365*14 && diff<=365*15){
			$("#age_result").html(age);
			$("#age_text").html("매일매일 고마운 고령기<br>반려동물을 세심하게 보살펴주세요!");
		}else if(diff>365*15 && diff<=365*16){
			$("#age_result").html(age);
			$("#age_text").html("매일매일 고마운 고령기<br>반려동물을 세심하게 보살펴주세요!");
		}else if(diff>365*16 && diff<=365*17){
			$("#age_result").html(age);
			$("#age_text").html("매일매일 고마운 고령기<br>반려동물을 세심하게 보살펴주세요!");
		}else if(diff>365*17 && diff<=365*18){
			$("#age_result").html(age);
			$("#age_text").html("매일매일 고마운 고령기<br>반려동물을 세심하게 보살펴주세요!");
		}else if(diff>365*18 && diff<=365*19){
			$("#age_result").html(age);
			$("#age_text").html("매일매일 고마운 고령기<br>반려동물을 세심하게 보살펴주세요!");
		}else if(diff>365*19 && diff<=365*20){
			$("#age_result").html(age);
			$("#age_text").html("매일매일 고마운 고령기<br>반려동물을 세심하게 보살펴주세요!");
		}else if(diff>365*20){
			$("#age_result").html(age +" 이상");
			$("#age_text").html("매일매일 고마운 고령기<br>반려동물을 세심하게 보살펴주세요!");
		}
	};//생애주기 계산식


function orCheck(chk,name){
	let obj = document.getElementsByName(name);
	for(let i=0; i<obj.length;i++){
		if(obj[i] != chk){
			obj[i].checked=false;
		}
	}
}//생애주기 계산기 반려동물 체크 1개만 하기

	

let slides = document.querySelector(".slides");
let slide = document.querySelectorAll(".slides li");
let currentIdx=0;
let slideCount=slide.length;
let slideWidth = 300;
let slideMargin=80;
let preBtn=document.querySelector(".prev");
let nextBtn=document.querySelector(".next");

makeClone();

function makeClone(){
	for(var i=0; i<slideCount; i++){
		var cloneSlide = slide[i].cloneNode(true);	//a.cloneNode()는 a요소를 복제,a.cloneNode(true)는 a의 자식요소까지 복제
		cloneSlide.classList.add("clone");
		slides.appendChild(cloneSlide);
	}
	for(var i= slideCount-1; i>=0; i--){
		var cloneSlide = slide[i].cloneNode(true);	
		cloneSlide.classList.add("clone");
		slides.prepend(cloneSlide);
	}
	updateWidth();
	setInitialPos();
	
	setTimeout(function(){
		slides.classList.add('animated');
	},100);
}


function updateWidth(){
	var crrentSlides=document.querySelectorAll(".slides li");
	var newSlideCount=crrentSlides.length;
	
	var newWidth=(slideWidth + slideMargin) * newSlideCount - slideMargin +'px';
	slides.style.width=newWidth;
}

function setInitialPos(){
	var initialTranslatevalue = -(slideWidth + slideMargin) * slideCount;
	slides.style.transform = 'translateX('+initialTranslatevalue+'px)';
}

nextBtn.addEventListener("click",function(){
	moveSlide(currentIdx+1);
});

preBtn.addEventListener("click",function(){
	moveSlide(currentIdx-1);

});

function moveSlide(num){
	slides.style.left = -num*(slideWidth + slideMargin)+'px';
	currentIdx=num;
	console.log(currentIdx,slideCount)
	if(currentIdx == slideCount ||currentIdx== -slideCount){
		setTimeout(function(){
			slides.classList.remove('animated');
			slides.style.left ='0px';
			currentIdx=0;
		},1000);
		setTimeout(function(){
			slides.classList.add('animated');
		},1100);
	}
}

//---------↑멀티플 슬라이드 ----------------------------------------------------


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



