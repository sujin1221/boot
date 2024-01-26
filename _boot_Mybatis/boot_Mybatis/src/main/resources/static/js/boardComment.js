console.log("boardComment.js in");
console.log(bnoVal);

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    const cmtText = document.getElementById('cmtText');
    if(cmtText.value == null || cmtText == ''){
        alert("댓글을 입력해주세요");
        cmtText.focus();
        return false;
    } else {
        let cmtData={
            bno: bnoVal,
            writer: document.getElementById('cmtWriter').innerText,
            content: cmtText.value
        };
        //전송
        postCommentToServer(cmtData).then(result=>{
            if(result === '1'){
                alert("댓글 등록 성공~!");
            }
            //뿌리기
            spreadCommentList(bnoVal);
        })
    }
});

async function postCommentToServer(cmtData){
        try {
            const url = "/comment/post";
            const config = {
                method: 'post',
                headers:{
                    'content-type': 'application/json; charset=utf-8' 
                },
                body: JSON.stringify(cmtData)
            };
            const resp = await fetch(url,config);
            const result = await resp.text();
            return result;
        } catch (error) {
            console.log(error);
        }
}

async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch('/comment/'+bno+"/"+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
};

function spreadCommentList(bno, page=1){
    getCommentListFromServer(bno, page).then(result=>{
        console.log(result); //ph cmtList
        const ul = document.getElementById('cmtListArea'); //전체 태그
        if(result.cmtList.length > 0){
            if(page == 1){ //페이지가 1일때
                ul.innerHTML = ''; //원래 있던 댓글 내용 지우도록 설정
            }
            for(let cvo of result.cmtList){
                let li = `<li class="list-group-item" data-cno="${cvo.cno}">`;
                li += `<div class="ms-2 me-auto">`;
                li += `<div class="fw-bold">${cvo.writer}</div>`;
                li += `${cvo.content}</div>`;
                li += `<span class="badge bg-dark rounded-pill">${cvo.modAt}</span>`;
                li += `<button type="button" class="btn btn-outline-warning mod" data-bs-toggle="modal" data-bs-target="#myModal">e</button>`;
                li += `<button type="button" class="btn btn-outline-danger del">x</button>`;
                li += `</li>`;
                ul.innerHTML += li;
            }
            //댓글에 대한 페이지 처리
            let moreBtn = document.getElementById('moreBtn');
            //현재 페이지 번호가 전체 페이지 번호보다 작다면...
            //아직 나와야할 페이지가 더 있다면...
             if(result.pgvo.pageNo < result.endPage){
                 //숨김 속성 해지
                 moreBtn.style.visibility = 'visible'; //표시
                 //페이지 하나 올리기
                 moreBtn.dataset.page = page+1;
             } else {
                moreBtn.style.visibility = 'hidden'; //표시 xx 
             }
        } else {
            let li = `<li class="list-group-item">Comment List Empty</li>`;
            ul.innerHTML = li;
        }     
    })
};

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('mod')){
        //수정
        //타겟에 가장 가까운 li 찾기(내버튼을 포함하고 있는..) 
        let li = e.target.closest('li');
        //nextSibling: 같은 부모의 다음 형제 객체를 반환
        let cmtText = li.querySelector('.fw-bold').nextSibling;
        console.log(cmtText);
        //nodeValue: 현재 선택한 노드의 값을 반환
        document.getElementById('cmtTextMod').value = cmtText.nodeValue;
        document.getElementById('cmtModBtn').setAttribute('data-cno', li.dataset.cno);
    } else if(e.target.id == 'cmtModBtn'){
        //modal 수정 버튼
        let cmtDataMod = {
            cno: e.target.dataset.cno,
            content: document.getElementById('cmtTextMod').value
        };
        //비동기 통신
        editCommentToServer(cmtDataMod).then(result=>{
            if(result == '1'){
                alert("댓글 수정 완료!");
                //modal창 닫혀야함 수정이 완료됐으니깐...
                document.querySelector('.btn-close').click();
            }
            //뿌리기 
            spreadCommentList(bnoVal);
        })
    } else if(e.target.classList.contains('del')){
        //삭제
        
    } else if(e.target.id == 'moreBtn'){
        spreadCommentList(bnoVal, parseInt(e.target.dataset.page));
    }
})

async function editCommentToServer(cmtDataMod){
    try {
        const url = "/comment/edit";
        const config = {
            method: 'put',
            headers: {
                'content-type':'application/json; charset=utf-8'
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


