package jamy.blog.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jamy.blog.dto.post.WritePostReq;
import jamy.blog.post.Post;
import jamy.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final 또는 @NotNull 이 붙은 필드의 생성자를 자동으로 생성해준다.
@CrossOrigin(origins = "*", allowedHeaders = "*") // CORS(교차 출처 자원 공유) 해결해주기.
@RequestMapping("/post") // 공통적인 url은 class에 @RequestMapping으로 설정해준다.
@RestController // @Controller + @ResponseBody (Java 객체를 HTTP 요청의 Body 내용으로 매핑하여 반환한다.)
public class PostController {
    private final PostService postService;

    @ApiOperation(value = "게시글을 등록", notes = "게시글을 등록합니다.") // Swagger 설명 설정
    @PostMapping("")
    public ResponseEntity<Post> save(@RequestBody WritePostReq writePostReq) {
        return new ResponseEntity<>(postService.save(writePostReq), HttpStatus.CREATED);
    }

    @ApiOperation(value = "게시글 전체 조회", notes = "게시글을 전체 조회합니다.")
    @GetMapping("")
    public ResponseEntity<List<Post>> findAll() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @ApiOperation(value = "게시글 단건 조회", notes = "게시글 id를 이용하여 단건 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }



    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @ApiOperation(value = "게시글 단건 수정", notes = "id에 해당하는 게시글을 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<Post> updateById(@PathVariable Long id, @RequestBody WritePostReq writePostReq) {
        return new ResponseEntity<>(postService.updateById(id, writePostReq), HttpStatus.OK);
    }

    @ApiImplicitParam(name = "id", value = "게시글 아이디")
    @ApiOperation(value = "게시글 단건 삭제", notes = "id에 해당하는 게시글을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.deleteById(id), HttpStatus.OK);
    }
}
