package com.yizit.mes.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 物料表
 * User: 李江峰
 * Date: 2017-12-14
 * Time: 11:32
 */
@Entity
@Table(name = "c_material_t")
@Data
public class Material {
    @Id
    @GeneratedValue
    private Long material_id;
    /**
     * 图号
     */
    private String part_no;
    /**
     * 物料名称
     */
    private String material_name;

    @Temporal(TemporalType.DATE)
    private Date created_time;


}
