/**
 * 
 */
$(document).ready(function(){
	$("#catkind_box").hide();
	$("#dog_check").prop("checked",true);
	$("#myPetCategory").val("DOG");
	//묘종선택 숨기기 & 강아지 체크 상태로 시작

    $("#pet_name").blur(function(){
        nameNotEmpty();
    });

    bindDomEvent();
});

//사용자가 이미지파일을 선택하면 이미지인지 확인
function bindDomEvent(){
    $(".mypet_img").on("change", function() {
        var fileName = $(this).val().split("\\").pop();  //이미지 파일명
        var fileExt = fileName.substring(fileName.lastIndexOf(".")+1); // 확장자 추출
        fileExt = fileExt.toLowerCase(); //소문자 변환

        if(fileExt != "jpg" && fileExt != "jpeg" && fileExt != "gif" && fileExt != "png" && fileExt != "bmp"){
            alert("이미지 파일만 등록이 가능합니다.");
            return;
        }

       //$(this).siblings(".custom-file-label").html(fileName);//이걸 이미지로 바꿔야함
    });
}

//function onClickUpload(){
//	let mypet_img = document.getElementById("fileInput");
//	mypet_img.click();
//}//사진추가

function uploadFile() {
    const fileInput = document.getElementById('fileInput');
    const previewImage = document.getElementById('previewImage');

    fileInput.onchange = function() {
        const file = fileInput.files[0];
        const reader = new FileReader();

        reader.onload = function(e) {
            previewImage.src = e.target.result;
        };

        reader.readAsDataURL(file);
    };
}// 사진 미리보기

function orCheck(chk, showSelect){
          let name = $(chk).attr('name');
             $('[name="' + name + '"]').each(function(index, element) {
                 if (element !== chk && $(element).is(':checked')) {
                     $(element).prop('checked', false);
                 }

                 if (element == chk && $(element).is(':checked')){
                 console.log( $(element).val() )
                 console.log(  $('[name="myPet' + name + '"]').val() )
                 console.log(name)

                    $('[name="myPet' + name + '"]').val( $(element).val() );
                 }
             });
	if(showSelect == 'dccheck'){
		if($("#dog_check").is(':checked')){
			$("#catkind_box").hide();
			$("#dogkind_box").show();
		}else{
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
	}else if($("#weight_input").val().length == 0){
		$("#weight_input_lb").html("멍냥이의 몸무게를 알려주세요!");
		$("#weight_input_lb").css({
			"color":"red",
				"fontSize":"13px",
				"marginTop":"15px"		
		});	
		return false;
	}else if($("#dogkind option:selected").val()=="else_dog"){
		if($("#dog_write").val().length == 0){
			$("#dog_write_lb").html("직접 입력해주세요!");
			$("#dog_write_lb").css({
				"color":"red",
					"fontSize":"13px",
					"marginTop":"15px"	
			});	
			return false;
		}
	}else if($("#catkind option:selected").val()=="else_cat"){
		if($("#cat_write").val().length == 0){
			$("#cat_write_lb").html("직접 입력해주세요!");
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


function petKindSet(kind){
    $("#myPetKind").val( $(kind).val() )
    let id = $(kind).attr('id');
    let value = $(kind).val();

    if(id == "dogkind" || id == "catkind"){
        $("#dog_write").val("");
        $("#cat_write").val("");
    }

    if(id == "dogkind" && value != "else_dog"){
        $("#dog_write").hide();
    }else if(id == "dogkind" && value == "else_dog"){
             $("#dog_write").show();
         }

    if(id == "catkind" && value != "else_cat"){
        $("#cat_write").hide();
    }else if(id == "catkind" && value == "else_cat"){
        $("#cat_write").show();
    }
}
//묘종, 견종 값 가져오기















