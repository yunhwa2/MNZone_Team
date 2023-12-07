/**
 * 
 */
$(document).ready(function(){
	$("#catkind_box").hide();	
	$("#dog_check").prop("checked",true);
	//묘종선택 숨기기 & 강아지 체크 상태로 시작

    	$("#pet_name").blur(function(){
    		nameNotEmpty();
    	});	
});

	
function onClickUpload(){
	let mypet_img = document.getElementById("mypet_img");
	mypet_img.click();
} 
// 사진 추가

function orCheck(chk,name){
	let obj = document.getElementsByName(name);
	for(let i=0; i<obj.length;i++){
		if(obj[i] != chk){
			obj[i].checked=false;
		}
	}
	if(name=='dccheck'){
		if($("#dog_check").is(':checked')){
			$("#catkind_box").hide();
			$("#dogkind_box").show();
		}else if($("#cat_check").is(':checked')){
			$("#catkind_box").show();
			$("#dogkind_box").hide();
		}
	}
}
//체크박스 중복체크 방지  & 묘종견종 요소 하나만 뜨기


function nameNotEmpty(){
	if($("#pet_name").val().length == 0){						
		$("#name_lb").html("멍냥이의 이름을 알려주세요!");
		$("#name_lb").css({
			"color":"red",
				"fontSize":"13px",
				"marginTop":"15px"
					
		});
		$("#pet_name").focus();								
	}else{
		$("#name_lb").html("");
	}
}//이름 유효성

function dateNotEmpty(value){
	const dateRegex = /^\d{4}-\d{2}-\d{2}$/; //? YYYY-MM-DD 형식의 정규식
	if (dateRegex.test(value)){
			return true;
		}else {
			return false;
		}
}//생년월일 유효성



function checknull(){
	if(!dateNotEmpty($("#birthday_input").val()))
	{
		$("#birthday_input_lb").html("올바르게 입력해주세요!");
		$("#birthday_input_lb").css({
			"color":"red",
				"fontSize":"13px",
				"marginTop":"15px"		
		});	
		return false;
	}else if($("#wieght_input").val().length == 0){
		$("#weight_input_lb").html("멍냥이의 몸무게를 알려주세요!");
		$("#weight_input_lb").css({
			"color":"red",
				"fontSize":"13px",
				"marginTop":"15px"		
		});	
		return false;
	}else if($("#dogkind option:selected").val()=="else_dog"){
		if($("#dog_write").val().length == 0){
			$("#dog_write_lb").html("직접입력해주세요!");
			$("#dog_write_lb").css({
				"color":"red",
					"fontSize":"13px",
					"marginTop":"15px"	
			});	
			return false;
		}
	}else if($("#catkind option:selected").val()=="else_cat"){
		if($("#cat_write").val().length == 0){
			$("#cat_write_lb").html("직접입력해주세요!");
			$("#cat_write_lb").css({
				"color":"red",
					"fontSize":"13px",
					"marginTop":"15px"	
			});	
			return false;
		}
	}else{
		return true;	
	}
	
}//나머지 유효성

















