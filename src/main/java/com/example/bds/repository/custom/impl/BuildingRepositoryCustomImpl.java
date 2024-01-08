package com.example.bds.repository.custom.impl;

import com.example.bds.builder.BuildingSearchBuilder;
import com.example.bds.constant.SystemConstant;
import com.example.bds.entity.BuildingEntity;
import com.example.bds.repository.custom.BuildingRepositoryCustom;
import com.example.bds.utils.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long countBuildingFound(BuildingSearchBuilder builderSearch) {
        StringBuilder finalQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder("");
        StringBuilder whereQuery = new StringBuilder("");

        joinQuery.append(buildSqlJoining(builderSearch));
        whereQuery.append(buildWhereCommonSql(builderSearch)).append(buildWhereSpecialSql(builderSearch));
        finalQuery.append("select count(*)").append("\nfrom building as b").append(joinQuery)
                .append(SystemConstant.ONE_EQUAL_ONE).append(whereQuery);
        String sqlQuery = finalQuery.toString();
        Query query = entityManager.createNativeQuery(sqlQuery);
        Long count = ((Number) query.getSingleResult()).longValue();
        return count;
    }

    private StringBuilder buildWhereSpecialSql(BuildingSearchBuilder builderSearch) {
        StringBuilder whereSpecialSql = new StringBuilder("");
        if (builderSearch.getStaffId() != null && builderSearch.getStaffId() != -1) {
            whereSpecialSql.append(" and ab.staffid =" + builderSearch.getStaffId());
        }
        if (builderSearch.getDistrictCode() != null && !builderSearch.getDistrictCode().equals("-1")){
            whereSpecialSql.append(" and b.district = '"+builderSearch.getDistrictCode()+"'");
        }
        if (builderSearch.getTypes() != null && builderSearch.getTypes().size() > 0) {
            String whereTypes = builderSearch.getTypes().stream().map(item -> " b.type like '%"+item+"%'").collect(Collectors.joining(" or"));
            whereSpecialSql.append(" and (")
                    .append(whereTypes)
                    .append(")");
        }
        if (builderSearch.getCostRentFrom() != null) {
            whereSpecialSql.append(" and b.rentprice >= " + builderSearch.getCostRentFrom());
        }
        if (builderSearch.getCostRentTo() != null) {
            whereSpecialSql.append(" and b.rentprice <= " + builderSearch.getCostRentTo());
        }
        if (builderSearch.getRentAreaFrom() != null || builderSearch.getRentAreaTo() != null) {
            whereSpecialSql.append(" and EXISTS (select * from rentarea as ra WHERE ra.buildingid = b.id");
            if (builderSearch.getRentAreaFrom() != null) {
                whereSpecialSql.append(" and ra.value >= " + builderSearch.getRentAreaFrom());
            }
            if (builderSearch.getRentAreaTo() != null) {
                whereSpecialSql.append(" and ra.value <= " + builderSearch.getRentAreaTo());
            }
            whereSpecialSql.append(")");
        }
        return whereSpecialSql;
    }

    private StringBuilder buildWhereCommonSql(BuildingSearchBuilder builderSearch) {
        StringBuilder whereCommonSql = new StringBuilder("");
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName().toLowerCase();
                if (!fieldName.equals("staffid") && !fieldName.equals("districtcode") && !fieldName.equals("types")
                        && !fieldName.startsWith("rentarea") && !fieldName.startsWith("costrent")) {
                    Object objValue = field.get(builderSearch);
                    if (objValue != null) {
                        if (field.getType().getName().equals("java.lang.String") && !StringUtils.isNullOrEmpty(objValue.toString())) {
                            whereCommonSql.append(" and b." + fieldName + " like '%" + objValue + "%'");
                        } else if (field.getType().getName().equals("java.lang.Integer")) {
                            whereCommonSql.append(" and b." + fieldName + " = " + objValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return whereCommonSql;
    }

    private StringBuilder buildSqlJoining(BuildingSearchBuilder builderSearch) {
        StringBuilder sqlJoin = new StringBuilder("");
        if (builderSearch.getStaffId() != null && builderSearch.getStaffId() < 0) {
            sqlJoin.append(" inner join assignmentbuilding as ab on ab.buildingid=b.id ");
        }
        return sqlJoin;
    }

    @Override
    public List<BuildingEntity> findBuilding(BuildingSearchBuilder builderSearch, Pageable pageable) {
        Integer limmit = pageable.getPageSize();
        Integer ofSet = pageable.getPageNumber()*limmit;
        StringBuilder finalQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder("");
        StringBuilder whereQuery = new StringBuilder("");

        joinQuery.append(buildSqlJoining(builderSearch));
        whereQuery.append(buildWhereCommonSql(builderSearch)).append(buildWhereSpecialSql(builderSearch));
        finalQuery.append("select b.*").append("\nfrom building as b").append(joinQuery)
                .append(SystemConstant.ONE_EQUAL_ONE).append(whereQuery)
                .append("\n limit "+limmit)
                .append("\n offset "+ofSet);
//                .append("\n group by b.id");
        String sqlQuery = finalQuery.toString();
        Query query = entityManager.createNativeQuery(sqlQuery, BuildingEntity.class);
        return query.getResultList();
    }
}
