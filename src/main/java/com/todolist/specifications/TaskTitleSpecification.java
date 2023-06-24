package com.todolist.specifications;

import com.todolist.models.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

public class TaskTitleSpecification implements Specification<Task> {
    private final String title;

    public TaskTitleSpecification(String title){
        this.title = title;
    }

    @Override
    public Predicate toPredicate(Root<Task> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("title"), "%"+title+"%");
    }
}
