//삭제 기능
const deleteButton = document.getElementById('delete-btn');

if(deleteButton){
    deleteButton.addEventListener('click',event =>{ //클릭 이벤트 발생시
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