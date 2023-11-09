package org.itmo.spacemarine.service;

import lombok.RequiredArgsConstructor;
import org.itmo.spacemarine.converter.SpaceMarineConverter;
import org.itmo.spacemarine.dto.SpaceMarineCreateRequestDto;
import org.itmo.spacemarine.dto.SpaceMarineResponseDto;
import org.itmo.spacemarine.dto.SpaceMarineUpdateDto;
import org.itmo.spacemarine.entity.SpaceMarine;
import org.itmo.spacemarine.exception.BusinessException;
import org.itmo.spacemarine.exception.ExceptionCode;
import org.itmo.spacemarine.repository.SpaceMarineCustomRepository;
import org.itmo.spacemarine.repository.SpaceMarineRepository;
import org.itmo.spacemarine.util.FilterParams;
import org.itmo.spacemarine.util.Page;
import org.itmo.spacemarine.util.SortParams;
import org.itmo.spacemarine.util.SpaceMarineFields;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SpaceMarineService {


    private final SpaceMarineRepository spaceMarineRepository;

    private final SpaceMarineCustomRepository spaceMarineCustomRepository;

    private final SpaceMarineConverter spaceMarineConverter;

    private final StarshipService starshipService;
    private final Map<String, List<String>> filtersMap = new HashMap<>();

    @PostConstruct
    private void initMap() {
        for (SpaceMarineFields value : SpaceMarineFields.values()) {
            filtersMap.put(value.getQueryParamName(), new ArrayList<>());
        }
    }

    public void addNewMarine(SpaceMarineCreateRequestDto dto) {
        spaceMarineRepository.save(spaceMarineConverter.convertFromRequestToSpaceMarine(dto));
    }

    public Page<SpaceMarine> getAll(HttpServletRequest request) {

        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");
        int page, pageSize;
        try {
            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
                if (page <= 0) {
                    throw new BusinessException(ExceptionCode.InvalidRequest, "Номер страницы не может быть отрицательным!");
                }
            } else page = 1;
            if (pageSizeParam != null) {
                pageSize = Integer.parseInt(pageSizeParam);
                if (pageSize <= 0) {
                    throw new BusinessException(ExceptionCode.InvalidRequest, "Размер страницы не может быть отрицательным!");
                }
            } else pageSize = 10;
        } catch (NumberFormatException ex) {
            throw new BusinessException(ExceptionCode.InvalidRequest, "Размер и номер страницы должны быть целым числом!");
        }


        List<String> filterParamsList;

        for (String paramName : filtersMap.keySet()) {
            String[] filterParamsArray = request.getParameterValues(paramName);
            if (filterParamsArray == null) filterParamsList = new ArrayList<>();
            else
                filterParamsList = Stream.of(filterParamsArray).filter(par -> !par.isBlank()).collect(Collectors.toList());
            filtersMap.put(paramName, filterParamsList);
        }

        List<FilterParams> filterParams = new ArrayList<>();
        for (SpaceMarineFields value : SpaceMarineFields.values()) {
            for (String param : filtersMap.get(value.getQueryParamName())) {
                filterParams.add(FilterParams.builder()
                        .mainField(value.getMainField())
                        .nestedField(value.getNestedField())
                        .value(param)
                        .build());
            }
        }

        List<SortParams> sortParams = new ArrayList<>();
        String[] sortParamsArray = request.getParameterValues("sort");
        if (sortParamsArray != null)
            for (String s : sortParamsArray) {
                SortParams.SortParamsBuilder sortParamsBuilder = SortParams.builder();

                String[] splitedSortParam = s.trim().split(" ");
                if (splitedSortParam.length == 0 || splitedSortParam.length > 2)
                    throw new BusinessException(ExceptionCode.InvalidRequest, "Параметр sort должен иметь длину 1-2 слова!");
                if (splitedSortParam.length == 2) {
                    if (splitedSortParam[1].equals("DESC")) {
                        sortParamsBuilder.isDescOrder(true);
                    } else throw new BusinessException(ExceptionCode.InvalidRequest, "Второе слово в параметре sort может быть только DESC!");
                }
                SpaceMarineFields field = SpaceMarineFields.valueOfQueryParam(splitedSortParam[0]);
                if (field == null) throw new BusinessException(ExceptionCode.InvalidRequest, "Поле, по которому вы хотите сортировать - не существует!");
                sortParamsBuilder.mainField(field.getMainField());
                sortParamsBuilder.nestedField(field.getNestedField());
                sortParams.add(sortParamsBuilder.build());
            }

        return spaceMarineCustomRepository.getSortedAndFilteredPage(sortParams, filterParams, page, pageSize);
    }

    public SpaceMarineResponseDto getById(Long id) {
        Optional<SpaceMarine> spaceMarineOptional = spaceMarineRepository.findById(id);
        return spaceMarineConverter.convertFromSpaceMarineToResponse(
                spaceMarineOptional.orElseThrow(() -> new BusinessException(ExceptionCode.SpaceMarineNotFound, "SpaceMarine с таким id не найден!")));
    }

    public void updateSpaceMarine(Long id, SpaceMarineUpdateDto dto) {
        Optional<SpaceMarine> spaceMarineOptional = spaceMarineRepository.findById(id);
        if (spaceMarineOptional.isEmpty()) throw new BusinessException(ExceptionCode.SpaceMarineNotFound, "SpaceMarine с таким id не найден!");
        SpaceMarine spaceMarine = spaceMarineOptional.get();
        spaceMarine.setAchievements(dto.getAchievements());
        spaceMarine.setCategory(dto.getCategory());
        spaceMarine.setHealth(dto.getHealth());
        spaceMarine.setChapter(dto.getChapter());
        spaceMarine.setCoordinates(dto.getCoordinates());
        spaceMarine.setStarship(dto.getStarshipId() != null ? starshipService.getStarship(dto.getStarshipId()): null);
        spaceMarine.setWeaponType(dto.getWeaponType());
        spaceMarine.setName(dto.getName());
        spaceMarineRepository.save(spaceMarine);
    }

    public void deleteSpaceMarine(Long id) {
        Optional<SpaceMarine> spaceMarineOptional = spaceMarineRepository.findById(id);
        if (spaceMarineOptional.isEmpty()) throw new BusinessException(ExceptionCode.SpaceMarineNotFound, "SpaceMarine с таким id не найден!");
        spaceMarineRepository.delete(spaceMarineOptional.get());
    }

    public SpaceMarineResponseDto getWithMaxField(String field) {
        SpaceMarineFields fullField = SpaceMarineFields.valueOfQueryParam(field);
        if (fullField == null)
            throw new BusinessException(ExceptionCode.InvalidRequest, "Указанного поля не существует!");

        int page = 1, pageSize = 1;

        List<FilterParams> filterParams = new ArrayList<>();
        List<SortParams> sortParams = new ArrayList<>();

        sortParams.add(SortParams.builder()
                .mainField(fullField.getMainField())
                .nestedField(fullField.getNestedField())
                .isDescOrder(true)
                .build());


        return getSpaceMarineResponseDto(page, pageSize, filterParams, sortParams);
    }

    public SpaceMarineResponseDto getWithMinField(String field) {
        SpaceMarineFields fullField = SpaceMarineFields.valueOfQueryParam(field);
        if (fullField == null)
            throw new BusinessException(ExceptionCode.InvalidRequest, "Указанного поля не существует!");

        int page = 1, pageSize = 1;

        List<FilterParams> filterParams = new ArrayList<>();
        List<SortParams> sortParams = new ArrayList<>();

        sortParams.add(SortParams.builder()
                        .mainField(fullField.getMainField())
                        .nestedField(fullField.getNestedField())
                        .isDescOrder(false)
                        .build());

        return getSpaceMarineResponseDto(page, pageSize, filterParams, sortParams);
    }

    private SpaceMarineResponseDto getSpaceMarineResponseDto(int page, int pageSize, List<FilterParams> filterParams, List<SortParams> sortParams) {
        Page<SpaceMarine> result = spaceMarineCustomRepository.getSortedAndFilteredPage(sortParams, filterParams, page, pageSize);

        if (result == null || result.getObjects() == null || result.getObjects().isEmpty())
            throw new BusinessException(ExceptionCode.SpaceMarineNotFound, "Сущность не найдена!");
        return spaceMarineConverter.convertFromSpaceMarineToResponse(result.getObjects().get(0));
    }

    public Long getHealthSum() {
        return spaceMarineRepository.getHealthSum();
    }
}
