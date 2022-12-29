
//1st
async function postCommentToServer(cmtData){
	try{
		const url ='/comment/post';
		const config={	//이게 url에 들어가는값
			method:'post',
			headers:{
				'Content-Type':'application/json; charset=utf-8'	//json형태로 보내겠다
			},
			body: JSON.stringify(cmtData)	//cmtData 를 String 형태로 바디에
		};
		const resp = await fetch(url, config);	//config를 url위치("/cmt/post"" CommentController)로 가서  resp에 담음
		const result = await resp.text();	//resp를 text형태로 result에 , out.pring(isOk)값
		return result;	//postCommentToServer(cmtData).then(result 로 감
	} catch(error){
		console.log(error);
	}
}

//2nd 버튼 클릭했을 때, detail.jsp ID cmtPostBtn
document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    console.log('클릭이벤');
	const cmtText = document.getElementById('cmtText').value;   //댓글입력 detail.jsp ID
	console.log(cmtText);
	if(cmtText == null || cmtText==''){	//댓글없으면
		alert('댓글을 입력해주세요.');
        cmtText.focus(); //커서를 입력창으로
		return false;
	}   else {	//댓글있으면
		let cmtData = {
			bno : bnoVal,
			writer : document.getElementById('cmtWriter').innerText, //댓글작성자 detail.jsp ID
			content : cmtText
		};  // 이렇게 3개의 정보를 담아 보내줌
        console.log(cmtData);
		postCommentToServer(cmtData).then(result => {
			if(result > 0){
				alert("댓글등록 성공!!!");
				document.getElementById('cmtText').value = "";	//댓글성공하면 다음 댓글값위해 댓글입력창비워둠
			}
            //화면에 ㄱㄱ
			getCommentList(cmtData.bno);	//댓글리스트 불러오기 CommentList로 감
		})
	}
})

//3rd 댓글 보내주기
async function spreadCommentFromServer(bno) {
    console.log(bno);
    try {
        const resp = await fetch('/comment/'+bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//댓글출력
function getCommentList(bno){
	spreadCommentFromServer(bno).then(result=>{	//cmtList
		console.log(result);
        const ul = document.getElementById('cmtListArea');
		if(result.length>0) {  //댓글이 존재하면
						
			ul.innerHTML ="";
            for(let cvo of result) {
                let li = `<li data-cno="${cvo.cno}" class ="list-group-item d-flex justify-content-between align-items-start>`;
                    li+= `<div class="input-group my-3"><div class="input-group-text">${cvo.writer}</div>`;
                    li+= `<input type="text" class="form-control" id="cmtTextMod" value="${cvo.content}"></div>`;
                    li+= `<span class="badge bg-dark rounded-pill">${cvo.mod_at}</span>`;
                    li+= `<button class="btn btn-sm mod" type="button">수정</button>`;
                    li+= `<button class="btn btn-sm del" type="button">삭제</button>`;
                    li+= `</li>`
                ul.innerHTML += li;
            
            }
        }	else {
                let li = `<li class="list-group-item d-flex justify-content-between align-items-start">댓글이 없습니다</li>`;
                ul.innerHTML += li;
        }
	})
}

//댓글수정
async function editCommentToServer (cmtDataMod){
    try {
        const url = '/comment/'+cmtDataMod.cno;
        
        const config = {
            method : 'put', 
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }    
}
//댓글삭제
async function removeCommentAtServer(cno){
    try {
        const url ='/comment/' +cno;
        const config = {
            method : 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('mod')) {
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        let textContent = li.querySelector('#cmtTextMod').value;
        let cmtDataMod = {
            cno : cnoVal,
            content : textContent
        };
        console.log(cmtDataMod);
        editCommentToServer(cmtDataMod).then(result => {
            if (result > 0) {
                alert('댓글 수정 성공!!');
            }
            getCommentList(bnoVal);
        });
    }   else if(e.target.classList.contains('del')){
        // 삭제 값 처리
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        
        removeCommentAtServer(cnoVal).then(result => {
            if (result > 0) {
                alert('댓글 삭제 성공!!');
            }
            getCommentList(bnoVal);
        })

    };
        
})