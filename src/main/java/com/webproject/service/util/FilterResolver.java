package com.webproject.service.util;


import com.webproject.dto.request.GetBooksRequest;
import com.webproject.model.Book;
import com.webproject.model.Tag;
import com.webproject.model.Theme;
import lombok.experimental.UtilityClass;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

@UtilityClass
public class FilterResolver {

    public CriteriaQuery<Book> createCriteriaResult(CriteriaBuilder builder, GetBooksRequest.Filter filter) {

        var query = builder.createQuery(Book.class);
        var root = query.from(Book.class);
        var predicate = createFilterPredicate(builder, root, filter);


        return query.select(root)
                .where(predicate)
                .orderBy(builder.asc(root.get("addDate")));

    }

    private Predicate createFilterPredicate(CriteriaBuilder builder, Path<?> root, GetBooksRequest.Filter filter) {
        return builder.and(
                //TODO created date from/to
                // TODO list tag -> list String
                // TODO list themes -> list String
//                addBetweenCriteria(builder,root.get("createdDate"),LocalDate.of((filter.getCreatedCentury()-1)*100+1,01,01),
//                                                                    LocalDate.of(filter.getCreatedCentury()*100,12,31)),
                addContainsCriteria(builder,root.get("themes"), filter.getTheme()),
                addEqualsCriteria(builder, root.get("author"), filter.getAuthor()),
                addContainsCriteria(builder,root.get("tags"), filter.getTags()),
                addBetweenCriteria(builder,root.get("createdDate"),filter.getAddDateFrom(),filter.getAddDateTo()));
    }

    private Predicate addBetweenCriteria(CriteriaBuilder builder, Path<LocalDate> path, LocalDate firstValue, LocalDate secondValue) {
        if (nonNull(firstValue) && nonNull(secondValue)) {
            return builder.between(path, firstValue, secondValue);
        }
        return builder.conjunction();
    }

    private Predicate addThemeIn(CriteriaBuilder builder, Path<Set<Theme.ThemeEnum>> path, List<String> stringThemes) {
        for (String stringTheme : stringThemes) {
            Theme.ThemeEnum theme = Theme.ThemeEnum.from(stringTheme);
            builder.and(addContainsCriteriaTags(builder, path, theme));
        }
        return builder.conjunction();
    }

    private <T> Predicate addContainsCriteriaTags(CriteriaBuilder builder, Path<Set<T>> path, T value) {
        if (nonNull(value)) {
            return builder.isMember(value,path);
        }
        return builder.conjunction();
    }

    private <T> Predicate addEqualsCriteria(CriteriaBuilder builder, Path<T> path, T value) {
        if (nonNull(value)) {
            return builder.equal(path,value);
        }
        return builder.conjunction();
    }

}
