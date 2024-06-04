package io.zenwave360.example.delivery.infrastructure.mongodb.inmemory;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;

public class InMemoryMongodbRepository<T> implements MongoRepository<T, String> {

    public interface PrimaryKeyGenerator<String> {

        String next();

    }

    private final Map<String, T> entities = new HashMap<>();

    // private final PrimaryKeyGenerator<String> primaryKeyGenerator = () ->
    // UUID.randomUUID().toString();
    private final PrimaryKeyGenerator<String> primaryKeyGenerator = new PrimaryKeyGenerator<String>() {
        private int next = 0;

        @Override
        public String next() {
            return String.valueOf(next++);
        }
    };

    public Map<String, T> getEntities() {
        return entities;
    }

    public boolean containsKey(String key) {
        return entities.containsKey(key);
    }

    public boolean containsEntity(T entity) {
        return entities.containsValue(entity);
    }

    protected <F> F readField(Object target, String fieldName) {
        try {
            return (F) FieldUtils.readField(target, fieldName, true);
        }
        catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Field not found or not accessible: " + fieldName);
        }
    }

    protected void writeField(Object target, String fieldName, Object value) {
        try {
            FieldUtils.writeField(target, fieldName, value, true);
        }
        catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Field not found or not accessible: " + fieldName);
        }
    }

    protected <F> List<T> findByField(String fieldName, F value) {
        return entities.values()
            .stream()
            .filter(entity -> isSameValue(value, readField(entity, fieldName)))
            .collect(Collectors.toList());
    }

    protected <F> T findByUniqueField(String fieldName, F value) {
        List<T> foundEntities = findByField(fieldName, value);
        if (foundEntities.isEmpty()) {
            return null;
        }
        else if (foundEntities.size() == 1) {
            return foundEntities.get(0);
        }
        else {
            throw new IllegalArgumentException(String.format("Field %s is not unique, found %s entities: %s", fieldName,
                    foundEntities.size(), foundEntities));
        }
    }

    protected boolean contains(Iterable values, Object value) {
        return StreamSupport.stream(values.spliterator(), false).anyMatch(e -> isSameValue(e, value));
    }

    protected boolean isSameValue(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        else {
            return o1.equals(o2);
        }
    }

    public <S extends T> S save(S entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String id = firstNonNull(readField(entity, "id"), primaryKeyGenerator.next());
        writeField(entity, "id", id);
        entities.put(id, entity);
        return entity;
    }

    public <S extends T> List<S> saveAll(Iterable<S> entitiesToSave) {
        if (entitiesToSave == null) {
            throw new IllegalArgumentException("entitiesToSave must not be null");
        }
        return StreamSupport.stream(entitiesToSave.spliterator(), false).map(e -> save(e)).collect(Collectors.toList());
    }

    public Optional<T> findById(String id) {
        return Optional.ofNullable(findByUniqueField("id", id));
    }

    public boolean existsById(String id) {
        return findById(id).isPresent();
    }

    public List<T> findAll() {
        return new ArrayList(entities.values());
    }

    public List<T> findAllById(Iterable<String> ids) {
        return entities.values().stream().filter(e -> contains(ids, readField(e, "id"))).collect(Collectors.toList());
    }

    public long count() {
        return entities.size();
    }

    public void deleteById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        T removedEntity = entities.remove(id);
        if (removedEntity == null) {
            throw new EmptyResultDataAccessException("Entity with id " + id + " not found", 1);
        }
    }

    public void delete(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        entities.remove(readField(entity, "id"));
    }

    public void deleteAllById(Iterable<? extends String> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("ids must not be null");
        }
        ids.forEach(id -> entities.remove(id));
    }

    public void deleteAll(Iterable<? extends T> entitiesToDelete) {
        if (entitiesToDelete == null) {
            throw new IllegalArgumentException("entitiesToDelete must not be null");
        }
        for (T entity : entitiesToDelete) {
            delete(entity);
        }
    }

    public void deleteAll() {
        entities.clear();
    }

    public List<T> findAll(Sort sort) {
        return null;
    }

    public Page<T> findAll(Pageable pageable) {
        int total = (int) count();
        int offset = (int) pageable.getOffset();
        int limit = pageable.getPageSize();
        return new PageImpl<>(findAll().subList(offset, Math.min(offset + limit, total)), pageable, total);
    }

    public <S extends T> S insert(S entity) {
        return save(entity);
    }

    public <S extends T> List<S> insert(Iterable<S> entities) {
        return saveAll(entities);
    }

    public <S extends T> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public <S extends T> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public <S extends T> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public <S extends T, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
