/**
 *
 */
/*
1. ID : 4~12자 이내의 영어와 숫자로 입력<br>
2. 첫문자는 숫자로 시작할 수 없음.<br>
3. Password : 4~12자 이내며, 입력 필수.<br><br>
*/

//아이디 검사 ---------------------------------------------------------------------------

   function Login()
   {
           var form = document.form1;

                  //아이디에서 입력 필수 조건문
                  if (form.id.value == "")
                  {
                          alert("아이디를 입력하세요!");
                          form.id.focus();//포커스를 id박스로 이동.
                          return;
                  }

                  //아이디 입력 문자수를 4~12자로 제한하는 조건문

                  if (form.id.value.length < 4 || form.id.value.length > 12)
                  {
                   alert("아이디는 4~12자 이내로 입력 가능합니다!");

                   form.id.select();//입력한 문자를 선택 상태로 만듬.
                   return;
                  }

            //입력된 문자의 길이만큼 루프를 돌면서 검사

            for (i=0; i<form.id.value.length; i++)
            {
                   var ch = form.id.value.charAt(i);//문자를 반환(정수형), 범위 검사 가능

                   //입력된 문자를 검사

                   if ( ( ch < "a" || ch > "z") && (ch < "A" || ch > "Z") && (ch < "0" || ch > "9" ) )
                   {
                    alert("아이디는 영문 소문자로만 입력 가능 합니다!");
                    form.id.select();
                    return;
                   }
            }

            //입력된 첫번째 문자가 숫자인지 검사하는 조건문

            if (!isNaN(form.id.value.substr(0,1)))

            {
                 alert("아이디는 숫자로 시작할 수 없습니다!");
                 form.id.select();
                 return;
            }



//패스워드 검사 -------------------------------------------------------------------------

            if (form.password.value == "")
            {
                 alert("패스워드를 입력하세요!");
                 form.password.focus();//포커스를 Password박스로 이동.
                 return;
            }



            if (form.password.value.length < 4 || form.password.value.length > 12)
            {
                 alert("비밀번호는 4~12자 이내로 입력 가능 합니다!");

                 form.password.select();
                 return;
            }

}


