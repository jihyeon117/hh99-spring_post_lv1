// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB
package com.sparta.spring_post.controller;

import com.sparta.spring_post.dto.PostRequestDto;
import com.sparta.spring_post.dto.PostResponseDto;
import com.sparta.spring_post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     // @Controller + @ResponseBody 컨트롤러 클래스 하위 메서드에 @ResponseBody 어노테이션을 붙이지 않아도 문자열과 JSON 등을 전송할 수 있음
                    // @Controller : View 에 표시될 데이터가 있는 Model 객체를 만들고 올바른 View 를 선택하는 일
                    // @RestController : 단순히 객체만들 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP 응답에 담아서 전송함
@RequiredArgsConstructor        // 의존성주입 종류 : Constructor, Setter, Field 타입이 있음
                                // @RequiredArgsConstructor : final 이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 lombok 어노테이션
public class PostController {

    // PostService 연결
    private final PostService postService;      // private 필드를 선언. 해당 필드는 PostService 타입이며 생성자를 통해 PostService 매개변수를 전달하여 초기화함
                                                // PostController 는 PostService 가 제공하는 메서드를 사용하여 데이터에 액세스하고 조작할 수 있음 ( 종속성 주입 )
                                                // MVC 디자인 패턴을 사용할 때 사용함

    // 목록 조회
    @GetMapping("/api/posts")       // HTTP GET ( URL : /api/posts )
    public List<PostResponseDto> getAllPosts() {        // PostResponseDto 객체의 List 를 반환하는 메서드
        // DTO 를 사용하면 반환되는 정보를 캡슐화하고 사용자 정의 할 수 있으므로 성능과 보안을 개선할 수 있음
        return postService.getAllPosts();               // PostService 객체의 getAllPosts() 메서드 호출
    }

    // 상세 조회
    @GetMapping("/api/posts/{id}")      // HTTP GET ( URL : /api/posts/{id} )
    public PostResponseDto getPost(@PathVariable Long id) {     // @PathVariable : URL 경로에 변수를 넣어주는 어노테이션
                                                                // null 이나 공백값이 들어가는 파라미터는 적용 X
        return postService.getPost(id);             // PostService 객체의 getPost() 메서드 호출
    }

    // 추가
    @PostMapping("/api/post")       // HTTP POST ( URL : /api/post )
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        // 클라이언트에서 받은 요청을 객체로 바인딩하기 위한 방법 3가지 : @RequestParam, @RequestBody, @ModelAttribute
        // @RequestParam : 1개의 HTTP 요청 파라미터를 받아오기 위해 사용됨 ( Body 를 직접 조회하지 않음 ) - 1:1 매핑
        // @RequestBody : 클라이언트가 전송하는 JSON 형태의 HTTP Body 내용을 Java 객체로 변환시켜줌 - 객체 매핑
        // ( 바인딩이 아닌 변환이기 때문에 값을 주입하기 위한 생성자나 Setter 가 없어도 저장됨 )
        // @ModelAttribute : 클라이언트가 요청시 전달되는 값을 객체 형태로 매핑해줌 - 객체 매핑
        // ( 변환이 아닌 바인딩이기 때문에 변수들의 Setter 함수가 없으면 저장되지 않음 )
        return postService.createPost(postRequestDto);      // PostService 객체의 createPost() 메서드 호출
    }

    // 수정
    @PutMapping("/api/post/{id}")       // HTTP PUT ( URL : /api/post/{id} )
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto);      // PostService 객체의 updatePost() 메서드 호출
    }

    // 삭제
    @DeleteMapping("/api/post/{id}")        // HTTP DELETE ( URL : /api/post/{id} )
    public String deletePost(@PathVariable Long id, @RequestParam("password") String password) {
        // password 변수는 "password" 이름의 요청 매개변수에서 값을 받음
        // 만약 "password" 매개변수가 요청에서 발견되지 않으면 "400 Bad Request" 오류가 반환됨
        return postService.deletePost(id, password);        // PostService 객체의 deletePost() 메서드 호출
    }

}
