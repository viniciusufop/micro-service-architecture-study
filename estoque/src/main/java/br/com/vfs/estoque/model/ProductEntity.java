package br.com.vfs.estoque.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author vinicius
 * @version : $<br/>
 * : $
 * @since 07/06/19 13:16
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    private String name;

    private Integer amount;

    private Double cost;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "change_date")
    private Date changeDate;
}
