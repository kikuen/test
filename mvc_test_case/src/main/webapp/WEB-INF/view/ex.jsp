<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>


<div id="displayDiv">

</div>

<script>
    var source = "<h1><p>{{name}}</p> <p>{{userid}}</p> <p>{{addr}}</p></h1>";
    var template = Handlebars.compile(source);
    var data  = {name:"홍길동", userid:"user00", addr:"조선 한양"};

    $("#displayDiv").html(template(data));

</script>

<div id="displayDiv">

</div>

<script id="template" type="text/x-handlebars-template">
    <span> {{ name }}</span>
    <div>
        <span> {{ userid }}</span>
        <span> {{ addr }}</span>
    </div>
</script>
<script>
    var source = $("#template").html();
    alert(source);
    var template = Handlebars.compile(source);
    var data  = {name:"홍길동", userid:"user00", addr:"조선 한양"};

    $("#displayDiv").html(template(data));

</script>


<ul id="replies">

</ul>

<script id="template2" type="text/x-handlebars-template">
    {{#each .}}
    <li class="replyLi">
        <div>{{rno}}</div>
        <div>{{replytext}}</div>
        <div>{{replydate}}</div>
    </li>
    {{/each}}
</script>
<script>
    var source = $("#template2").html();
    var template = Handlebars.compile(source);
    var data  = [
        {rno:1, replytext:'1번 댓글....', replydate:new Date()},
        {rno:2, replytext:'2번 댓글....', replydate:new Date()},
        {rno:3, replytext:'3번 댓글....', replydate:new Date()},
        {rno:4, replytext:'4번 댓글....', replydate:new Date()},
        {rno:5, replytext:'5번 댓글....', replydate:new Date()}
    ];
    $("#replies").html(template(data));

</script>
</body>
</html>
