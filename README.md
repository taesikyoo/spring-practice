# 광일공방 백엔드

- [x] USER MVC 구현(Controller - Service - Repository)
- [x] session을 활용하여 로그인 기능 구현
- [x] POST MVC 구현(Controller - Service - Repository)
- [x] h2 database와 spring data jpa를 활용하여 Repo 구현
- [x] User/Post CRUD 모두 구현

# Blind 댓글 구현
## CommentController 설계 (PostController 설계 추가)
| 기능                    | 전송방식 | URI 예                         |
| :---------------------- | :------- | :----------------------------- |
| 댓글 추가             | POST     | posts/{postId}/comments/add           |
| 특정 포스트의 모든 댓글 가져오기 | GET | posts/{postId}/comments
| 댓글 삭제             | DELETE   | comments/{commentId}/delete |              
| 댓글 수정             | PUT      | comments/{commentId}/edit |             
