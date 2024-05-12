package ru.svetlanailina.helpdesk.backend17.service;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.svetlanailina.helpdesk.backend17.entity.Category;
import ru.svetlanailina.helpdesk.backend17.repo.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Cacheable(cacheNames = "categories")
    public List<Category> findAll(String email) {
        return categoryRepository.findByUserEmailOrderByTitleAsc(email);
    }

    public List<Category> findByTitle(String title, String email) {
        return categoryRepository.findByTitle(title, email);
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public void update(Category category) {
        categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
