package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models;

import lombok.Getter;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")
@Getter
public class Department extends AbstractEntity<DepartmentId> {

    private String departmentName;

    private Department(){
        super(DepartmentId.randomId(DepartmentId.class));
    }
}
