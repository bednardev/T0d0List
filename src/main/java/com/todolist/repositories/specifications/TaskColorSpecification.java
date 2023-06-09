package com.todolist.repositories.specifications;

import com.todolist.models.Color;
import com.todolist.models.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

public class TaskColorSpecification implements Specification<Task> {
    private final String color;
    public TaskColorSpecification(String color){
        this.color = color;
    }

    @Override
    public Predicate toPredicate(Root<Task> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("color"), color);
    }
    public static Specification<Task> colorLike(Color color) {
        return (root, query, builder) -> builder.equal(root.get("color"), color);
    }
}
