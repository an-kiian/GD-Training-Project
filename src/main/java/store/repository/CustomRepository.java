package store.repository;

import com.querydsl.core.types.dsl.EntityPathBase;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomRepository<T, P extends EntityPathBase<T>, ID extends Serializable> extends CrudRepository<T, ID>, QueryDslPredicateExecutor<T>, QuerydslBinderCustomizer<P> {

    @Override
    default void customize(QuerydslBindings querydslBindings, P root) {

    }
}
