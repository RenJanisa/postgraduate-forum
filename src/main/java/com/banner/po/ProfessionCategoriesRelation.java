package com.banner.po;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 专业分类关系表
 * </p>
 *
 * @author banner
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProfessionCategoriesRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专业分类id
     */
    private Long categoriesId;

    /**
     * 专业id
     */
    private Long professionId;


}
