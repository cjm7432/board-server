package com.mydev.boardserver.service;

import com.mydev.boardserver.dto.CategoryDTO;
public interface CategoryService {

    void register(String accountId, CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void delete(int categoryId);
}
