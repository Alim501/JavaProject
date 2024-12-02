package com.example.demoAuth.repository;

import com.example.demoAuth.entity.Task;
import com.example.demoAuth.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    Page<Task> findByUser(User user, Pageable pageable);

    // Поиск задач по пользователю и заголовку (регистронезависимый)
    @Query("{ 'user._id': ?0, 'title': { $regex: ?1, $options: 'i' }, 'category._id':  ?2   }")
    Page<Task> findByUserAndTitleContainingAndCategory(String userId, String title, String categoryId, Pageable pageable);

    // Поиск по пользователю и заголовку
    Page<Task> findByUserAndTitleContaining(String userId, String title, Pageable pageable);

    // Поиск по пользователю и категории
    @Query("{ 'user._id': ?0, 'category': ?1 }")
    Page<Task> findByUserAndCategory(String userId, String categoryId, Pageable pageable);

    // Поиск всех задач пользователя
    Page<Task> findByUser(String user, Pageable pageable);


    // Найти задачи по пользователю и точному заголовку
    Page<Task> findByUserAndTitle(User user, String title, Pageable pageable);

}
