package mk.ukim.finki.dipl.programmanagement.programmanagement.domain.models;

import lombok.NonNull;
import mk.finki.ukim.dipl.sharedkernel.sharedkernel.domain.base.DomainObjectId;

public class DepartmentId extends DomainObjectId {

    private DepartmentId() {
        super(DepartmentId.randomId(DepartmentId.class).getId());
    }

    public DepartmentId(@NonNull String uuid) {
        super(uuid);
    }

    public static DepartmentId of(String uuid) {
        DepartmentId departmentId = new DepartmentId(uuid);
        return departmentId;
    }
}
