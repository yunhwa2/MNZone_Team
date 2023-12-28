/**
 * 
 */

const log=document.getElementById("logking")
const other=document.getElementsByClassName("other")
const log_log=document.getElementsByClassName("log_log")
const t_member_input=document.getElementsByClassName("t_member_input")

function login1(){
	log.classList.remove('blind')
	other.classList.remove('blind')
//	document.querySelector('body').classList.add("oh")

}
function logout(){

	log.classList.add("blind")
	other.classList.add("blind")
	t_member.classList.add("blind")
//	document.querySelector('body').classList.remove("oh")

	for(let i=0;i<t_member_input.length;i++){
		t_member_input[i].value=null
	}
	for(let i=0;i<log_log.length;i++){
		log_log[i].value=null
	}
}

const t_member=document.getElementById("t_member")

function signin() {
    log.classList.add("blind");
    t_member.classList.remove("blind");

}

//1. ID : 4~12자 이내의 영어와 숫자로 입력<br>
//2. 첫문자는 숫자로 시작할 수 없음.<br>
//3. Password : 4~12자 이내며, 입력 필수.<br><br>

//아이디 검사 ---------------------------------------------------------------------------


function Login()
{
        var form = document.form1;

               //아이디에서 입력 필수 조건문
               if (form.txtID.value == "")
               {
                       alert("아이디를 입력하세요!");
                       form.txtID.focus();//포커스를 id박스로 이동.
                       return;
               }

               //아이디 입력 문자수를 4~12자로 제한하는 조건문

               if (form.txtID.value.length < 4 || form.txtID.value.length > 12)
               {
                alert("아이디는 4~12자 이내로 입력 가능합니다!");

                form.txtID.select();//입력한 문자를 선택 상태로 만듬.
                return;
               }

         //입력된 문자의 길이만큼 루프를 돌면서 검사

         for (i=0; i<form.txtID.value.length; i++)
         {
                var ch = form.txtID.value.charAt(i);//문자를 반환(정수형), 범위 검사 가능

                //입력된 문자를 검사

                if ( ( ch < "a" || ch > "z") && (ch < "A" || ch > "Z") && (ch < "0" || ch > "9" ) )
                {
                 alert("아이디는 영문 소문자로만 입력 가능 합니다!");
                 form.txtID.select();
                 return;
                }
         }

         //입력된 첫번째 문자가 숫자인지 검사하는 조건문

         if (!isNaN(form.txtID.value.substr(0,1)))

         {
              alert("아이디는 숫자로 시작할 수 없습니다!");
              form.txtID.select();
              return;
         }



//패스워드 검사 -------------------------------------------------------------------------

         if (form.txtPwd.value == "")
         {
              alert("패스워드를 입력하세요!");
              form.txtPwd.focus();//포커스를 Password박스로 이동.
              return;
         }



         if (form.txtPwd.value.length < 4 || form.txtPwd.value.length > 12)
         {
              alert("비밀번호는 4~12자 이내로 입력 가능 합니다!");

              form.txtPwd.select();
              return;
         }

}







