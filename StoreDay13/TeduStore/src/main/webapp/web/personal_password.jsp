<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
	 uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 达内学子商城</title>
    <link href="../css/orders.css" rel="Stylesheet"/>
    <link href="../css/header.css" rel="Stylesheet"/>
    <link href="../css/footer.css" rel="Stylesheet"/>
    <link href="../css/personage.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="">首页<span>&gt;</span>个人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <!-- 左边栏-->
    
    <jsp:include page="left.jsp"></jsp:include>
    <!-- 右边栏-->
    <!--个人信息头部-->
    <div class="rightsidebar_box rt">
        <div class="rs_header">
            <span ><a href="../user/showPersonInfo.do">我的信息</a></span>
            <span class="rs_header_active"><a href="../user/showPassword.do">安全管理</a></span>
        </div>

        <!--安全管理 -->
        <div class="rs_content">
            <p class="change_password_title">更改密码</p>
            <div class="new_password">
                <span class="word">输入旧密码：</span>
                <input type="password" name="oldPwd" id="oldPwd"/>
                <span class="change_hint" id="oldPwdSpan"></span>
            </div>
            <div class="new_password">
                <span class="word">输入新密码：</span>
                <input type="password" name="newPwd" id="newPwd"/>
                <span class="change_hint" id="newPwdSpan"></span>
            </div>
            <div class="confirm_password">
                <span class="word">确认新密码：</span>
                <input type="password" name="confirmPwd" id="confirmPwd"/>
                <span class="confirm_hint" id="confirmPwdSpan"></span>
            </div>
            <div class="save_password" onclick="updatePassword()">
                	保存更改
            </div>
        </div>


    </div>
</div>

<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="../images/footer/icon1.png" alt=""/>

        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="../images/footer/icon2.png" alt=""/>

        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="../images/footer/icon3.png" alt=""/>

        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="../images/footer/icon4.png" alt=""/>

        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<div class="foot_bj">
    <div id="foot">
        <div class="lf">
             <p class="footer1"><img src="../images/footer/logo.png" alt="" class=" footLogo"/></p>
             <p class="footer2"><img src="../images/footer/footerFont.png" alt=""/></p>
        </div>
        <div class="foot_left lf">
            <ul>
                <li><a href="#"><h3>买家帮助</h3></a></li>
                <li><a href="#">新手指南</a></li>
                <li><a href="#">服务保障</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>商家帮助</h3></a></li>
                <li><a href="#">商家入驻</a></li>
                <li><a href="#">商家后台</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>关于我们</h3></a></li>
                <li><a href="#">关于达内</a></li>
                <li><a href="#">联系我们</a></li>
                <li>
                    <img src="../images/footer/wechat.png" alt=""/>
                    <img src="../images/footer/sinablog.png" alt=""/>
                </li>
            </ul>
        </div>
        <div class="service">
            <p>学子商城客户端</p>
            <img src="../images/footer/ios.png" class="lf">
            <img src="../images/footer/android.png" alt="" class="lf"/>
        </div>
        <div class="download">
            <img src="../images/footer/erweima.png">
        </div>
		<!-- 页面底部-备案号 #footer -->
        <div class="record">
            &copy;2017 达内集团有限公司 版权所有 京ICP证xxxxxxxxxxx
        </div>
    </div>

</div>
</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/index.js"></script>
<script src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/orders.js"></script>
<script type="text/javascript">
	//验证密码长度
	function checkPasswordLength(pwd){
		return pwd.length>=6&&pwd.length<=9;
	}
	//验证新密码和确认密码是否相同
	function checkEqualsPwd(){
		//获取新密码框的值
		var newPwdValue = $("#newPwd").val();
		//获取确认新密码框的值
		var confirmPwdValue = 
			$("#confirmPwd").val();
		return newPwdValue==confirmPwdValue;
		
	}
	
	//旧密码框长度的验证
	$("#oldPwd").blur(function(){
		//获取旧密码
		var oldPwdValue = $("#oldPwd").val();
		if(checkPasswordLength(oldPwdValue)){
			$("#oldPwdSpan").html("密码格式正确");
			$("#oldPwdSpan").css("color","green");
		}else{
			$("#oldPwdSpan").html("密码格式不正确");
			$("#oldPwdSpan").css("color","red");
		}
	});
	//新密码框长度的验证
	$("#newPwd").blur(function(){
		//获取旧密码
		var newPwdValue = $("#newPwd").val();
		if(checkPasswordLength(newPwdValue)){
			$("#newPwdSpan").html("密码格式正确");
			$("#newPwdSpan").css("color","green");
		}else{
			$("#newPwdSpan").html("密码格式不正确");
			$("#newPwdSpan").css("color","red");
		}
	});
	//新密码和确认新密码是否一致
	$("#confirmPwd").blur(function(){
		if(checkEqualsPwd()){
			$("#confirmPwdSpan").
					html("两次密码输入一致");
			$("#confirmPwdSpan").
					css("color","green");
		}else{
			$("#confirmPwdSpan").
					html("两次密码输入不一致");
			$("#confirmPwdSpan").
					css("color","red");
		}
	});
	//保存修改
	function updatePassword(){
		var oldPwdValue = $("#oldPwd").val();
		var newPwdValue = $("#newPwd").val();
		if(checkPasswordLength(oldPwdValue)&&
			checkPasswordLength(newPwdValue)&&
			checkEqualsPwd()){
			$.ajax({
				url:"../user/updatePassword.do",
				data:"oldPwd="+oldPwdValue+
					  "&newPwd="+newPwdValue,
				type:"post",
				dataType:"json",
				success:function(obj){
					if(obj.state==0){
						alert(obj.message);
					}else{
						location="../user/showLogin.do";
					}
				}
			});
		}
	}
</script>
<!-- 处理侧边栏 -->
<script type="text/javascript">
	$(function(){
		//把所有的dd隐藏
	    $("#leftsidebar_box dd").hide();
		//设置账号管理的列表显示
	    $("#leftsidebar_box .count_managment dd").show();
	   //设置所有的图片为 myOrder2.png
		$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
      //设置账号管理的图片为myOrder1.png
		$("#leftsidebar_box .count_managment").find('img').attr("src","../images/myOrder/myOrder1.png");
       
	});
</script>


</html>













