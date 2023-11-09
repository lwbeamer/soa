package org.itmo.spacemarine.repository;

import lombok.extern.slf4j.Slf4j;
import org.itmo.spacemarine.entity.AstartesCategory;
import org.itmo.spacemarine.entity.SpaceMarine;
import org.itmo.spacemarine.entity.Weapon;
import org.itmo.spacemarine.exception.BusinessException;
import org.itmo.spacemarine.exception.ExceptionCode;
import org.itmo.spacemarine.util.FilterParams;
import org.itmo.spacemarine.util.Page;
import org.itmo.spacemarine.util.SortParams;
import org.itmo.spacemarine.util.SpaceMarineFields;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class SpaceMarineCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<SpaceMarine> getSortedAndFilteredPage(List<SortParams> sortList, List<FilterParams> filtersList, Integer page, Integer size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> flatQuery = criteriaBuilder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> root = flatQuery.from(SpaceMarine.class);

        CriteriaQuery<SpaceMarine> select = flatQuery.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (!filtersList.isEmpty()) {
            predicates = new ArrayList<>();

            for (FilterParams filter : filtersList) {

                if (filter.getNestedField() != null) {
                    predicates.add(criteriaBuilder.equal(
                            root.get(filter.getMainField()).get(filter.getNestedField()),
                            getProperFieldValue(filter)));
                } else {
                    predicates.add(criteriaBuilder.equal(
                            root.get(filter.getMainField()),
                            getProperFieldValue(filter)));
                }
            }

            select.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }

        if (!sortList.isEmpty()) {
            List<Order> orderList = new ArrayList<>();

            for (SortParams sortItem : sortList) {
                if (sortItem.isDescOrder()) {
                    if (sortItem.getNestedField() != null) {
                        orderList.add(criteriaBuilder.desc(root.get(sortItem.getMainField()).get(sortItem.getNestedField())));
                    } else {
                        orderList.add(criteriaBuilder.desc(root.get(sortItem.getMainField())));
                    }
                } else {
                    if (sortItem.getNestedField() != null) {
                        orderList.add(criteriaBuilder.asc(root.get(sortItem.getMainField()).get(sortItem.getNestedField())));
                    } else {
                        orderList.add(criteriaBuilder.asc(root.get(sortItem.getMainField())));
                    }
                }
            }
            select.orderBy(orderList);
        }

        TypedQuery<SpaceMarine> typedQuery = entityManager.createQuery(select);

        Page<SpaceMarine> ret = new Page<>();

        typedQuery.setFirstResult((page - 1) * size);
        typedQuery.setMaxResults(size);


        long totalRecords;
        if (!predicates.isEmpty()) {
            Query queryTotal = entityManager.createQuery("select count(*) from SpaceMarine");
            totalRecords = (long) queryTotal.getSingleResult();
        } else {
            CriteriaBuilder qb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = qb.createQuery(Long.class);
            cq.select(qb.count(cq.from(SpaceMarine.class)));
            cq.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            totalRecords = entityManager.createQuery(cq).getSingleResult();
        }

        ret.setPage(page);
        ret.setPageSize(size);
        ret.setTotalPages((int) Math.ceil(((double) totalRecords) / size));

        ret.setObjects(typedQuery.getResultList());

        return ret;
    }

    private Object getProperFieldValue(FilterParams filter) {
        try {
            if (Objects.equals(filter.getMainField(), "weaponType")) {
                return Weapon.valueOf(filter.getValue().toUpperCase());
            } else if (Objects.equals(filter.getMainField(), "category")) {
                return String.valueOf(AstartesCategory.valueOf(filter.getValue().toUpperCase()));
            } else return filter.getValue();
        } catch (IllegalArgumentException e) {
            throw new BusinessException(ExceptionCode.InvalidRequest, "Значения weaponType или category не могут быть произвольными!");
        }
    }

//    public SpaceMarine findMaxByField(SpaceMarineFields fullField) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Object> subQuery = criteriaBuilder.createQuery(Object.class);
//        Root<SpaceMarine> subRoot = subQuery.from(SpaceMarine.class);
//
//        Expression<Object> expression;
//
//        if (fullField.getNestedField() == null) {
//            expression = subRoot.get(fullField.getMainField());
//        } else {
//            expression = subRoot.get(fullField.getMainField()).get(fullField.getNestedField());
//        }
//
//        subQuery.select(expression);
//
//        List<Object> results = entityManager.createQuery(subQuery).getResultList();
//
//        if (results.isEmpty()) {
//            return null;
//        }
//
//        Object maxResult = Collections.max(results, Comparator.nullsLast(Comparator.naturalOrder()));
//
//        CriteriaQuery<SpaceMarine> criteriaQuery = criteriaBuilder.createQuery(SpaceMarine.class);
//        Root<SpaceMarine> root = criteriaQuery.from(SpaceMarine.class);
//
//        if (fullField.getNestedField() == null) {
//            criteriaQuery.select(root)
//                    .where(criteriaBuilder.equal(root.get(fullField.getMainField()), maxResult));
//        } else {
//            criteriaQuery.select(root)
//                    .where(criteriaBuilder.equal(root.get(fullField.getMainField()).get(fullField.getNestedField()), maxResult));
//        }
//
//        TypedQuery<SpaceMarine> query = entityManager.createQuery(criteriaQuery);
//        query.setMaxResults(1);
//
//        List<SpaceMarine> result = query.getResultList();
//        return result.isEmpty() ? null : result.get(0);
//    }
//
//    public SpaceMarine findMinByField(SpaceMarineFields fullField) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<SpaceMarine> criteriaQuery = criteriaBuilder.createQuery(SpaceMarine.class);
//        Root<SpaceMarine> root = criteriaQuery.from(SpaceMarine.class);
//
//        Expression<Integer> minExpression;
//
//        if (fullField.getNestedField() == null) {
//            minExpression = criteriaBuilder.min(root.get(fullField.getMainField()));
//            criteriaQuery.select(root)
//                    .where(criteriaBuilder.equal(root.get(fullField.getMainField()), minExpression));
//        } else {
//            minExpression = criteriaBuilder.min(root.get(fullField.getMainField()).get(fullField.getNestedField()));
//            criteriaQuery.select(root)
//                    .where(criteriaBuilder.equal(root.get(fullField.getNestedField()).get(fullField.getNestedField()), minExpression));
//        }
//
//        TypedQuery<SpaceMarine> query = entityManager.createQuery(criteriaQuery);
//        query.setMaxResults(1);
//
//        List<SpaceMarine> result = query.getResultList();
//        return result.isEmpty() ? null : result.get(0);
//    }
}
