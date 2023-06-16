void function (){
    var user = sessionStorage.getItem("user");
    if (user != null) {
        // todo: 完成退出页面
        document.getElementById("sign").setAttribute("href", "#");
        document.getElementById("sign").onclick =
            function (){
                $.ajax({
                    type: "GET",
                    url: "/user/signout",
                    dataType: 'json',
                    async: true,
                    data: {},
                    success: function (data) {
                        if (data["status"] === 0) {
                            sessionStorage.removeItem("user");
                            document.location.href = window.location.href;
                        }
                    }
                })
            };
        document.getElementById("sign").innerHTML = "Sign out";
    } else {
        document.getElementById("sign").setAttribute("href", "/signin.html")
        document.getElementById("sign").innerHTML = "Sign in";
    }
}();