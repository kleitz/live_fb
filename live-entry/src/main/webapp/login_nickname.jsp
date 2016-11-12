<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>好视通直播会议</title>
    <link href="/styles/public.css" rel="stylesheet">
    <link href="/styles/login.css" rel="stylesheet">
    <script src="/scripts/lib/jquery-1.8.3.min.js"></script>
</head>
<body>
<div id="main">
    <div id="container">
        <h1 class="logo"><a href="#">好视通直播会议</a></h1>
        <div class="login-box l">
            <h3 class="meeting-tit"><strong class="blue">加入会议 / 培训 / 直播</strong> <strong class="second">登录好视通帐号</strong></h3>
            <p class="blue meeting-info">会议室ID : ${room.id} <em class="mar15">会议室名称 : ${room.liveName}</em></p>
            <input style="ime-mode:disabled;" onpaste="return false;"  onkeypress="keyPress(this)" class="invitation-code-text" type="text" placeholder="使用邀请码加入会议/培训/直播">
            <input class="nickname-text" type="text" placeholder="昵称" id="nickName">
            <button class="login-btn" onclick="login();">加入</button>
        </div>
        <div class="download-client r">
            <img class="" src="/images/01.png"/>
            <p>使用客户端加入</p>
            <p>活得更好的用户体验</p>
            <a href="" class="download-btn"></a>
        </div>
    </div>
    <footer>
        <ul class="menu">
            <li><a href=""> 反馈问题</a></li>
            <li><a href=""> 在线帮助</a></li>
            <li><a href=""> FAQ</a></li>
            <li style="border-right: none"><a href=""> 技术支持 : 400-9900-967</a></li>
            <li style="border-right: none" class="border-none">
                <select class="language">
                    <option>简体中文</option>
                    <option>繁体中文</option>
                    <option>English</option>
                </select>
            </li>
        </ul>
        <p class="copyright">©2016深圳银澎云计算有限公司版权所有</p>
    </footer>
</div>
</body>
<script type="text/javascript">
	function login(){
		if($('.invitation-code-text').length>0){
			var codeText = $('.invitation-code-text').val()
		}
		else{
			var codeText = ''
		}
		var nickName = $('#nickName').val();
		var sendData = '{"inviteCode":"'+codeText+'","nickName":"'+nickName+'"}'
		console.log(sendData)
		 $.ajax({
             type:"post",
             dateType:"json",
             data: sendData,
             url : "/live/61y19d/login",
             beforeSend : function(){
                 
             },
             success:function(resp){
            	 console.log(resp);
            	 if(resp.data.code == 1){
            	        window.location.href = '/main.jsp'
            	 }
            	 else{
            		 alert('帐号或密码错误')
            	 }
             },
             complete: function () {
                
             },
             error:function(){
                 alert('error');
             }
         });
	}
	//邀请码输入限制
	function keyPress(obj) {    
     var keyCode = event.keyCode;    
     if ((keyCode >= 48 && keyCode <= 57) && ($(obj).val().length<=8))    
    {    
         event.returnValue = true;    
     } else {    
           event.returnValue = false;    
    }    
 }   
</script>
</html>