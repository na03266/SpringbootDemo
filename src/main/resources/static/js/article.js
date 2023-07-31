//삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => { //클릭 이벤트 발생시
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, { //패치 메서드로 딜리트 요청
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.'); //요청 완료 메세지
                location.replace('/articles'); //실행시 사용자의 화면을 시작 화면으로 이동.
            });
    });
}

//수정 기능
// id가 modify-btn 인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: `PUT`,
            headers: {
                "content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
        .then(() => {
            alert('수정이 완료되었습니다.');
            location.replace(`/articles/${id}`);
        });
    });

}

//등록 기능
// id가 create-btn인 엘리먼트
const createButton = document.getElementById("cteate-btn")

if(createButton){
    //클릭 이벤트가 감지되면 생성 API 요청
    createButton.addEventListener("click", (event) => {
        fetch("/api/articles",{
            method:"POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        }).then(() =>{
            alert("등록 완료되었습니다.");
            location.replace("/articles");
        });
    });
}