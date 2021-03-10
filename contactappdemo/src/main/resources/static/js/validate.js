function kiemtrasdt()
{
    var sdt = document.getElementById('sdt').value;
    var pattSdt = /^\d{9,12}/;
    var check = true;

    if (sdt == null || sdt == "") {
        document.getElementById("messloisdt").innerHTML = "* Số điện thoại không được để trống";
        check = false;
    } else if (pattSdt.test(sdt) == false) {
        document.getElementById("messloisdt").innerHTML = "* Số điện thoại từ 9-12 số";
        check = false;
    } else {
        document.getElementById("messloisdt").innerHTML = "";
    }
    return check;
}

function kiemtraten()
{
    var name = document.getElementById('name').value;
    var check = true;

    if (name == null || name == "") {
        document.getElementById("messloiname").innerHTML = "*Tên không được để trống";
        check = false;
    }
    else {
        document.getElementById("messloiname").innerHTML = "";
    }
    return check;
}


function kiemtrapassword()
{

    var password = document.getElementById('password').value;
    var check = true;

    if (password == null || password == "") {
        document.getElementById("messloipassword").innerHTML = "*Password không được để trống";
        check = false;
    }
    else {
        document.getElementById("messloipassword").innerHTML = "";
    }
    return check;
}


function them() {

    if(kiemtraten()==true && kiemtrasdt()==true){
      return true;
    }    else {
        //alert("Thêm không thành công");
        return false;
    }




}

function dangky() {

    if(kiemtraten()==true && kiemtrasdt()==true && kiemtrapassword()==true){
      return true;
    }    else {
        //alert("Đăng ký không thành công");
        return false;
    }




}