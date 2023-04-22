// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB
package com.sparta.spring_post.service;

import com.sparta.spring_post.dto.PostRequestDto;
import com.sparta.spring_post.dto.PostResponseDto;
import com.sparta.spring_post.entity.Post;
import com.sparta.spring_post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
// @Service : 해당 클래스를 루트 컨테이너에 빈 ( Bean) 객체로 생성해주는 어노테이션
// 부모 어노테이션인 @Component 를 붙여줘도 똑같이 루트 컨테이너에 생성되지만 가시성이 떨어지기 때문에 잘 사용하지 않음
@RequiredArgsConstructor
// @RequiredArgsConstructor : final 이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 lombok 어노테이션
public class PostService {

    // PostRepository 연결
    private final PostRepository postRepository;
    // private 필드를 선언. 해당 필드는 PostRepository 타입이며 생성자를 통해 PostRepository 매개변수를 전달하여 초기화함

    // 목록 조회
    @Transactional(readOnly = true)
    // Transaction : DB 관리 시스템 또는 유사한 시스템에서 상호작용의 단위 ( 더 이상 쪼개질 수 없는 최소의 연산 )
    // @Transactional : 해당 범위 내 메서드가 트랜잭션이 되도록 보장해줌 ( 선언적 트랜잭션 - 선언만으로 관리를 용이하게 해줌 )
    // (readOnly = true) : 읽기 전용 모드 - 예상치 못한 Entity 의 생성, 수정, 삭제를 예방할 수 있음
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
        // postRepository 인스턴스를 사용하여 findAllByOrderByModifiedAtDesc() 메서드를 호출함
        List<PostResponseDto> dtos = new ArrayList<>();
        // 게시물 목록을 검색한 후 각 게시물에 대해 새 PostResponseDto 개체를 만듦
        for (Post post : posts) {
            dtos.add(new PostResponseDto(post));
        }
        return dtos;
        // DB 의 모든 게시물을 나타내는 PostResponseDto 개체 목록 반환
    }

    // 상세 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {       // 게시물의 ID 를 나타내는 Long id 매개 변수를 사용함
        Post post = postRepository.findById(id).orElseThrow();  // findById() 메서드를 호출하여 DB 에서 게시물을 검색함
                                                                // 게시물을 찾을 수 없으면 예외가 발생함
        PostResponseDto dtos = new PostResponseDto(post);
        // 새 PostResponseDto 개체를 만들고 변수를 저장함
        dtos.getClass();
        // getClass() : 개체의 런타임 클래스를 반환하는 Java Object 클래스에 내장된 메서드
        // PostResponseDto 클래스의 클래스 개체가 반환됨
        // 사실 getClass() 말고 더 좋은 방법이 있을 것 같음
        return dtos;
    }

    // 추가
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = postRepository.save(postRequestDto.toEntity());
        // toEntity() : DTO 개체를 DB 의 게시물을 나타내는 Entity 인 Post 클래스의 인스턴스로 변환하는 PostRequestDto 클래스에 구현된 사용자 지정 메서드임
        // postRepository.save() : 새로 작성된 Post Entity 를 DB 에 저장함
        return new PostResponseDto(post);
        // 새로 생성된 Post Entity 를 기반으로 새 PostResponseDto 개체를 생성하고 반환함
    }

    // 수정
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow();
        // postRepository.findById() 메서드를 사용하여 해당 ID를 사용하여 DB 에서 기존 게시물을 검색함
        // 존재하지 않으면 예외를 던짐
        PostResponseDto dtos = new PostResponseDto(post);
        // 검색된 Post Entity 를 기반으로 새 PostResponseDto 개체를 만들고 변수에 할당함
        if (!post.getPassword().equals(postRequestDto.getPassword())) {
            // 검색된 게시물의 암호가 PostRequestDto 개체에 제공된 암호와 일치하는지 확인
            return dtos;
            // 일치하지 않으면 메서드는 게시물을 업데이트 하지 않고 기존 PostResponseDto 개체를 반환함
        }
        post.update(postRequestDto);
        // update() : PostRequestDto 개체의 데이터를 기반으로 Post Entity 의 필드를 업데이트하는 Post 클래스에 구현된 사용자 지정 메서드
        return new PostResponseDto(post);
        // 새로 생성된 Post Entity 를 기반으로 새 PostResponseDto 개체를 생성하고 반환함
    }

    // 삭제
    @Transactional
    public String deletePost(Long id, String password) {
        // id 와 password 를 기반으로 DB 에서 게시물을 삭제하고 삭제 성공 여부를 나타내는 문자열을 반환함
        Post post = postRepository.findById(id).orElseThrow();
        // postRepository.findById() 메서드를 사용하여 해당 ID를 사용하여 DB 에서 기존 게시물을 검색함
        // 존재하지 않으면 예외를 던짐
        if (!post.getPassword().equals(password)) {
            // 검색된 게시물의 암호가 인수로 제공된 암호와 일치하는지 확인
            return "비밀번호가 일치하지 않습니다.";
            // 일치하지 않으면 "비밀번호가 일치하지 않습니다." 문자열 반환
        }
        postRepository.deleteById(id);
        // 암호가 일치하면 deleteById() 메서드 호출
        return "성공적으로 삭제되었습니다.";
        // "성공적으로 삭제되었습니다." 문자열 반환
    }

}
