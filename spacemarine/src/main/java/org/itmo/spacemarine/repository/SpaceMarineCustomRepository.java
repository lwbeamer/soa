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
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

//        if (page != null && size != null) {
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
//        }

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
}
