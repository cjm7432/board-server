package com.mydev.boardserver.controller;

import com.mydev.boardserver.aop.LoginCheck;
import com.mydev.boardserver.dto.CategoryDTO;
import com.mydev.boardserver.service.impl.CategoryServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public void registerCategory(String accountId,
                                 @RequestBody CategoryDTO categoryDTO) {

        categoryService.register(accountId, categoryDTO);
    }

    @PatchMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public void updateCategory(String accountId,
                               @PathVariable(name = "categoryId") int categoryId,
                               @RequestBody CategoryRequest categoryRequest) {

        CategoryDTO categoryDTO = new CategoryDTO(categoryId, categoryRequest.getName(),
                                                    CategoryDTO.SortStatus.NEWEST, 10, 1);
        categoryService.update(categoryDTO);
    }

    @DeleteMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public void deleteCategory(String accountId,
                               @PathVariable(name = "categoryId") int categoryId) {

        categoryService.delete(categoryId);
    }

    @Getter
    @Setter
    private static class CategoryRequest {
        private int id;
        private String name;
    }

}
