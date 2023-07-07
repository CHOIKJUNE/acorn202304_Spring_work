<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/insertform.jsp</title>
<style>
	.container {
		display: flex;
		flex-direction: column;
		justify-content: end;
 		margin-bottom: 10px;
  		height: 30vh;  
	}
	textarea {
		width: 768px;
		height: 300px;
	}
	.content {
		display: flex;
		justify-content: center;
	}
	.title {
		border: 1px solid #e6e6e6;
		padding: 10px;
		border-radius: 4px;
		font-size: 16px;
    	color: #666666;
	}
	body {
		color: #666666;
	}
	body h1 {
		font-size: 34px;
    	color: #333;
	}
	.buttonSet {
		margin-top: 6px;
		padding-left: 320px;
	}
	#resetBtn {
		min-width: 53px;
    	font-size: 16px;
    	color: #fff;
   	    text-align: center;
   		background-color: #747a86;
    	border-radius: 2px;
   	 	padding: 12px 14px 12px 14px;
    	border: 1px solid #747a86;
    	display: inline-block;
    	line-height: 1;
    	cursor: pointer;
	}
	#submitBtn {
	    min-width: 53px;
	    font-size: 16px;
	    color: #fff;
	    text-align: center;
	    background-color: #455d9d;
	    border-radius: 2px;
	    padding: 12px 14px 12px 14px;
	    border: 1px solid #455d9d;
	    display: inline-block;
	    line-height: 1;
	    cursor: pointer;
	}
</style>
</head>
<body style="margin-left:60vh;">
<script src=" https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
<div class="container">
<h1>자유 게시판</h1>
	<form action="insert" method="post">
		<label>제목</label>
			<input class="title form-control" name="title" type="text" placeholder="제목을 입력하세요"">
			<input type="hidden" id="writer" name="writer" value="${id}">
</div>
	<textarea name="content" id="content" rows="10" name="content"></textarea>
		<div class="buttonSet">
			<button type="reset" id="resetBtn">취소</button>
			<button type="submit" onclick="submitContents(this)" id="submitBtn">등록</button>
		</div>
	</form>
	<!-- SmartEditor 에서 필요한 javascript 로딩  -->
	<script src="${pageContext.request.contextPath }/resources/SmartEditor/js/HuskyEZCreator.js"></script>
	<script>
		var oEditors = [];
		
		//추가 글꼴 목록
		//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
		
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "content",
			sSkinURI: "${pageContext.request.contextPath}/resources/SmartEditor/SmartEditor2Skin.html",	
			htParams : {
				bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
				fOnBeforeUnload : function(){
					//alert("완료!");
				}
			}, //boolean
			fOnAppLoad : function(){
				//예제 코드
				//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator: "createSEditor2"
		});
		
		function pasteHTML() {
			var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
			oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
		}
		
		function showHTML() {
			var sHTML = oEditors.getById["content"].getIR();
			alert(sHTML);
		}
			
		function submitContents(elClickedObj) {
			//SmartEditor 에 의해 만들어진(작성한글) 내용이 textarea 의 value 가 되도록 한다. 
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
			
			// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.
			
			try {
				//폼 제출하기 
				elClickedObj.form.submit();
			} catch(e) {}
		}
		
		function setDefaultFont() {
			var sDefaultFont = '궁서';
			var nFontSize = 24;
			oEditors.getById["content"].setDefaultFont(sDefaultFont, nFontSize);
		}
	</script>
	<script>
		$("#submitBtn").on("click",(e)=>{
			e.preventDefault();
	console.log($(".title").val());
	console.log($("#writer").val());
	console.log($("#content").val());
			const board_info = {
					"title": $(".title").val(),
					"writer": $("#writer").val(),
					"content": $("#content").val()
			}
			const jsonBoardInfo = JSON.stringify(board_info);
			fetch("${pageContext.request.contextPath}/board/insert", {
				method: "POST",
				headers: {
				    "Content-Type": "application/json"
		    	},
		    	body: jsonBoardInfo
			})
			.then(res=>res.json)
			.then(data=>{
				console.log(data);
			})
		})
	</script>		
</body>
</html>
