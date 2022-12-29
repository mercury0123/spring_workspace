document.getElementById("trigger").addEventListener("click", function(){
    document.getElementById("files").click();
});

//정규 표현식을 이용한 생성자 함수 만들기 \ 시작 $ 끝
//fileload 시, 특정확장자만 + 특정용량으로만 업로드 가능하게
//ex) 실행파일 금지, 이미지 파일만 업로드
const regExp = new RegExp("\.(exe|sh|bat|msi|dll|js)$");    //실행파일 금지
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif|bmp)$");  //이미지 파일
const maxSize = 1024*1024*20;                               //20MB 이상 업로드 금지

function fileSizeValidation (fileName, filesize) {
    if (regExp.test(fileName)) {            //실행파일 존재시 0 리턴 거부
        return 0;
    } else if (!regExpImg.test(fileName)){  //이미지파일 부존재시 0 리턴 거부
        return 0;
    } else if (filesize > maxSize) {
        return 0;
    } else {
        return 1;
    }
}

document.addEventListener("change", (e)=>{
    if(e.target.id == "files") {    //files 에 어떤 움직임이 있다면
       
        //input type = "file" element fileObject 객체로 리턴
        const fileObject = document.getElementById("files").files;
        console.log(fileObject);

        let div = document.getElementById("fileZone");  //register div 값 
        div.innerHTML="";
        //파일 여러개 첨부시 list 형태로 출력
        let ul = `<ul class ="list-group list-group-flush">`;
        let isOk = 1;
        for (let file of fileObject) {            
            let validResult = fileSizeValidation(file.name, file.size); //위 함수 참고. 0 or 1 값
            // 여러 첨부 파일 모두가 조건을 만족해야 OK 하게 설정
            isOk *= validResult;    //0이 하나라도 들어오면 0 값, 업로드 거부!!
            ul+=`<li class="list-group-item d-flex justify-content-between align-items-start">`;
            //업로드 가능여부 표시
            ul+= `${validResult ? '<div class="fw-bold">업로드 가능' 
                : '<div class="fw-bold text-danger">업로드 불가'}</div>`;
            ul+= `${file.name}`;
            ul+= `<span class="badge bg-${validResult ? "success":"danger"} rounded-pill">
                ${file.size} Bytes</span></li>`;
            
        }
        ul+=`</ul>`;
        div.innerHTML = ul;

        if(isOk == 0) {
            document.getElementById("regBtn").disabled = true; //조건에 맞지 않는 파일 첨부시, 클릭 거부!!
        }

    }
});