void function (){
    var user = sessionStorage.getItem("user");
    if (user != null) {
        // todo: 完成退出页面
        document.getElementById("sign").setAttribute("href", "/user/signout")
        document.getElementById("sign").innerHTML = "Sign out";
    } else {
        document.getElementById("sign").setAttribute("href", "/signin.html")
        document.getElementById("sign").innerHTML = "Sign in";
    }
}();