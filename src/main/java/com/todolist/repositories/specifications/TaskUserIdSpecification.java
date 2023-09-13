
package com.todolist.repositories.specifications;

import com.todolist.models.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class TaskUserIdSpecification implements Specification<Task> {
    private final Long userId;

    public TaskUserIdSpecification(Long userId) {
        this.userId = userId;
    }

    @Override
    public Predicate toPredicate(Root<Task> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(criteriaBuilder.function(("TO_CHAR"), String.class, root.get("userId"), criteriaBuilder.literal('2')), criteriaBuilder.parameter(String.class, "userId"));
    }

    public static Specification<Task> userIdLike(Long userId) {
        return (root, query, builder) -> builder.equal(root.get("userId"), userId);
    }
}