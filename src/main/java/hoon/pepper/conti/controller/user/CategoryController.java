package hoon.pepper.conti.controller.user;

import hoon.pepper.common.wrapper.ResultResponse;
import hoon.pepper.conti.persistence.entity.CategoryEntity;
import hoon.pepper.conti.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/category")
@RequiredArgsConstructor
@Api(tags = "카테고리 컨트롤러")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation(value="콘티 리스트")
    public ResultResponse<List<CategoryEntity>> getCategoryList() {
        return new ResultResponse<>(categoryService.getCategoryList());
    }
}
