/**
 *
 */
//비밀번호 확인
	$(document).ready(function(){
	    $('#mypass').keyup(function(){
	      $('#chkNotice').html('');
	    });

	    $('#mypasschk').keyup(function(){

	        if($('#mypass').val() != $('#mypasschk').val()){
	          $('#chkNotice').html('비밀번호 일치하지 않음<br><br>');
	          $('#chkNotice').css('color', '#f82a2aa3');
	        } else{
	          $('#chkNotice').html('비밀번호 일치함<br><br>');
	          $('#chkNotice').css('color', '#199894b3');
	        }

	    });
	});

//사진 미리보기
function uploadFile() {
    const fileInput = document.getElementById('fileInput');
    const memberImage = document.getElementById('memberImage');

    fileInput.onchange = function() {
        const file = fileInput.files[0];
        const reader = new FileReader();

        reader.onload = function(e) {
            memberImage.src = e.target.result;
        };

        reader.readAsDataURL(file);
    };
}






//도시
	$(document).ready(function() {
		 var area0 = ["시/도 선택","서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"];
		  var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
		   var area2 = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
		   var area3 = ["대덕구","동구","서구","유성구","중구"];
		   var area4 = ["광산구","남구","동구",     "북구","서구"];
		   var area5 = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
		   var area6 = ["남구","동구","북구","중구","울주군"];
		   var area7 = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
		   var area8 = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
		   var area9 = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
		   var area10 = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
		   var area11 = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
		   var area12 = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
		   var area13 = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
		   var area14 = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
		   var area15 = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
		   var area16 = ["서귀포시","제주시","남제주군","북제주군"];

		   // 시/도 선택 박스 초기화

		   $("select[name^=sido]").each(function() {
		    $selsido = $(this);
		    $.each(eval(area0), function() {
		     $selsido.append("<option value='"+this+"'>"+this+"</option>");
		    });
		    $selsido.next().append("<option value=''>구/군 선택</option>");
		   });



		   // 시/도 선택시 구/군 설정

		   $("select[name^=sido]").change(function() {
		    var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
		    var $gugun = $(this).next(); // 선택영역 군구 객체
		    $("option",$gugun).remove(); // 구군 초기화

		    if(area == "area0")
		     $gugun.append("<option value=''>구/군 선택</option>");
		    else {
		     $.each(eval(area), function() {
		      $gugun.append("<option value='"+this+"'>"+this+"</option>");
		     });
		    }
		   });


		  });


//생년월일
let birthYear=document.getElementById("t_member_birth_year");

	let isYearOptionExisted = false;
	birthYear.addEventListener('focus', function () {
	  if(!isYearOptionExisted) {
	    isYearOptionExisted = true
	    for(let i = 1910; i <= 2023; i++) {
	      let YearOption = document.createElement('option')
	      YearOption.setAttribute('value', i)
	      YearOption.innerText = i
	      this.appendChild(YearOption);
	    }
	  }
	});//select년도 채우기

	let birthMonth=document.getElementById("t_member_birth_month");

	let isMonthOptionExisted = false;
	birthMonth.addEventListener('focus', function () {
	  if(!isMonthOptionExisted) {
	    isMonthOptionExisted = true
	    for(let i = 1; i <= 12; i++) {
	      let MonthOption = document.createElement('option')
	      if(i<10){
	        MonthOption.setAttribute('value','0'+i)
	      }else{
	        MonthOption.setAttribute('value',i)
	      }
	      MonthOption.innerText = 0+i
	      this.appendChild(MonthOption);
	    }
	  }
	});//select 월 채우기

	let birthDay=document.getElementById("t_member_birth_day");

	let isDayOptionExisted = false;
	birthDay.addEventListener('focus', function () {
	  if(!isDayOptionExisted) {
		  isDayOptionExisted = true
	    for(let i = 1; i <= 31; i++) {
	      let DayOption = document.createElement('option')
	      if(i<10){
	        DayOption.setAttribute('value', '0'+i)
	      }else{
	        DayOption.setAttribute('value', i)
	      }

	      DayOption.innerText = i
	      this.appendChild(DayOption);
	    }
	  }
	});//select 일 채우기




	//Ⅰ.추후에 파일의 input태그 value를 가져오기 위해 접근 방식을 정의
/*
	function signUpCheck(){
	let myid = document.getElementById("myid").value                      //파일의 input 태그의 #myid로 접근
	//let idError=document.getElementById("idError").value

	let mypass = document.getElementById("mypass").value
	//let pswError=document.getElementById("pswError").value

	let myname = document.getElementById("myname").value
	//let nameError = document.getElementById("nameError").value

	let nickname = document.getElementById("nickname").value
	//let nickError = document.getElementById("nickError").value

	let birth_year = document.getElementById("birth_year").value

	let birth_month = document.getElementById("birth_year").value

	let birth_day = document.getElementById("birth_year").value

	let myemail = document.getElementById("myemail").value
//	let emailError = document.getElementById("emailError").value

	let myph = document.getElementById("myph").value
	//-----------------------------------------------------

	//Ⅱ.정규표현식 정의
//	const idexamine = /^[A-Za-z0-9]{6,20}$/  // 정규표현식 ex1. 대문자A~Z 소문자a~z 숫자0~9범위 허용, 4글자부터 10글자까지
	//const passexamine //패스워드 정규표현식 정의 위에 형식으로 직접
	//const myfakenameexmain
	//const mybirthexmain
	//const myemailexmain
	//const myphexmain
	//------------------------------------------------------

	*/
	//Ⅲ.함수정의

/*	function signUpCheck() {
		  let myid = document.getElementById("myid").value;
		  let mypass = document.getElementById("mypass").value;
		  let myname = document.getElementById("myname").value;
		  let nickname = document.getElementById("nickname").value;
		  let birth_year = document.getElementById("birth_year").value;
		  let birth_month = document.getElementById("birth_month").value;
		  let birth_day = document.getElementById("birth_day").value;
		  let myemail = document.getElementById("myemail").value;
		  let myph = document.getElementById("myph").value;

		  let regEmail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

		  if (!regEmail.test(myemail)) {
		    alert("담당자 이메일은 이메일 형식(@)만 가능합니다");
		    return;
		  }
	}

	*/





























